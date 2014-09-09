package unusual.tests.lobbyTest

import org.scalatest.exceptions.StackDepthException
import unusual.model.Resolution
import unusual.pages.components.PaginatorControl
import unusual.testTags.scala._
import unusual.tests._
import unusual.pages._

class LobbyTestCommon extends SharedTest {


  // NUMBER OF FILTERED CONTESTS
  val N_CONTESTS_NO_FILTER = 186
  val N_CONTESTS_FREE = 24
  val N_CONTESTS_LEAGUE = 108
  val N_CONTESTS_FIFTY_FIFTY = 36
  val N_CONTESTS_HEAD_TO_HEAD = 18
  val MIN_ENTRY_FEE_FILTER = 2
  val N_CONTESTS_ENTRY_FEE = 108

  val N_CONTESTS_SEARCH_1 = 93
  val FILTERS_PANEL_SEARCH_TEXT_1 = "vie., 13"

  val N_CONTESTS_SEARCH_2 = 0
  val FILTERS_PANEL_SEARCH_TEXT_2 = "NingunTorneoDeberiaLlamarseAsi"

  val N_CONTESTS_SEARCH_3 = 186
  val FILTERS_PANEL_SEARCH_TEXT_3 = "jun"

  val MAX_PAGINATOR_PAGE = 19

  def goToLobby(resolution:Resolution): Unit = {
    assert(goToLobbyPage.isDefaultState(N_CONTESTS_NO_FILTER))
  }

  def changeResolutionTests(resolution:Resolution): Unit = {
    val isDefaultSmall = () => new LobbyPage(Resolution.SMALL).isDefaultState(N_CONTESTS_NO_FILTER)
    val isDefaultMedium = () => new LobbyPage(Resolution.MEDIUM).isDefaultState(N_CONTESTS_NO_FILTER)
    val isDefaultBig = () => new LobbyPage(Resolution.BIG).isDefaultState(N_CONTESTS_NO_FILTER)

    if (resolution == Resolution.BIG) {

      goToLobbyPage
      assert(isDefaultBig(), "Big is not default state")

      reloadPage
      explicitChangeBrowserResolution(Resolution.MEDIUM)
      assert(isDefaultMedium(), "From big to medium")


      explicitChangeBrowserResolution(Resolution.BIG)
      reloadPage
      explicitChangeBrowserResolution(Resolution.MEDIUM)
      explicitChangeBrowserResolution(Resolution.SMALL)
      assert(isDefaultSmall(), "From big to medium to small")


      explicitChangeBrowserResolution(Resolution.BIG)
      reloadPage
      explicitChangeBrowserResolution(Resolution.SMALL)
      assert(isDefaultSmall(), "From big to small")

    } else if (resolution == Resolution.MEDIUM) {
      goToLobbyPage
      assert(isDefaultMedium(), "Medium is not default state")

      reloadPage
      explicitChangeBrowserResolution(Resolution.BIG)
      assert(isDefaultMedium(), "From medium to big")


      explicitChangeBrowserResolution(Resolution.MEDIUM)
      reloadPage
      explicitChangeBrowserResolution(Resolution.SMALL)
      assert(isDefaultSmall(), "From medium to small")

    } else {
      goToLobbyPage
      assert(isDefaultSmall(), "Small is not default state")

      reloadPage
      explicitChangeBrowserResolution(Resolution.MEDIUM)
      assert(isDefaultMedium(), "From small to medium")


      explicitChangeBrowserResolution(Resolution.SMALL)
      reloadPage
      explicitChangeBrowserResolution(Resolution.MEDIUM)
      explicitChangeBrowserResolution(Resolution.BIG)
      assert(isDefaultBig(), "From small to medium to big")


      explicitChangeBrowserResolution(Resolution.SMALL)
      reloadPage
      explicitChangeBrowserResolution(Resolution.BIG)
      assert(isDefaultBig(), "From small to big")
    }
  }

  def checkClearFiltersButton(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests

    if (resolution == Resolution.SMALL){
      page.clearFilters
    }

    assert(page.areAllFiltersClear)
    page.searchContestByName(FILTERS_PANEL_SEARCH_TEXT_2)
        .setEntryFeeFilter(4, 4)
        .clickFreeContestFilter
        .clickLeagueContestFilter
        .clickFiftyFiftyContestsFilter
        .clickHeadToHeadContestsFilter
        .getNumberOfContests must be(0)
    page.clearFilters
    assert(page.areAllFiltersClear)
    page.getNumberOfContests must be(N_CONTESTS_NO_FILTER)
  }

  def lookForDefaultContests(resolution:Resolution): Unit = {
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .getNumberOfContests must be(N_CONTESTS_NO_FILTER)
  }

  def filterByFreeContests(resolution:Resolution): Unit = {
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .clickFreeContestFilter
                 .getNumberOfContests must be(N_CONTESTS_FREE)
  }

  def filterByLeagueContests(resolution:Resolution): Unit = {
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .clickLeagueContestFilter
                 .getNumberOfContests must be(N_CONTESTS_LEAGUE)
  }

  def filterByFiftyFiftyContests(resolution:Resolution): Unit = {
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .clickFiftyFiftyContestsFilter
                 .getNumberOfContests must be(N_CONTESTS_FIFTY_FIFTY)
  }

  def filterByHeadToHeadContests(resolution:Resolution): Unit = {
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .clickHeadToHeadContestsFilter
                 .getNumberOfContests must be(N_CONTESTS_HEAD_TO_HEAD)
  }

  def filterByEntryFee(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
    page.setEntryFeeFilter(MIN_ENTRY_FEE_FILTER, page.MAX_ENTRY_MONEY)
        .getNumberOfContests must be(N_CONTESTS_ENTRY_FEE)
  }

  def checkEntryFeeFilterCtrl(resolution:Resolution): Unit = {
    val page = goToLobbyPage
    val MAX = page.MAX_ENTRY_MONEY
    val MIN = 0

    page.clickOnMenuAllContests
        .clickOnMenuFreeContests
        .clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.setEntryFeeFilter(1, MAX)
    assert(page.getInferiorMoneyFilter == 1)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.setEntryFeeFilter(MIN, 4)
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == 4)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.setEntryFeeFilter(1, 4)
    assert(page.getInferiorMoneyFilter == 1)
    assert(page.getSuperiorMoneyFilter == 4)

    page.setEntryFeeFilter(2, 3)
    assert(page.getInferiorMoneyFilter == 2)
    assert(page.getSuperiorMoneyFilter == 3)

    page.setEntryFeeFilter(4, 3)
    assert(page.getInferiorMoneyFilter == 3)
    assert(page.getSuperiorMoneyFilter == 3)

    page.setEntryFeeFilter(3, 2)
    assert(page.getInferiorMoneyFilter == 3)
    assert(page.getSuperiorMoneyFilter == 3)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

  }

  def filterByFreeContestsWithMinFilter(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters

    page.setEntryFeeFilter(1, page.MAX_ENTRY_MONEY)
        .clickFreeContestFilter
        .getNumberOfContests must be(0)
  }

  def playFirstContest(resolution:Resolution): Unit = {
    goToLobbyPage.clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .playContestNumber(1)
  }

  def searchContest(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
                            .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_1)
    if (resolution != Resolution.SMALL){
      page.getNumberOfContests must be(N_CONTESTS_SEARCH_1)
    }
  }

  def searchNonExistentContest(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
                            .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_2)
    if (resolution != Resolution.SMALL){
      page.getNumberOfContests must be(N_CONTESTS_SEARCH_2)
    }
  }

  def searchAnotherContest(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
                            .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_3)
    if (resolution != Resolution.SMALL){
      page.getNumberOfContests must be(N_CONTESTS_SEARCH_3)
    }
  }

  def orderByName(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
                            .clickSortContestsByName

    assert(page.getNumberOfContests == N_CONTESTS_NO_FILTER, "Contests disappeared during sort")
    assert(page.areContestsOrderedByName, "Contest are not sorted by name")
  }

  def orderByEntryFee(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
                            .clickSortContestsByEntryFee

    assert(page.getNumberOfContests == N_CONTESTS_NO_FILTER, "Contests disappeared during sort")
    assert(page.areContestsOrderedByEntryFee, "Contest are not sorted by entry fee")
  }

  def orderByStartTime(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
                            .clickSortContestsByStartTime

    assert(page.getNumberOfContests == N_CONTESTS_NO_FILTER, "Contests disappeared during sort")
    assert(page.areContestsOrderedByStartTime, "Contest are not sorted by start time")
  }

  /// PAGINATOR TESTS

  def paginatorMainFunctionality(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    assert(paginator.isAt)
    assert( paginator.isDisplayed )


    paginator.getNumberOfPages must be (MAX_PAGINATOR_PAGE)
    paginator.getCurrentPage must be (1)
    paginator.goToLastPage.getCurrentPage must be (MAX_PAGINATOR_PAGE)
    paginator.goToFirstPage.getCurrentPage must be (1)
    paginator.goToNextPage.getCurrentPage must be (2)
    paginator.goToNextPage.getCurrentPage must be (3)
    paginator.goToNextPage.getCurrentPage must be (4)
    paginator.goToPreviousPage.getCurrentPage must be (3)
    paginator.goToPreviousPage.getCurrentPage must be (2)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPage(10).getCurrentPage must be (10)

    paginator.goToPage(MAX_PAGINATOR_PAGE).getCurrentPage must be (MAX_PAGINATOR_PAGE)
    paginator.goToNextPage.getCurrentPage must be (MAX_PAGINATOR_PAGE)
    paginator.goToNextPage.getCurrentPage must be (MAX_PAGINATOR_PAGE)
    paginator.goToLastPage.getCurrentPage must be (MAX_PAGINATOR_PAGE)

    paginator.goToPage(1).getCurrentPage must be (1)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToFirstPage.getCurrentPage must be (1)

    intercept [Exception] { paginator.goToPage(0) }
    intercept [Exception] { paginator.goToPage(-1) }
    intercept [Exception] { paginator.goToPage(MAX_PAGINATOR_PAGE + 1) }

  }

  def paginatorIsDisplayedWhenNecessary(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    assert(paginator.isAt)
    assert(paginator.isDisplayed)
    paginator.getNumberOfPages must be (MAX_PAGINATOR_PAGE)

    page.setEntryFeeFilter(4, 4)
    assert(!paginator.isDisplayed)
    paginator.getNumberOfPages must be (1)
  }

  def knownBugSequence_DisappearedPaginatorOnFilter(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clearFilters
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    assert(paginator.isAt)
    assert(paginator.isDisplayed)
    paginator.goToLastPage
    page.clickFreeContestFilter
    assert(paginator.isDisplayed)
    paginator.getCurrentPage must be (1)
    paginator.getNumberOfPages must be (3)
  }

  def knownBugSequence_PaginatorOrderedRefresh(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clickOnMenuAllContests
                            .clickOnMenuFreeContests
                            .clickSortContestsByName
    val paginator = new PaginatorControl(resolution, page.CONTEST_LIST_CONTAINER)
    paginator.goToNextPage.goToNextPage.goToNextPage.goToNextPage

    intercept [StackDepthException] {
      eventually (timeout(12 seconds)) { assert(paginator.getCurrentPage == 1) }
    }

  }


/*
  def searchContest(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
      .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_1)
    if (resolution != Resolution.SMALL){
      page.checkNumberOfContests(N_CONTESTS_SEARCH_1)
    }
  }
*/


}
