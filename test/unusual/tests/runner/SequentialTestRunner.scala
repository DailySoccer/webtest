package unusual.tests.runner

import org.scalatest.Sequential
import unusual.model.{ViewContestState, EnterContestState, LobbyState, Contest}

import unusual.tests.contestDescriptionTest._
import unusual.tests.enterContestTest.EnterContestAuthTest
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController._
import unusual.tests.viewContestEntryTest.ViewContestAuthTest


class SequentialTestRunner extends Sequential(
  new InitializerTest
  , new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  //, new ContestDescriptionAuthTest(Contest.TIME_0_LIST(0))
  //, new EnterContestAuthTest(EnterContestState.TIME_0_LIST(0))
  //, new EnterContestAuthTest(EnterContestState.TIME_0_LIST(1))
  //, new EnterContestAuthTest(EnterContestState.TIME_0_LIST(2))
  //, new ViewContestAuthTest(ViewContestState.TIME_0_LIST(0))
  /*{
    val test = new ContestDescriptionAuthTest
    test.contest = Contest.DEFAULT_LIST(0)
    test
  }, {
    val test = new ContestDescriptionAuthTest
    test.contest = Contest.DEFAULT_LIST(1)
    test
  }, {
    val test = new ContestDescriptionAuthTest
    test.contest = Contest.DEFAULT_LIST(2)
    test
  }, {
  val test = new LobbyAuthTest
  test.lobbyState = LobbyState.DEFAULT_LOBBY
  test
  }, {
    val test = new EnterContestAuthTest
    test.enterContestState.contest = Contest.DEFAULT_LIST(0)
    test
  }, {
    val test = new EnterContestAuthTest
    test.enterContestState.contest = Contest.DEFAULT_LIST(1)
    test
  }, {
    val test = new EnterContestAuthTest
    test.enterContestState.contest = Contest.DEFAULT_LIST(2)
    test
  }, *//*, {
    val test = new TimeShiftTest(15,6,2014,0,0)
    test
  }*//*
  {
    val test = new ContestDescriptionAuthTest
    val contest = new Contest()
    contest.name = "vie., 13 jun."
    contest.description = "Free: 5 de 3 jugadores - LIM. SAL.: 90000"
    contest.entryFee = "0€"
    contest.prize = "0€"
    contest.numMatches = 3
    contest.numContestants = 5
    contest.numPrizes = 0
    contest.id = "540d4d1330045601813966c9"
    test.contest = contest
    test.contestOrder = 1
    test
  }
  ,{
    val test = new ContestDescriptionAuthTest
    val contest = new Contest()
    contest.name = "sáb., 14 jun."
    contest.description = "50/50: 9 de 10 jugadores - LIM. SAL.: 90000"
    contest.entryFee = "5€"
    contest.prize = "45€"
    contest.numMatches = 4
    contest.numContestants = 9
    contest.numPrizes = 5
    contest.id = "540d4d1d3004560181396ab4"
    test.contest = contest
    test.contestOrder = 186
    test
  }
  ,new EnterContestAuthTest
  ,new EnterContestVisitorTest
  ,new LobbyVisitorTest
  ,new HomeAuthTest
  ,new HomeVisitorTest */
  //new FutureJump,

  )