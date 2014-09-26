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

class LobbyTestCommon(lobbySt: LobbyState) extends SharedTest {

  var lobbyState = lobbySt


  val N_CONTESTS_SEARCH = 0
  val FILTERS_PANEL_SEARCH_TEXT = "NingunTorneoDeberiaLlamarseAsi"


  def goToLobby(implicit resolution:Resolution): Unit = {
    val page = goToLobbyPage(lobbyState)
    assert(page.isDefaultState(lobbyState.numContests_NoFilter), "Lobby is not at default state")
  }

  def changeResolutionTests(implicit resolution:Resolution): Unit = {
    val isDefaultSmall = () => new LobbyPage(Resolution.SMALL, lobbyState.maxEntryMoney).isDefaultState(lobbyState.numContests_NoFilter)
    val isDefaultMedium = () => new LobbyPage(Resolution.MEDIUM, lobbyState.maxEntryMoney).isDefaultState(lobbyState.numContests_NoFilter)
    val isDefaultBig = () => new LobbyPage(Resolution.BIG, lobbyState.maxEntryMoney).isDefaultState(lobbyState.numContests_NoFilter)

    if (resolution == Resolution.BIG) {

      goToLobbyPage(lobbyState)
      assert(isDefaultBig(), "Big is not default state")

      reloadPage
      changeBrowserResolution(Resolution.MEDIUM)
      assert(isDefaultMedium(), "From big to medium")


      changeBrowserResolution(Resolution.BIG)
      reloadPage
      changeBrowserResolution(Resolution.MEDIUM)
      changeBrowserResolution(Resolution.SMALL)
      assert(isDefaultSmall(), "From big to medium to small")


      changeBrowserResolution(Resolution.BIG)
      reloadPage
      changeBrowserResolution(Resolution.SMALL)
      assert(isDefaultSmall(), "From big to small")

    } else if (resolution == Resolution.MEDIUM) {

      goToLobbyPage(lobbyState)
      assert(isDefaultMedium(), "Medium is not default state")

      reloadPage
      changeBrowserResolution(Resolution.BIG)
      assert(isDefaultMedium(), "From medium to big")


      changeBrowserResolution(Resolution.MEDIUM)
      reloadPage
      changeBrowserResolution(Resolution.SMALL)
      assert(isDefaultSmall(), "From medium to small")

    } else {

      goToLobbyPage(lobbyState)
      assert(isDefaultSmall(), "Small is not default state")

      reloadPage
      changeBrowserResolution(Resolution.MEDIUM)
      assert(isDefaultMedium(), "From small to medium")


      changeBrowserResolution(Resolution.SMALL)
      reloadPage
      changeBrowserResolution(Resolution.MEDIUM)
      changeBrowserResolution(Resolution.BIG)
      assert(isDefaultBig(), "From small to medium to big")


      changeBrowserResolution(Resolution.SMALL)
      reloadPage
      changeBrowserResolution(Resolution.BIG)
      assert(isDefaultBig(), "From small to big")

    }
  }

  def checkClearFiltersButton(implicit resolution:Resolution): Unit = {
    val page = goToLobbyPage(lobbyState).clickOnMenuAllContests
                                        .clickOnMenuFreeContests

    if (resolution == Resolution.SMALL){
      page.clearFilters
    }

    assert(page.areAllFiltersClear, "Filters are not clear")
    page.searchContestByName(FILTERS_PANEL_SEARCH_TEXT)
        .setEntryFeeFilter(0, 0)
        .clickLeagueContestFilter
        .clickFiftyFiftyContestsFilter
        .clickHeadToHeadContestsFilter
        .getNumberOfContests must be(0)
    page.clearFilters
    assert(page.areAllFiltersClear, "Filters are not clear after clear filters")
    page.getNumberOfContests must be(lobbyState.numContests_NoFilter)
  }

  def lookAtContestDescription(implicit resolution:Resolution): Unit = {
    if (resolution == Resolution.BIG){
      val page = goToLobbyContest.openContestDescription(1)
      val description = new ContestDescriptionWindow(resolution)

      eventually { assert(description.isAt, "Description should be visible") }
      description.close
      eventually { assert(!description.isAt, "Description should NOT be visible") }

      page.openContestDescription(lobbyState.numContests_NoFilter)
      eventually { assert(description.isAt, "Description should be visible") }
      description.close
      eventually { assert(!description.isAt, "Description should NOT be visible") }

    } else {
      featureNotTestableInResolution
    }
  }

  def lookForDefaultContests(implicit resolution:Resolution): Unit = {
    goToLobbyContest.getNumberOfContests must be(lobbyState.numContests_NoFilter)
  }

  def filterByFreeContests(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickFreeContestFilter
                 .getNumberOfContests must be(lobbyState.numContests_Free)
  }

  def filterByLeagueContests(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickLeagueContestFilter
                 .getNumberOfContests must be(lobbyState.numContests_League)
  }

  def filterByFiftyFiftyContests(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickFiftyFiftyContestsFilter
                 .getNumberOfContests must be(lobbyState.numContests_FiftyFifty)
  }

  def filterByHeadToHeadContests(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickHeadToHeadContestsFilter
                 .getNumberOfContests must be(lobbyState.numContests_HeadToHead)
  }

  def filterByEntryFee(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest
    page.setEntryFeeFilter(lobbyState.minEntryFeeFilter, page.MAX_ENTRY_MONEY)
      .getNumberOfContests must be(lobbyState.numContests_MinEntryFee)
  }

  def filterByBeginnerSalary(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickBeginnerSalaryFilter
                    .getNumberOfContests must be(lobbyState.numContests_Beginner)
  }

  def filterByStandardSalary(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickStandardSalaryFilter
                    .getNumberOfContests must be(lobbyState.numContests_Standard)
  }

  def filterByExpertSalary(implicit resolution:Resolution): Unit = {
    goToLobbyContest.clickExpertSalaryFilter
                    .getNumberOfContests must be(lobbyState.numContests_Expert)
  }

  def checkEntryFeeFilterCtrl(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest
    val MAX = page.MAX_ENTRY_MONEY
    val MIN = 0

    assert(page.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(page.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    page.setEntryFeeFilter(1, MAX)
    assert(page.getInferiorMoneyFilter == 1, "Inferior filter should be: " + 1)
    assert(page.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(page.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    page.setEntryFeeFilter(MIN, 4)
    assert(page.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(page.getSuperiorMoneyFilter == 4, "Superior filter should be: " + 4)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(page.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

    page.setEntryFeeFilter(1, 4)
    assert(page.getInferiorMoneyFilter == 1, "Inferior filter should be: " + 1)
    assert(page.getSuperiorMoneyFilter == 4, "Superior filter should be: " + 4)

    page.setEntryFeeFilter(2, 3)
    assert(page.getInferiorMoneyFilter == 2, "Inferior filter should be: " + 2)
    assert(page.getSuperiorMoneyFilter == 3, "Superior filter should be: " + 3)

    page.setEntryFeeFilter(4, 3)
    assert(page.getInferiorMoneyFilter == 3, "Inferior filter should be: " + 3)
    assert(page.getSuperiorMoneyFilter == 3, "Superior filter should be: " + 3)

    page.setEntryFeeFilter(3, 2)
    assert(page.getInferiorMoneyFilter == 3, "Inferior filter should be: " + 3)
    assert(page.getSuperiorMoneyFilter == 3, "Superior filter should be: " + 3)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN, "Inferior filter should be: " + MIN)
    assert(page.getSuperiorMoneyFilter == MAX, "Superior filter should be: " + MAX)

  }

  def filterByFreeContestsWithMinFilter(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest

    page.setEntryFeeFilter(1, page.MAX_ENTRY_MONEY)
        .clickFreeContestFilter
        .getNumberOfContests must be(0)
  }

  def playFirstContest(implicit resolution:Resolution): Unit = {
    goToLobbyContest.playContestNumber(1)
  }

  def searchContests(implicit resolution:Resolution): Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToLobbyContest
      for ((key,value) <- lobbyState.filterPanel_SearchResults) {
        logger.debug("Search key: " + key + " || contest should be: " + value)
        assert(page.searchContestByName(key).getNumberOfContests == value, "search does not match")
      }
    }
  }

  def searchNonExistentContest(implicit resolution:Resolution): Unit = {
    if (resolution != Resolution.SMALL) {
      val page = goToLobbyContest.searchContestByName(FILTERS_PANEL_SEARCH_TEXT)
      page.getNumberOfContests must be(N_CONTESTS_SEARCH)
    }
  }

  def orderByName(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest
    assert(page.areContestsOrderedByName, "Contest are not sorted by name")

    page.clickSortContestsByName.clickSortContestsByName
    assert(page.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
    assert(page.areContestsOrderedByName, "Contest are not sorted by name")
  }

  def orderByEntryFee(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest.clickSortContestsByEntryFee

    assert(page.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
    assert(page.areContestsOrderedByEntryFee, "Contest are not sorted by entry fee")
  }

  def orderByStartTime(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest.clickSortContestsByStartTime

    assert(page.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
    assert(page.areContestsOrderedByStartTime, "Contest are not sorted by start time")
  }

  /// PAGINATOR TESTS

  def paginatorMainFunctionality(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    assert( paginator.isAt, "Paginator is not at the page.")
    assert( paginator.isDisplayed, "Paginator is not displayed.")


    paginator.getNumberOfPages must be (lobbyState.maxPaginatorPage)
    paginator.getCurrentPage must be (1)
    paginator.goToLastPage.getCurrentPage must be (lobbyState.maxPaginatorPage)
    paginator.goToFirstPage.getCurrentPage must be (1)
    paginator.goToNextPage.getCurrentPage must be (2)
    paginator.goToNextPage.getCurrentPage must be (3)
    paginator.goToNextPage.getCurrentPage must be (4)
    paginator.goToPreviousPage.getCurrentPage must be (3)
    paginator.goToPreviousPage.getCurrentPage must be (2)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPage(10).getCurrentPage must be (10)

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

  def paginatorIsDisplayedWhenNecessary(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    assert( paginator.isAt, "Paginator is not at the page.")
    assert( paginator.isDisplayed, "Paginator is not displayed.")
    paginator.getNumberOfPages must be (lobbyState.maxPaginatorPage)

    page.searchContestByName(FILTERS_PANEL_SEARCH_TEXT)
        .setEntryFeeFilter(1, 1)
        .clickFreeContestFilter
    assert(!paginator.isDisplayed, "Paginator is displayed.")
    paginator.getNumberOfPages must be (1)
  }

  def knownBugSequence_DisappearedPaginatorOnFilter(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    assert( paginator.isAt, "Paginator is not at the page.")
    assert( paginator.isDisplayed, "Paginator is not displayed.")
    paginator.goToLastPage
    page.clickFreeContestFilter
    assert( paginator.isDisplayed, "Paginator is not displayed.")
    paginator.getCurrentPage must be (1)
    assert(paginator.getNumberOfPages >= 1, "Number of pages should be grater (or equals) than 1")
  }

  def knownBugSequence_PaginatorOrderedRefresh(implicit resolution:Resolution): Unit = {
    val page = goToLobbyContest.clickSortContestsByName
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    paginator.goToNextPage.goToNextPage.goToNextPage.goToNextPage

    withClue("Paginator should not reset page at lobby contest-list refresh") {
      intercept [StackDepthException] {
        eventually (timeout(12 seconds)) { assert(paginator.getCurrentPage == 1) }
      }
    }

  }


  private def goToLobbyContest:LobbyPage = {
    goToLobbyPage(lobbyState).clickOnMenuAllContests.clickOnMenuFreeContests.clearFilters
  }


/*
  def searchContest(implicit resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
      .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_1)
    if (resolution != Resolution.SMALL){
      page.checkNumberOfContests(N_CONTESTS_SEARCH_1)
    }
  }
*/


}
