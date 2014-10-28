package unusual.tests.contestDescriptionTest

import unusual.model.Resolution
import unusual.pages.components.{ContestDescriptionWindow}
import unusual.tests._
import unusual.pages._
import unusual.model._

class ContestDescriptionCommon(cont: Contest, res:Resolution) extends SharedTest(res) {

  var contest = cont

  def changeTabs: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)

      eventually {
        contestDescription.changeToTab(ContestDescriptionWindow.INFO_TAB)
        assert(contestDescription.activeTab == ContestDescriptionWindow.INFO_TAB)
      }
      contestDescription.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB)
      assert(contestDescription.activeTab == ContestDescriptionWindow.CONTESTANTS_TAB)
      contestDescription.changeToTab(ContestDescriptionWindow.PRIZES_TAB)
      assert(contestDescription.activeTab == ContestDescriptionWindow.PRIZES_TAB)
    } else {
      featureNotTestableInResolution
    }
  }

  def numberOfMatches: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)
      eventually { contestDescription.changeToTab(ContestDescriptionWindow.INFO_TAB) }

      eventually { assert(contestDescription.countMatches == contest.numMatches) }
    } else {
      val enterContestState = new EnterContestState
      enterContestState.contest = contest
      eventually {
        goToEnterContest(enterContestState).openContestDescription
      }
      assert(new ContestDescriptionWindow(status.resolution).countMatches == contest.numMatches)
    }
  }

  def numberOfContestants: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)
      eventually { contestDescription.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB) }

      assert(contestDescription.countContestants == contest.numContestants)
    } else {
      val enterContestState = new EnterContestState
      enterContestState.contest = contest
      eventually {
        goToEnterContest(enterContestState).openContestDescription
      }
      eventually { assert(new ContestDescriptionWindow(status.resolution).countContestants == contest.numContestants) }
    }
  }

  def numberOfPrizes: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)
      eventually { contestDescription.changeToTab(ContestDescriptionWindow.PRIZES_TAB) }

      eventually { assert(contestDescription.countPrizes == contest.numPrizes) }
    } else {
      val enterContestState = new EnterContestState
      enterContestState.contest = contest
      eventually {
        goToEnterContest(enterContestState).openContestDescription
      }
      eventually { assert(new ContestDescriptionWindow(status.resolution).countPrizes == contest.numPrizes) }
    }
  }

  def contestName: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)

      eventually { assert(contestDescription.getContestName == contest.name) }
    } else {
      featureNotTestableInResolution
    }
  }

  def contestDescription: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)

      eventually { assert(contestDescription.getContestDescription == contest.description) }
    } else {
      featureNotTestableInResolution
    }
  }

  def contestEntryFee: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)

      eventually { assert(contestDescription.getContestEntryFee == contest.entryFee) }
    } else {
      featureNotTestableInResolution
    }
  }

  def contestPrize: Unit = {
    if (status.resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(status.resolution)

      eventually { assert(contestDescription.getContestPrize == contest.prize) }
    } else {
      featureNotTestableInResolution
    }
  }

  def knownBugSequence_ScrollBarDisappeared: Unit = {
    if (status.resolution == Resolution.BIG){
      goToLobbyContestDescription
      logger.debug("Open contest description")
      val desc = new ContestDescriptionWindow(status.resolution)
      val enterContestState = new EnterContestState
      enterContestState.contest = contest
      val enterContPage:EnterContestPage = new EnterContestPage(status.resolution, enterContestState)
      eventually { desc.enterContest }
      logger.debug("Click on enter contest")
      eventually (timeout(5 seconds)) { assert(enterContPage.isAt) }
    } else {
      featureNotTestableInResolution
    }
  }

  private def goToLobbyContestDescription:LobbyPage = {
    var lobby: LobbyPage = null
    if (status.resolution == Resolution.BIG) {
      lobby = goToLobbyPage(LobbyState.DEFAULT_LOBBY).clearFilters
                                             .searchContestByName(contest.name)
                                             .openContestDescription(1)
    } else {
      featureNotTestableInResolution
    }
    lobby
  }

}
