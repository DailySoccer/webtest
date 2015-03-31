package unusual.tests.lobbyTest

import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
import org.scalatest.exceptions.StackDepthException
import org.scalatest.selenium.WebBrowser
import unusual.model.{Contest, Resolution}
import unusual.model.pageStates.{EnterContestState, LobbyState}
import unusual.pages.components.page.EnterContestDescriptionTab
import unusual.pages.components.{ContestDescriptionWindow, PaginatorControl}
import unusual.testTags.scala._
import unusual.tests._
import unusual.pages._
import unusual.tests.contestDescriptionTest.ContestDescriptionWindowCommon

abstract class LobbyTestCommon(lobbySt: LobbyState, res:Resolution) extends SharedTest(res) {

  var lobbyState = lobbySt
  var _lobbyPageInstance: LobbyPage = null

  def lobbyPage: LobbyPage = {
    if (_lobbyPageInstance == null) {
      _lobbyPageInstance = goToLobbyPage(lobbyState)
      changeMenuPositioning
      _lobbyPageInstance.isAt
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
    lobbyPage
  }

  def lookForDefaultState: Unit = {
    eventually {
      assert(lobbyPage.isDefaultState(lobbyState.numContests_NoFilter), "Lobby is not at default state")
    }
  }

  def lookAtContestsName: Unit = {
    logger.debug(s"looking at contest names")
    eventually {
      val name = lobbyPage.getContestName(1).toLowerCase
      assert(name.startsWith(lobbySt.contestNames(1).toLowerCase), s"Contest names do not match(1): current($name) expected(${lobbySt.contestNames(1)})")
    }
    eventually {
      val name = lobbyPage.getContestName(2).toLowerCase
      assert(name.startsWith(lobbySt.contestNames(2).toLowerCase), s"Contest names do not match(1): current($name) expected(${lobbySt.contestNames(2)})")
    }
  }

  def lookAtContestDescription: Unit = {
    if (status.resolution == Resolution.BIG) {
      logger.debug("Looking at contest description")
      lobbyPage.openContestDescription(1)
      logger.debug("Contest description should be opened(1)")
      val description = new ContestDescriptionWindow(status.resolution)

      eventually {
        assert(description.isAt, "Description should be visible")
      }
      logger.debug("Description is visible")
      description.close
      logger.debug("Description should be closed")
      eventually {
        assert(!description.isAt, "Description should NOT be visible")
      }
      logger.debug("Description is closed")

      lobbyPage.openContestDescription(lobbyState.numContests_NoFilter)
      logger.debug("Last contest description should be opened(2)")
      eventually {
        assert(description.isAt, "Description should be visible")
      }
      logger.debug("Last description is visible")
      description.close
      logger.debug("Last description should be closed")
      eventually {
        assert(!description.isAt, "Description should NOT be visible")
      }
      logger.debug("Last description is closed")

      lobbyPage.goToFirstMatchesPage

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
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      eventually { assert(isDefaultMedium(), "From big to medium") }


      changeBrowserResolution(Resolution.BIG)
      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      changeBrowserResolution(Resolution.SMALL)
      eventually { assert(isDefaultSmall(), "From big to medium to small") }


      changeBrowserResolution(Resolution.BIG)
      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.SMALL)
      eventually { assert(isDefaultSmall(), "From big to small") }

    } else if (status.resolution == Resolution.MEDIUM) {

      goToLobbyPage(lobbyState)
      eventually { assert(isDefaultMedium(), "Medium is not default state") }

      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.BIG)
      eventually { assert(isDefaultMedium(), "From medium to big") }


      changeBrowserResolution(Resolution.MEDIUM)
      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.SMALL)
      eventually { assert(isDefaultSmall(), "From medium to small") }

    } else {

      goToLobbyPage(lobbyState)
      assert(isDefaultSmall(), "Small is not default state")

      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      eventually { assert(isDefaultMedium(), "From small to medium") }


      changeBrowserResolution(Resolution.SMALL)
      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.MEDIUM)
      changeBrowserResolution(Resolution.BIG)
      eventually { assert(isDefaultBig(), "From small to medium to big") }


      changeBrowserResolution(Resolution.SMALL)
      reloadPage
      changeMenuPositioning
      eventually { assert(lobbyPage.isAt) }
      changeBrowserResolution(Resolution.BIG)
      eventually { assert(isDefaultBig(), "From small to big") }

    }
    changeMenuPositioning
  }

  /*
   * END BASIC INFO TESTS
   */


  /*
   * FILTERS TESTS
   */
  def checkClearFiltersButton: Unit = {
    Given("a lobby page filters should be clear")
    assert(lobbyPage.filters.areAllClear, "Filters are not clear")
    When("set up some filters that hide all contests")
    lobbyPage.filters.search(FILTERS_PANEL_SEARCH_TEXT)
    logger.debug("searched by name")
    lobbyPage.filters.entryFee.set(0, 0)

    val tournaments = lobbyPage.filters.tournaments
    logger.debug("filter by entry fee 0 , 0")
    tournaments.league.doClick
    logger.debug("filter by league contest")
    tournaments.fiftyFifty.doClick
    logger.debug("filter by fifty fifty")
    tournaments.headToHead.doClick
    logger.debug("filter by head to head")
    eventually(timeout(3 seconds)) {
      assert(lobbyPage.getNumberOfContests == 0, "Number of contests is greater than 0")
    }
    And("clear filters")
    lobbyPage.filters.clear
    Then("filters should be clear again")
    assert(lobbyPage.filters.areAllClear, "Filters are not clear after clear filters")
    And("number of contest should be restored")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Filters are not cleared correctly or some contests disappeared.")
  }

  def filterByFreeContests: Unit = {
    lobbyPage.filters.tournaments.free.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Free, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Free}")
    lobbyPage.filters.clear
  }

  def filterByLeagueContests: Unit = {
    lobbyPage.filters.tournaments.league.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_League, s"Num of contests: $numContests, expected: ${lobbyState.numContests_League}")
    lobbyPage.filters.clear
  }

  def filterByFiftyFiftyContests: Unit = {
    lobbyPage.filters.tournaments.fiftyFifty.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_FiftyFifty, s"Num of contests: $numContests, expected: ${lobbyState.numContests_FiftyFifty}")
    lobbyPage.filters.clear
  }

  def filterByHeadToHeadContests: Unit = {
    lobbyPage.filters.tournaments.headToHead.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_HeadToHead, s"Num of contests: $numContests, expected: ${lobbyState.numContests_HeadToHead}")
    lobbyPage.filters.clear
  }

  def filterByEntryFee: Unit = {
    lobbyPage.filters.entryFee.set(lobbyState.minEntryFeeFilter, lobbyPage.MAX_ENTRY_MONEY)
    eventually(timeout(3 seconds)) {
      val numContests = lobbyPage.getNumberOfContests
      assert(numContests == lobbyState.numContests_MinEntryFee, s"Num of contests: $numContests, expected: ${lobbyState.numContests_MinEntryFee}")
    }
    lobbyPage.filters.clear
  }

  def filterByBeginnerSalary: Unit = {
    lobbyPage.filters.salaryLimit.beginner.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Beginner, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Beginner}")
    lobbyPage.filters.clear
  }

  def filterByStandardSalary: Unit = {
    lobbyPage.filters.salaryLimit.standard.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Standard, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Standard}")
    lobbyPage.filters.clear
  }

  def filterByExpertSalary: Unit = {
    lobbyPage.filters.salaryLimit.expert.doClick
    val numContests = lobbyPage.getNumberOfContests
    assert(numContests == lobbyState.numContests_Expert, s"Num of contests: $numContests, expected: ${lobbyState.numContests_Expert}")
    lobbyPage.filters.clear
  }

  def checkEntryFeeFilterCtrl: Unit = {
    val MAX = lobbyPage.MAX_ENTRY_MONEY
    val MIN = 0
    val checkEntryFee = (inferior:Integer, superior:Integer) => {
      eventually(timeout(3 seconds)) {
        assert(lobbyPage.filters.entryFee.getInferior == inferior, "Inferior filter should be: " + inferior)
      }
      eventually(timeout(3 seconds)) {
        assert(lobbyPage.filters.entryFee.getSuperior == superior, "Superior filter should be: " + superior)
      }
    }

    checkEntryFee(MIN, MAX)

    lobbyPage.filters.entryFee.set(1, MAX)
    checkEntryFee(1, MAX)

    lobbyPage.filters.clear
    checkEntryFee(MIN, MAX)

    lobbyPage.filters.entryFee.set(MIN, 4)
    checkEntryFee(MIN, 4)

    lobbyPage.filters.clear
    checkEntryFee(MIN, MAX)

    lobbyPage.filters.entryFee.set(1, 4)
    checkEntryFee(1, 4)

    lobbyPage.filters.entryFee.set(2, 3)
    checkEntryFee(2, 3)

    lobbyPage.filters.entryFee.set(4, 3)
    checkEntryFee(3, 3)

    lobbyPage.filters.entryFee.set(3, 2)
    checkEntryFee(3, 3)

    lobbyPage.filters.clear
    checkEntryFee(MIN, MAX)

  }

  def filterByFreeContestsWithMinFilter: Unit = {
    logger.debug("set entry fee")
    lobbyPage.filters.entryFee.set(1, lobbyState.maxEntryMoney)
    logger.debug("set free filter")
    lobbyPage.filters.tournaments.free.doClick

    logger.debug("comparing contests")
    eventually(timeout(3 seconds)) {
      lobbyPage.getNumberOfContests must be(0)
    }
    logger.debug("clear filters")
    lobbyPage.filters.clear
  }

  def searchContests: Unit = {
    if (status.resolution != Resolution.SMALL) {
      for ((key, value) <- lobbyState.filterPanel_SearchResults) {
        logger.debug("Search key: " + key + " || contest should be: " + value)
        lobbyPage.filters.search(key)
        assert(lobbyPage.getNumberOfContests == value, "search does not match")
      }
      lobbyPage.filters.clear
    } else {
      featureNotTestableInResolution
    }
  }

  def searchNonExistentContest: Unit = {
    if (status.resolution != Resolution.SMALL) {
      lobbyPage.filters.search(FILTERS_PANEL_SEARCH_TEXT)
      lobbyPage.getNumberOfContests must be(N_CONTESTS_SEARCH)
      lobbyPage.filters.clear
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
    lobbyPage.filters.orderBy.name.doClick

    assert(lobbyPage.areContestsOrderedByName, "Contest are not sorted by name")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
  }

  def orderByEntryFee: Unit = {
    lobbyPage.filters.orderBy.entryFee.doClick

    assert(lobbyPage.areContestsOrderedByEntryFee, "Contest are not sorted by entry fee")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
  }

  def orderByStartTime: Unit = {
    lobbyPage.filters.orderBy.startTime.doClick
    logger.debug("Contest should be ordered by start time by default")
    assert(lobbyPage.areContestsOrderedByStartTime, "Contest are not sorted by name")
    assert(lobbyPage.getNumberOfContests == lobbyState.numContests_NoFilter, "Contests disappeared during sort")
  }

  def ReorderByStartTime: Unit = {
    logger.debug("Contest should be sorted by start time by default and should be at the same state after sort them two times")
    lobbyPage.filters.orderBy.startTime.doClick.doClick
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

  def paginatorMainFunctionality: Unit = {
    goToLobbyPage(lobbyState)
    reloadPage()
    changeMenuPositioning
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)

    assert(paginator.isAt, "Paginator is not at the page.")
    assert(paginator.isDisplayed, "Paginator is not displayed.")
    logger.debug("Paginator is displayed correctly")

    paginator.getNumberOfPages must be(lobbyState.maxPaginatorPage)
    logger.debug(s"Paginator pages is ${lobbyState.maxPaginatorPage}")
    paginator.getCurrentPage must be(1)
    logger.debug(s"Current page is 1")
    paginator.goToLastPage.getCurrentPage must be(lobbyState.maxPaginatorPage)
    logger.debug(s"Go to Last page => Current page is ${lobbyState.maxPaginatorPage}")
    paginator.goToFirstPage.getCurrentPage must be(1)
    logger.debug(s"Go to First page => Current pages is 1")
    paginator.goToNextPage.getCurrentPage must be(2)
    logger.debug(s"Go to Next page => Current pages is 2")
    paginator.goToNextPage.getCurrentPage must be(3)
    logger.debug(s"Go to Next page => Current pages is 3")
    paginator.goToPreviousPage.getCurrentPage must be(2)
    logger.debug(s"Current page is ${lobbyState.maxPaginatorPage}")
    paginator.goToPreviousPage.getCurrentPage must be(1)
    logger.debug(s"Paginator pages is ${lobbyState.maxPaginatorPage}")
    paginator.goToPage(4).getCurrentPage must be(4)
    logger.debug(s"Paginator pages is ${lobbyState.maxPaginatorPage}")

    paginator.goToPage(lobbyState.maxPaginatorPage).getCurrentPage must be(lobbyState.maxPaginatorPage)
    paginator.goToNextPage.getCurrentPage must be(lobbyState.maxPaginatorPage)
    paginator.goToNextPage.getCurrentPage must be(lobbyState.maxPaginatorPage)
    paginator.goToLastPage.getCurrentPage must be(lobbyState.maxPaginatorPage)

    paginator.goToPage(1).getCurrentPage must be(1)
    paginator.goToPreviousPage.getCurrentPage must be(1)
    paginator.goToPreviousPage.getCurrentPage must be(1)
    paginator.goToFirstPage.getCurrentPage must be(1)

    intercept[Exception] {
      paginator.goToPage(0)
    }
    intercept[Exception] {
      paginator.goToPage(-1)
    }
    intercept[Exception] {
      paginator.goToPage(lobbyState.maxPaginatorPage + 1)
    }
  }

  def paginatorIsDisplayedWhenNecessary: Unit = {
    lobbyPage
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)
    assert(paginator.isAt, "Paginator is not at the page.")
    assert(paginator.isDisplayed, "Paginator is not displayed.")
    paginator.getNumberOfPages must be(lobbyState.maxPaginatorPage)

    lobbyPage.filters.search(FILTERS_PANEL_SEARCH_TEXT).entryFee.set(1, 1)

    lobbyPage.filters.tournaments.free.doClick

    assert(!paginator.isDisplayed, "Paginator is displayed.")
    paginator.getNumberOfPages must be(1)
    lobbyPage.filters.clear
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
    assert(paginator.isAt, "Paginator is not at the page.")
    assert(paginator.isDisplayed, "Paginator is not displayed.")
    logger.debug("go to last page")
    paginator.goToLastPage
    logger.debug("filter by league contest")
    lobbyPage.filters.tournaments.league.doClick
    logger.debug("paginator should be displayed")
    assert(paginator.isDisplayed, "Paginator is not displayed.")
    logger.debug("look at current page")
    paginator.getCurrentPage must be(3)
    logger.debug("look at number of pages")
    assert(paginator.getNumberOfPages > 1, "Number of pages should be grater (or equals) than 1")
    lobbyPage.filters.clear
  }

  def knownBugSequence_PaginatorOrderedRefresh: Unit = {
    lobbyPage
    val paginator = new PaginatorControl(status.resolution, lobbyPage.CONTEST_LIST_CONTAINER)
    paginator.goToNextPage.goToNextPage.goToNextPage.goToNextPage

    withClue("Paginator should not reset page at lobby contest-list refresh") {
      intercept[StackDepthException] {
        eventually(timeout(12 seconds)) {
          assert(paginator.getCurrentPage == 1)
        }
      }
    }
    lobbyPage.filters.clear

    /*
     * END BUG TESTS
     */
  }
}
