package unusual.pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByCssSelector
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.Action
import unusual.model._

import scala.util.control.Exception


class LobbyPage(res:Resolution)  extends SharedPage(res) {

  override val url = SharedPage.baseUrl + "/#/lobby"

  val ROWS_PER_PAGE = 10

  val CONTEST_LIST_MENU_MOBILE = "#lobbyContestListMenuXS"
  val MENU_ALL_CONTEST = "#lobby_AllContests"
    val MENU_PRIZED_CONTEST = "#lobby_PrizedContests"
    val MENU_FREE_CONTEST = "#lobby_FreeContests"
  val MENU_MY_CONTEST = "#lobby_MyContests"

  val PROMOS_COMPONENT = "#promosComponent"
  val CONTEST_LIST_CONTAINER = ".contests-list-root"
  val CONTEST_ROW_CONTAINER_CLASS = ".contest-row"

  val CONTEST_COLUMN_NAME_CLASS = ".column-contest-name"
  val CONTEST_NAME_CLASS = ".column-name"
  val CONTEST_DESCRIPTION_CLASS = ".column-description"

  val CONTEST_COLUMN_FEE_CLASS = ".column-contest-price"
  val CONTEST_FEE_CLASS = ".column-contest-price-content span"

  val CONTEST_COLUMN_PRIZE_CLASS = ".column-contest-prize"
  val CONTEST_PRIZE_CLASS = ".column-contest-prize-content span"

  val CONTEST_COLUMN_DATE_DAY_CLASS = ".column-contest-start-date"
  val CONTEST_DATE_DAY_CLASS = ".column-start-date-day"
  val CONTEST_DATE_HOUR_CLASS = ".column-start-date-hour"

  val CONTEST_COLUMN_ACTION_CLASS = ".column-contest-action"

  val FILTERS_BUTTON_ID = Map(
    "mobile" -> "filtersButtonMobile",
    "desktop" -> "filtersButtonDesktop"
  )

  val FILTERS_PANEL_SEARCH_ID = "contestFastSearch"

  val FILTERS_PANEL = "#filtersPanel"
  /*val FILTERS_PANEL_FILTER_FREE_CHECK = "filtroFree"
  val FILTERS_PANEL_FILTER_LEAGUE_CHECK = "filtroleague"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK = "filtroFiftyFifty"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK = "filtroHeadToHead"
  val FILTERS_PANEL_FILTER_FREE_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_FREE_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_LEAGUE_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_LEAGUE_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK + "\"]"

  val SORT_BY_NAME = "#sortContestName"
  val SORT_BY_ENTRY_FEE = "#sortContestEntryFee"
  val SORT_BY_START_TIME = "#sortContestStartTime"

  val MAX_ENTRY_MONEY = 5

  def open = {
    go to url
    this
  }

  override def isAt = {
    var _isAt = true
    _isAt = _isAt && isWholePage

    _isAt = _isAt && isElemDisplayed(PROMOS_COMPONENT)

    if (resolution != Resolution.SMALL) {
      _isAt = _isAt && isElemDisplayed(CONTEST_LIST_CONTAINER)
    } else {
      _isAt = _isAt && isElemDisplayed(CONTEST_LIST_MENU_MOBILE)
    }

    _isAt
  }

  def isDefaultState(numberOfContest:Int):Boolean = {
    var isDefault = true
    val filtersPanel = find(cssSelector(FILTERS_PANEL))
    isDefault = isDefault && filtersPanel != None
    logger.debug("{" + FILTERS_PANEL + "} exists", isDefault)
    if ( isDefault ) {
      isDefault = isDefault && !filtersPanel.get.isDisplayed
      logger.debug("{" + FILTERS_PANEL + "} is not displayed", isDefault)
    }


    if (resolution == Resolution.SMALL) {
      isDefault = isDefault && isElemDisplayed(CONTEST_LIST_MENU_MOBILE)

    } else {
      isDefault = isDefault && new PaginatorControl(resolution, CONTEST_LIST_CONTAINER).getCurrentPage == 1
      logger.debug("Paginator page should be 1", isDefault)
      isDefault = isDefault && areAllFiltersClear
      logger.debug("Are all filters clear", isDefault)
      isDefault = isDefault && getNumberOfContests == numberOfContest
      logger.debug("Number of contests: current(" + getNumberOfContests + "), expected(" + numberOfContest + ")", isDefault)
    }

    isDefault
  }

  def isNotLoggedIn: Boolean = {
    new MenuBar(resolution).isLoginBar
  }
  def isLoggedIn: Boolean = {
    new MenuBar(resolution).isLoggedBar
  }

  def clickOnMenuAllContests = {
    if (resolution == Resolution.SMALL) {
      click on find(cssSelector(MENU_ALL_CONTEST)).get
    }
    this
  }

  def clickOnMenuFreeContests = {
    if (resolution == Resolution.SMALL) {
      click on find(cssSelector(MENU_FREE_CONTEST)).get
    }
    this
  }

  def clickOnMenuMyContests = {
    if (resolution == Resolution.SMALL) {
      click on find(cssSelector(MENU_MY_CONTEST)).get
    }
    this
  }

  def clickOnMenuPrizedContests = {
    if (resolution == Resolution.SMALL) {
      click on find(cssSelector(MENU_PRIZED_CONTEST)).get
    }
    this
  }


  def clearFilters = {
    var cleanBtt = find(cssSelector(".reset-button-wrapper .btn-reset")).get

    openFilters
    eventually { click on cleanBtt }

    this
  }

  def setEntryFeeFilter(inf: Int, sup: Int) = {
    openFilters

    val sliderWidth = find(cssSelector("#slider-range")).get.underlying.getSize().width - 1
    val inferior = find(cssSelector("#slider-range .noUi-origin:nth-child(1) div")).get.underlying
    val superior = find(cssSelector("#slider-range .noUi-origin:nth-child(2) div")).get.underlying
    val infBound = inf - getInferiorMoneyFilter
    val supBound = sup - getSuperiorMoneyFilter

    var distance: Float = sliderWidth * infBound
    distance /= MAX_ENTRY_MONEY

    var dragAndDrop:Action = new Actions(driver)
                                    .clickAndHold(inferior)
                                    .moveByOffset(Math.floor(distance).toInt, 0)
                                    .release(inferior)
                                    .build()

    dragAndDrop.perform()

    distance = sliderWidth * supBound
    distance /= MAX_ENTRY_MONEY

    dragAndDrop = new Actions(driver)
                          .clickAndHold(superior)
                          .moveByOffset(Math.floor(distance).toInt, 0)
                          .release(superior)
                          .build()

    dragAndDrop.perform()

    this
  }

  def clickFreeContestFilter = {
    applyFilter(FILTERS_PANEL_FILTER_FREE_LABEL)
  }

  def clickLeagueContestFilter = {
    applyFilter(FILTERS_PANEL_FILTER_LEAGUE_LABEL)
  }

  def clickFiftyFiftyContestsFilter = {
    applyFilter(FILTERS_PANEL_FILTER_FIFTY_FIFTY_LABEL)
  }

  def clickHeadToHeadContestsFilter = {
    applyFilter(FILTERS_PANEL_FILTER_HEAD_TO_HEAD_LABEL)
  }

  def clickSortContestsByName = {
    click on find(cssSelector(SORT_BY_NAME)).get
    this
  }

  def clickSortContestsByEntryFee = {
    click on find(cssSelector(SORT_BY_ENTRY_FEE)).get
    this
  }

  def clickSortContestsByStartTime = {
    click on find(cssSelector(SORT_BY_START_TIME)).get
    this
  }

  def searchContestByName(name: String) = {
    if (resolution != Resolution.SMALL) {
      val cssSel  = "#" + FILTERS_PANEL_SEARCH_ID + " input"
      eventually { textField(cssSelector(cssSel)).value = name }
    } else {
      unavailableFunctionOnResolution("searchContestByName")
    }
    this
  }

  def playContestNumber(ordinal: Int) = {
    val cssSel  = CONTEST_ROW_CONTAINER_CLASS + ":nth-child(" + ordinal + ") " + CONTEST_COLUMN_ACTION_CLASS + " button"
    eventually { click on find(cssSelector(cssSel)).get }
    this
  }

  def areContestsBetweenEntryValues(amountRows: Int, min: Double, max : Double): Boolean = {
    val cssSelPre  = CONTEST_ROW_CONTAINER_CLASS + ":nth-child("
    val cssSelPost = ") " + CONTEST_COLUMN_FEE_CLASS + " " + CONTEST_FEE_CLASS
    var areBetween = true
    for (i <- 1 to amountRows) {
      eventually {

        var rowText = find(cssSelector(cssSelPre + i + cssSelPost)).get.text
        var entryNum = java.lang.Double.parseDouble(rowText.substring(0, rowText.length - 1))

        areBetween =  areBetween && entryNum <= max && entryNum >= min
      }
    }

    areBetween
  }

  def areContestsOrderedByName(): Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val pagesCount = paginator.getNumberOfPages
    var areOrdered = fastLobby_ContestAreOrderedByName

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByName
        if(!areOrdered) scala.util.control.Breaks.break
      }
    }

    areOrdered
  }

  def areContestsOrderedByEntryFee(): Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val pagesCount = paginator.getNumberOfPages
    var areOrdered = fastLobby_ContestAreOrderedByEntryFee

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByEntryFee
        if(!areOrdered) scala.util.control.Breaks.break
      }
    }

    areOrdered
  }

  def areContestsOrderedByStartTime(): Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val pagesCount = paginator.getNumberOfPages
    var areOrdered = fastLobby_ContestAreOrderedByStartTime

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByStartTime
        if(!areOrdered) scala.util.control.Breaks.break
      }
    }

    areOrdered
  }

  def getNumberOfContests:Int = {
    /*// Es estable pero me parece cutre.
    var maxRow = 1
    var found = true
    while (found) {
      try {
        eventually (timeout(2 seconds)){
          find(cssSelector(CONTEST_ROW_CONTAINER_CLASS + ":nth-child(" + maxRow + ")")) should be ('defined)
          maxRow += 1
          found = true
        }
      } catch {
        case _: Exception => {
          found = false
          maxRow -= 1
        }
      }
    }
    maxRow should be (n)
    */
    /* Esta forma parece mas bonita pero casca por el refresh
    eventually (timeout(50 seconds)){
      var rows = findAll(cssSelector(CONTEST_ROW_CONTAINER_CLASS))
      assert(rows.length == n)
    }
    */

    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val currPage = paginator.getCurrentPage
    paginator.goToLastPage
    val pages = paginator.getNumberOfPages

    // Esta parece la unica rapida y efectiva
    val lastPageRows = fastCountByCssSelector(CONTEST_LIST_CONTAINER + " .contest-row")
    paginator.goToPage(currPage)

    // return
    (pages - 1) * ROWS_PER_PAGE + lastPageRows
  }

  def getInferiorMoneyFilter :Int = {
    val inferiorTextNode = find(cssSelector("#filtersPanel .filter-column-entry-fee .entry-fee-value-min")).get
    var n:Integer = 0

    eventually {
      n = Integer.parseInt( inferiorTextNode.text.substring(5, inferiorTextNode.text.length - 1) )
    }

    n
  }

  def getSuperiorMoneyFilter :Int = {
    val superiorTextNode = find(cssSelector("#filtersPanel .filter-column-entry-fee .entry-fee-value-max")).get
    var n:Integer = 0

    eventually {
      n = Integer.parseInt( superiorTextNode.text.substring(5, superiorTextNode.text.length - 1) )
    }

    n
  }

  def areAllFiltersClear:Boolean = {
    var areClear = true
    openFilters
    areClear = areClear && getSuperiorMoneyFilter == MAX_ENTRY_MONEY
    areClear = areClear && getInferiorMoneyFilter == 0

    val searchCssSel  = "#" + FILTERS_PANEL_SEARCH_ID + " input"
    areClear = areClear && textField(cssSelector(searchCssSel)).value == ""

    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_FREE_CHECK).isSelected
    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_LEAGUE_CHECK).isSelected
    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK).isSelected
    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK).isSelected
    closeFilters
    areClear
  }

  private def applyFilter(forId : String) = {

    openFilters
    val filter = find(cssSelector(forId)).get

    eventually { filter.isDisplayed }
    click on filter

    this
  }

  private def openFilters = {
    val panel = find(id(FILTERS_PANEL)).get
    if (!panel.isDisplayed) {
      eventually {
        if (resolution == Resolution.SMALL) {
          click on find(id(FILTERS_BUTTON_ID("mobile"))).get
        } else {
          click on find(id(FILTERS_BUTTON_ID("desktop"))).get
        }
      }
    }
    this
  }

  private def closeFilters = {
    val panel = find(id(FILTERS_PANEL)).get
    if (panel.isDisplayed) {
      eventually {
        if (resolution == Resolution.SMALL) {
          click on find(id(FILTERS_BUTTON_ID("mobile"))).get
        } else {
          click on find(id(FILTERS_BUTTON_ID("desktop"))).get
        }
      }
    }
    this
  }

  // NOT USED, DELETEABLE
/*
  private def buildContestFromRow(row : WebElement): Contest ={
    var contest = new Contest("", "", "", "", "")

    var nameColumn = row.findElement(new ByCssSelector(CONTEST_COLUMN_NAME_CLASS))
    contest.name = nameColumn.findElement(new ByCssSelector(CONTEST_NAME_CLASS)).getText
    contest.description = nameColumn.findElement(new ByCssSelector(CONTEST_DESCRIPTION_CLASS)).getText

    var feeColumn = row.findElement(new ByCssSelector(CONTEST_COLUMN_FEE_CLASS))
    contest.entryFee = "0€"//feeColumn.findElement(new ByCssSelector(CONTEST_FEE_CLASS)).getText

    var prizesColumn = row.findElement(new ByCssSelector(CONTEST_COLUMN_PRIZE_CLASS))
    contest.prize = prizesColumn.findElement(new ByCssSelector(CONTEST_PRIZE_CLASS)).getText

    var dateColumn = row.findElement(new ByCssSelector(CONTEST_COLUMN_DATE_DAY_CLASS))
    contest.date = dateColumn.findElement(new ByCssSelector(CONTEST_DATE_DAY_CLASS)).getText + " " +
                    dateColumn.findElement(new ByCssSelector(CONTEST_DATE_HOUR_CLASS)).getText

    contest
  }
*/

  /*
  eventually {
    var rows = findAll(cssSelector(CONTEST_ROW_CONTAINER_CLASS))
    //println(rows.next)
    a = Array[Contest]()

    for (row <- rows) {
      var realRow = row.underlying
      var cont: Contest = buildContestFromRow(realRow)
      a = a :+ cont
    }

  }
  */
  //a

}








