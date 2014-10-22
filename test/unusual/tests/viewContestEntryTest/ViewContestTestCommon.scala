package unusual.tests.viewContestEntryTest

import unusual.model._
import unusual.pages._
import unusual.tests._
import unusual.tests.enterContestTest.EnterContestTestCommon

class ViewContestTestCommon(state: ViewContestState, res:Resolution) extends SharedTest(res) {

  val viewContestState = state
  var page:ViewContestPage = null

  def accessToViewContest:Unit = {
    val page = goToViewContest(viewContestState)
  }

  def isCorrectLineup:Unit = {
    val playerLineup = state.contest.affordableLineup.soccerPlayerList

    for(i <- 0 to 10) {
      val player = page.getSoccerPlayer(i + 1)

      assert(player == playerLineup(i), s"SoccerPlayer #$i does not match {page = $player, state = ${playerLineup(i)}" )
    }
  }

  def checkPlayersList:Unit = {
    var everyUserExists = true
    var i = 0
    for(user <- state.userList) {
      i += 1
      val isUserName = page.getUserName(i) == user.firstName
      logger.debug(s"User[$i] should be: ${user.firstName}", isUserName)
      everyUserExists &&= isUserName
    }

    assert(everyUserExists, "Player list does not match")
  }

  def isRightContestName:Unit = {

  }

  def isRightContestDescription:Unit = {

  }

  def checkMatches:Unit = {

  }

  def checkEntryAndPrize:Unit = {

  }

  def checkButtons:Unit = {

  }

}
