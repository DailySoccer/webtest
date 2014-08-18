package pages

import model._
import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByCssSelector

class LobbyPage extends SharedPage {
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

  val FILTERS_HEADER_ID = "contestSortsFilters"
  val FILTERS_BUTTON_CLASS = ".filterToggler"

  val FILTERS_PANEL_ID = "filtersPanel"
  val FILTERS_PANEL_FILTER_FREE = "label[for=\"filtroFree\"]"

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


  def allContest = {
    checkNumberOfContests(36)
  }

  def freeContests = {

    openFilters

    eventually {
      click on find(cssSelector(FILTERS_PANEL_FILTER_FREE)).get
    }
    checkNumberOfContests(6)
  }

  private def checkNumberOfContests(n:Int) = {

    eventually {
      var rows = findAll(cssSelector(CONTEST_ROW_CONTAINER_CLASS + " " + CONTEST_PRIZE_CLASS))
      assert(rows.length == n)
    }

    this
  }

  private def openFilters = {
    val panel = find(id(FILTERS_PANEL_ID)).get
    if (!panel.isDisplayed) {
      println("PANEL NO ESTA DISPLAYED " + "#" + FILTERS_HEADER_ID + " " + FILTERS_BUTTON_CLASS)
      // println(find(cssSelector("#" + FILTERS_HEADER_ID + " " + FILTERS_BUTTON_CLASS)).get)
      eventually {
        click on find(cssSelector("#" + FILTERS_HEADER_ID + " " + FILTERS_BUTTON_CLASS)).get
      }3º
      println("PANEL DEBERIA ESTAR DISPLAYED")
    }
  }

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








