package unusual.tests.runner

import org.scalatest.Sequential
import unusual.model.Contest
import unusual.model.LobbyState

import unusual.tests.contestDescriptionTest._
import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.homeTest._
import unusual.tests.menuTest._


class SequentialTestRunner extends Sequential(
  {
    val test = new LobbyAuthTest
    val state = new LobbyState
    state.numContests_NoFilter = 672
    state.numContests_Free = 24
    state.numContests_League = 432
    state.numContests_FiftyFifty = 144
    state.numContests_HeadToHead = 72

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 540

    state.filterPanel_SearchResults = Map(
      "vie., 13" -> 336,
      "jun" -> 672
    )

    test.lobbyState = state
    test
  }
  /*{
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