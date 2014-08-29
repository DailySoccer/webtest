package unusual.tests.enterContestTest

import unusual.model._
import unusual.pages._
import unusual.testTags.scala._
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


  def filterByGoalKeeper(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
    if (resolution != Resolution.SMALL) {
      page.getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
    }
  }

  def filterByDefense(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_DEFENSE)
    if (resolution != Resolution.SMALL) {
      page.getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)
    }
  }

  def filterByMiddle(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_MIDDLE)
    if (resolution != Resolution.SMALL) {
      page.getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)
    }
  }

  def filterByForward(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_FORWARD)
    if (resolution != Resolution.SMALL) {
      page.getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)
    }
  }

  def filterByAll(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    new EnterContestPage(resolution)
          .setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .getNumberOfSoccerPlayers must be (N_ALL_PLAYERS)
    assert(N_GOAL_KEEPER_PLAYERS + N_DEFENSE_PLAYERS + N_MIDDLE_PLAYERS + N_FORWARD_PLAYERS == N_ALL_PLAYERS)
  }


  def selectGoalKeeper(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    new EnterContestPage(resolution)
      .selectGoalKeeperOnMyTeam
      .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
  }

  def selectDefense(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.selectDefenseOnMyTeam(1)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectDefenseOnMyTeam(2)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectDefenseOnMyTeam(3)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectDefenseOnMyTeam(4)
        .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)

    page.cancelSoccerPlayerSelection
  }

  def selectMiddle(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.selectMiddleOnMyTeam(1)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectMiddleOnMyTeam(2)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectMiddleOnMyTeam(3)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectMiddleOnMyTeam(4)
        .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)

    page.cancelSoccerPlayerSelection
  }

  def selectForward(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    page.selectForwardOnMyTeam(1)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
        .selectForwardOnMyTeam(2)
        .getNumberOfSoccerPlayers must be (N_FORWARD_PLAYERS)

    page.cancelSoccerPlayerSelection
  }


  def multipleRandomSelection(resolution:Resolution):Unit = {
    var i = 0
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    val allFunctions = Map[Int, (EnterContestPage) => EnterContestPage] (
        1 -> ((page) => {
          println(s"[additional-info] GoalKeeper Selected")
          page.selectGoalKeeperOnMyTeam
              .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS)
          page
        }),
        2 -> ((page) => {
          val r = scala.util.Random.nextInt(4) + 1
          println(s"[additional-info] Defense{$r} Selected")
          page.selectDefenseOnMyTeam(r)
              .getNumberOfSoccerPlayers must be (N_DEFENSE_PLAYERS)
          page
        }),
        3 -> ((page) => {
          val r = scala.util.Random.nextInt(4) + 1
          println(s"[additional-info] Middle{$r} Selected")
          page.selectMiddleOnMyTeam(r)
              .getNumberOfSoccerPlayers must be (N_MIDDLE_PLAYERS)
          page
        }),
        4 -> ((page) => {
          val r = scala.util.Random.nextInt(2) + 1
          println(s"[additional-info] Forward{$r} Selected")
          page.selectForwardOnMyTeam(r)
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
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.setSoccerPlayerMatchFilter(1)
    if (resolution != Resolution.SMALL) { page.getNumberOfSoccerPlayers must be (N_ALL_PLAYERS) }
  }

  def filterByMatch_CleAus(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.setSoccerPlayerMatchFilter(2)
    if (resolution != Resolution.SMALL) { page.getNumberOfSoccerPlayers must be (N_CLE_AUS) }
  }

  def filterByMatch_ColGrc(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.setSoccerPlayerMatchFilter(3)
    if (resolution != Resolution.SMALL) { page.getNumberOfSoccerPlayers must be (N_COL_GRC) }
  }

  def filterByMatch_UryCri(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.setSoccerPlayerMatchFilter(4)
    if (resolution != Resolution.SMALL) { page.getNumberOfSoccerPlayers must be (N_URY_CRI) }
  }


  def filterMix_GoalKeeper_UryCri(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.selectGoalKeeperOnMyTeam
        .setSoccerPlayerMatchFilter(4)

    page.getNumberOfSoccerPlayers must be (N_MIX_GK_URY_CRI)
  }

  def filterMix_GoalKeeper_CleAus(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.selectGoalKeeperOnMyTeam
      .setSoccerPlayerMatchFilter(2)
    page.getNumberOfSoccerPlayers must be (N_MIX_GK_CLE_AUS)
  }

  def filterMix_Defense_UryCri(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.selectDefenseOnMyTeam(1)
        .setSoccerPlayerMatchFilter(4)
    page.getNumberOfSoccerPlayers must be (N_MIX_DEF_URY_CRI)
  }

  def filterMix_Middle_CleAus(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.selectMiddleOnMyTeam(1)
        .setSoccerPlayerMatchFilter(2)
    page.getNumberOfSoccerPlayers must be (N_MIX_MID_CLE_AUS)
  }

  def filterMix_Forward_ColGrc(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.selectForwardOnMyTeam(1)
        .setSoccerPlayerMatchFilter(3)
    page.getNumberOfSoccerPlayers must be (N_MIX_FOR_COL_GRC)
  }


  def addFirstGoalKeeperFromList(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.getLineUpSalary must be (90000)

    assert(page.getSoccerPlayerFromLineUp(1).isEmpty)
    if (resolution == Resolution.SMALL) {
      page.selectGoalKeeperOnMyTeam
    }

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(1))

    if (resolution != Resolution.SMALL) {
      assert(playerOnList != page.getSoccerPlayerFromList(1))
      page.getNumberOfSoccerPlayers must be (N_ALL_PLAYERS - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
          .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
          .setSoccerPlayerNameFilterSearch(playerOnList.name)
          .getNumberOfSoccerPlayers must be (0)
    }

    assert(page.getLineUpSalary == 79300)
  }

  def addFourthDefenseFromList(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.getLineUpSalary must be (90000)

    assert(page.getSoccerPlayerFromLineUp(3).isEmpty)
    page.selectDefenseOnMyTeam(2)

    val playerOnList = page.getSoccerPlayerFromList(4)
    page.addSoccerPlayerFromList(4)
    assert(playerOnList == page.getSoccerPlayerFromLineUp(3))

    if (resolution != Resolution.SMALL) {
      assert(playerOnList != page.getSoccerPlayerFromList(1))
      page.getNumberOfSoccerPlayers must be (N_ALL_PLAYERS - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_GOAL_KEEPER)
        .getNumberOfSoccerPlayers must be (N_GOAL_KEEPER_PLAYERS - 1)

      page.setSoccerPlayerPositionFilter(SoccerPlayer.POS_ALL)
        .setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be (0)
    }

    assert(page.getLineUpSalary == 79300)
  }

  def pickAndClearWholeLineup(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.getLineUpSalary must be (90000)

    pickWholeLineup_ExpendAllMoney(resolution)

    page.getLineUpSalary must be (0)

    page.removeSoccerPlayerFromLineUp(1).getLineUpSalary must be (8800)
    page.removeSoccerPlayerFromLineUp(4).getLineUpSalary must be (15500)
    page.removeSoccerPlayerFromLineUp(8).getLineUpSalary must be (24100)
    page.removeSoccerPlayerFromLineUp(11).getLineUpSalary must be (32700)
    page.clearLineupList.getLineUpSalary must be (90000)
  }

  def pickTooExpensiveLineUp(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.getLineUpSalary must be (90000)

    pickWholeLineup_Expensive(resolution)

    page.getLineUpSalary must be (-34500)

    assert( page.confirmLineup.isOverSalaryErrorShown )

    page.clearLineupList.getLineUpSalary must be (90000)
  }

  def pickFailLineupAndCorrectIt(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.getLineUpSalary must be (90000)

    pickWholeLineup_Expensive(resolution)

    page.getLineUpSalary must be (-34500)
    //Thread.sleep(600000);
    assert( page.confirmLineup.isOverSalaryErrorShown )

    page.removeSoccerPlayerFromLineUp(1)
      .selectGoalKeeperOnMyTeam
      .setSoccerPlayerNameFilterSearch("tose")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(2)
      .selectDefenseOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("tza")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(6)
      .selectMiddleOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("fuen")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(8)
      .selectMiddleOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("troi")
      .addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(10)
      .selectForwardOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("orell")
      .addSoccerPlayerFromList(1)

    page.getLineUpSalary must be (1200)

    page.confirmLineup
    //page.clearLineupList.getLineUpSalary must be (90000)
  }


  def knownBugSequence_DuplicatedPlayersAtDeleteAll(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)
    var playerOnList:SoccerPlayer = null

    if (resolution == Resolution.SMALL) {
      page.selectDefenseOnMyTeam(1)
      playerOnList = page.getSoccerPlayerFromList(1)
      page.addSoccerPlayerFromList(1)

    } else{
      playerOnList = page.getSoccerPlayerFromList(4)
      page.addSoccerPlayerFromList(4)
    }

    page.clearLineupList

    if (resolution == Resolution.SMALL) {
      page.selectDefenseOnMyTeam(1)
    }

    page.setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be (1)
    page.addSoccerPlayerFromList(1)

    if (resolution == Resolution.SMALL) {
      page.selectDefenseOnMyTeam(2)
    }

    page.setSoccerPlayerNameFilterSearch("")
        .setSoccerPlayerNameFilterSearch(playerOnList.name)
        .getNumberOfSoccerPlayers must be (0) // En la reproduccion del bug es 1

  }

  def knownBugSequence_DisappearedPlayers(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    if (resolution == Resolution.SMALL) {
      page.selectGoalKeeperOnMyTeam
    }

    val playerOnList = page.getSoccerPlayerFromList(1)
    page.addSoccerPlayerFromList(1)
    page.removeSoccerPlayerFromLineUp(1)

    if (resolution == Resolution.SMALL) {
      page.selectGoalKeeperOnMyTeam
    }

    page.setSoccerPlayerNameFilterSearch(playerOnList.name)
    page.getNumberOfSoccerPlayers must be (1)
  }

  def knownBugSequence_DuplicatedPlayersAtInsert(resolution:Resolution):Unit = {
    goToLobbyPage.playContestNumber(1)
    val page = new EnterContestPage(resolution)

    page.selectDefenseOnMyTeam(1)

    page.manyClicksOnAddSoccer(1)
    assert(page.getSoccerPlayerFromLineUp(3).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(4).isEmpty)
    assert(page.getSoccerPlayerFromLineUp(5).isEmpty)

  }


  private def pickWholeLineup_Expensive(resolution:Resolution):Unit = {
    new EnterContestPage(resolution)
      .selectGoalKeeperOnMyTeam
      .setSoccerPlayerNameFilterSearch("navas")
      .addSoccerPlayerFromList(1)

      .selectDefenseOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("gianc")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("zapata")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("armero")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(4)
      .setSoccerPlayerNameFilterSearch("yepes")
      .addSoccerPlayerFromList(1)

      .selectMiddleOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("james ro")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("borges")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("bola")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(4)
      .setSoccerPlayerNameFilterSearch("yelt")
      .addSoccerPlayerFromList(1)

      .selectForwardOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("bry")
      .addSoccerPlayerFromList(1)
      .selectForwardOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("geo")
      .addSoccerPlayerFromList(1)
    // left -34500
  }

  private def pickWholeLineup_Cheap(resolution:Resolution):Unit = {
    new EnterContestPage(resolution)
      .selectGoalKeeperOnMyTeam
      .setSoccerPlayerNameFilterSearch("stef")
      .addSoccerPlayerFromList(1)

      .selectDefenseOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("alb")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("way")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("heiner")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(4)
      .setSoccerPlayerNameFilterSearch("wri")
      .addSoccerPlayerFromList(1)

      .selectMiddleOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("calvo")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("fuen")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("troi")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(4)
      .setSoccerPlayerNameFilterSearch("luon")
      .addSoccerPlayerFromList(1)

      .selectForwardOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("ore")
      .addSoccerPlayerFromList(1)
      .selectForwardOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("pare")
      .addSoccerPlayerFromList(1)
    // left 40500
  }

  private def pickWholeLineup_ExpendAllMoney(resolution:Resolution):Unit = {
    new EnterContestPage(resolution)
      .selectGoalKeeperOnMyTeam
      .setSoccerPlayerNameFilterSearch("fer")
      .addSoccerPlayerFromList(1)

      .selectDefenseOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("gim")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("san")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("spira")
      .addSoccerPlayerFromList(1)
      .selectDefenseOnMyTeam(4)
      .setSoccerPlayerNameFilterSearch("fuc")
      .addSoccerPlayerFromList(1)

      .selectMiddleOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("marce")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("francisco")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(3)
      .setSoccerPlayerNameFilterSearch("laza")
      .addSoccerPlayerFromList(1)
      .selectMiddleOnMyTeam(4)
      .setSoccerPlayerNameFilterSearch("andre")
      .addSoccerPlayerFromList(1)

      .selectForwardOnMyTeam(1)
      .setSoccerPlayerNameFilterSearch("lui")
      .addSoccerPlayerFromList(1)
      .selectForwardOnMyTeam(2)
      .setSoccerPlayerNameFilterSearch("jack")
      .addSoccerPlayerFromList(1)
    // left 0
  }

}
