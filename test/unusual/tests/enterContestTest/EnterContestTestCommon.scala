package unusual.tests.enterContestTest

import org.specs2.specification.Given
import unusual.model._
import unusual.pages._
import unusual.tests._

abstract class EnterContestTestCommon(state: EnterContestState, res:Resolution) extends SharedTest(res) {

  val enterContestState = state
  var _enterContestPageInstance:EnterContestPage = null
  def enterContestPage:EnterContestPage = {
    if(_enterContestPageInstance == null) {
      _enterContestPageInstance = goToEnterContest(enterContestState)
      changeMenuPositioning
    }
    _enterContestPageInstance
  }

  /*
   * ORDER BY TESTS
   */
  def checkDefaultState:Unit = {

    val N_ALL_PLAYERS = enterContestState.contest.numAllPlayers
    val INITIAL_SALARY = enterContestState.contest.initialSalary

    Given("Enter Contest page")
    eventually { enterContestPage }
    val lobby = new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
    Then("page should be at and default state")
    assert(enterContestPage.isAt && enterContestPage.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    When("go navigate back and forward")
    goBack()
    goForward()
    Then("page should be at and default state")
    assert(enterContestPage.isAt)
    assert(enterContestPage.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    When("go navigate back and click on play button")
    goBack()

    if (status.resolution == Resolution.SMALL) {
      lobby.playContestNumber(state.contest.startDateOrder)
    } else {
      lobby.filters.search(state.contest.name)
      lobby.playContestNumber(1)
    }

    Then("page should be at and default state")
    assert(enterContestPage.isAt, "is not at")
    assert(enterContestPage.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY), "is not default")
    When("click on close button and navigate back")
    enterContestPage.clickOnCloseButton
    goBack()
    Then("page should be at and default state")
    assert(enterContestPage.isAt && enterContestPage.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    When("click on close button and click on play button")
    enterContestPage.clickOnCloseButton
    lobby.filters.clear

    if (status.resolution == Resolution.SMALL) {
      lobby.playContestNumber(state.contest.startDateOrder)
    } else {
      lobby.filters.search(state.contest.name)
      lobby.playContestNumber(1)
    }

    Then("page should be at and default state")
    assert(enterContestPage.isAt && enterContestPage.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    /*
    reloadPage()
    assert(page.isAt.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    */

    changeMenuPositioning
  }

  def orderByPosition:Unit = {
    if (status.resolution == Resolution.BIG) {
      //val page = goToEnterContest(enterContestState)
      if (enterContestPage.isOrderedByPos) { // si ya esta ordenado, lo ordenamos inversamente...
        enterContestPage.orderByPosition
      }// ... y lo ordenamos al derecho
      enterContestPage.orderByPosition

      assert(new EnterContestPage(status.resolution, enterContestState).isOrderedByPos)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByName:Unit = {
    if (status.resolution != Resolution.SMALL) {
      assert(enterContestPage.orderByName.isOrderedByName, "Is not ordered by name")
    } else {
      assert(enterContestPage.selectGoalKeeperFromLineup.orderByName.isOrderedByName, "(Goalkeeper) Is not ordered by name")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectDefenseFromLineup(1).isOrderedByName, "(Defense) Is not ordered by name")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectMiddleFromLineup(1).isOrderedByName, "(Middle) Is not ordered by name")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectForwardFromLineup(1).isOrderedByName, "(Forward) Is not ordered by name")
      enterContestPage.cancelSoccerPlayerSelection
    }
  }
  def orderByDFP:Unit = {
    if (status.resolution != Resolution.SMALL) {
      assert(enterContestPage.orderByDFP.isOrderedByDFP)
    } else {
      assert(enterContestPage.selectGoalKeeperFromLineup.orderByDFP.isOrderedByDFP, "(Goalkeeper) Is not ordered by DFP")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectDefenseFromLineup(1).isOrderedByDFP, "(Defense) Is not ordered by DFP")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectMiddleFromLineup(1).isOrderedByDFP, "(Middle) Is not ordered by DFP")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectForwardFromLineup(1).isOrderedByDFP, "(Forward) Is not ordered by DFP")
      enterContestPage.cancelSoccerPlayerSelection
    }
  }
  def orderByPlayed:Unit = {
    if (status.resolution == Resolution.BIG) {
      assert(enterContestPage.orderByPlayed.isOrderedByPlayed)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderBySalary:Unit = {
    if (status.resolution != Resolution.SMALL) {
      assert(enterContestPage.orderBySalary.isOrderedBySalary, "page is not ordered by salary")
    } else {
      assert(enterContestPage.selectGoalKeeperFromLineup.orderBySalary.isOrderedBySalary, "(Goalkeeper) Is not ordered by Salary")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectDefenseFromLineup(1).isOrderedBySalary, "(Defense) Is not ordered by Salary")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectMiddleFromLineup(1).isOrderedBySalary, "(Middle) Is not ordered by Salary")
      enterContestPage.cancelSoccerPlayerSelection
      assert(enterContestPage.selectForwardFromLineup(1).isOrderedBySalary, "(Forward) Is not ordered by Salary")
      enterContestPage.cancelSoccerPlayerSelection
    }
  }
  /*
   * END ORDER BY TESTS
   */


  /*
   * FILTER BY TESTS
   */
  def filterByGoalKeeper:Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
      enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByDefense:Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_DEFENSE)
      enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers)
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMiddle:Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_MIDDLE)
      enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numMiddlePlayers)
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByForward:Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)
      enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numForwardPlayers)
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByAll:Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
                      .getNumberOfSoccerPlayers must be(enterContestState.contest.numAllPlayers)
    } else {
      featureNotTestableInResolution
    }
    assert(enterContestState.contest.numGoalKeepersPlayers
            + enterContestState.contest.numDefensePlayers
            + enterContestState.contest.numMiddlePlayers
            + enterContestState.contest.numForwardPlayers == enterContestState.contest.numAllPlayers)
  }

  def filterByMatch:Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage
      Given("a list of matches")
      for ((matchOrder, numPlayers) <- enterContestState.contest.matchPlayers) {
        When("select match by order: " + matchOrder)
        enterContestPage.setSoccerPlayerMatchFilter(matchOrder)
        Then("displayed players should be: " + numPlayers)
        enterContestPage.getNumberOfSoccerPlayers must be (numPlayers)
      }
    } else {
      featureNotTestableInResolution
    }
  }

  def filterMix_Position_Match: Unit = {
    if (status.resolution != Resolution.SMALL) {
      enterContestPage
      Given("a list of matches")
      for ((matchOrder, positionList) <- enterContestState.contest.matchPositionMixNumPlayers) {
        for ((positionOrder, numPlayers) <- positionList) {

          When("select match by order: " + matchOrder)
          enterContestPage.setSoccerPlayerMatchFilter(matchOrder)

          And("select position by order: " + positionOrder)
          enterContestPage.setSoccerPlayerPositionFilter(positionOrder)

          Then("displayed players should be: " + numPlayers)
          assert(enterContestPage.getNumberOfSoccerPlayers == numPlayers)
        }
      }
      enterContestPage.setSoccerPlayerMatchFilter(1)
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
    } else {
      featureNotTestableInResolution
    }
  }
  /*
   * END FILTER BY TESTS
   */

  /*
   * SELECT POS TESTS
   */
  def selectGoalKeeper:Unit = {
    enterContestPage.selectGoalKeeperFromLineup
        .getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)
    enterContestPage.cancelSoccerPlayerSelection
  }

  def selectDefense:Unit = {
    val N_DEFENSE_PLAYERS = enterContestState.contest.numDefensePlayers
    enterContestPage.selectDefenseFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectDefenseFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectDefenseFromLineup(3)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectDefenseFromLineup(4)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
  }

  def selectMiddle:Unit = {
    enterContestPage
    val N_MIDDLE_PLAYERS = enterContestState.contest.numMiddlePlayers
    enterContestPage.selectMiddleFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectMiddleFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectMiddleFromLineup(3)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectMiddleFromLineup(4)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
  }

  def selectForward:Unit = {
    enterContestPage
    val N_FORWARD_PLAYERS = enterContestState.contest.numForwardPlayers

    enterContestPage.selectForwardFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
        .selectForwardFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    enterContestPage.cancelSoccerPlayerSelection
  }


  def multipleRandomSelection:Unit = {
    enterContestPage
    val N_FORWARD_PLAYERS = enterContestState.contest.numForwardPlayers
    val N_DEFENSE_PLAYERS = enterContestState.contest.numDefensePlayers
    val N_MIDDLE_PLAYERS = enterContestState.contest.numMiddlePlayers
    val N_GOAL_KEEPER_PLAYERS = enterContestState.contest.numGoalKeepersPlayers

    val allFunctions = Map[Int, (EnterContestPage) => EnterContestPage] (
        1 -> ((page) => {
          logger.debug("GoalKeeper Selected")
          page.selectGoalKeeperFromLineup
              .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
          page
        }),
        2 -> ((page) => {
          val r = scala.util.Random.nextInt(4) + 1
          logger.debug(s"Defense{$r} Selected")
          page.selectDefenseFromLineup(r)
              .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)
          page
        }),
        3 -> ((page) => {
          val r = scala.util.Random.nextInt(4) + 1
          logger.debug(s"Middle{$r} Selected")
          page.selectMiddleFromLineup(r)
              .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)
          page
        }),
        4 -> ((page) => {
          val r = scala.util.Random.nextInt(2) + 1
          logger.debug(s"Forward{$r} Selected")
          page.selectForwardFromLineup(r)
              .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)
          page
        })
      )

    for(i <- 0 to 20){
      allFunctions(scala.util.Random.nextInt(4) + 1)(enterContestPage).cancelSoccerPlayerSelection
    }
  }

  def addFirstGoalKeeperFromList:Unit = {
    enterContestPage

    enterContestPage.getLineUpSalary must be (enterContestState.contest.initialSalary)

    assert(enterContestPage.getSoccerPlayerFromLineUp(1).isEmpty)
    enterContestPage.selectGoalKeeperFromLineup

    val playerOnList = enterContestPage.getSoccerPlayerFromList(1)
    enterContestPage.addSoccerPlayerFromList(1)
    assert(playerOnList == enterContestPage.getSoccerPlayerFromLineUp(1))

    if (status.resolution != Resolution.SMALL) {
      assert(playerOnList == enterContestPage.getSoccerPlayerFromList(1))
      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .getNumberOfSoccerPlayers must be (enterContestState.contest.numAllPlayers)

      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
          .getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)

      enterContestPage.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .setSoccerPlayerNameFilterSearch((if (playerOnList.name.length > 17) playerOnList.name.substring(0, 17) else playerOnList.name).toLowerCase)
          .getNumberOfSoccerPlayers must be (1)
    }

    assert(enterContestPage.getLineUpSalary < enterContestState.contest.initialSalary)
    enterContestPage.clearLineupList
  }

  def addFourthDefenseFromList:Unit = {
    enterContestPage

    assert(enterContestPage.getSoccerPlayerFromLineUp(2).isEmpty)
    enterContestPage.selectDefenseFromLineup(2)

    val playerOnList = enterContestPage.getSoccerPlayerFromList(4)
    enterContestPage.addSoccerPlayerFromList(4)
    assert(playerOnList == enterContestPage.getSoccerPlayerFromLineUp(2))

    enterContestPage.selectDefenseFromLineup(3)
    assert(playerOnList == enterContestPage.getSoccerPlayerFromList(4))
    enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers)

    assert(enterContestPage.setSoccerPlayerNameFilterSearch((if (playerOnList.name.length > 17) playerOnList.name.substring(0, 17) else playerOnList.name).toLowerCase)
               .getNumberOfSoccerPlayers == 1, "Player should exist.")

    enterContestPage.cancelSoccerPlayerSelection
    assert(enterContestPage.getLineUpSalary < enterContestState.contest.initialSalary)
    enterContestPage.clearLineupList
  }

  def pickAndClearWholeLineup:Unit = {
    enterContestPage.clearLineupList
    val contest = enterContestState.contest

    enterContestPage.pickWholeLineup(contest.affordableLineup)

    Thread.sleep(6000)
    assert(enterContestPage.getLineUpSalary == (contest.initialSalary - contest.affordableLineup.price))
    enterContestPage.removeSoccerPlayerFromLineUp(1).getLineUpSalary must be > 0

    enterContestPage.clearLineupList.getLineUpSalary must be (contest.initialSalary)
  }

  def pickTooExpensiveLineUp:Unit = {
    enterContestPage

    enterContestPage.getLineUpSalary must be (enterContestState.contest.initialSalary)

    enterContestPage.pickWholeLineup(enterContestState.contest.expensiveLineup)
    enterContestPage.getLineUpSalary must be < 0

    assert( enterContestPage.confirmLineup.isOverSalaryErrorShown )

    //enterContestPage.clearLineupList.getLineUpSalary must be (enterContestState.contest.initialSalary)
  }

  def CorrectFailLineup:Unit = {
    val expensiveLineup = enterContestState.contest.expensiveLineup.soccerPlayerList
    val expendAllMoneyLineup = enterContestState.contest.affordableLineup.soccerPlayerList

    for (i <- 0 to 10) {
      if (expensiveLineup(i) != expendAllMoneyLineup(i)) {
        enterContestPage.removeSoccerPlayerFromLineUp(i + 1)

        if (i == 0) {
          enterContestPage.selectGoalKeeperFromLineup
        } else if (i > 0 && i <= 4) {
          enterContestPage.selectDefenseFromLineup(i)
        } else if (i > 4 && i <= 8) {
          enterContestPage.selectMiddleFromLineup(i - 4)
        } else if (i > 8 && i <= 10) {
          enterContestPage.selectForwardFromLineup(i - 8)
        }

        enterContestPage.setSoccerPlayerNameFilterSearch(expendAllMoneyLineup(i).name)
            .addSoccerPlayerFromList(1)
      }
    }

    assert(enterContestPage.getLineUpSalary == (enterContestState.contest.initialSalary - enterContestState.contest.affordableLineup.price))

    enterContestPage.confirmLineup
    val viewContestState = new ViewContestState
    viewContestState.contest = state.contest
    new ViewContestPage(status.resolution, viewContestState).isAt
    //new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney).isAt
  }

/*
  def tryToConfirmMultipleTimes:Unit = {
    val page = goToEnterContest("540d4d1430045601813966ff")

    pickWholeLineup_Cheap(status.resolution)

    page.manyClicksOnConfirm

    Thread.sleep(15000)
  }
*/

  def knownBugSequence_DuplicatedPlayersAtDeleteAll:Unit = {
    _enterContestPageInstance = null
    enterContestPage

    Given("EnterContest page")
    enterContestPage
    //var playerOnList:SoccerPlayer = null

    And("select a soccer player")
    enterContestPage.selectDefenseFromLineup(1)
    logger.debug("Selected defense")
    val playerOnList:SoccerPlayer = enterContestPage.getSoccerPlayerFromList(1)
    val playerName = (if (playerOnList.name.length > 17) playerOnList.name.substring(0, 17) else playerOnList.name).toLowerCase
    logger.debug("Soccer info recolected")
    enterContestPage.addSoccerPlayerFromList(1)
    logger.debug("Added a soccer player from list")

    When("clear lineup")
    enterContestPage.clearLineupList

    And("search the soccer player")
    enterContestPage.selectDefenseFromLineup(1)

    Then("soccer player should appear alone.")
    enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers)

    When("add the soccer player")
    enterContestPage.addSoccerPlayerFromList(1)

    And("search him again")
    enterContestPage.selectDefenseFromLineup(2)
    Then("soccer player should appear alone as inserted (action button as remove).")
    logger.debug(s"Soccer player lines: ${enterContestPage.getNumberOfSoccerPlayers}, should be: ${enterContestState.contest.numDefensePlayers}")
    enterContestPage.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers)

  }

  def knownBugSequence_DisappearedPlayers:Unit = {
    _enterContestPageInstance = null
    enterContestPage

    if (status.resolution == Resolution.SMALL) {
      enterContestPage.selectGoalKeeperFromLineup
    }

    val playerOnList = enterContestPage.getSoccerPlayerFromList(1)
    enterContestPage.addSoccerPlayerFromList(1)
    enterContestPage.removeSoccerPlayerFromLineUp(1)

    if (status.resolution == Resolution.SMALL) {
      enterContestPage.selectGoalKeeperFromLineup
    }

    enterContestPage.setSoccerPlayerNameFilterSearch((if (playerOnList.name.length > 17) playerOnList.name.substring(0, 17) else playerOnList.name).toLowerCase)
    enterContestPage.getNumberOfSoccerPlayers must be (1)
  }

  def knownBugSequence_DuplicatedPlayersAtInsert:Unit = {
    _enterContestPageInstance = null
    enterContestPage

    enterContestPage.selectDefenseFromLineup(1)

    enterContestPage.manyClicksOnAddSoccer(1)
    assert(enterContestPage.getSoccerPlayerFromLineUp(3).isEmpty)
    assert(enterContestPage.getSoccerPlayerFromLineUp(4).isEmpty)
    assert(enterContestPage.getSoccerPlayerFromLineUp(5).isEmpty)

  }

  def knownBugSequence_AddForwardAsGoalKeeper:Unit = {
    _enterContestPageInstance = null
    enterContestPage

    if (status.resolution != Resolution.SMALL) {
      enterContestPage.selectGoalKeeperFromLineup
          .setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)

      enterContestPage.addSoccerPlayerFromList(1)
      assert(enterContestPage.getSoccerPlayerFromLineUp(1).isEmpty)
      assert(!enterContestPage.getSoccerPlayerFromLineUp(10).isEmpty)
      enterContestPage.clearLineupList
    }

  }

}
