package unusual.tests.lobbyTest

import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
import org.scalatest.exceptions.StackDepthException
import org.scalatest.selenium.WebBrowser
import unusual.model.{LobbyState, Resolution}
import unusual.pages.components.{ContestDescriptionWindow, PaginatorControl}
import unusual.testTags.scala._
import unusual.tests._
import unusual.pages._
import unusual.tests.contestDescriptionTest.ContestDescriptionCommon

abstract class LobbyTestCommon(lobbySt: LobbyState, res:Resolution) extends SharedTest(res) {

  var lobbyState = lobbySt
  var _lobbyPageInstance:LobbyPage = null
  def lobbyPage:LobbyPage = {
    if(_lobbyPageInstance == null) {
      _lobbyPageInstance = goToLobbyPage(lobbyState)
    }
    _lobbyPageInstance
  }

  val N_CONTESTS_SEARCH = 0
  val FILTERS_PANEL_SEARCH_TEXT = "NingunTorneoDeberiaLlamarseAsi"


  /*
   * BASIC INFO TESTS
   */
  def goToLobby: Unit = {
    _lobbyPageInstance = null
    lobbyPage // inside is checked assert(page.isAt)
  }

  def lookForDefaultState: Unit = {
    eventually { assert(lobbyPage.isDefaultState(lobbyState.numContests_NoFilter), "Lobby is not at default state") }
  }

  def lookAtContestDescription: Unit = {
    if (status.resolution == Resolution.BIG){
      logger.debug("Looking at contest description")
      lobbyPage.openContestDescription(1)
      logger.debug("Contest description should be opened(1)")
      val description = new ContestDescriptionWindow(status.resolution)

      eventually { assert(description.isAt, "Description should be visible") }
      logger.debug("Description is visible")
      description.close
      logger.debug("Description should be closed")
      eventually { assert(!description.isAt, "Description should NOT be visible") }
      logger.debug("Description is closed")

      lobbyPage.openContestDescription(lobbyState.numContests_NoFilter)
      logger.debug("Last contest description should be opened(2)")
      eventually { assert(description.isAt, "Description should be visible") }
      logger.debug("Last description is visible")
      description.close
      logger.debug("Last description should be closed")
      eventually { assert(!description.isAt, "Description should NOT be visible") }
      logger.debug("Last description is closed")

    } else {
      featureNotTestableInResolution
    }
  }

  def changeResolutionTests: Unit = {
    val isDefaultSmall = () => new LobbyPage(Resolution.SMALL, lobbyState.maxEntryMoney).isDefaultState(lobbyState.numContests_NoFilter)
    val isDefaultMedium = () => new LobbyPage(Resolution.MEDIUM, lobbyState.maxEntryMoney).isDefaultState(lobbyState.numContests_NoFilter)
    val isDefaultBig = () => new LobbyPage(Resolution.BIG, lobbyState.maxEntryMoney).isDefaultState(lobbyState.numContests_NoFilter)

    if (status.resolution == Resolution.BIG) {

      goToLobbyPage(lobbyState)
      eventually { assert(isDefaultBig(), "Big is not default state") }

      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      eventually { assert(isDefaultMedium(), "From big to medium") }


      changeBrowserResolution(Resolution.BIG)
      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      changeBrowserResolution(Resolution.SMALL)
      eventually { assert(isDefaultSmall(), "From big to medium to small") }


      changeBrowserResolution(Resolution.BIG)
      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.SMALL)
      eventually { assert(isDefaultSmall(), "From big to small") }

    } else if (status.resolution == Resolution.MEDIUM) {

      goToLobbyPage(lobbyState)
      eventually { assert(isDefaultMedium(), "Medium is not default state") }

      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.BIG)
      eventually { assert(isDefaultMedium(), "From medium to big") }


      changeBrowserResolution(Resolution.MEDIUM)
      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.SMALL)
      eventually { assert(isDefaultSmall(), "From medium to small") }

    } else {

      goToLobbyPage(lobbyState)
      assert(isDefaultSmall(), "Small is not default state")

      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      eventually { assert(isDefaultMedium(), "From small to medium") }


      changeBrowserResolution(Resolution.SMALL)
      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      changeBrowserResolution(Resolution.BIG)
      eventually { assert(isDefaultBig(), "From small to medium to big") }


      changeBrowserResolution(Resolution.SMALL)
      reloadPage
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.BIG)
      eventually { assert(isDefaultBig(), "From small to big") }
      
    }
  }
  /*
   * END BASIC INFO TESTS
   */


  /*
   * FILTERS TESTS
   */
  def checkClearFiltersButton: Unit = {
    Given("a lobby page filters should be clear")
    assert(lobbyPage.areAllFiltersClear, "Filters are not clear")
    When("set up some filters that hide all contests")
    lobbyPage.searchContestByName(FILTERS_PANEL_SEARCH_TEXT)
    logger.debug("searched by name")
    lobbyPage.setEntryFeeFilter(0, 0)
    logger.debug("filter by entry fee 0 , 0")
    lobbyPage.clickLeagueContestFilter
    logger.debug("filter by league contest")
    lobbyPage.clickFiftyFiftyContestsFilter
    logger.debug("filter by fifty fifty")
    lobbyPage.clickHeadToHeadContestsFilter
    logger.debug("filter by head to head")
    assert(lobbyPage.getNumberOfContests == 0, "Number of contests is greater than 0")
    And("clear filters")
    lobbyPage.clearFilters
    Then("filters should be clear again")
    assert(lobbyPage.areAllFiltersClear, "Filters are not clear after clear filters")
    And("number of contest should be restored")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Filters are not cleared correctly or some contests disappeared.")
  }

  def filterByFreeContests: Unit = {
    lobbyPage.clickFreeContestFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Free, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Free}")
    lobbyPage.clearFilters
  }

  def filterByLeagueContests: Unit = {
    lobbyPage.clickLeagueContestFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_League, s"Num of contests: $numContests, expected: ${lobbyState.numContests_League}")
    lobbyPage.clearFilters
  }

  def filterByFiftyFiftyContests: Unit = {
    lobbyPage.clickFiftyFiftyContestsFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_FiftyFifty, s"Num of contests: $numContests, expected: ${lobbyState.numContests_FiftyFifty}")
    lobbyPage.clearFilters
  }

  def filterByHeadToHeadContests: Unit = {
    lobbyPage.clickHeadToHeadContestsFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_HeadToHead, s"Num of contests: $numContests, expected: ${lobbyState.numContests_HeadToHead}")
    lobbyPage.clearFilters
  }

  def filterByEntryFee: Unit = {
    lobbyPage.setEntryFeeFilter(lobbyState.minEntryFeeFilter, lobbyPage.MAX_ENTRY_MONEY)
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_MinEntryFee, s"Num of contests: $numContests, expected: ${lobbyState.numContests_MinEntryFee}")
    lobbyPage.clearFilters
  }

  def filterByBeginnerSalary: Unit = {
    lobbyPage.clickBeginnerSalaryFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Beginner, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Beginner}")
    lobbyPage.clearFilters
  }

  def filterByStandardSalary: Unit = {
    lobbyPage.clickStandardSalaryFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Standard, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Standard}")
    lobbyPage.clearFilters
  }

  def filterByExpertSalary: Unit = {
    lobbyPage.clickExpertSalaryFilter
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Expert, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Expert}")
    lobbyPage.clearFilters
  }

  def checkEntryFeeFilterCtrl: Unit = {
    val MAX = lobbyPage.MAX_ENTRY_MONEY
    val MIN = 0

    assert(lobbyPage.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(lobbyPage.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    lobbyPage.setEntryFeeFilter(1, MAX)
    assert(lobbyPage.getInferiorMoneyFilter == 1, "Inferior filter should be: " + 1)
    assert(lobbyPage.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    lobbyPage.clearFilters
    assert(lobbyPage.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(lobbyPage.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    lobbyPage.setEntryFeeFilter(MIN, 4)
    assert(lobbyPage.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(lobbyPage.getSuperiorMoneyFilter == 4, "Superior filter should be: " + 4)

    lobbyPage.clearFilters
    assert(lobbyPage.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(lobbyPage.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    lobbyPage.setEntryFeeFilter(1, 4)
    assert(lobbyPage.getInferiorMoneyFilter == 1, "Inferior filter should be: " + 1)
    assert(lobbyPage.getSuperiorMoneyFilter == 4, "Superior filter should be: " + 4)

    lobbyPage.setEntryFeeFilter(2, 3)
    assert(lobbyPage.getInferiorMoneyFilter == 2, "Inferior filter should be: " + 2)
    assert(lobbyPage.getSuperiorMoneyFilter == 3, "Superior filter should be: " + 3)

    lobbyPage.setEntryFeeFilter(4, 3)
    assert(lobbyPage.getInferiorMoneyFilter == 3, "Inferior filter should be: " + 3)
    assert(lobbyPage.getSuperiorMoneyFilter == 3, "Superior filter should be: " + 3)

    lobbyPage.setEntryFeeFilter(3, 2)
    assert(lobbyPage.getInferiorMoneyFilter == 3, "Inferior filter should be: " + 3)
    assert(lobbyPage.getSuperiorMoneyFilter == 3, "Superior filter should be: " + 3)

    lobbyPage.clearFilters
    assert(lobbyPage.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(lobbyPage.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

  }

  def filterByFreeContestsWithMinFilter: Unit = {
    lobbyPage.setEntryFeeFilter(1, lobbyPage.MAX_ENTRY_MONEY)
              .clickFreeContestFilter
              .getNumberOfContests must be(0)
    lobbyPage.clearFilters
  }

  def searchContests: Unit = {
    if (status.resolution != Resolution.SMALL) {
      for ((key,value) <- lobbyState.filterPanel_SearchResults) {
        logger.debug("Search key: " + key + " || contest should be: " + value)
        assert(lobbyPage.searchContestByName(key).getNumberOfContests == value, "search does not match")
      }
      lobbyPage.clearFilters
    } else {
      featureNotTestableInResolution
    }
  }

  def searchNonExistentContest: Unit = {
    if (status.resolution != Resolution.SMALL) {
      lobbyPage.searchContestByName(FILTERS_PANEL_SEARCH_TEXT)
               .getNumberOfContests must be(N_CONTESTS_SEARCH)
      lobbyPage.clearFilters
    } else {
      featureNotTestableInResolution
    }
  }
  /*
   * END FILTERS TESTS
   */


  /*
   * ORDER BY TESTS
   */
  def orderByName: Unit = {
    lobbyPage.clickSortContestsByName

    assert(lobbyPage.areContestsOrderedByName, "Contest are not sorted by name")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
  }

  def orderByEntryFee: Unit = {
    lobbyPage.clickSortContestsByEntryFee

    assert(lobbyPage.areContestsOrderedByEntryFee, "Contest are not sorted by entry fee")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
  }

  def orderByStartTime: Unit = {
    lobbyPage.clickSortContestsByStartTime
    logger.debug("Contest should be ordered by start time by default")
    assert(lobbyPage.areContestsOrderedByStartTime, "Contest are not sorted by name")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
  }

  def ReorderByStartTime: Unit = {
    logger.debug("Contest should be sorted by start time by default and should be at the same state after sort them two times")
    lobbyPage.clickSortContestsByStartTime.clickSortContestsByStartTime
    logger.debug("clicked two times in sort contest")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
    logger.debug("No contest disappeared during process")
    assert(lobbyPage.areContestsOrderedByStartTime, "Contest are not sorted by start time")
    logger.debug("Contest are still sorted by name")
  }
  /*
   * END ORDER BY TESTS
   */


  /*
   * OTHERS TESTS
   */
  def playFirstContest: Unit = {
    lobbyPage.playContestNumber(1)
  }

  def paginatorMainFunctionality: Unit = {
    goToLobbyPage(lobbyState)
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)
    assert( paginator.isAt, "Paginator is not at the page.")
    assert( paginator.isDisplayed, "Paginator is not displayed.")


    paginator.getNumberOfPages must be (lobbyState.maxPaginatorPage)
    paginator.getCurrentPage must be (1)
    paginator.goToLastPage.getCurrentPage must be (lobbyState.maxPaginatorPage)
    paginator.goToFirstPage.getCurrentPage must be (1)
    paginator.goToNextPage.getCurrentPage must be (2)
    paginator.goToNextPage.getCurrentPage must be (3)
    paginator.goToPreviousPage.getCurrentPage must be (2)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPage(4).getCurrentPage must be (4)

    paginator.goToPage(lobbyState.maxPaginatorPage).getCurrentPage must be (lobbyState.maxPaginatorPage)
    paginator.goToNextPage.getCurrentPage must be (lobbyState.maxPaginatorPage)
    paginator.goToNextPage.getCurrentPage must be (lobbyState.maxPaginatorPage)
    paginator.goToLastPage.getCurrentPage must be (lobbyState.maxPaginatorPage)

    paginator.goToPage(1).getCurrentPage must be (1)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToFirstPage.getCurrentPage must be (1)

    intercept [Exception] { paginator.goToPage(0) }
    intercept [Exception] { paginator.goToPage(-1) }
    intercept [Exception] { paginator.goToPage(lobbyState.maxPaginatorPage + 1) }
  }

  def paginatorIsDisplayedWhenNecessary: Unit = {
    lobbyPage
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)
    assert( paginator.isAt, "Paginator is not at the page.")
    assert( paginator.isDisplayed, "Paginator is not displayed.")
    paginator.getNumberOfPages must be (lobbyState.maxPaginatorPage)

    lobbyPage.searchContestByName(FILTERS_PANEL_SEARCH_TEXT)
             .setEntryFeeFilter(1, 1)
             .clickFreeContestFilter
    assert(!paginator.isDisplayed, "Paginator is displayed.")
    paginator.getNumberOfPages must be (1)
    lobbyPage.clearFilters
  }
  /*
   * END OTHERS TESTS
   */

  /*
   * BUG TESTS
   */
  def knownBugSequence_DisappearedPaginatorOnFilter: Unit = {
    logger.debug("go to lobby")
    lobbyPage
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)
    logger.debug("look at paginator")
    assert( paginator.isAt, "Paginator is not at the page.")
    assert( paginator.isDisplayed, "Paginator is not displayed.")
    logger.debug("go to last page")
    paginator.goToLastPage
    logger.debug("filter by free contest")
    lobbyPage.clickLeagueContestFilter
    logger.debug("paginator should not be displayed")
    assert( paginator.isDisplayed, "Paginator is not displayed.")
    logger.debug("look at current page")
    paginator.getCurrentPage must be (1)
    logger.debug("look at number of pages")
    assert(paginator.getNumberOfPages > 1, "Number of pages should be grater (or equals) than 1")
    lobbyPage.clearFilters
  }

  def knownBugSequence_PaginatorOrderedRefresh: Unit = {
    lobbyPage
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)
    paginator.goToNextPage.goToNextPage.goToNextPage.goToNextPage

    withClue("Paginator should not reset page at lobby contest-list refresh") {
      intercept [StackDepthException] {
        eventually (timeout(12 seconds)) { assert(paginator.getCurrentPage == 1) }
      }
    }
    lobbyPage.clearFilters
    /*
     * END BUG TESTS
     */
  }

/*
  private def goToLobbyContest:LobbyPage = {
    goToLobbyPage(lobbyState).clearFilters
  }
*/

/*
  def searchContest(): Unit = {
    val page = goToLobbyPage.clearFilters
      .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_1)
    if (resolution != Resolution.SMALL){
      page.checkNumberOfContests(N_CONTESTS_SEARCH_1)
    }
  }
*/


}
