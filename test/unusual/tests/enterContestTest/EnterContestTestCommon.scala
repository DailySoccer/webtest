package unusual.tests.enterContestTest

import org.specs2.specification.Given
import unusual.model._
import unusual.pages._
import unusual.tests._

class EnterContestTestCommon(state: EnterContestState) extends SharedTest {

  val enterContestState = state

  def checkDefaultState(implicit resolution:Resolution):Unit = {

    val N_ALL_PLAYERS = enterContestState.contest.numAllPlayers
    val INITIAL_SALARY = enterContestState.contest.initialSalary

    Given("Enter Contest page")
    val lobby = goToLobbyPage().clearFilters
    var page:EnterContestPage = null
    eventually { page = goToEnterContest(enterContestState) }
    //val lobby = new LobbyPage(resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney)
    Then("page should be at and default state")
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
         .playContestNumber(enterContestState.contest.nameOrder)
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
         .playContestNumber(enterContestState.contest.nameOrder)
    Then("page should be at and default state")
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    /*
    reloadPage()
    assert(page.isAt.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    */

  }

  def orderByPosition(implicit resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      val page = goToEnterContest(enterContestState)
      if (page.isOrderedByPos) { // si ya esta ordenado, lo ordenamos inversamente...
        page.orderByPosition
      }// ... y lo ordenamos al derecho
      page.orderByPosition

      assert(new EnterContestPage(resolution, enterContestState).isOrderedByPos)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByName(implicit resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      val page = goToEnterContest(enterContestState).orderByName
      assert(page.isOrderedByName, "Is not ordered by name")
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByDFP(implicit resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      assert(goToEnterContest(enterContestState).orderByDFP.isOrderedByDFP)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByPlayed(implicit resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      assert(goToEnterContest(enterContestState).orderByPlayed.isOrderedByPlayed)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderBySalary(implicit resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      assert(goToEnterContest(enterContestState).orderBySalary.isOrderedBySalary)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByGoalKeeper(implicit resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByDefense(implicit resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_DEFENSE)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numDefensePlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMiddle(implicit resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_MIDDLE)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numMiddlePlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByForward(implicit resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest(enterContestState)
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)
      page.getNumberOfSoccerPlayers must be (enterContestState.contest.numForwardPlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByAll(implicit resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
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


  def selectGoalKeeper(implicit resolution:Resolution):Unit = {
    goToEnterContest(enterContestState)
    new EnterContestPage(resolution, enterContestState)
      .selectGoalKeeperFromLineup
      .getNumberOfSoccerPlayers must be (enterContestState.contest.numGoalKeepersPlayers)
  }

  def selectDefense(implicit resolution:Resolution):Unit = {
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

  def selectMiddle(implicit resolution:Resolution):Unit = {
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

  def selectForward(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)
    val N_FORWARD_PLAYERS = enterContestState.contest.numForwardPlayers

    page.selectForwardFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectForwardFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
  }


  def multipleRandomSelection(implicit resolution:Resolution):Unit = {
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

  def filterByMatch(implicit resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
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
  /*
  def filterByMatch_None(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(1)
          .getNumberOfSoccerPlayers must be (contest.numAllPlayers)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMatch_CleAus(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(2)
          .getNumberOfSoccerPlayers must be (N_CLE_AUS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMatch_ColGrc(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(3)
          .getNumberOfSoccerPlayers must be (N_COL_GRC)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMatch_UryCri(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(4)
          .getNumberOfSoccerPlayers must be (N_URY_CRI)
    } else {
      featureNotTestableInResolution
    }
  }
  */

  def filterMix_Position_Match(implicit resolution:Resolution): Unit = {
    if (resolution != Resolution.SMALL) {
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
/*
  def filterMix_GoalKeeper_UryCri(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectGoalKeeperFromLineup
        .setSoccerPlayerMatchFilter(4)

    page.getNumberOfSoccerPlayers must be (N_MIX_GK_URY_CRI)
  }

  def filterMix_GoalKeeper_CleAus(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectGoalKeeperFromLineup
      .setSoccerPlayerMatchFilter(2)
    page.getNumberOfSoccerPlayers must be (N_MIX_GK_CLE_AUS)
  }

  def filterMix_Defense_UryCri(implicit resolution:Resolution):Unit = {
    goToEnterContest()
    val page = new EnterContestPage(resolution)

    page.selectDefenseFromLineup(1)
        .setSoccerPlayerMatchFilter(4)
    page.getNumberOfSoccerPlayers must be (N_MIX_DEF_URY_CRI)
  }

  def filterMix_Middle_CleAus(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectMiddleFromLineup(1)
        .setSoccerPlayerMatchFilter(2)
    page.getNumberOfSoccerPlayers must be (N_MIX_MID_CLE_AUS)
  }

  def filterMix_Forward_ColGrc(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectForwardFromLineup(1)
        .setSoccerPlayerMatchFilter(3)
    page.getNumberOfSoccerPlayers must be (N_MIX_FOR_COL_GRC)
  }
*/

  def addFirstGoalKeeperFromList(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
    page.selectGoalKeeperFromLineup

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(1))

    if (resolution != Resolution.SMALL) {
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

  def addFourthDefenseFromList(implicit resolution:Resolution):Unit = {
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

    page.setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be equals 0


    assert(page.getLineUpSalary < enterContestState.contest.initialSalary)
  }

  def pickAndClearWholeLineup(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    page.pickWholeLineup(enterContestState.contest.expendAllMoney)

    page.getLineUpSalary must be equals 0
    page.removeSoccerPlayerFromLineUp(1).getLineUpSalary must be > 0

    page.clearLineupList.getLineUpSalary must be (enterContestState.contest.initialSalary)
  }

  def pickTooExpensiveLineUp(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    page.pickWholeLineup(enterContestState.contest.expensiveLineup)
    page.getLineUpSalary must be < 0

    assert( page.confirmLineup.isOverSalaryErrorShown )

    page.clearLineupList.getLineUpSalary must be (enterContestState.contest.initialSalary)
  }

  def pickFailLineupAndCorrectIt(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)

    page.getLineUpSalary must be (enterContestState.contest.initialSalary)

    page.pickWholeLineup(enterContestState.contest.expensiveLineup)

    page.getLineUpSalary must be < 0

    assert( page.confirmLineup.isOverSalaryErrorShown )

    val expensiveLineup = enterContestState.contest.expensiveLineup.soccerPlayerList
    val expendAllMoneyLineup = enterContestState.contest.expendAllMoney.soccerPlayerList

    for (i <- 0 to 10){
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

    page.getLineUpSalary must be equals 0

    page.confirmLineup
    //new LobbyPage(resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney).isAt
  }

/*
  def tryToConfirmMultipleTimes(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest("540d4d1430045601813966ff")

    pickWholeLineup_Cheap(resolution)

    page.manyClicksOnConfirm

    Thread.sleep(15000)
  }
*/

  def knownBugSequence_DuplicatedPlayersAtDeleteAll(implicit resolution:Resolution):Unit = {
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

  def knownBugSequence_DisappearedPlayers(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)

    if (resolution == Resolution.SMALL) {
      page.selectGoalKeeperFromLineup
    }

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(1)

    if (resolution == Resolution.SMALL) {
      page.selectGoalKeeperFromLineup
    }

    page.setSoccerPlayerNameFilterSearch(playerOnList.name)
    page.getNumberOfSoccerPlayers must be (1)
  }

  def knownBugSequence_DuplicatedPlayersAtInsert(implicit resolution:Resolution):Unit = {
    val page = goToEnterContest(enterContestState)

    page.selectDefenseFromLineup(1)

    page.manyClicksOnAddSoccer(1)
    assert(page.getSoccerPlayerFromLineUp(3).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(4).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(5).isEmpty)

  }

  def knownBugSequence_AddForwardAsGoalKeeper(implicit resolution:Resolution):Unit = {

    if (resolution != Resolution.SMALL) {

      val page = goToEnterContest(enterContestState)

      page.selectGoalKeeperFromLineup
          .setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)

      page.addSoccerPlayerFromList(1)
      assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
      assert(!page.getSoccerPlayerFromLineUp(10).isEmpty)
    }

  }
/*
  private def pickWholeLineup(implicit resolution:Resolution, lineup:Lineup):Unit = {
    val list = lineup.soccerPlayerList

    new EnterContestPage(resolution, enterContestState.contest)

      .selectMiddleFromLineup(1) // MIDDLE
      .setSoccerPlayerNameFilterSearch(list(5).name)
      .addSoccerPlayerFromList(1)

      .selectDefenseFromLineup(1) // DEFENSE
      .setSoccerPlayerNameFilterSearch(list(1).name)
      .addSoccerPlayerFromList(1)

      .selectForwardFromLineup(1) // FORWARD
      .setSoccerPlayerNameFilterSearch(list(9).name)
      .addSoccerPlayerFromList(1)

      .selectMiddleFromLineup(2) // MIDDLE
      .setSoccerPlayerNameFilterSearch(list(6).name)
      .addSoccerPlayerFromList(1)

      .selectGoalKeeperFromLineup // GOALKEEPER
      .setSoccerPlayerNameFilterSearch(list(0).name)
      .addSoccerPlayerFromList(1)

      .selectDefenseFromLineup(2) // DEFENSE
      .setSoccerPlayerNameFilterSearch(list(2).name)
      .addSoccerPlayerFromList(1)

      .selectMiddleFromLineup(3) // MIDDLE
      .setSoccerPlayerNameFilterSearch(list(7).name)
      .addSoccerPlayerFromList(1)

      .selectForwardFromLineup(2) // FORWARD
      .setSoccerPlayerNameFilterSearch(list(10).name)
      .addSoccerPlayerFromList(1)

      .selectMiddleFromLineup(4) // MIDDLE
      .setSoccerPlayerNameFilterSearch(list(8).name)
      .addSoccerPlayerFromList(1)

      .selectDefenseFromLineup(3) // DEFENSE
      .setSoccerPlayerNameFilterSearch(list(3).name)
      .addSoccerPlayerFromList(1)

      .selectDefenseFromLineup(4) // DEFENSE
      .setSoccerPlayerNameFilterSearch(list(4).name)
      .addSoccerPlayerFromList(1)
  }*/
/*
  private def enterInContest: LobbyPage ={
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuPrizedContests
                 .playContestNumber(1)
  }
*/
}
