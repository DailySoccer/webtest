package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.SharedPage

class ContestDescriptionWindow(res:Resolution) extends SharedPage(res) {

  val CONTENT = "contest-info"
  val MODAL = "#infoContestModal"

  val HEADER = CONTENT + " .modal-info-head"
  val BODY = CONTENT + " .modal-info-content"

  val TAB_ACTIVE = " .active"

  val INFO_PANE = ".matches-involved"
  val INFO_PANE_MATCH = INFO_PANE + " .match"

  val RULES_DESCRIPTION = CONTENT + " .rules-description"

  val CONTESTANTS_PANE = CONTENT + " .contestant-list"
  val CONTESTANTS_PANE_CONTESTANT = CONTESTANTS_PANE + " .contestant-element"

  val PRIZES_PANE = CONTENT + " #prizes-list"
  val PRIZES_PANE_PRIZE = PRIZES_PANE + " .prize-element-wrapper"

  val ENTER_CONTEST_BUTTON = CONTENT + " .buton-place button"

  val CLOSE_BUTTON = CONTENT + " .modal-info-head button.close"

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
        click on find(cssSelector(CLOSE_BUTTON)).get
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
        click on find(cssSelector(tab)).get
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

}

object ContestDescriptionWindow {

  val TABS_CONTAINER = "#modalInfoContestTabs"
  val INFO_TAB        = TABS_CONTAINER + " li:nth-child(" + 1 + ")"
  val CONTESTANTS_TAB = TABS_CONTAINER + " li:nth-child(" + 2 + ")"
  val PRIZES_TAB      = TABS_CONTAINER + " li:nth-child(" + 3 + ")"

}
