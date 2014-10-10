package unusual.pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByCssSelector
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.Action
import org.scalatest.selenium.WebBrowser
import unusual.model._
import unusual.pages.components.{ContestDescriptionWindow, FooterBar, PaginatorControl, MenuBar}

import scala.util.control.Exception


class LobbyPage(res:Resolution, maxEntryMoney: Int)  extends SharedPage(res) {

  override val url = SharedPage.baseUrl + "/#/lobby"

  val ROWS_PER_PAGE = 10

  val CONTEST_LIST_MENU_MOBILE = "#lobbyContestListMenuXS"
  /*
  val MENU_ALL_CONTEST = "#lobby_AllContests"
    val MENU_PRIZED_CONTEST = "#lobby_PrizedContests"
    val MENU_FREE_CONTEST = "#lobby_FreeContests"
  val MENU_MY_CONTEST = "#lobby_MyContests"
  */
  val PROMOS_COMPONENT = "#promosComponent"
  val CONTEST_LIST_CONTAINER = ".contests-list-root"
  val CONTEST_ROW_CONTAINER = ".contest-row"
  def CONTEST_ROW_CONTAINER(ordinal:Int):String = CONTEST_ROW_CONTAINER + ":nth-child(" + ordinal + ")"

  //val CONTEST_COLUMN_NAME = ".column-contest-name"
  //val CONTEST_NAME = ".column-name"
  //val CONTEST_DESCRIPTION = ".column-description"

  //val CONTEST_COLUMN_PRIZE = ".column-contest-prize"
  //val CONTEST_PRIZE = ".column-contest-prize-content span"

  //val CONTEST_COLUMN_DATE_DAY = ".column-contest-start-date"
  //val CONTEST_DATE_DAY = ".column-start-date-day"
  //val CONTEST_DATE_HOUR = ".column-start-date-hour"

  //val CONTEST_COLUMN_ACTION = ".column-contest-action"


  def CONTEST_ROW_PLAY_BUTTON(ordinal:Int):String = CONTEST_ROW_CONTAINER(ordinal) + " .column-contest-action button"
  def CONTEST_ROW_FEE(ordinal:Int):String = CONTEST_ROW_CONTAINER(ordinal) + " .column-contest-price .column-contest-price-content span"
  def CONTEST_ROW_NAME(ordinal:Int):String = CONTEST_ROW_CONTAINER(ordinal) + " .column-contest-name .column-name"


  /**************** FILTERS CONSTANTS ****************/

  def FILTERS_BUTTON  = if (resolution == Resolution.SMALL) "#filtersButtonMobile" else "#filtersButtonDesktop"
  //val FILTERS_BUTTON_DESKTOP = "#filtersButtonDesktop"

  val FILTERS_PANEL_SEARCH   = "#contestFastSearch input"

  val FILTERS_PANEL = "#filtersPanel"
  val RESET_FILTERS_BUTTON = ".reset-button-wrapper .btn-reset"

  val FILTERS_PANEL_FILTER_FREE_CHECK            = "filtroFree"
  val FILTERS_PANEL_FILTER_LEAGUE_CHECK          = "filtroleague"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK     = "filtroFiftyFifty"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK    = "filtroHeadToHead"
  val FILTERS_PANEL_FILTER_BEGINNER_SALARY       = "filtroPrincipiante"
  val FILTERS_PANEL_FILTER_STANDARD_SALARY       = "filtroEstandar"
  val FILTERS_PANEL_FILTER_EXPERT_SALARY         = "filtroExperto"

  val FILTERS_PANEL_FILTER_FREE_LABEL            = "label[for=\"" + FILTERS_PANEL_FILTER_FREE_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_LEAGUE_LABEL          = "label[for=\"" + FILTERS_PANEL_FILTER_LEAGUE_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_LABEL     = "label[for=\"" + FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_LABEL    = "label[for=\"" + FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_BEGINNER_SALARY_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_BEGINNER_SALARY + "\"]"
  val FILTERS_PANEL_FILTER_STANDARD_SALARY_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_STANDARD_SALARY + "\"]"
  val FILTERS_PANEL_FILTER_EXPERT_SALARY_LABEL   = "label[for=\"" + FILTERS_PANEL_FILTER_EXPERT_SALARY + "\"]"

  val SORT_BY_NAME = "#sortContestName"
  val SORT_BY_ENTRY_FEE = "#sortContestEntryFee"
  val SORT_BY_START_TIME = "#sortContestStartTime"

  var MAX_ENTRY_MONEY = maxEntryMoney

  val SLIDER_RANGE = "#slider-range"
  val SLIDER_RANGE_INFERIOR      = SLIDER_RANGE  + " .noUi-origin:nth-child(1) div"
  val SLIDER_RANGE_SUPERIOR      = SLIDER_RANGE  + " .noUi-origin:nth-child(2) div"
  val SLIDER_RANGE_TEXT_INFERIOR = FILTERS_PANEL + " .filter-column-entry-fee .entry-fee-value-min"
  val SLIDER_RANGE_TEXT_SUPERIOR = FILTERS_PANEL + " .filter-column-entry-fee .entry-fee-value-max"


  /**************** COMMON METHODS ****************/

  override def isAt = {
    var _isAt = true

    _isAt = new MenuBar(resolution).isAt
    logger.debug("Menu bar: ", _isAt)
    _isAt = _isAt && new FooterBar(resolution).isAt
    logger.debug("Footer bar: ", _isAt)

    _isAt = _isAt && (currentUrl == url || currentUrl == SharedPage.baseUrl)
    logger.debug("URL is " + currentUrl + ", should be " + url, _isAt)
    _isAt = _isAt && pageTitle == TITLE
    logger.debug("Title is " + pageTitle + ", should be " + TITLE, _isAt)


    _isAt = _isAt && isElemDisplayed(PROMOS_COMPONENT)

    if (resolution != Resolution.SMALL) {
      _isAt = _isAt && isElemDisplayed(CONTEST_LIST_CONTAINER)
    } else {
      _isAt = _isAt && isElemDisplayed(CONTEST_LIST_MENU_MOBILE)
    }

    _isAt
  }

  /**************** STATE METHODS ****************/

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


  /**************** NAVIGATION XS METHODS ****************/
/*
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

*/
  /**************** FILTERS METHODS ****************/

  def clearFilters = {
    val cleanBtt = find(cssSelector(RESET_FILTERS_BUTTON)).get

    openFilters
    eventually { click on cleanBtt }

    this
  }

  def setEntryFeeFilter(inf: Int, sup: Int) = {
    openFilters
    val sliderWidth = find(cssSelector(SLIDER_RANGE)).get.underlying.getSize.width - 1
    val inferior = find(cssSelector(SLIDER_RANGE_INFERIOR)).get.underlying
    val superior = find(cssSelector(SLIDER_RANGE_SUPERIOR)).get.underlying
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

  def clickBeginnerSalaryFilter = {
    applyFilter(FILTERS_PANEL_FILTER_BEGINNER_SALARY_LABEL)
  }

  def clickStandardSalaryFilter = {
    applyFilter(FILTERS_PANEL_FILTER_STANDARD_SALARY_LABEL)
  }

  def clickExpertSalaryFilter = {
    applyFilter(FILTERS_PANEL_FILTER_EXPERT_SALARY_LABEL)
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
      eventually { textField(cssSelector(FILTERS_PANEL_SEARCH)).value = name }
    } else {
      unavailableFunctionOnResolution("searchContestByName")
    }
    this
  }

  def getInferiorMoneyFilter :Int = {
    val inferiorTextNode = find(cssSelector(SLIDER_RANGE_TEXT_INFERIOR)).get
    var n:Integer = 0

    eventually { n = Integer.parseInt( inferiorTextNode.text.substring(5, inferiorTextNode.text.length - 1) ) }

    n
  }

  def getSuperiorMoneyFilter :Int = {
    val superiorTextNode = find(cssSelector(SLIDER_RANGE_TEXT_SUPERIOR)).get
    var n:Integer = 0

    eventually { n = Integer.parseInt( superiorTextNode.text.substring(5, superiorTextNode.text.length - 1) ) }

    n
  }

  def areAllFiltersClear:Boolean = {
    var areClear = true
    openFilters
    areClear = areClear && getSuperiorMoneyFilter == MAX_ENTRY_MONEY
    areClear = areClear && getInferiorMoneyFilter == 0

    val searchCssSel  = FILTERS_PANEL_SEARCH
    areClear = areClear && textField(cssSelector(searchCssSel)).value == ""

    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_FREE_CHECK).isSelected
    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_LEAGUE_CHECK).isSelected
    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK).isSelected
    areClear = areClear && !checkbox(FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK).isSelected
    closeFilters
    areClear
  }


  /**************** CONTESTS METHODS ****************/


  def playContestNumber(ordinal: Int) = {
    val subOrdinal = ((ordinal - 1) % PaginatorControl.ELEMENTS_PER_PAGE) + 1
    val page = Math.floor((ordinal - 1) / PaginatorControl.ELEMENTS_PER_PAGE).toInt + 1
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    paginator.goToPage(page)
    eventually { click on find(cssSelector( CONTEST_ROW_PLAY_BUTTON(subOrdinal) )).get }
    this
  }

  def areContestsBetweenEntryValues(amountRows: Int, min: Double, max : Double): Boolean = {
    var areBetween = true
    for (i <- 1 to amountRows) {
      eventually {
        val rowText = find(cssSelector(CONTEST_ROW_FEE(i))).get.text
        val entryNum = java.lang.Double.parseDouble(rowText.substring(0, rowText.length - 1))

        areBetween =  areBetween && entryNum <= max && entryNum >= min
      }
    }

    areBetween
  }

  def areContestsOrderedByName: Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val pagesCount = paginator.getNumberOfPages
    var areOrdered: Boolean = fastLobby_ContestAreOrderedByName

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByName
        if(!areOrdered) scala.util.control.Breaks.break()
      }
    }

    //Thread.sleep(100000)
    areOrdered
  }

  def areContestsOrderedByEntryFee: Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val pagesCount = paginator.getNumberOfPages
    var areOrdered = fastLobby_ContestAreOrderedByEntryFee

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByEntryFee
        if(!areOrdered) scala.util.control.Breaks.break()
      }
    }

    areOrdered
  }

  def areContestsOrderedByStartTime: Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    val pagesCount = paginator.getNumberOfPages
    var areOrdered = fastLobby_ContestAreOrderedByStartTime

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByStartTime
        if(!areOrdered) scala.util.control.Breaks.break()
      }
    }

    //Thread.sleep(100000)
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
    val lastPageRows = fastCountByCssSelector(CONTEST_ROW_CONTAINER)
    paginator.goToPage(currPage)

    // return
    (pages - 1) * ROWS_PER_PAGE + lastPageRows
  }

  def openContestDescription(idx:Int) = {

    val page:Int = (Math.floor( (idx - 1) / 10) + 1).toInt
    new PaginatorControl(resolution, CONTEST_LIST_CONTAINER).goToPage(page)
    val row:Int = ((idx-1) % 10) + 1
    logger.debug(s"Calculated page[$page] and row[$row])")

    eventually { isElemDisplayed(CONTEST_ROW_NAME(row)) }
    logger.debug(s"Contest row name at row($row) exists")
    eventually (timeout(60 seconds)) {
      fastClicksByCssSelector(1, CONTEST_ROW_NAME(row))
      //click on find(cssSelector( CONTEST_ROW_NAME(row) )).get
    }
    logger.debug(s"Has been clicked in name")
    this

  }

  /**************** PRIVATE METHODS ****************/

  private def applyFilter(forId : String) = {
    openFilters
    val filter = find(cssSelector(forId)).get

    eventually { filter.isDisplayed }
    click on filter

    this
  }

  private def openFilters = {
    val button = find(cssSelector(FILTERS_BUTTON + ".toggleOff"))

    //var panel:WebBrowser.Element = null

    if (button != None) {
      eventually { assert( !find(cssSelector(FILTERS_PANEL)).get.isDisplayed ) }
      click on button.get
    }
    /*
    if (panel.isDisplayed) {
      eventually {
        if (resolution == Resolution.SMALL) {
          click on find(cssSelector(FILTERS_BUTTON_MOBILE)).get
        } else {
          click on find(cssSelector(FILTERS_BUTTON_DESKTOP)).get
        }
      }
    }
    */

    this
  }

  private def closeFilters = {
    val button = find(cssSelector(FILTERS_BUTTON + ".toggleOn"))

    if (button != None) {
      eventually { assert( find(cssSelector(FILTERS_PANEL)).get.isDisplayed ) }
      click on button.get
    }

    this
  }

}








