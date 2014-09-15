package unusual.tests.runner

import org.scalatest.Sequential
import unusual.model.Contest

import unusual.tests.contestDescriptionTest._
import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.homeTest._
import unusual.tests.menuTest._


class SequentialTestRunner extends Sequential(
  {
    val test = new ContestDescriptionAuthTest
    val contest = new Contest("vie., 13 jun.", "Free: 5 de 3 jugadores - LIM. SAL.: 90000", "0€", "0€")
    contest.numMatches = 3
    contest.numContestants = 5
    contest.numPrizes = 0
    test.contest = contest
    test.contestOrder = 1
    test
  }
  ,{
    val test = new ContestDescriptionAuthTest
    val contest = new Contest("sab., 14 jun.", "50/50: 9 de 10 jugadores - LIM. SAL.: 90000", "5€", "45€")
    contest.numMatches = 4
    contest.numContestants = 9
    contest.numPrizes = 5
    test.contest = contest
    test.contestOrder = 186
    test
  }
  ,new EnterContestAuthTest
  ,new EnterContestVisitorTest
  ,new LobbyAuthTest
  ,new LobbyVisitorTest
  ,new HomeAuthTest
  ,new HomeVisitorTest
  //new FutureJump,

  )