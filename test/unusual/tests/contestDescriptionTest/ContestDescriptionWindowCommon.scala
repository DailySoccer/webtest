package unusual.tests.contestDescriptionTest

import unusual.model.Resolution
import unusual.model.pageStates.{LobbyState, EnterContestState}
import unusual.pages.components.page.EnterContestDescriptionTab
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
      changeMenuPositioning
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

      eventually { assert(contestDescriptionWindow.getContestName == contest.name.toUpperCase) }

    } else {
      featureNotTestableInResolution
    }

  }

  def contestDescription: Unit = {
    if (status.resolution == Resolution.BIG) {

      eventually { assert(contest.isEqualsDescription(contestDescriptionWindow.getContestDescription)) }

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
      lobby = goToLobbyPage(LobbyState.DEFAULT_LOBBY)
      lobby.filters.clear
      lobby.filters.search(contest.name)
      lobby.openContestDescription(1)
    } else {
      featureNotTestableInResolution
    }
    new ContestDescriptionWindow(status.resolution)
  }


  object enterContest {

    var _descriptionTabInstance:EnterContestDescriptionTab = null
    def descriptionTab:EnterContestDescriptionTab = {
      if(_descriptionTabInstance == null) {
        _descriptionTabInstance = goToEnterContestDescriptionTab
        changeMenuPositioning
      }
      _descriptionTabInstance
    }

    def numberOfMatches: Unit = assert(descriptionTab.countMatches == contest.numMatches)

    def numberOfContestants: Unit = assert(descriptionTab.countContestants == contest.numContestants)

    def numberOfPrizes: Unit = assert(descriptionTab.countPrizes == contest.numPrizes)


    private def goToEnterContestDescriptionTab:EnterContestDescriptionTab = {
      val state = new EnterContestState
      state.contest = contest
      logger.debug("go to enter contest")
      val enterContestPage = new EnterContestPage(status.resolution, state)
      val desc = new EnterContestDescriptionTab(status.resolution)

      logger.debug("change to info tab")
      eventually {
        enterContestPage.changeToInfoTab
        assert(desc.isAt)
      }

      desc
    }
  }

}
