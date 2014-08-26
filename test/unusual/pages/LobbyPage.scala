package unusual.pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByCssSelector
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.Action
import unusual.model._

import scala.util.control.Exception


class LobbyPage(res:Resolution)  extends SharedPage {

  val url = SharedPage.baseUrl + "/#/lobby"
  val resolution: Resolution = res

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
   *  - Salary limit: beginner, standard, expert
   *  - Competition: europa league, spanish league, english league...
   */

  val TITLE   = "Daily Soccer"

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

  val FILTERS_PANEL_ID = "filtersPanel"
  val FILTERS_PANEL_FILTER_FREE_CHECK = "filtroFree"
  val FILTERS_PANEL_FILTER_LEAGUE_CHECK = "filtroLiga"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK = "filtroFiftyFifty"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK = "filtroHeadToHead"
  val FILTERS_PANEL_FILTER_FREE_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_FREE_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_LEAGUE_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_LEAGUE_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_FIFTY_FIFTY_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK + "\"]"
  val FILTERS_PANEL_FILTER_HEAD_TO_HEAD_LABEL = "label[for=\"" + FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK + "\"]"

  val MAX_ENTRY_MONEY = 100

  def open = {
    go to url
    this
  }

  override def isAt = {
    new MenuBar(resolution).isAt
    new FooterBar(resolution).isAt

    eventually {
      currentUrl should be (url)
      pageTitle should be (TITLE)

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
    new MenuBar(resolution).isLoginBar
  }
  def isLoggedIn = {
    new MenuBar(resolution).isLoggedBar
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

  def checkContestsEntryValues(amountRows: Int, min: Double, max : Double) = {
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

  def checkNumberOfContests(n : Int) = {
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

    // Esta parece la unica rapida y efectiva
    eventually (timeout(2 seconds)) {
      fastCountByCssSelector("#contestsListRoot .contest-row") should be(n)
    }
    this
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

  def checkAllFiltersAreClear = {
    openFilters
    assert(getSuperiorMoneyFilter == MAX_ENTRY_MONEY)
    assert(getInferiorMoneyFilter == 0)

    val searchCssSel  = "#" + FILTERS_PANEL_SEARCH_ID + " input"
    assert( textField(cssSelector(searchCssSel)).value == "")

    checkbox(FILTERS_PANEL_FILTER_FREE_CHECK).isSelected should be (false)
    checkbox(FILTERS_PANEL_FILTER_LEAGUE_CHECK).isSelected should be (false)
    checkbox(FILTERS_PANEL_FILTER_FIFTY_FIFTY_CHECK).isSelected should be (false)
    checkbox(FILTERS_PANEL_FILTER_HEAD_TO_HEAD_CHECK).isSelected should be (false)

    this
  }

  private def applyFilter(forId : String) = {

    openFilters
    val filter = find(cssSelector(forId)).get

    eventually {
      filter.isDisplayed
    }
    click on filter

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








