package unusual.tests.viewContestEntryTest

import unusual.model._
import unusual.pages._
import unusual.tests._
import unusual.tests.enterContestTest.EnterContestTestCommon

class ViewContestTestCommon(state: ViewContestState, res:Resolution) extends SharedTest(res) {

  val viewContestState = state
  var _viewContestPageInstance:ViewContestPage = null
  def viewContestPage:ViewContestPage = {
    if(_viewContestPageInstance == null) {
      _viewContestPageInstance = goToViewContest(viewContestState)
      changeMenuPositioning
    }
    _viewContestPageInstance
  }
/*
  def accessToViewContest:Unit = {
    goToViewContest(viewContestState)
  }
*/
  def isCorrectLineup:Unit = {
    val playerLineup = state.contest.affordableLineup.soccerPlayerList

    for(i <- 0 to 10) {
      eventually {
        val player = viewContestPage.getSoccerPlayer(i + 1)
        assert(player == playerLineup(i), s"SoccerPlayer #$i does not match {page = $player, state = ${playerLineup(i)}" )
      }
    }
  }

  def checkPlayersList:Unit = {
    var everyUserExists = true
    var i = 0
    if (res == Resolution.SMALL) {
      viewContestPage.changeToUsersTab
    }
    for(user <- state.userList) {
      i += 1
      val isUserName = viewContestPage.getUserName(i) == user.firstName
      logger.debug(s"User[$i] should be: ${user.firstName}", isUserName)
      everyUserExists &&= isUserName
    }

    assert(everyUserExists, "Player list does not match")
  }

  def isRightContestInfo:Unit = {
    assert(viewContestState.contest.name == viewContestPage.getContestName)
    assert(viewContestState.contest.joinedDescription == viewContestPage.getContestDescription)
    assert(viewContestState.contest.entryFee == viewContestPage.getContestEntry)
    assert(viewContestState.contest.prize == viewContestPage.getContestPrize)
  }

  def checkMatches:Unit = {
    assert(viewContestState.contest.numMatches == viewContestPage.getNumMatches)
  }

  def checkEditButton:Unit = {
    if (res == Resolution.SMALL) {
      viewContestPage.changeToLineUpTab
    }
    eventually { viewContestPage.goEditTeam }
    val ecState = new EnterContestState
    ecState.contest = viewContestState.contest
    val ecPage = new EnterContestPage(res, ecState)

    eventually { assert( ecPage.isAt ) }
    eventually { assert( ecPage.isLineupFull ) }
    eventually { assert( ecPage.getLineUpSalary == (ecState.contest.initialSalary - ecState.contest.affordableLineup.price )) }
    if (res != Resolution.SMALL) {
      eventually { assert( ecPage.getNumberOfSoccerPlayers == ecState.contest.numAllPlayers ) }
    }
    ecPage.confirmLineup

    eventually { isRightContestInfo }
  }

  def goOtherContests:Unit = {
    viewContestPage.changeToLineUpTab.goOtherContests
    val lobby = new LobbyPage(res, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
    eventually { assert( lobby.isAt ) }
    assert(lobby.getNumberOfContests == LobbyState.DEFAULT_LOBBY.numContests_NoFilter - 1)
    goBack
  }

  def cancelEntry:Unit = {
    viewContestPage.changeToLineUpTab.cancelContestEntry
    val lobby = new LobbyPage(res, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
    eventually { assert( lobby.isAt ) }
    assert(lobby.getNumberOfContests == LobbyState.DEFAULT_LOBBY.numContests_NoFilter)
    goBack
  }
}
