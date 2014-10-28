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

    state.numContests_NoFilter = 39
    state.numContests_Free = 9
    state.numContests_League = 21
    state.numContests_FiftyFifty = 6
    state.numContests_HeadToHead = 3
    state.numContests_Beginner = 13
    state.numContests_Standard = 16
    state.numContests_Expert = 10

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 24

    state.maxEntryMoney = 6

    state.filterPanel_SearchResults = Map(
      "sáb., 14" -> 0,
      "jun" -> 39,
      "!!" -> 1
    )

/*
    state.numContests_NoFilter = 321
    state.numContests_Free = 15
    state.numContests_League = 216
    state.numContests_FiftyFifty = 72
    state.numContests_HeadToHead = 18
    state.numContests_Beginner = 319
    state.numContests_Standard = 1
    state.numContests_Expert = 1

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 255

    state.maxEntryMoney = 6

    state.filterPanel_SearchResults = Map(
      "sáb., 14" -> 0,
      "jun" -> 321,
      "!!" -> 1
    )
*/
    state.contests = Contest.TIME_0_LIST

    state
  }

}
