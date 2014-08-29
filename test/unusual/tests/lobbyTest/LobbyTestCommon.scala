package unusual.tests.lobbyTest

import unusual.model.Resolution
import unusual.testTags.scala._
import unusual.tests._
import unusual.pages._

class LobbyTestCommon extends SharedTest {


  // NUMBER OF FILTERED CONTESTS
  val N_CONTESTS_NO_FILTER = 132
  val N_CONTESTS_FREE = 24
  val N_CONTESTS_LEAGUE = 72
  val N_CONTESTS_FIFTY_FIFTY = 24
  val N_CONTESTS_HEAD_TO_HEAD = 12
  val N_CONTESTS_ENTRY_FEE = 54
  val N_CONTESTS_SEARCH_1 = 66
  val FILTERS_PANEL_SEARCH_TEXT_1 = "2014/06/14"

  def checkClearFiltersButton(resolution:Resolution): Unit = {
    val page = goToLobbyPage.checkAllFiltersAreClear
                 .searchContestByName("NingunTorneoDeberiaLlamarseAsi")
                 .setEntryFeeFilter(80, 90)
                 .clickFreeContestFilter
                 .clickLeagueContestFilter
                 .clickFiftyFiftyContestsFilter
                 .clickHeadToHeadContestsFilter
                 .checkNumberOfContests(0)
                 .clearFilters
                 .checkAllFiltersAreClear
                 .checkNumberOfContests(N_CONTESTS_NO_FILTER)
  }

  def lookForDefaultContests(resolution:Resolution): Unit = {
    goToLobbyPage.clearFilters
                 .checkNumberOfContests(N_CONTESTS_NO_FILTER)
  }

  def filterByFreeContests(resolution:Resolution): Unit = {
    goToLobbyPage.clearFilters
                 .clickFreeContestFilter
                 .checkNumberOfContests(N_CONTESTS_FREE)
  }

  def filterByLeagueContests(resolution:Resolution): Unit = {
    goToLobbyPage.clearFilters
                 .clickLeagueContestFilter
                 .checkNumberOfContests(N_CONTESTS_LEAGUE)
  }

  def filterByFiftyFiftyContests(resolution:Resolution): Unit = {
    goToLobbyPage.clearFilters
                 .clickFiftyFiftyContestsFilter
                 .checkNumberOfContests(N_CONTESTS_FIFTY_FIFTY)
  }

  def filterByHeadToHeadContests(resolution:Resolution): Unit = {
    goToLobbyPage.clearFilters
                 .clickHeadToHeadContestsFilter
                 .checkNumberOfContests(N_CONTESTS_HEAD_TO_HEAD)
  }

  def filterByEntryFee(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
    page.setEntryFeeFilter(2, page.MAX_ENTRY_MONEY)
        .checkNumberOfContests(N_CONTESTS_ENTRY_FEE)
  }

  def checkEntryFeeFilterCtrl(resolution:Resolution): Unit = {
    val page = goToLobbyPage
    val MAX = page.MAX_ENTRY_MONEY
    val MIN = 0

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.setEntryFeeFilter(25, MAX)
    assert(page.getInferiorMoneyFilter == 25)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.setEntryFeeFilter(MIN, 75)
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == 75)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

    page.setEntryFeeFilter(25, 75)
    assert(page.getInferiorMoneyFilter == 25)
    assert(page.getSuperiorMoneyFilter == 75)

    page.setEntryFeeFilter(40, 60)
    assert(page.getInferiorMoneyFilter == 40)
    assert(page.getSuperiorMoneyFilter == 60)

    page.setEntryFeeFilter(70, 60)
    assert(page.getInferiorMoneyFilter == 60)
    assert(page.getSuperiorMoneyFilter == 60)

    page.setEntryFeeFilter(60, 50)
    assert(page.getInferiorMoneyFilter == 60)
    assert(page.getSuperiorMoneyFilter == 60)

    page.clearFilters
    assert(page.getInferiorMoneyFilter == MIN)
    assert(page.getSuperiorMoneyFilter == MAX)

  }

  def filterByFreeContestsWithMinFilter(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters

    page.setEntryFeeFilter(1, page.MAX_ENTRY_MONEY)
        .clickFreeContestFilter
        .checkNumberOfContests(0)
  }

  def playFirstContest(resolution:Resolution): Unit = {
    goToLobbyPage.clearFilters
                 .playContestNumber(1)
  }

  def searchContest(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
                            .searchContestByName(FILTERS_PANEL_SEARCH_TEXT_1)
    if (resolution != Resolution.SMALL){
      page.checkNumberOfContests(N_CONTESTS_SEARCH_1)
    }
  }


  /// PAGINATOR TESTS

  def paginatorMainFunctionality(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
    val paginator = new PaginatorControl(resolution, "lobby").isAt
    assert( paginator.isDisplayed )

    paginator.getNumberOfPages must be (14)
    paginator.getCurrentPage must be (1)
    paginator.goToLastPage.getCurrentPage must be (14)
    paginator.goToFirstPage.getCurrentPage must be (1)
    paginator.goToNextPage.getCurrentPage must be (2)
    paginator.goToNextPage.getCurrentPage must be (3)
    paginator.goToNextPage.getCurrentPage must be (4)
    paginator.goToPreviousPage.getCurrentPage must be (3)
    paginator.goToPreviousPage.getCurrentPage must be (2)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPage(10).getCurrentPage must be (10)

    paginator.goToPage(14).getCurrentPage must be (14)
    paginator.goToNextPage.getCurrentPage must be (14)
    paginator.goToNextPage.getCurrentPage must be (14)
    paginator.goToLastPage.getCurrentPage must be (14)

    paginator.goToPage(1).getCurrentPage must be (1)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToPreviousPage.getCurrentPage must be (1)
    paginator.goToFirstPage.getCurrentPage must be (1)

    intercept [Exception] { paginator.goToPage(0) }
    intercept [Exception] { paginator.goToPage(-1) }
    intercept [Exception] { paginator.goToPage(15) }

  }

  def paginatorIsDisplayedWhenNecessary(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
    val paginator = new PaginatorControl(resolution, "lobby").isAt
    assert(paginator.isDisplayed)
    paginator.getNumberOfPages must be (14)

    page.setEntryFeeFilter(page.MAX_ENTRY_MONEY, page.MAX_ENTRY_MONEY)
    assert(!paginator.isDisplayed)
    paginator.getNumberOfPages must be (1)
  }

  def knownBugSequence_DisappearedPaginatorOnFilter(resolution:Resolution): Unit = {
    val page = goToLobbyPage.clearFilters
    val paginator = new PaginatorControl(resolution, "lobby").isAt
    assert(paginator.isDisplayed)
    paginator.goToLastPage
    page.clickFreeContestFilter
    assert(paginator.isDisplayed)
    paginator.getCurrentPage must be (1)
    paginator.getNumberOfPages must be (3)
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
