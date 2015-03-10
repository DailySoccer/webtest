package unusual.tests.lobbyTest

import unusual.model.Resolution
import unusual.model.pageStates.LobbyState
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests.SharedTest

class LobbyVisitorTest(lobbySt:LobbyState, res:Resolution) extends LobbyTestCommon(lobbySt, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureVisitor
  }

  "As visitor" when sizeTesting({
      lobbyPageBehaviorBasicInfo
      lobbyPageBehaviorFilters
      lobbyPageBehaviorOrderBy
      lobbyPageBehaviorOthers
      lobbyPageBehaviorBUG
    })

  def lobbyPageBehaviorBasicInfo: Unit = {

    "watch basic information" which consistIn {

      "go to lobby logged out" in goToLobby

      "look for default state" in lookForDefaultState

      "change resolutions" in changeResolutionTests
    }
  }

  def lobbyPageBehaviorFilters: Unit = {

    "use filters" which filterBy {

      "clear filters control" in checkClearFiltersButton

      "free contests" in filterByFreeContests

      "league contests" in filterByLeagueContests

      "fifty fifty contests" in filterByFiftyFiftyContests

      "head to head contests" in filterByHeadToHeadContests

      "beginner salary" in filterByBeginnerSalary

      "standard salary" in filterByStandardSalary

      "expert salary" in filterByExpertSalary

      "free contests with min filter applied" in filterByFreeContestsWithMinFilter

      "entry fee (check filter is applied)" in filterByEntryFee

      "entry fee (check control functionality)" in checkEntryFeeFilterCtrl

      "(search) contest name" in searchContests

      "(search) non existent contest name" in searchNonExistentContest
    }
  }


  def lobbyPageBehaviorOrderBy: Unit = {

    "use order buttons" which orderBy {

      "order by name" in orderByName

      "order by entry fee" in orderByEntryFee

      "order by start time" in orderByStartTime

      "Reorder by start time" in ReorderByStartTime
    }
  }


  def lobbyPageBehaviorOthers: Unit = {
    "look at paginator" which {

      "check paginator main functionality" in paginatorMainFunctionality

      "see paginator displayed when necessary" in paginatorIsDisplayedWhenNecessary
    }
  }


  def lobbyPageBehaviorBUG: Unit = {

    "perform known BUG SEQUENCE" which causes {

      "Disappeared paginator on contests filter" in knownBugSequence_DisappearedPaginatorOnFilter

      "Paginator goes to first page after refresh ordered list of contest." in knownBugSequence_PaginatorOrderedRefresh
    }
  }


}
