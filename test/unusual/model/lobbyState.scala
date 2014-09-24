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

    state.numContests_NoFilter = 636
    state.numContests_Free = 24
    state.numContests_League = 432
    state.numContests_FiftyFifty = 154
    state.numContests_HeadToHead = 36

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 510

    state.maxEntryMoney = 6

    state.filterPanel_SearchResults = Map(
      "vie., 13" -> 318,
      "jun" -> 636
    )

    state.contests = Contest.DEFAULT_LIST

    state
  }

}
