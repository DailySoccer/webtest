package pages

import model._
import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByCssSelector
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.Action


class LobbyPage extends SharedPage {

  /**
   * Additional tests:
   *  - Order by...
   *  - Filters Conjunction
   *  - Click on a contest to see it
   *      - Click on contenders
   *      - Click on prizes
   *      - Click on information
   *      - Click on EnterContest
   *  - Search a nonexistent contest
   *  -
   */

  val TITLE   = "Daily Soccer"

  val url = SharedPage.baseUrl + "/#/lobby"

  val PROMOS_COMPONENT_ID = "promosComponent"
  val CONTEST_LIST_CONTAINER = "contestsListRoot"
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
  val FILTERS_PANEL_SEARCH_TEXT_1 = "2014/06/14"

  val FILTERS_PANEL_ID = "filtersPanel"
  val FILTERS_PANEL_FILTER_FREE = "label[for=\"filtroFree\"]"
  val FILTERS_PANEL_FILTER_LEAGUE = "label[for=\"filtroLiga\"]"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY = "label[for=\"filtroFiftyFifty\"]"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD = "label[for=\"filtroHeadToHead\"]"

  // NUMBER OF FILTERED CONTESTS
  val N_CONTESTS_NO_FILTER = 36
  val N_CONTESTS_FREE = 6
  val N_CONTESTS_LEAGUE = 18
  val N_CONTESTS_FIFTY_FIFTY = 6
  val N_CONTESTS_HEAD_TO_HEAD = 6
  val N_CONTESTS_SEARCH_1 = 18


  val MAX_ENTRY_MONEY = 100

  def open = {
    go to url
    this
  }

  override def isAt = {
    currentUrl should be (url)
    pageTitle should be (TITLE)

    new MenuBar().isAt
    new FooterBar().isAt
    eventually {
      find(id(PROMOS_COMPONENT_ID)) should be ('defined)
      find(id(CONTEST_LIST_CONTAINER)) should be ('defined)
    }
    // eventually { find(id("legend")).get.text should be (LEGEND) }
    // find(name(FORM_EMAIL)) should be ('defined)
    // find(name(FORM_PASSWORD)) should be ('defined)

    // find(cssSelector("form input[name='Email']")) should be ('defined)
    // find(cssSelector("form input[name='Password']")) should be ('defined)

    this
  }

  def isNotLoggedIn = {
    new MenuBar().isLoginBar
  }
  def isLoggedIn = {
    new MenuBar().isLoggedBar
  }

  def checkEntryMinFeeFilterControl = {
    cleanFilters
    assert(getInferiorMoneyFilter == 0)
    setMoneyFilter(25, MAX_ENTRY_MONEY)
    assert(getInferiorMoneyFilter == 25)
    cleanFilters
    assert(getInferiorMoneyFilter == 0)
    this
  }

  def checkEntryMaxFeeFilterControl = {
    cleanFilters
    assert(getSuperiorMoneyFilter == MAX_ENTRY_MONEY)
    setMoneyFilter(0, 75)
    assert(getSuperiorMoneyFilter == 75)
    cleanFilters
    assert(getSuperiorMoneyFilter == MAX_ENTRY_MONEY)
    this
  }

  def checkEntryBothFeeFilterControl = {
    cleanFilters
    assert(getSuperiorMoneyFilter == MAX_ENTRY_MONEY)
    assert(getInferiorMoneyFilter == 0)
    setMoneyFilter(0, 75)
    assert(getSuperiorMoneyFilter == 75)
    assert(getInferiorMoneyFilter == 0)
    setMoneyFilter(25, 75)
    assert(getSuperiorMoneyFilter == 75)
    assert(getInferiorMoneyFilter == 25)
    cleanFilters
    assert(getSuperiorMoneyFilter == MAX_ENTRY_MONEY)
    assert(getInferiorMoneyFilter == 0)
    this
  }

  def allContest = {
    cleanFilters
    checkNumberOfContests(N_CONTESTS_NO_FILTER)
    this
  }

  def freeContests = {
    cleanFilters
    applyFilter(FILTERS_PANEL_FILTER_FREE)
    checkNumberOfContests(N_CONTESTS_FREE)
    checkContestsAre(N_CONTESTS_FREE, "Free") // Seguramente haya que quitarlo
    checkContestsEntryValues(N_CONTESTS_FREE, 0, 0)

    this
  }

  def leagueContests = {
    cleanFilters
    applyFilter(FILTERS_PANEL_FILTER_LEAGUE)
    checkNumberOfContests(N_CONTESTS_LEAGUE)
    checkContestsAre(N_CONTESTS_LEAGUE, "Liga")
    checkContestsEntryValues(N_CONTESTS_LEAGUE, 0, MAX_ENTRY_MONEY)
    this
  }

  def fiftyFiftyContests = {
    cleanFilters
    applyFilter(FILTERS_PANEL_FILTER_FIFTY_FIFTY)
    checkNumberOfContests(N_CONTESTS_FIFTY_FIFTY)
    checkContestsAre(N_CONTESTS_FIFTY_FIFTY, "50/50")
    checkContestsEntryValues(N_CONTESTS_FIFTY_FIFTY, 0, MAX_ENTRY_MONEY)
    this
  }

  def headToHeadContests = {
    cleanFilters
    applyFilter(FILTERS_PANEL_FILTER_HEAD_TO_HEAD)
    checkNumberOfContests(N_CONTESTS_HEAD_TO_HEAD)
    checkContestsAre(N_CONTESTS_HEAD_TO_HEAD, "Head-to-head")
    checkContestsEntryValues(N_CONTESTS_HEAD_TO_HEAD, 0, MAX_ENTRY_MONEY)
    this
  }

  def freeContestsWithMinFilter = {
    cleanFilters
    applyFilter(FILTERS_PANEL_FILTER_FREE)
    setMoneyFilter(1, MAX_ENTRY_MONEY)

    checkNumberOfContests(0)

    this
  }

  def playFirstContest = {
    val cssSel  = CONTEST_ROW_CONTAINER_CLASS + " " + CONTEST_COLUMN_ACTION_CLASS + " button"

    eventually { click on find(cssSelector(cssSel)).get }
    this
  }

  def searchContest = {
    val cssSel  = "#" + FILTERS_PANEL_SEARCH_ID + " input"

    eventually { textField(cssSelector(cssSel)).value = FILTERS_PANEL_SEARCH_TEXT_1 }
    eventually { checkNumberOfContests(N_CONTESTS_SEARCH_1) }
    this
  }



  private def cleanFilters = {
    openFilters
    var cleanBtt = find(cssSelector(".reset-button-wrapper .btn-reset")).get

    eventually {
      click on cleanBtt
    }
    setMoneyFilter(0, MAX_ENTRY_MONEY) // TODO: QUITAR CUANDO ESTE ARREGLADO

    this
  }

  private def applyFilter(forId : String) = {

    openFilters
    val filter = find(cssSelector(forId)).get

    eventually {
      filter.isDisplayed
    }
    click on filter
    click on filter // TODO: QUITAR CUANDO ESTE ARREGLADO

    this
  }

  private def checkContestsEntryValues(amountRows: Int, min: Double, max : Double) = {
    val cssSelPre  = CONTEST_ROW_CONTAINER_CLASS + ":nth-child("
    val cssSelPost = ") " + CONTEST_COLUMN_FEE_CLASS + " " + CONTEST_FEE_CLASS
    for (i <- 1 to amountRows) {
      eventually {

        var rowText = find(cssSelector(cssSelPre + i + cssSelPost)).get.text
        var entryNum = java.lang.Double.parseDouble(rowText.substring(0, rowText.length - 1))

        assert(entryNum <= max && entryNum >= min)
      }
    }

    this
  }

  private def checkContestsAre(amountRows: Int, name : String) = {
    val cssSelPre  = CONTEST_ROW_CONTAINER_CLASS + ":nth-child("
    val cssSelPost = ") " + CONTEST_COLUMN_NAME_CLASS + " " + CONTEST_DESCRIPTION_CLASS
    for(i <- 1 to amountRows){
      eventually {

        var rowText = find(cssSelector(cssSelPre + i + cssSelPost)).get.text
        rowText should startWith (name)

      }
    }

    this
  }

  private def checkNumberOfContests(n : Int) = {

    eventually {
      var rows = findAll(cssSelector(CONTEST_ROW_CONTAINER_CLASS))
      assert(rows.length == n)
    }

    this
  }

  private def getInferiorMoneyFilter :Int = {
    val inferiorTextNode = find(cssSelector("#filtersPanel .filter-column-entry-fee .entry-fee-value-min")).get
    var n:Integer = 0

    eventually {
      n = Integer.parseInt( inferiorTextNode.text.substring(5, inferiorTextNode.text.length - 1) )
    }

    n
  }

  private def getSuperiorMoneyFilter :Int = {
    val superiorTextNode = find(cssSelector("#filtersPanel .filter-column-entry-fee .entry-fee-value-max")).get
    var n:Integer = 0

    eventually {
      n = Integer.parseInt( superiorTextNode.text.substring(5, superiorTextNode.text.length - 1) )
    }

    n
  }

  private def setMoneyFilter(inf: Int, sup: Int) = {
    val sliderWidth = find(cssSelector("#slider-range")).get.underlying.getSize().width
    val inferior = find(cssSelector("#slider-range .noUi-origin:nth-child(1) div")).get.underlying
    val superior = find(cssSelector("#slider-range .noUi-origin:nth-child(2) div")).get.underlying
    val infBound = inf - getInferiorMoneyFilter
    val supBound = sup - getSuperiorMoneyFilter

    openFilters

    var dragAndDrop:Action = new Actions(driver)
      .clickAndHold(inferior)
      .moveByOffset(Math.round(sliderWidth * infBound / MAX_ENTRY_MONEY), 0)
      .release(inferior)
      .build()

    dragAndDrop.perform()

    dragAndDrop = new Actions(driver)
      .clickAndHold(superior)
      .moveByOffset(Math.round(sliderWidth * supBound / MAX_ENTRY_MONEY), 0)
      .release(superior)
      .build()

    dragAndDrop.perform()

    this
  }

  private def openFilters = {
    val panel = find(id(FILTERS_PANEL_ID)).get
    if (!panel.isDisplayed) {
      val desktopFilter = find(id(FILTERS_BUTTON_ID("desktop"))).get
      val mobileFilter = find(id(FILTERS_BUTTON_ID("mobile"))).get
      eventually {
        if (!desktopFilter.isDisplayed) {
          click on mobileFilter
        } else {
          click on desktopFilter
        }
      }
    }
    this
  }

  // NOT USED, DELETEABLE

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








