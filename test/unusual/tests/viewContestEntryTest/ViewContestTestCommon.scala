package unusual.tests.viewContestEntryTest

import unusual.model._
import unusual.model.pageStates.{ViewContestState, LobbyState, EnterContestState}
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
    if (res == Resolution.SMALL) {
      viewContestPage.changeToUsersTab
    }

    val userListLen = state.userList.length
    for(i <- 1 to userListLen) {
      val userName = viewContestPage.getUserName(i)
      var j = 0

      logger.debug(s"'$userName' check:")
      var isUserName = false
      isUserName = userName == state.userList(i - 1).firstName
      while(!isUserName && j < userListLen) {
        isUserName = userName == state.userList(j).firstName
        logger.debug(s"  - '${state.userList(j).firstName}'", isUserName)
        j+=1
      }

      if(isUserName) logger.debug(s"User[$i] '$userName' found")
      else logger.error(s"User[$i]: $userName not found in players list")

      everyUserExists &&= isUserName
    }

    assert(everyUserExists, "Player list does not match")
  }

  def isRightContestInfo:Unit = {
    assert(viewContestState.contest.name.toUpperCase == viewContestPage.getContestName)
    assert(viewContestState.contest.isEqualsJoinedDescription(viewContestPage.getContestDescription))
    assert(viewContestState.contest.entryFee == viewContestPage.getContestEntry)
    assert(viewContestState.contest.prize == viewContestPage.getContestPrize)
  }

  def checkMatches:Unit = {
    assert(viewContestState.contest.numMatches == viewContestPage.getNumMatches)
  }

  def simpleCheckEditButton:Unit = {

    Given("a lineup in view contest")
    if (res == Resolution.SMALL) {
      viewContestPage.changeToLineUpTab
    }
    When("press edit team")
    eventually { viewContestPage.goEditTeam }
    val ecState = new EnterContestState
    ecState.contest = viewContestState.contest
    val ecPage = new EnterContestPage(res, ecState)

    And("lineup should be full")
    eventually { assert( ecPage.isLineupFull ) }
    And("salary should be consistent with lineup")
    eventually { assert( ecPage.getLineUpSalary == (ecState.contest.initialSalary - ecState.contest.affordableLineup.price )) }

    Then("confirm lineup")
    ecPage.confirmLineup
  }

  def checkEditButton:Unit = {
    Given("a lineup in view contest")
    if (res == Resolution.SMALL) {
      viewContestPage.changeToLineUpTab
    }
    When("press edit team")
    eventually { viewContestPage.goEditTeam }
    val ecState = new EnterContestState
    ecState.contest = viewContestState.contest
    val ecPage = new EnterContestPage(res, ecState)

    Then("Enter contest page should be at")
    eventually { assert( ecPage.isAt ) }
    And("lineup should be full")
    eventually { assert( ecPage.isLineupFull ) }
    And("salary should be consistent with lineup")
    eventually { assert( ecPage.getLineUpSalary == (ecState.contest.initialSalary - ecState.contest.affordableLineup.price )) }
    if (res != Resolution.SMALL) {
      And("number of players should be coherent")
      eventually { assert( ecPage.getNumberOfSoccerPlayers == ecState.contest.numAllPlayers ) }
    }
    Then("confirm lineup")
    ecPage.confirmLineup

    eventually { assert(viewContestPage.isAt, "view contest page is not loaded") }

    // ADDED BECAUSE OF A KNOWN BUG
    When("Reload view contest page")
    reloadPage()

    eventually { assert(viewContestPage.isAt, "view contest page is not loaded") }

    And("go edit team again")
    eventually { viewContestPage.goEditTeam }
    Then("Enter contest page should be at")
    eventually { assert( ecPage.isAt ) }
    And("lineup should be full")
    eventually { assert( ecPage.isLineupFull ) }
    And("salary should be consistent with lineup")
    eventually { assert( ecPage.getLineUpSalary == (ecState.contest.initialSalary - ecState.contest.affordableLineup.price )) }
    if (res != Resolution.SMALL) {
      And("number of players should be coherent")
      eventually { assert( ecPage.getNumberOfSoccerPlayers == ecState.contest.numAllPlayers ) }
    }
    When("confirm lineup")
    ecPage.confirmLineup

    Then("contest info should be ok")
    eventually { isRightContestInfo }
  }

  def goOtherContests:Unit = {
    if (!isSafari) {
      viewContestPage.changeToLineUpTab.goOtherContests
      val lobby = new LobbyPage(res, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
      eventually { assert( lobby.isAt ) }
      assert(lobby.getNumberOfContests == LobbyState.DEFAULT_LOBBY.numContests_NoFilter - 1)
      goBack
    } else {
      testSkippedBecauseIsSafari
    }
  }

  def cancelEntry:Unit = {
    viewContestPage.changeToLineUpTab
    eventually { viewContestPage.cancelContestEntry }
    val lobby = new LobbyPage(res, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
    eventually { assert(lobby.isAt) }
    assert(lobby.getNumberOfContests == LobbyState.DEFAULT_LOBBY.numContests_NoFilter)

    if (!isSafari) {
      goBack
      eventually { assert(lobby.isAt) }
    } else {
      testSkippedBecauseIsSafari
    }
  }

  def goToViewContestLoggedOut:Unit = {
    Given("a enter contest page")
    val enterContestState = new EnterContestState
    enterContestState.contest = viewContestState.contest
    val enterContest = goToEnterContest(enterContestState)
    When("enter, help modal should be shown")
    eventually { assert(enterContest.isHelpModalShown, "Enter contest help modal is not shown") }
    Then("close it")
    enterContest.closeHelpModal
    enterContest.clearLineupList
    And("pick whole lineup")
    enterContest.pickWholeLineup(enterContestState.contest.affordableLineup)
    Then("confirm it")
    enterContest.confirmLineup
    And("a sign up modal should be shown")
    eventually { assert(enterContest.isSignUpModalShown, "Enter contest sign up modal is not shown") }

    /* Register */
    When("it is displayed")
    val signUpPage = new SignUpPage(status.resolution)
    Then("sign up")
    And("the modal should hide")
    eventually (timeout(30 seconds)){
      signUpPage.doSignUp(User.NEW)
      eventually (timeout(5 seconds)) {
        assert(!enterContest.isSignUpModalShown, "Enter contest sign up modal is shown")
      }
    }
    Then("confirm again")
    enterContest.confirmLineup

    /* Should show help modal */
    When("it navigates to view contest page")
    _viewContestPageInstance = new ViewContestPage(status.resolution, state)
    And("a help modal should be shown")
    eventually { assert(_viewContestPageInstance.isHelpModalShown, "View contest help modal is not shown") }

    Then("click to close modal")
    _viewContestPageInstance.closeHelpModal
    And("it should be closed")
    eventually { assert(!_viewContestPageInstance.isHelpModalShown, "View contest help modal is not shown") }
  }

}
