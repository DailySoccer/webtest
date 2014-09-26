package unusual.model

import unusual.pages.components.PaginatorControl


class LobbyState {

  var contests = new Array[Contest](0)

  var numContests_NoFilter = 0
  var numContests_Free = 0
  var numContests_League = 0
  var numContests_FiftyFifty = 0
  var numContests_HeadToHead = 0
  var numContests_Beginner = 0
  var numContests_Standard = 0
  var numContests_Expert = 0

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

    state.numContests_NoFilter = 639
    state.numContests_Free = 27
    state.numContests_League = 432
    state.numContests_FiftyFifty = 144
    state.numContests_HeadToHead = 36
    state.numContests_Beginner = 637
    state.numContests_Standard = 1
    state.numContests_Expert = 1

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 510

    state.maxEntryMoney = 6

    state.filterPanel_SearchResults = Map(
      "sáb., 14" -> 318,
      "jun" -> 639
    )

    state.contests = Contest.TIME_0_LIST

    state
  }

}