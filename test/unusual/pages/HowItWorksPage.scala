package unusual.pages

import unusual.model.Resolution
import unusual.model.pageStates.EnterContestState

/**
 * Created by gregorioiniestaovejero on 9/4/15.
 */
class HowItWorksPage(res: Resolution) extends SharedPage(res) {

  val CONTENT = "#helpInfo"
  val TABS_CONTAINER = CONTENT + " .help-info-tabs"
  val HOW_IT_WORKS_TAB = CONTENT + " #how-works-content"
  val RULES_AND_SCORING_TAB = CONTENT + " #rules-scores-content"

  override def isAt = {
    var _isAt = true

    _isAt = _isAt && isElemDisplayed(CONTENT)
    logger.debug("Global container:  ", _isAt)
    _isAt = _isAt && isElemDisplayed(TABS_CONTAINER)
    logger.debug("Tabs container ", _isAt)
    _isAt = _isAt && existsElem(HOW_IT_WORKS_TAB)
    logger.debug("How it works tab container exists", _isAt)
    _isAt = _isAt && existsElem(RULES_AND_SCORING_TAB)
    logger.debug("Rules and scoring tab container exists", _isAt)
    _isAt = _isAt && (isElemDisplayed(RULES_AND_SCORING_TAB) || isElemDisplayed(HOW_IT_WORKS_TAB))
    logger.debug("How it works tab or Rules and scoring tab should be displayed ", _isAt)


    _isAt
  }

}
