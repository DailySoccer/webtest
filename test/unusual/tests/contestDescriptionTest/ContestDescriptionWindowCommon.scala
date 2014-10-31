package unusual.tests.contestDescriptionTest

import unusual.model.Resolution
import unusual.pages.components.{ContestDescriptionWindow}
import unusual.tests._
import unusual.pages._
import unusual.model._

class ContestDescriptionWindowCommon(cont: Contest, res:Resolution) extends SharedTest(res) {

  var contest = cont
  var _contestDescriptionWindowInstance:ContestDescriptionWindow = null
  def contestDescriptionWindow:ContestDescriptionWindow = {
    if(_contestDescriptionWindowInstance == null) {
      _contestDescriptionWindowInstance = goToLobbyContestDescription
    }
    _contestDescriptionWindowInstance
  }

  def changeTabs: Unit = {
    if (status.resolution == Resolution.BIG) {
      eventually {
        contestDescriptionWindow.changeToTab(ContestDescriptionWindow.INFO_TAB)
        assert(contestDescriptionWindow.activeTab == ContestDescriptionWindow.INFO_TAB)
      }
      contestDescriptionWindow.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB)
      assert(contestDescriptionWindow.activeTab == ContestDescriptionWindow.CONTESTANTS_TAB)
      contestDescriptionWindow.changeToTab(ContestDescriptionWindow.PRIZES_TAB)
      assert(contestDescriptionWindow.activeTab == ContestDescriptionWindow.PRIZES_TAB)
    } else {
      featureNotTestableInResolution
    }
  }

  def numberOfMatches: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { contestDescriptionWindow.changeToTab(ContestDescriptionWindow.INFO_TAB) }
      eventually { assert(contestDescriptionWindow.countMatches == contest.numMatches) }

    } else {
      featureNotTestableInResolution
    }
  }

  def numberOfContestants: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { contestDescriptionWindow.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB) }
      assert(contestDescriptionWindow.countContestants == contest.numContestants)

    } else {
      featureNotTestableInResolution
    }
  }

  def numberOfPrizes: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { contestDescriptionWindow.changeToTab(ContestDescriptionWindow.PRIZES_TAB) }
      eventually { assert(contestDescriptionWindow.countPrizes == contest.numPrizes) }

    } else {
      featureNotTestableInResolution
    }
  }

  def contestName: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { assert(contestDescriptionWindow.getContestName == contest.name) }

    } else {
      featureNotTestableInResolution
    }

  }

  def contestDescription: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { assert(contestDescriptionWindow.getContestDescription == contest.description) }

    } else {
      featureNotTestableInResolution
    }
  }

  def contestEntryFee: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { assert(contestDescriptionWindow.getContestEntryFee == contest.entryFee) }

    } else {
      featureNotTestableInResolution
    }
  }

  def contestPrize: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { assert(contestDescriptionWindow.getContestPrize == contest.prize) }

    } else {
      featureNotTestableInResolution
    }
  }

  def knownBugSequence_ScrollBarDisappeared: Unit = {
    if (status.resolution == Resolution.BIG){

      logger.debug("Prepare model")
      val enterContestState = new EnterContestState
      enterContestState.contest = contest
      val enterContPage:EnterContestPage = new EnterContestPage(status.resolution, enterContestState)

      logger.debug("Open contest description")
      logger.debug("Click on enter contest")
      eventually { contestDescriptionWindow.enterContest }
      logger.debug("Enter contest page should be at")
      eventually { assert(enterContPage.isAt) }

    } else {
      featureNotTestableInResolution
    }
  }

  private def goToLobbyContestDescription:ContestDescriptionWindow = {
    var lobby: LobbyPage = null
    if (status.resolution == Resolution.BIG) {
      lobby = goToLobbyPage(LobbyState.DEFAULT_LOBBY).clearFilters
                                             .searchContestByName(contest.name)
                                             .openContestDescription(1)
    } else {
      featureNotTestableInResolution
    }
    new ContestDescriptionWindow(status.resolution)
  }

}
