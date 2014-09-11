package unusual.tests.enterContestTest

import unusual.model._
import unusual.pages._
import unusual.tests._

class EnterContestTestCommon extends SharedTest {

  val N_GOAL_KEEPER_PLAYERS = 19
  val N_DEFENSE_PLAYERS = 43
  val N_MIDDLE_PLAYERS = 55
  val N_FORWARD_PLAYERS = 27

  val N_CLE_AUS = 50
  val N_COL_GRC = 48
  val N_URY_CRI = 46

  val N_ALL_PLAYERS = 144

  val N_MIX_GK_URY_CRI = 6
  val N_MIX_GK_CLE_AUS = 7
  val N_MIX_DEF_URY_CRI = 16
  val N_MIX_MID_CLE_AUS = 22
  val N_MIX_FOR_COL_GRC = 9

  val INITIAL_SALARY = 90000

  def checkDefaultState(resolution:Resolution):Unit = {
    val page = goToEnterContest()
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    goBack()
    goForward()
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    goBack()
    goToEnterContest()
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    page.clickOnCloseButton
    goBack()
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    page.clickOnCloseButton
    goToEnterContest()
    assert(page.isAt && page.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    /*
    reloadPage()
    assert(page.isAt.isDefaultState(N_ALL_PLAYERS, INITIAL_SALARY))
    */

  }

  def orderByPosition(resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      val page = goToEnterContest()
      if (page.isOrderedByPos) { // si ya esta ordenado, lo ordenamos inversamente...
        page.orderByPosition
      }// ... y lo ordenamos al derecho
      page.orderByPosition

      assert(new EnterContestPage(resolution).isOrderedByPos)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByName(resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      val page = goToEnterContest().orderByName
      assert(page.isOrderedByName, "Is not ordered by name")
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByDFP(resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      assert(goToEnterContest().orderByDFP.isOrderedByDFP)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderByPlayed(resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      assert(goToEnterContest().orderByPlayed.isOrderedByPlayed)
    } else {
      featureNotTestableInResolution
    }
  }
  def orderBySalary(resolution:Resolution):Unit = {
    if (resolution == Resolution.BIG) {
      assert(goToEnterContest().orderBySalary.isOrderedBySalary)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByGoalKeeper(resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest()
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
      page.getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByDefense(resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest()
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_DEFENSE)
      page.getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMiddle(resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest()
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_MIDDLE)
      page.getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByForward(resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToEnterContest()
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)
      page.getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByAll(resolution:Resolution):Unit = {
    if (resolution != Resolution.SMALL) {
      goToEnterContest()
        .setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
        .getNumberOfSoccerPlayers must be(N_ALL_PLAYERS)
    } else {
      featureNotTestableInResolution
    }
    assert(N_GOAL_KEEPER_PLAYERS + N_DEFENSE_PLAYERS + N_MIDDLE_PLAYERS + N_FORWARD_PLAYERS == N_ALL_PLAYERS)
  }


  def selectGoalKeeper(resolution:Resolution):Unit = {
    goToEnterContest()
    new EnterContestPage(resolution)
      .selectGoalKeeperFromLineup
      .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
  }

  def selectDefense(resolution:Resolution):Unit = {
    val page = goToEnterContest()
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

  def selectMiddle(resolution:Resolution):Unit = {
    val page = goToEnterContest()
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

  def selectForward(resolution:Resolution):Unit = {
    val page = goToEnterContest()
    page.selectForwardFromLineup(1)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectForwardFromLineup(2)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
  }


  def multipleRandomSelection(resolution:Resolution):Unit = {
    var i = 0
    val page = goToEnterContest()

    val allFunctions = Map[Int, (EnterContestPage) => EnterContestPage] (
        1 -> ((page) => {
          logger.debug("GoalKeeper Selected")
          page.selectGoalKeeperFromLineup
              .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
          page
        }),
        2 -> ((page) => {
          val r = scala.util.Random.nextInt(4) + 1
          logger.debug("Defense{$r} Selected")
          page.selectDefenseFromLineup(r)
              .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)
          page
        }),
        3 -> ((page) => {
          val r = scala.util.Random.nextInt(4) + 1
          logger.debug("Middle{$r} Selected")
          page.selectMiddleFromLineup(r)
              .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)
          page
        }),
        4 -> ((page) => {
          val r = scala.util.Random.nextInt(2) + 1
          logger.debug("Forward{$r} Selected")
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


  def filterByMatch_None(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(1)
          .getNumberOfSoccerPlayers must be (N_ALL_PLAYERS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMatch_CleAus(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(2)
          .getNumberOfSoccerPlayers must be (N_CLE_AUS)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMatch_ColGrc(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(3)
          .getNumberOfSoccerPlayers must be (N_COL_GRC)
    } else {
      featureNotTestableInResolution
    }
  }

  def filterByMatch_UryCri(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    if (resolution != Resolution.SMALL) {
      page.setSoccerPlayerMatchFilter(4)
          .getNumberOfSoccerPlayers must be (N_URY_CRI)
    } else {
      featureNotTestableInResolution
    }
  }


  def filterMix_GoalKeeper_UryCri(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectGoalKeeperFromLineup
        .setSoccerPlayerMatchFilter(4)

    page.getNumberOfSoccerPlayers must be (N_MIX_GK_URY_CRI)
  }

  def filterMix_GoalKeeper_CleAus(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectGoalKeeperFromLineup
      .setSoccerPlayerMatchFilter(2)
    page.getNumberOfSoccerPlayers must be (N_MIX_GK_CLE_AUS)
  }

  def filterMix_Defense_UryCri(resolution:Resolution):Unit = {
    goToEnterContest()
    val page = new EnterContestPage(resolution)

    page.selectDefenseFromLineup(1)
        .setSoccerPlayerMatchFilter(4)
    page.getNumberOfSoccerPlayers must be (N_MIX_DEF_URY_CRI)
  }

  def filterMix_Middle_CleAus(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectMiddleFromLineup(1)
        .setSoccerPlayerMatchFilter(2)
    page.getNumberOfSoccerPlayers must be (N_MIX_MID_CLE_AUS)
  }

  def filterMix_Forward_ColGrc(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectForwardFromLineup(1)
        .setSoccerPlayerMatchFilter(3)
    page.getNumberOfSoccerPlayers must be (N_MIX_FOR_COL_GRC)
  }


  def addFirstGoalKeeperFromList(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.getLineUpSalary must be (INITIAL_SALARY)

    assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
    page.selectGoalKeeperFromLineup

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(1))

    if (resolution != Resolution.SMALL) {
      assert(playerOnList != page.getSoccerPlayerFromList(1))
      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .getNumberOfSoccerPlayers must be (N_ALL_PLAYERS - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
          .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .setSoccerPlayerNameFilterSearch(playerOnList.name)
          .getNumberOfSoccerPlayers must be (0)
    }

    assert(page.getLineUpSalary == 79300)
  }

  def addFourthDefenseFromList(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.getLineUpSalary must be (INITIAL_SALARY)

    assert(page.getSoccerPlayerFromLineUp(2).isEmpty)
    page.selectDefenseFromLineup(2)

    val playerOnList = page.getSoccerPlayerFromList(4)
    page.addSoccerPlayerFromList(4)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(2))

    page.selectDefenseFromLineup(3)
    assert(playerOnList != page.getSoccerPlayerFromList(4))
    page.getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS - 1)

    page.setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be (0)


    assert(page.getLineUpSalary == 85500)
  }

  def pickAndClearWholeLineup(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.getLineUpSalary must be (INITIAL_SALARY)

    pickWholeLineup_ExpendAllMoney(resolution)

    page.getLineUpSalary must be (0)

    page.removeSoccerPlayerFromLineUp(1).getLineUpSalary must be (8800)
    page.removeSoccerPlayerFromLineUp(4).getLineUpSalary must be (15500)
    page.removeSoccerPlayerFromLineUp(8).getLineUpSalary must be (24100)
    page.removeSoccerPlayerFromLineUp(11).getLineUpSalary must be (32700)
    page.clearLineupList.getLineUpSalary must be (INITIAL_SALARY)
  }

  def pickTooExpensiveLineUp(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.getLineUpSalary must be (INITIAL_SALARY)

    pickWholeLineup_Expensive(resolution)

    page.getLineUpSalary must be (-34500)

    assert( page.confirmLineup.isOverSalaryErrorShown )

    page.clearLineupList.getLineUpSalary must be (INITIAL_SALARY)
  }

  def pickFailLineupAndCorrectIt(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.getLineUpSalary must be (INITIAL_SALARY)

    pickWholeLineup_Expensive(resolution)

    page.getLineUpSalary must be (-34500)
    //Thread.sleep(600000);
    assert( page.confirmLineup.isOverSalaryErrorShown )

    page.removeSoccerPlayerFromLineUp(1)
      .selectGoalKeeperFromLineup
      .setSoccerPlayerNameFilterSearch("tose")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(2)
      .selectDefenseFromLineup(1)
      .setSoccerPlayerNameFilterSearch("tza")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(6)
      .selectMiddleFromLineup(1)
      .setSoccerPlayerNameFilterSearch("fuen")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(8)
      .selectMiddleFromLineup(3)
      .setSoccerPlayerNameFilterSearch("troi")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(10)
      .selectForwardFromLineup(1)
      .setSoccerPlayerNameFilterSearch("orell")
      .addSoccerPlayerFromList(1)

    page.getLineUpSalary must be (1200)

    page.confirmLineup
    new LobbyPage(resolution).isAt
    //page.clearLineupList.getLineUpSalary must be (INITIAL_SALARY)
  }

  def tryToConfirmMultipleTimes(resolution:Resolution):Unit = {
    val page = goToEnterContest("540d4d1430045601813966ff")

    pickWholeLineup_Cheap(resolution)

    page.manyClicksOnConfirm

    Thread.sleep(15000)
  }

  def knownBugSequence_DuplicatedPlayersAtDeleteAll(resolution:Resolution):Unit = {
    val page = goToEnterContest()
    var playerOnList:SoccerPlayer = null

    if (resolution == Resolution.SMALL) {
      page.selectDefenseFromLineup(1)
      playerOnList = page.getSoccerPlayerFromList(1)
      page.addSoccerPlayerFromList(1)
    } else {
      playerOnList = page.getSoccerPlayerFromList(4)
      page.addSoccerPlayerFromList(4)
    }

    page.clearLineupList

    if (resolution == Resolution.SMALL) {
      page.selectDefenseFromLineup(1)
    }

    page.setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be (1)
    page.addSoccerPlayerFromList(1)

    if (resolution == Resolution.SMALL) {
      page.selectDefenseFromLineup(2)
    }

    page.setSoccerPlayerNameFilterSearch("")
        .setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be (0) // En la reproduccion del bug es 1

  }

  def knownBugSequence_DisappearedPlayers(resolution:Resolution):Unit = {
    val page = goToEnterContest()

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

  def knownBugSequence_DuplicatedPlayersAtInsert(resolution:Resolution):Unit = {
    val page = goToEnterContest()

    page.selectDefenseFromLineup(1)

    page.manyClicksOnAddSoccer(1)
    assert(page.getSoccerPlayerFromLineUp(3).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(4).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(5).isEmpty)

  }

  def knownBugSequence_AddForwardAsGoalKeeper(resolution:Resolution):Unit = {

    if (resolution != Resolution.SMALL) {

      val page = goToEnterContest()

      page.selectGoalKeeperFromLineup
          .setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)

      page.addSoccerPlayerFromList(1)
      assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
      assert(!page.getSoccerPlayerFromLineUp(10).isEmpty)
    }

  }


  private def pickWholeLineup_Expensive(resolution:Resolution):Unit = {
    new EnterContestPage(resolution)
      .selectMiddleFromLineup(1) // MIDDLE
      .setSoccerPlayerNameFilterSearch("james ro")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(1) // DEFENSE
      .setSoccerPlayerNameFilterSearch("gianc")
      .addSoccerPlayerFromList(1)
      .selectForwardFromLineup(1) // FORWARD
      .setSoccerPlayerNameFilterSearch("bry")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(2) // MIDDLE
      .setSoccerPlayerNameFilterSearch("borges")
      .addSoccerPlayerFromList(1)
      .selectGoalKeeperFromLineup // GOALKEEPER
      .setSoccerPlayerNameFilterSearch("navas")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(2) // DEFENSE
      .setSoccerPlayerNameFilterSearch("zapata")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(3) // MIDDLE
      .setSoccerPlayerNameFilterSearch("bola")
      .addSoccerPlayerFromList(1)
      .selectForwardFromLineup(2) // FORWARD
      .setSoccerPlayerNameFilterSearch("geo")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(4) // MIDDLE
      .setSoccerPlayerNameFilterSearch("yelt")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(3) // DEFENSE
      .setSoccerPlayerNameFilterSearch("armero")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(4) // DEFENSE
      .setSoccerPlayerNameFilterSearch("yepes")
      .addSoccerPlayerFromList(1)

    // left -34500
  }

  private def pickWholeLineup_Cheap(resolution:Resolution):Unit = {
    new EnterContestPage(resolution)
      .selectForwardFromLineup(1) // FORWARD
      .setSoccerPlayerNameFilterSearch("ore")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(1) // MIDDLE
      .setSoccerPlayerNameFilterSearch("calvo")
      .addSoccerPlayerFromList(1)
      .selectGoalKeeperFromLineup // GOALKEEPER
      .setSoccerPlayerNameFilterSearch("stef")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(1) // DEFENSE
      .setSoccerPlayerNameFilterSearch("alb")
      .addSoccerPlayerFromList(1)
      .selectForwardFromLineup(2) // FORWARD
      .setSoccerPlayerNameFilterSearch("pare")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(2) // MIDDLE
      .setSoccerPlayerNameFilterSearch("fuen")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(2) // DEFENSE
      .setSoccerPlayerNameFilterSearch("way")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(3) // DEFENSE
      .setSoccerPlayerNameFilterSearch("heiner")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(3) // MIDDLE
      .setSoccerPlayerNameFilterSearch("troi")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(4) // DEFENSE
      .setSoccerPlayerNameFilterSearch("wri")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(4) // MIDDLE
      .setSoccerPlayerNameFilterSearch("luon")
      .addSoccerPlayerFromList(1)

    // left 40500
  }

  private def pickWholeLineup_ExpendAllMoney(resolution:Resolution):Unit = {
    new EnterContestPage(resolution)

      .selectDefenseFromLineup(1) // DEFENSE
      .setSoccerPlayerNameFilterSearch("gim")
      .addSoccerPlayerFromList(1)
      .selectForwardFromLineup(1) // FORWARD
      .setSoccerPlayerNameFilterSearch("lui")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(2) // DEFENSE
      .setSoccerPlayerNameFilterSearch("san")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(1) // MIDDLE
      .setSoccerPlayerNameFilterSearch("marce")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(2) // MIDDLE
      .setSoccerPlayerNameFilterSearch("francisco")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(3) // DEFENSE
      .setSoccerPlayerNameFilterSearch("spira")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(3) // MIDDLE
      .setSoccerPlayerNameFilterSearch("laza")
      .addSoccerPlayerFromList(1)
      .selectDefenseFromLineup(4) // DEFENSE
      .setSoccerPlayerNameFilterSearch("fuc")
      .addSoccerPlayerFromList(1)
      .selectGoalKeeperFromLineup // GOALKEEPER
      .setSoccerPlayerNameFilterSearch("fer")
      .addSoccerPlayerFromList(1)
      .selectForwardFromLineup(2) // FORWARD
      .setSoccerPlayerNameFilterSearch("jack")
      .addSoccerPlayerFromList(1)
      .selectMiddleFromLineup(4) // MIDDLE
      .setSoccerPlayerNameFilterSearch("andre")
      .addSoccerPlayerFromList(1)
    // left 0
  }
/*
  private def enterInContest: LobbyPage ={
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuPrizedContests
                 .playContestNumber(1)
  }
*/
}
