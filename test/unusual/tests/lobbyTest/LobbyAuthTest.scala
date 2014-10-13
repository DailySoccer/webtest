package unusual.tests.lobbyTest

import unusual.model.{LobbyState, Resolution}
import unusual.pages.{HomePage, EnterContestPage, LobbyPage}
import unusual.testTags.scala._
import unusual.tests.contestDescriptionTest._


class LobbyAuthTest_BasicInfo(lobbySt: LobbyState) extends LobbyAuthTest(lobbySt){
  "(Basic Info) Auth user" when sizeTesting(enterContestPageBehaviorBasicInfo)
}

class LobbyAuthTest_Filters(lobbySt: LobbyState) extends LobbyAuthTest(lobbySt){
  "(Filters) Auth user" when sizeTesting(enterContestPageBehaviorFilters)
}

class LobbyAuthTest_OrderBy(lobbySt: LobbyState) extends LobbyAuthTest(lobbySt){
  "(Order By) Auth user" when sizeTesting(enterContestPageBehaviorOrderBy)
}

class LobbyAuthTest_Others(lobbySt: LobbyState) extends LobbyAuthTest(lobbySt){
  "(Others) Auth user" when sizeTesting(enterContestPageBehaviorOthers)
}

class LobbyAuthTest_Bug(lobbySt: LobbyState) extends LobbyAuthTest(lobbySt){
  "(BUG) Auth user" when sizeTesting(enterContestPageBehaviorBUG)
}


/**
 * No lo vamos a usar a priori
 */
class LobbyAuthTest_All(lobbySt: LobbyState) extends LobbyAuthTest(lobbySt){
  "(Basic Info)Auth user" when sizeTesting(enterContestPageBehaviorBasicInfo)

  "(Filters)Auth user" when sizeTesting(enterContestPageBehaviorFilters)

  "(Order By)Auth user" when sizeTesting(enterContestPageBehaviorOrderBy)

  "(Others)Auth user" when sizeTesting(enterContestPageBehaviorOthers)

  "(BUG)Auth user" when sizeTesting(enterContestPageBehaviorBUG)
}

abstract class LobbyAuthTest(lobbySt: LobbyState) extends LobbyTestCommon(lobbySt) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  def enterContestPageBehaviorBasicInfo(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "watch basic information" which consistIn {

      "to be logged in" in goToLobby

      "change resolutions" in changeResolutionTests

      "look for default contests" in lookForDefaultContests

      "look at contest description" taggedAs WIP in lookAtContestDescription
    }
  }


  def enterContestPageBehaviorFilters(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "use filters" which filterBy {

      "clear filters control" in checkClearFiltersButton

      "free contests" in filterByFreeContests

      "league contests" in filterByLeagueContests

      "fifty fifty contests" in filterByFiftyFiftyContests

      "head to head contests" in filterByHeadToHeadContests

      "free contests with min filter applied" in filterByFreeContestsWithMinFilter

      "entry fee (check filter is applied)" in filterByEntryFee

      "entry fee (check control funtionallity)" in checkEntryFeeFilterCtrl

      "(search) contest name" in searchContests

      "(search) non existent contest name" in searchNonExistentContest
    }
  }


  def enterContestPageBehaviorOrderBy(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "use order buttons" which orderBy {

      "order by name" in orderByName

      "order by entry fee" in orderByEntryFee

      "order by start time" in orderByStartTime

      "Reorder by start time" in ReorderByStartTime
    }
  }


  def enterContestPageBehaviorOthers(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "press play button of first contest to select team" in playFirstContest

    "look at paginator" which {

      "check paginator main functionality" in paginatorMainFunctionality

      "see paginator displayed when necessary" in paginatorIsDisplayedWhenNecessary
    }
  }


  def enterContestPageBehaviorBUG(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "perform known BUG SEQUENCE" which causes {

      "Disappeared paginator on contests filter" in knownBugSequence_DisappearedPaginatorOnFilter

      "Paginator goes to first page after refresh ordered list of contest." in knownBugSequence_PaginatorOrderedRefresh
    }
  }



  def enterContestPageBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "watch basic information" which consistIn {

      "to be logged in" in goToLobby

      "change resolutions" in changeResolutionTests

      "look for default contests" in lookForDefaultContests

      "look at contest description" taggedAs WIP in lookAtContestDescription
    }

    "use filters" which filterBy {

      "clear filters control" in checkClearFiltersButton

      "free contests" in filterByFreeContests

      "league contests" in filterByLeagueContests

      "fifty fifty contests" in filterByFiftyFiftyContests

      "head to head contests" in filterByHeadToHeadContests

      "free contests with min filter applied" in filterByFreeContestsWithMinFilter

      "entry fee (check filter is applied)" in filterByEntryFee

      "entry fee (check control funtionallity)" in checkEntryFeeFilterCtrl

      "(search) contest name" in searchContests

      "(search) non existent contest name" in searchNonExistentContest
    }

    "use order buttons" which orderBy {

      "order by name" in orderByName

      "order by entry fee" in orderByEntryFee

      "order by start time" in orderByStartTime

      "Reorder by start time" in ReorderByStartTime
    }

    "press play button of first contest to select team" in playFirstContest

    "look at paginator" which {

      "check paginator main functionality" in paginatorMainFunctionality

      "see paginator displayed when necessary" in paginatorIsDisplayedWhenNecessary
    }

    "perform known BUG SEQUENCE" which causes {

      "Disappeared paginator on contests filter" in knownBugSequence_DisappearedPaginatorOnFilter

      "Paginator goes to first page after refresh ordered list of contest." in knownBugSequence_PaginatorOrderedRefresh
    }

  }

}
