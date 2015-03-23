package unusual.model.pageStates

import unusual.model.Contest
import unusual.pages.components.PaginatorControl


class LobbyState {

  var contests = new Array[Contest](0)
  var contestNames = Map(0 -> "")

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
    "" -> 0
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
      "sat, 14" -> 0,
      "jun" -> 39,
      "!!" -> 1
    )
    state.contestNames = Map(
      //1 -> "jue., 12 jun.!!",
      //2 -> "sat, 14 jun - 1"
    )

    state.contests = Contest.TIME_0_LIST

    state
  }
  var TIME_1_LOBBY = {
    val state = new LobbyState

    state.numContests_NoFilter = 37
    state.numContests_Free = 7
    state.numContests_League = 21
    state.numContests_FiftyFifty = 6
    state.numContests_HeadToHead = 3
    state.numContests_Beginner = 12
    state.numContests_Standard = 15
    state.numContests_Expert = 10

    state.minEntryFeeFilter = 2
    state.numContests_MinEntryFee = 24

    state.maxEntryMoney = 6

    state.filterPanel_SearchResults = Map(
      "sat, 14" -> 36,
      "jun" -> 37,
      "!!" -> 1
    )
    state.contestNames = Map(
      1 -> "jue., 12 jun.!!",
      2 -> "sat, 14 jun"
    )

    state.contests = Contest.TIME_1_LIST

    state
  }
}
