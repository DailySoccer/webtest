package unusual.pages.components.page


import unusual.model.Resolution
import unusual.pages.SharedPage

class EnterContestDescriptionTab(res:Resolution) extends SharedPage(res) {

  val CONTENT = "contest-info"

  val MATCHES_PANE = ".matches-involved"
  val MATCHES_PANE_MATCH = MATCHES_PANE + " .match"

  val RULES_DESCRIPTION = CONTENT + " .rules-description"

  val CONTESTANTS_PANE = CONTENT + " .contestant-list"
  val CONTESTANTS_PANE_CONTESTANT = CONTESTANTS_PANE + " .contestant-element"

  val PRIZES_PANE = CONTENT + " #prizes-list"
  val PRIZES_PANE_PRIZE = PRIZES_PANE + " .prize-element-wrapper"


  override def open = {
    logger.error("Trying to open a control.")
    this
  }

  override def isAt = {
    isElemDisplayed(MATCHES_PANE) &&
      isElemDisplayed(RULES_DESCRIPTION) &&
      isElemDisplayed(CONTESTANTS_PANE) &&
      isElemDisplayed(PRIZES_PANE)
  }

  def countMatches: Int = {
    val elems = findAll(cssSelector(MATCHES_PANE_MATCH))
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

}