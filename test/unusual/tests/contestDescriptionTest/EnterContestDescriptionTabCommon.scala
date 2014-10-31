package unusual.tests.contestDescriptionTest

import unusual.model.Resolution
import unusual.pages.components.page.EnterContestDescriptionTab
import unusual.pages.components.{ContestDescriptionWindow}
import unusual.tests._
import unusual.pages._
import unusual.model._

class EnterContestDescriptionTabCommon(cont: Contest, res:Resolution) extends SharedTest(res) {

  var contest = cont
  var _descriptionTabInstance:EnterContestDescriptionTab = null
  def descriptionTab:EnterContestDescriptionTab = {
    if(_descriptionTabInstance == null) {
      _descriptionTabInstance = goToEnterContestDescriptionTab
    }
    _descriptionTabInstance
  }

  def numberOfMatches: Unit = {
    assert(descriptionTab.countMatches == contest.numMatches)
  }

  def numberOfContestants: Unit = {
    assert(descriptionTab.countContestants == contest.numContestants)
  }

  def numberOfPrizes: Unit = {
    assert(descriptionTab.countPrizes == contest.numPrizes)
  }


  private def goToEnterContestDescriptionTab:EnterContestDescriptionTab = {
    val state = new EnterContestState
    state.contest = contest
    logger.debug("go to enter contest")
    val enterContestPage = goToEnterContest(state)
    val desc = new EnterContestDescriptionTab(status.resolution)
    logger.debug("change to info tab")
    eventually {
      enterContestPage.changeToInfoTab
      assert(desc.isAt)
    }

    desc
  }

}
