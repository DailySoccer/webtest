package unusual.model

import unusual.pages.components.PaginatorControl


class LobbyState {

  var contests = new Array[Contest](0)

  var numContests_NoFilter = 0
  var numContests_Free = 0
  var numContests_League = 0
  var numContests_FiftyFifty = 0
  var numContests_HeadToHead = 0

  var minEntryFeeFilter = 0
  var numContests_MinEntryFee = 0

  var maxEntryMoney = 0

  var filterPanel_SearchResults = Map(
    "vie., 13" -> 0,
    "jun" -> 0
  )

  def maxPaginatorPage:Int = (numContests_NoFilter - 1) / PaginatorControl.ELEMENTS_PER_PAGE + 1

}


object LobbyState {


  // 12/06/14 08:00:39
  var DEFAULT_LOBBY = {
    val state = new LobbyState

    state.numContests_NoFilter = 1275
    state.numContests_Free = 51
    state.numContests_League = 864
    state.numContests_FiftyFifty = 288
    state.numContests_HeadToHead = 72

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 1020

    state.maxEntryMoney = 6

    state.filterPanel_SearchResults = Map(
      "vie., 13" -> 637,
      "jun" -> 1275
    )

    /*
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
    */
    state.contests = Contest.DEFAULT_LIST

    state
  }

/* Array({
    val contest = new Contest()
    contest.name = "jue., 12 jun."
    contest.description = "Free: 2 de 3 jugadores - LIM. SAL.: 90000"
    contest.entryFee = "0€"
    contest.prize = "0€"
    contest.id = "541ac0be300430b52c466ce3"
    contest.date = "Hoy"
    contest.numMatches = 3
    contest.numContestants = 2
    contest.numPrizes = 0

    contest.nameOrder = 2
    contest.entryFeeOrder = 1
    contest.startDateOrder = 1
    contest
  }, {
    val contest = new Contest()
    contest.name = "jue., 12 jun!!"
    contest.description = "Free: 3 de 4 jugadores - LIM. SAL.: 70000"
    contest.entryFee = "0€"
    contest.prize = "0€"
    contest.id = "541afb21300430b52c4691d0"
    contest.date = "13/06"
    contest.numMatches = 2
    contest.numContestants = 3
    contest.numPrizes = 0

    contest.nameOrder = 1
    contest.entryFeeOrder = 50
    contest.startDateOrder = 638
    contest
  }, {
    val contest = new Contest()
    contest.name = "jue., 12 jun. Perso1"
    contest.description = "Free: 4 de 5 jugadores - LIM. SAL.: 90000"
    contest.entryFee = "0€"
    contest.prize = "0€"
    contest.id = "541afb21300430b52c4691cb"
    contest.date = "Hoy"
    contest.numMatches = 5
    contest.numContestants = 4
    contest.numPrizes = 0

    contest.nameOrder = 638
    contest.entryFeeOrder = 49
    contest.startDateOrder = 637
    contest
  }
  )*/
}
