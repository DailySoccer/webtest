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

  val FILTERS_PANEL_FILTER_SPANISH_LEAGUE        = "filterLeagueEsp"
  val FILTERS_PANEL_FILTER_PREMIERE_LEAGUE       = "filterLeagueUK"
  val FILTERS_PANEL_FILTER_CHAMPIONS_LEAGUE      = "filterUCL"

  val FILTERS_PANEL_FILTER_FREE_CHECK            = "filterTournamentTypeFree"
  val FILTERS_PANEL_FILTER_LEAGUE_CHECK          = "filterTournamentTypeLeague"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK     = "filterTournamentTypeFiftyFifty"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK    = "filterTournamentTypeHeadToHead"

  val FILTERS_PANEL_FILTER_BEGINNER_SALARY       = "filterTournamentTierBeginner"
  val FILTERS_PANEL_FILTER_STANDARD_SALARY       = "filterTournamentTierStandard"
  val FILTERS_PANEL_FILTER_EXPERT_SALARY         = "filterTournamentTierSkilled"

  val SORT_BY_NAME = "#orderByName"
  val SORT_BY_ENTRY_FEE = "#orderByEntryFee"
  val SORT_BY_START_TIME = "#orderByStartDate"

  var MAX_ENTRY_MONEY = maxEntryMoney

  val SLIDER_RANGE = "#slider-range"
  val SLIDER_RANGE_INFERIOR      = SLIDER_RANGE  + " .noUi-origin:nth-child(1) div"
  val SLIDER_RANGE_SUPERIOR      = SLIDER_RANGE  + " .noUi-origin:nth-child(2) div"
  val SLIDER_RANGE_TEXT_INFERIOR = FILTERS_PANEL + " .filter-column-entry-fee .entry-fee-value-min"
  val SLIDER_RANGE_TEXT_SUPERIOR = FILTERS_PANEL + " .filter-column-entry-fee .entry-fee-value-max"


  object filters {

    private def _open = {
      eventually { assert( find(cssSelector(FILTERS_PANEL + ".collapsing")) == None ) }

      val button = find(cssSelector(FILTERS_BUTTON + ".toggleOff"))
      if (button != None) { click on button.get }

      this
    }

    private def _close = {
      eventually { assert( find(cssSelector(FILTERS_PANEL + ".collapsing")) == None ) }

      val button = find(cssSelector(FILTERS_BUTTON + ".toggleOn"))
      if (button != None) { click on button.get }

      this
    }

    def clear = {
      val cleanBtt = find(cssSelector(RESET_FILTERS_BUTTON)).get

      filters._open
      eventually { click on cleanBtt }

      this
    }

    def areAllClear:Boolean = {
      var areClear = true
      _open
      logger.debug("filters are open")

      val supMoneyFilter = entryFee.getSuperior
      val infMoneyFilter = entryFee.getInferior
      areClear = areClear && supMoneyFilter == MAX_ENTRY_MONEY
      logger.debug(s"Superior money filter is $supMoneyFilter, should be $MAX_ENTRY_MONEY", areClear)
      areClear = areClear && infMoneyFilter == 0
      logger.debug(s"Inferior money filter is $infMoneyFilter, should be 0", areClear)

      val searchFilter = textField(cssSelector(FILTERS_PANEL_SEARCH)).value
      areClear = areClear && searchFilter == ""
      logger.debug(s"Search input should be clear and '$searchFilter'", areClear)

      areClear = areClear && !tournaments.free.isSelected
      logger.debug(s"Filter Free should be unchecked 2", areClear)
      areClear = areClear && !tournaments.league.isSelected
      logger.debug(s"Filter League should be unchecked 2", areClear)
      areClear = areClear && !tournaments.fiftyFifty.isSelected
      logger.debug(s"Filter Fifty Fifty should be unchecked 2", areClear)
      areClear = areClear && !tournaments.headToHead.isSelected
      logger.debug(s"Filter Head to Head should be unchecked 2", areClear)

      //Thread.sleep(5000)
      areClear = areClear && !salaryLimit.beginner.isSelected
      logger.debug(s"Filter Beginner should be unchecked", areClear)
      areClear = areClear && !salaryLimit.standard.isSelected
      logger.debug(s"Filter Standard should be unchecked", areClear)
      areClear = areClear && !salaryLimit.expert.isSelected
      logger.debug(s"Filter Expert should be unchecked", areClear)

      _close
      areClear
    }
    def search(name: String) = {
      if (resolution != Resolution.SMALL) {
        eventually { textField(cssSelector(FILTERS_PANEL_SEARCH)).value = name }
      } else {
        unavailableFunctionOnResolution("searchContestByName")
      }
      this
    }


    class BooleanFilter(cssSelString:String) {
      def check = checkbox(cssSelString)
      def label = find(cssSelector(s"label[for='${cssSelString}']")).get

      def isSelected: Boolean = check.isSelected
      def isEnabled: Boolean = {
        val attr = check.attribute("disabled")
        !attr.isDefined || attr.get == "true"
      }
      def isDisplayed: Boolean = label.isDisplayed

      def doClick = {
        _open
        eventually { assert(isDisplayed) }
        click on label
        this
      }
    }
    class OrderByFilter(cssSelString:String) {
      def doClick = {
        click on find(cssSelector(cssSelString)).get
        this
      }
    }

    object competition {
      def spanishLeague  :BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_SPANISH_LEAGUE)
      def premiereLeague :BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_PREMIERE_LEAGUE)
      def championsLeague:BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_CHAMPIONS_LEAGUE)
    }
    object tournaments {
      def free      :BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_FREE_CHECK)
      def headToHead:BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK)
      def league    :BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_LEAGUE_CHECK)
      def fiftyFifty:BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK)
    }
    object salaryLimit {
      def beginner:BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_BEGINNER_SALARY)
      def standard:BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_STANDARD_SALARY)
      def expert  :BooleanFilter = new BooleanFilter(FILTERS_PANEL_FILTER_EXPERT_SALARY)
    }
    object orderBy {
      def name = new OrderByFilter(SORT_BY_NAME)
      def entryFee = new OrderByFilter(SORT_BY_ENTRY_FEE)
      def startTime = new OrderByFilter(SORT_BY_START_TIME)
    }
    object entryFee {
      def set(inf: Int, sup: Int) = {
        _open
/*
        val sliderWidth = find(cssSelector(SLIDER_RANGE)).get.underlying.getSize.width - 1
        val inferior = find(cssSelector(SLIDER_RANGE_INFERIOR)).get.underlying
        val superior = find(cssSelector(SLIDER_RANGE_SUPERIOR)).get.underlying
        val infBound = inf - getInferior
        val supBound = sup - getSuperior

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
*/
        sliderSetVal(inf, sup, SLIDER_RANGE)
        this
      }
      def getInferior :Int = {
        val inferiorTextNode = find(cssSelector(SLIDER_RANGE_TEXT_INFERIOR)).get
        var text = ""
        var n:Integer = 0

        eventually { text = inferiorTextNode.text }

        // Ignoramos el comienzo de la cadena "MIN: "
        n = Integer.parseInt( text.substring(6, text.length).replace(",", "") )

        n
      }
      def getSuperior :Int = {
        val superiorTextNode = find(cssSelector(SLIDER_RANGE_TEXT_SUPERIOR)).get
        var n:Integer = 0

        // Ignoramos el comienzo de la cadena "MAX: "
        eventually { n = Integer.parseInt( superiorTextNode.text.substring(6, superiorTextNode.text.length).replace(",", "") ) }

        n
      }
    }
  }




  /**************** COMMON METHODS ****************/

  override def isAt = {
    var _isAt = true

    _isAt = new MenuBar(resolution).isAt
    logger.debug("Menu bar: ", _isAt)
    _isAt = _isAt && new FooterBar(resolution).isAt
    logger.debug("Footer bar: ", _isAt)

    _isAt = _isAt && (currentUrl == url || currentUrl == SharedPage.baseUrl || currentUrl == SharedPage.baseUrl + "/")
    logger.debug("URL is " + currentUrl + ", should be " + url, _isAt)
    _isAt = _isAt && pageTitle == TITLE
    logger.debug("Title is " + pageTitle + ", should be " + TITLE, _isAt)


    _isAt = _isAt && isElemDisplayed(PROMOS_COMPONENT)

    //if (resolution != Resolution.SMALL) {
    _isAt = _isAt && isElemDisplayed(CONTEST_LIST_CONTAINER)
    /*} else {
      _isAt = _isAt && isElemDisplayed(CONTEST_LIST_MENU_MOBILE)
    }*/

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

    /*
    if (resolution == Resolution.SMALL) {
      isDefault = isDefault && isElemDisplayed(CONTEST_LIST_MENU_MOBILE)

    } else {*/
      val currentPage = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER).getCurrentPage
      isDefault = isDefault && currentPage == 1
      logger.debug(s"Paginator page is $currentPage, should be 1", isDefault)

      isDefault = isDefault && filters.areAllClear
      logger.debug("Are all filters clear", isDefault)

      val currNumOfContests = getNumberOfContests
      isDefault = isDefault && currNumOfContests == numberOfContest
      logger.debug("Number of contests: current(" + currNumOfContests + "), expected(" + numberOfContest + ")", isDefault)
      isDefault = isDefault && areContestsOrderedByStartTime
      logger.debug("Contests are ordered by start time", isDefault)
    //}

    isDefault
  }

  def isNotLoggedIn: Boolean = new MenuBar(resolution).isLoginBar

  def isLoggedIn: Boolean = new MenuBar(resolution).isLoggedBar


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
/*
  def setEntryFeeFilter(inf: Int, sup: Int) = {
    filters._open
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
  }*/
/*
  def clickFreeContestFilter = {
    filters.tournaments.free.doClick
  }

  def clickLeagueContestFilter = {
    filters.tournaments.league.doClick
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
*/
/*
  def searchContestByName(name: String) = {
    if (resolution != Resolution.SMALL) {
      eventually { textField(cssSelector(FILTERS_PANEL_SEARCH)).value = name }
    } else {
      unavailableFunctionOnResolution("searchContestByName")
    }
    this
  }
  */
/*
  def getInferiorMoneyFilter :Int = {
    val inferiorTextNode = find(cssSelector(SLIDER_RANGE_TEXT_INFERIOR)).get
    var text = ""
    var n:Integer = 0

    eventually {
      text = inferiorTextNode.text
    }

    n = Integer.parseInt( text.substring(5, text.length - 1).replace(",", "") )

    n
  }

  def getSuperiorMoneyFilter :Int = {
    val superiorTextNode = find(cssSelector(SLIDER_RANGE_TEXT_SUPERIOR)).get
    var n:Integer = 0

    eventually { n = Integer.parseInt( superiorTextNode.text.substring(5, superiorTextNode.text.length - 1).replace(",", "") ) }

    n
/*
    logger.error("Forzando devolver un 6 para continuar el paso de test")
    6*/
  }
*/
  /*trait EntryFeeFilter {
    def inferiorBound: Boolean = checkbox(cssSelString).isSelected
    def superiorBound: Boolean = (!checkbox(cssSelString).attribute("disabled").isDefined) || (checkbox(FILTERS_PANEL_FILTER_FREE_CHECK).attribute("disabled").get == "true")
    protected val cssSelString: String
  }*/

  //val filters = Filters

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
    paginator.goToFirstPage
    val pagesCount = paginator.getNumberOfPages
    var areOrdered: Boolean = fastLobby_ContestAreOrderedByName

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByName
        logger.debug(s"page: $i, is ordered: ", areOrdered)
        if(!areOrdered) scala.util.control.Breaks.break()
      }
    }

    areOrdered
  }

  def areContestsOrderedByEntryFee: Boolean = {
    val paginator = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER)
    paginator.goToFirstPage
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
    val currPage = paginator.getCurrentPage
    paginator.goToFirstPage
    val pagesCount = paginator.getNumberOfPages
    logger.debug(s"Num pages: $pagesCount")
    var areOrdered = fastLobby_ContestAreOrderedByStartTime
    logger.debug(s"Contest are ordered at current page", areOrdered)

    scala.util.control.Breaks.breakable {
      for (i <- 1 to pagesCount) {
        logger.debug(s"go to next page ($i)")
        paginator.goToNextPage
        areOrdered = areOrdered && fastLobby_ContestAreOrderedByStartTime
        logger.debug(s"Contest are ordered at current page", areOrdered)
        if(!areOrdered) scala.util.control.Breaks.break()
      }
    }

    paginator.goToPage(currPage)
    logger.debug(s"Return to $currPage")

    logger.debug(s"Contest are ordered at all", areOrdered)
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
    logger.debug(s"currentPage is $currPage")

    var pages = 0
    eventually {
      pages = paginator.getNumberOfPages
      logger.debug(s"Number of pages is $pages")
      logger.debug("Goes to last page")
      paginator.goToLastPage
      assert(paginator.getCurrentPage == pages, "paginator does not go to last page")
    }

    // Esta parece la unica rapida y efectiva
    val lastPageRows = fastCountByCssSelector(CONTEST_ROW_CONTAINER)
    logger.debug(s"Number of Rows are: $lastPageRows")
    paginator.goToPage(currPage)
    logger.debug(s"Goes to page $currPage")

    // return
    (pages - 1) * ROWS_PER_PAGE + lastPageRows
  }

  def openContestDescription(idx:Int) = {

    val page:Int = (Math.floor( (idx - 1) / 10) + 1).toInt
    new PaginatorControl(resolution, CONTEST_LIST_CONTAINER).goToPage(page)
    val row:Int = ((idx-1) % 10) + 1
    logger.debug(s"Calculated page[$page] and row[$row]), ${CONTEST_ROW_NAME(row)}")

    eventually (timeout(60 seconds)) { isElemDisplayed(CONTEST_ROW_NAME(row)) }
    logger.debug(s"Contest row name at row($row) exists")
    eventually (timeout(60 seconds)) {
      fastClicksByCssSelector(1, CONTEST_ROW_NAME(row))
      //click on find(cssSelector( CONTEST_ROW_NAME(row) )).get
    }
    logger.debug(s"Has been clicked in name")
    this

  }

  def goToFirstMatchesPage = new PaginatorControl(resolution, CONTEST_LIST_CONTAINER).goToFirstPage



}








