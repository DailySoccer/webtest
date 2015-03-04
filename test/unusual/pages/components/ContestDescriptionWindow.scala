package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.{components, SharedPage}

class ContestDescriptionWindow(res:Resolution) extends SharedPage(res) {

  val CONTENT = "contest-info"
  val MODAL = "#infoContestModal"

  val HEADER = CONTENT + " contest-header"

  val CONTEST_NAME = HEADER + " .contest-name"
  val CONTEST_DESCRIPTION = HEADER + " .contest-explanation"

  val CONTEST_ENTRY_FEE = HEADER + " .contest-price .contest-coins-content span"
  val CONTEST_PRIZE = HEADER + " .contest-prize .contest-coins-content span"

  val CLOSE_BUTTON = HEADER + " button.close"

  val BODY = CONTENT + " .modal-info-content"

  val TAB_ACTIVE = ".active"
  val TAB_LINK:Map[String, String] = Map[String, String](
    ContestDescriptionWindow.INFO_TAB -> (ContestDescriptionWindow.INFO_TAB + " a"),
    ContestDescriptionWindow.CONTESTANTS_TAB -> (ContestDescriptionWindow.CONTESTANTS_TAB + " a"),
    ContestDescriptionWindow.PRIZES_TAB -> (ContestDescriptionWindow.PRIZES_TAB + " a")
  )
  val CONTESTANTS_TAB_LINK = ContestDescriptionWindow.CONTESTANTS_TAB + " a"
  val PRIZES_TAB_LINK      = ContestDescriptionWindow.PRIZES_TAB + " a"

  val INFO_PANE = ".matches-involved"
  val INFO_PANE_MATCH = INFO_PANE + " .match"

  val RULES_DESCRIPTION = CONTENT + " .rules-description"

  val CONTESTANTS_PANE = CONTENT + " .contestant-list"
  val CONTESTANTS_PANE_CONTESTANT = CONTESTANTS_PANE + " .contestant-element"

  val PRIZES_PANE = CONTENT + " #prizes-list"
  val PRIZES_PANE_PRIZE = PRIZES_PANE + " .prize-element-wrapper"

  val ENTER_CONTEST_BUTTON = CONTENT + " #btn-go-enter-contest"



  override def open = {
    logger.error("Trying to open a control.")
    this
  }

  override def isAt = {
    isElemDisplayed(INFO_PANE) &&
      isElemDisplayed(RULES_DESCRIPTION) &&
      existsElem(CONTESTANTS_PANE) &&
      existsElem(PRIZES_PANE)
  }

  def close = {
    if (resolution == Resolution.BIG){
      if (isElemDisplayed(CLOSE_BUTTON)) {
        eventually { click on find(cssSelector(CLOSE_BUTTON)).get }
      } else {
        fail("Close button is not displayed")
      }
    } else {
      unavailableFunctionOnResolution("close")
    }

    this
  }

  def changeToTab(tab:String): ContestDescriptionWindow = {
    if (resolution != Resolution.BIG) {
      unavailableFunctionOnResolution("changeToTab(" + tab + ")")
      return this
    }

    if (tab == ContestDescriptionWindow.INFO_TAB ||
        tab == ContestDescriptionWindow.CONTESTANTS_TAB ||
        tab == ContestDescriptionWindow.PRIZES_TAB) {
      if (isElemDisplayed(tab)) {
        click on find(cssSelector(TAB_LINK(tab))).get
      } else {
        fail(tab + " is not displayed")
      }
    } else {
      fail(tab + " is not a valid tab")
    }
    this
  }

  def countMatches: Int = {
    val elems = findAll(cssSelector(INFO_PANE_MATCH))
    elems.length
  }

  def countContestants: Int = {
    val elems = findAll(cssSelector(CONTESTANTS_PANE_CONTESTANT))
    elems.length
  }

  def countPrizes: Int = {
    val elems = findAll(cssSelector(PRIZES_PANE_PRIZE))
    elems.length
  }

  def activeTab: String = {
    var tab = ContestDescriptionWindow.INFO_TAB
    if (find(cssSelector(tab + TAB_ACTIVE)) == None) {
      tab = ContestDescriptionWindow.CONTESTANTS_TAB
      if (find(cssSelector(tab + TAB_ACTIVE)) == None) {
        tab = ContestDescriptionWindow.PRIZES_TAB
        if (find(cssSelector(tab + TAB_ACTIVE)) == None) {
          fail("No tab is active.")
        }
      }
    }

    tab
  }

  def enterContest = {
    if (isElemDisplayed(ENTER_CONTEST_BUTTON)) {
      click on find(cssSelector(ENTER_CONTEST_BUTTON)).get
    } else {
      if (resolution == Resolution.BIG) {
        fail(ENTER_CONTEST_BUTTON + " is not displayed")
      } else {
        unavailableFunctionOnResolution("enterContest")
      }
    }
    this
  }

  def getContestName:String = {
    var text = ""
    eventually {
      logger.debug(s"Looking at contest name {$CONTEST_NAME}")
      text = find(cssSelector(CONTEST_NAME)).get.text
      logger.debug(s"Contest name: $text")
    }
    text
  }
  def getContestDescription:String = {
    var text = ""
    eventually {
      logger.debug(s"Looking at contest description {$CONTEST_DESCRIPTION}")
      text = find(cssSelector(CONTEST_DESCRIPTION)).get.text
      logger.debug(s"Contest description: $text")
    }
    text
  }
  def getContestEntryFee:String = {
    var text = ""
    eventually {
      logger.debug(s"Looking at contest entry fee {$CONTEST_ENTRY_FEE}")
      text = find(cssSelector(CONTEST_ENTRY_FEE)).get.text
      text = text.substring(0, text.length())
      logger.debug(s"Contest entry fee: $text")
    }
    text
    //eventually { find(cssSelector(CONTEST_ENTRY_FEE)).get.text }
  }
  def getContestPrize:String = {
    var text = ""
    eventually {
      logger.debug(s"Looking at contest prize {$CONTEST_PRIZE}")
      text = find(cssSelector(CONTEST_PRIZE)).get.text
      text = text.substring(0, text.length())
      logger.debug(s"Contest prize: $text")
    }
    text
    //eventually { find(cssSelector(CONTEST_PRIZE)).get.text }
  }

}

object ContestDescriptionWindow {

  val TABS_CONTAINER = "#modalInfoContestTabs"
  val INFO_TAB        = TABS_CONTAINER + " li:nth-child(" + 1 + ")"
  val CONTESTANTS_TAB = TABS_CONTAINER + " li:nth-child(" + 2 + ")"
  val PRIZES_TAB      = TABS_CONTAINER + " li:nth-child(" + 3 + ")"

}
