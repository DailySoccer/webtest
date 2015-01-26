package unusual.tests.lobbyTest

import unusual.model.Resolution
import unusual.model.pageStates.LobbyState
import unusual.pages.{HomePage, EnterContestPage, LobbyPage}
import unusual.testTags.scala._
import unusual.tests.contestDescriptionTest._


class LobbyAuthTest_BasicInfo(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "(Basic Info) Auth user" when sizeTesting(lobbyPageBehaviorBasicInfo)
}

class LobbyAuthTest_Filters(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "(Filters) Auth user" when sizeTesting(lobbyPageBehaviorFilters)
}

class LobbyAuthTest_OrderBy(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "(Order By) Auth user" when sizeTesting(lobbyPageBehaviorOrderBy)
}

class LobbyAuthTest_Others(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "(Others) Auth user" when sizeTesting(lobbyPageBehaviorOthers)
}

class LobbyAuthTest_Bug(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "(BUG) Auth user" when sizeTesting(lobbyPageBehaviorBUG)
}


/**
 * No lo vamos a usar a priori
 */
class LobbyAuthTest_All(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "Auth user" when {
    sizeTesting({
      lobbyPageBehaviorBasicInfo
      lobbyPageBehaviorFilters
      lobbyPageBehaviorOrderBy
      lobbyPageBehaviorOthers
      lobbyPageBehaviorBUG
    })
/*
    sizeTesting(lobbyPageBehaviorFilters)

    sizeTesting(lobbyPageBehaviorOrderBy)

    sizeTesting(lobbyPageBehaviorOthers)

    sizeTesting(lobbyPageBehaviorBUG)*/
  }
/*
  "(Filters)Auth user" when sizeTesting(lobbyPageBehaviorFilters)

  "(Order By)Auth user" when sizeTesting(lobbyPageBehaviorOrderBy)

  "(Others)Auth user" when sizeTesting(lobbyPageBehaviorOthers)

  "(BUG)Auth user" when sizeTesting(lobbyPageBehaviorBUG)*/
}

abstract class LobbyAuthTest(lobbySt: LobbyState, res:Resolution) extends LobbyTestCommon(lobbySt, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  def lobbyPageBehaviorBasicInfo: Unit = {

    "watch basic information" which consistIn {

      //"go to lobby logged in" in goToLobby

      //"look for default state" in lookForDefaultState

      //"look at contest description" in lookAtContestDescription

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


  /**
   * Borrar si finalmente se lanzan individualmente
   * @deprecated
   */
  /*def lobbyPageBehavior: Unit = {
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

  }*/

}
