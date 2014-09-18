package unusual.model

import unusual.pages.components.PaginatorControl


class LobbyState {

  var contests = List()

  var numContests_NoFilter = 186
  var numContests_Free = 24
  var numContests_League = 108
  var numContests_FiftyFifty = 36
  var numContests_HeadToHead = 18

  var minEntryFeeFilter = 2
  var numContests_MinEntryFee = 108

  var filterPanel_SearchResults = Map(
    "vie., 13" -> 93,
    "jun" -> 186
  )

  def maxPaginatorPage = (numContests_NoFilter - 1 / PaginatorControl.ELEMENTS_PER_PAGE) + 1

}
