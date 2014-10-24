package unusual.tests.enterContestTest

import org.specs2.specification.Given
import unusual.model._
import unusual.pages._
import unusual.tests._

abstract class EnterContestTestCommon(state: EnterContestState, res:Resolution) extends SharedTest(res) {

  val enterContestState = state

  def checkDefaultState:Unit = {

    val N_ALL_PLAYERS = enterContestState.contest.numAllPlayers
    val INITIAL_SALARY = enterContestState.contest.initialSalary

    Given("Enter Contest page")
    val lobby = goToLobbyPage().clearFilters
    var page:EnterContestPage = null
    eventually { page = goToEnterContest(enterContestState) }
    //val lobby = new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
    Then("page should be at and default state")
      Thread.sleep(10000)
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    When("go navigate back and forward")
    goBack()
    goForward()
    Then("page should be at and default state")
    assert(page.isAt)
    assert(page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    When("go navigate back and click on play button")
    goBack()
    lobby.clearFilters
    if (status.resolution == Resolution.SMALL) {
      lobby.playContestNumber(state.contest.nameOrder)
    } else {
      lobby.searchContestByName(state.contest.name).playContestNumber(1)
    }
    Then("page should be at and default state")
    assert(page.isAt, "is not at")
    assert(page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY), "is not default")
    When("click on close button and navigate back")
    page.clickOnCloseButton
    goBack()
    Then("page should be at and default state")
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    When("click on close button and click on play button")
    page.clickOnCloseButton
    lobby.clearFilters
    if (status.resolution == Resolution.SMALL) {
      lobby.playContestNumber(state.contest.nameOrder)
    } else {
      lobby.searchContestByName(state.contest.name)
        .playContestNumber(1)
    }
    Then("page should be at and default state")
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    /*
    reloadPage()
    assert(page.isAt.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    */

  }

  def orderByPosition:Unit = {
    if (status.resolution == Resolution.BIG) {
      val page = goToEnterContest(enterContestState)
      if (page.isOrderedByPos) { // si ya esta ordenado, lo ordenamos inversamente...
        page.orderByPosition
      }// ... y lo ordenamos al derecho
      page.orderByPosition

      assert(new EnterContestPage(status.resolution, enterContestState).isOrderedByPos)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByName:Unit = {
    if (status.resolution == Resolution.BIG) {
      val page = goToEnterContest(enterContestState).orderByName
      assert(page.isOrderedByName, "Is not ordered by name")
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByDFP:Unit = {
    if (status.resolution == Resolution.BIG) {
      assert(goToEnterContest(enterContestState).orderByDFP.isOrderedByDFP)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByPlayed:Unit = {
    if (status.resolution == Resolution.BIG) {
      assert(goToEnterContest(enterContestState).orderByPlayed.isOrderedByPlayed)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderBySalary:Unit = {
    if (status.resolution == Resolution.BIG) {
      assert(goToEnterContest(enterContestState).orderBySalary.isOrderedBySalary, "page is not ordered by salary")
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByGoalKeeper:Unit = {
    if (status.resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByDefense:Unit = {
    if (status.resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_DEFENSE)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMiddle:Unit = {
    if (status.resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_MIDDLE)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numMiddlePlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByForward:Unit = {
    if (status.resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numForwardPlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByAll:Unit = {
    if (status.resolution != Resolution.SMALL) {
      goToEnterContest(enterContestState)
        .setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
        .getNumberOfSoccerPlayers must be(enterContestState.contest.numAllPlayers)
    } else {
      featureNotTestableInResolution
    }
    assert(enterContestState.contest.numGoalKeepersPlayers
            + enterContestState.contest.numDefensePlayers
            + enterContestState.contest.numMiddlePlayers
            + enterContestState.contest.numForwardPlayers == enterContestState.contest.numAllPlayers)
  }


  def selectGoalKeeper:Unit = {
    goToEnterContest(enterContestState)
    new EnterContestPage(status.resolution, enterContestState)
        .selectGoalKeeperFromLineup
        .getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)
  }

  def selectDefense:Unit = {
    val page = goToEnterContest(enterContestState)
    val N_DEFENSE_PLAYERS = enterContestState.contest.numDefensePlayers
    page.selectDefenseFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectDefenseFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectDefenseFromLineup(3)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectDefenseFromLineup(4)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
  }

  def selectMiddle:Unit = {
    val page = goToEnterContest(enterContestState)
    val N_MIDDLE_PLAYERS = enterContestState.contest.numMiddlePlayers
    page.selectMiddleFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectMiddleFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectMiddleFromLineup(3)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectMiddleFromLineup(4)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
  }

  def selectForward:Unit = {
    val page = goToEnterContest(enterContestState)
    val N_FORWARD_PLAYERS = enterContestState.contest.numForwardPlayers

    page.selectForwardFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectForwardFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
  }


  def multipleRandomSelection:Unit = {
    var i = 0
    val page = goToEnterContest(enterContestState)
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

    while(i < 20){
      allFunctions(scala.util.Random.nextInt(4) + 1)(page).cancelSoccerPlayerSelection
      i += 1
    }
  }

  def filterByMatch:Unit = {
    if (status.resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      Given("a list of matches")
      for ((matchOrder, numPlayers) <- enterContestState.contest.matchPlayers) {
        When("select match by order: " + matchOrder)
        page.setSoccerPlayerMatchFilter(matchOrder)
        Then("displayed players should be: " + numPlayers)
        page.getNumberOfSoccerPlayers must be (numPlayers)
      }
    } else {
      featureNotTestableInResolution
    }
  }

  def filterMix_Position_Match: Unit = {
    if (status.resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      Given("a list of matches")
      for ((matchOrder, positionList) <- enterContestState.contest.matchPositionMixNumPlayers) {
        for ((positionOrder, numPlayers) <- positionList) {

          When("select match by order: " + matchOrder)
          page.setSoccerPlayerMatchFilter(matchOrder)

          And("select position by order: " + positionOrder)
          page.setSoccerPlayerPositionFilter(positionOrder)

          Then("displayed players should be: " + numPlayers)
          assert(page.getNumberOfSoccerPlayers == numPlayers)
        }
      }
    } else {
      featureNotTestableInResolution
    }
  }

  def addFirstGoalKeeperFromList:Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
    page.selectGoalKeeperFromLineup

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(1))

    if (status.resolution != Resolution.SMALL) {
      assert(playerOnList != page.getSoccerPlayerFromList(1))
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .getNumberOfSoccerPlayers must be (enterContestState.contest.numAllPlayers - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
          .getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .setSoccerPlayerNameFilterSearch(playerOnList.name)
          .getNumberOfSoccerPlayers must be (0)
    }

    assert(page.getLineUpSalary < enterContestState.contest.initialSalary)
  }

  def addFourthDefenseFromList:Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    assert(page.getSoccerPlayerFromLineUp(2).isEmpty)
    page.selectDefenseFromLineup(2)

    val playerOnList = page.getSoccerPlayerFromList(4)
    page.addSoccerPlayerFromList(4)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(2))

    page.selectDefenseFromLineup(3)
    assert(playerOnList != page.getSoccerPlayerFromList(4))
    page.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers - 1)

    assert(page.setSoccerPlayerNameFilterSearch(playerOnList.name)
               .getNumberOfSoccerPlayers == 0, "Should not exist any player.")


    assert(page.getLineUpSalary < enterContestState.contest.initialSalary)
  }

  def pickAndClearWholeLineup:Unit = {
    val page = goToEnterContest(enterContestState)
    val contest = enterContestState.contest
    page.getLineUpSalary must be (contest.initialSalary)

    page.pickWholeLineup(contest.affordableLineup)

    assert(page.getLineUpSalary == (contest.initialSalary - contest.affordableLineup.price))
    page.removeSoccerPlayerFromLineUp(1).getLineUpSalary must be > 0

    page.clearLineupList.getLineUpSalary must be (contest.initialSalary)
  }

  def pickTooExpensiveLineUp:Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    page.pickWholeLineup(enterContestState.contest.expensiveLineup)
    page.getLineUpSalary must be < 0

    assert( page.confirmLineup.isOverSalaryErrorShown )

    page.clearLineupList.getLineUpSalary must be (enterContestState.contest.initialSalary)
  }

  def pickFailLineupAndCorrectIt:Unit = {
    val page = goToEnterContest(enterContestState)
    val contest = enterContestState.contest

    page.getLineUpSalary must be (contest.initialSalary)

    page.pickWholeLineup(contest.expensiveLineup)

    page.getLineUpSalary must be < 0

    assert( page.confirmLineup.isOverSalaryErrorShown )

    val expensiveLineup = contest.expensiveLineup.soccerPlayerList
    val expendAllMoneyLineup = contest.affordableLineup.soccerPlayerList

    for (i <- 0 to 10) {
      if (expensiveLineup(i) != expendAllMoneyLineup(i)) {
        page.removeSoccerPlayerFromLineUp(i + 1)

        if (i == 0) {
          page.selectGoalKeeperFromLineup
        } else if (i > 0 && i <= 4) {
          page.selectDefenseFromLineup(i)
        } else if (i > 4 && i <= 8) {
          page.selectMiddleFromLineup(i - 4)
        } else if (i > 8 && i <= 10) {
          page.selectForwardFromLineup(i - 8)
        }

        page.setSoccerPlayerNameFilterSearch(expendAllMoneyLineup(i).name)
            .addSoccerPlayerFromList(1)
      }
    }

    assert(page.getLineUpSalary == (contest.initialSalary - contest.affordableLineup.price))

    page.confirmLineup
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
    Given("EnterContest page")
    val page = goToEnterContest(enterContestState)
    var playerOnList:SoccerPlayer = null

    And("select a soccer player")
    page.selectDefenseFromLineup(1)
    playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)

    When("clear lineup")
    page.clearLineupList

    And("search the soccer player")
    if (playerOnList.name.length > 19) {
      playerOnList.name = playerOnList.name.substring(0, 19)
    }
    page.selectDefenseFromLineup(1)
        .setSoccerPlayerNameFilterSearch(playerOnList.name)

    Then("soccer player should appear alone.")
    page.getNumberOfSoccerPlayers must be (1)

    When("add the soccer player")
    page.addSoccerPlayerFromList(1)

    And("search him again")
    page.selectDefenseFromLineup(2)
    page.setSoccerPlayerNameFilterSearch("")
        .setSoccerPlayerNameFilterSearch(playerOnList.name)
    Then("soccer player should not appear.")
    page.getNumberOfSoccerPlayers must be (0) // En la reproduccion del bug es 1

  }

  def knownBugSequence_DisappearedPlayers:Unit = {
    val page = goToEnterContest(enterContestState)

    if (status.resolution == Resolution.SMALL) {
      page.selectGoalKeeperFromLineup
    }

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(1)

    if (status.resolution == Resolution.SMALL) {
      page.selectGoalKeeperFromLineup
    }

    page.setSoccerPlayerNameFilterSearch((if (playerOnList.name.toLowerCase.length > 17) playerOnList.name.substring(0, 17) else playerOnList.name).toLowerCase)
    page.getNumberOfSoccerPlayers must be (1)
  }

  def knownBugSequence_DuplicatedPlayersAtInsert:Unit = {
    val page = goToEnterContest(enterContestState)

    page.selectDefenseFromLineup(1)

    page.manyClicksOnAddSoccer(1)
    assert(page.getSoccerPlayerFromLineUp(3).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(4).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(5).isEmpty)

  }

  def knownBugSequence_AddForwardAsGoalKeeper:Unit = {

    if (status.resolution != Resolution.SMALL) {

      val page = goToEnterContest(enterContestState)

      page.selectGoalKeeperFromLineup
          .setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)

      page.addSoccerPlayerFromList(1)
      assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
      assert(!page.getSoccerPlayerFromLineUp(10).isEmpty)
    }

  }

}
