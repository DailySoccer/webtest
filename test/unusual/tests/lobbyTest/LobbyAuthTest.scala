package unusual.tests.lobbyTest

import unusual.model.{Contest, Resolution}
import unusual.model.pageStates.LobbyState
import unusual.pages.{HomePage, EnterContestPage, LobbyPage}
import unusual.tests.contestDescriptionTest._
import unusual.tests.runner.SequentialTestRunner


class LobbyAuthTest_All(lobbySt: LobbyState, res:Resolution) extends LobbyAuthTest(lobbySt, res){
  "Auth user" when sizeTesting({
      lobbyPageBehaviorBasicInfo
      lobbyPageBehaviorFilters
      lobbyPageBehaviorOrderBy
      lobbyPageBehaviorOthers
      lobbyPageBehaviorBUG
    })

}

abstract class LobbyAuthTest(lobbySt: LobbyState, res:Resolution) extends LobbyTestCommon(lobbySt, res) {

  //val contestDescription: ContestDescriptionWindowCommon = new ContestDescriptionWindowCommon(cont, res)

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  def lobbyPageBehaviorBasicInfo: Unit = {

    "watch basic information" which consistIn {

      "go to lobby logged in" in goToLobby

      "look for default state" in lookForDefaultState
    }
  }

  def lobbyPageBehaviorFilters: Unit = {

    "use filters" which filterBy {

      //"clear filters control" in checkClearFiltersButton

      "free contests" in filterByFreeContests

      /*"league contests" in filterByLeagueContests

      "fifty fifty contests" in filterByFiftyFiftyContests

      "head to head contests" in filterByHeadToHeadContests

      "beginner salary" in filterByBeginnerSalary

      "standard salary" in filterByStandardSalary

      "expert salary" in filterByExpertSalary

      "free contests with min filter applied" in filterByFreeContestsWithMinFilter

      //"entry fee (check filter is applied)" in filterByEntryFee

      //"entry fee (check control functionality)" in checkEntryFeeFilterCtrl

      //"(search) contest name" in searchContests

      //"(search) non existent contest name" in searchNonExistentContest
      */
    }
  }


  def lobbyPageBehaviorOrderBy: Unit = {

    "use order buttons" which orderBy {
      /*
      "order by name" in orderByName

      "order by entry fee" in orderByEntryFee

      "order by start time" in orderByStartTime
      */
      "Reorder by start time" in ReorderByStartTime
    }
  }


  def lobbyPageBehaviorOthers: Unit = {
    /*"look at paginator" which {

      //"check paginator main functionality" in paginatorMainFunctionality

      //"see paginator displayed when necessary" in paginatorIsDisplayedWhenNecessary
    }*/
  }


  def lobbyPageBehaviorBUG: Unit = {
    /*
    "perform known BUG SEQUENCE" which causes {

      "Disappeared paginator on contests filter" in knownBugSequence_DisappearedPaginatorOnFilter

      "Paginator goes to first page after refresh ordered list of contest." in knownBugSequence_PaginatorOrderedRefresh
    }
    */
  }

}
