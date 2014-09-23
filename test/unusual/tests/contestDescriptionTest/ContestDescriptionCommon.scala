package unusual.tests.contestDescriptionTest

import unusual.model.Resolution
import unusual.pages.components.{ContestDescriptionWindow}
import unusual.tests._
import unusual.pages._
import unusual.model._

class ContestDescriptionCommon extends SharedTest {

  var contest = Contest.DEFAULT_LIST(0)

  def changeTabs(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)

      contestDescription.changeToTab(ContestDescriptionWindow.INFO_TAB)
      assert(contestDescription.activeTab == ContestDescriptionWindow.INFO_TAB)
      contestDescription.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB)
      assert(contestDescription.activeTab == ContestDescriptionWindow.CONTESTANTS_TAB)
      contestDescription.changeToTab(ContestDescriptionWindow.PRIZES_TAB)
      assert(contestDescription.activeTab == ContestDescriptionWindow.PRIZES_TAB)
    } else {
      featureNotTestableInResolution
    }
  }

  def numberOfMatches(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.INFO_TAB)

      assert(contestDescription.countMatches == contest.numMatches)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).countMatches == contest.numMatches)
    }
  }

  def numberOfContestants(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB)

      assert(contestDescription.countContestants == contest.numContestants)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).countContestants == contest.numContestants)
    }
  }

  def numberOfPrizes(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.PRIZES_TAB)

      assert(contestDescription.countPrizes == contest.numPrizes)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).countPrizes == contest.numPrizes)
    }
  }

  def contestName(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestName == contest.name)
    } else {
      featureNotTestableInResolution
    }
  }

  def contestDescription(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestDescription == contest.description)
    } else {
      featureNotTestableInResolution
    }
  }

  def contestEntryFee(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestEntryFee == contest.entryFee)
    } else {
      featureNotTestableInResolution
    }
  }

  def contestPrize(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContestDescription
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestPrize == contest.prize)
    } else {
      featureNotTestableInResolution
    }
  }

  def knownBugSequence_ScrollBarDisappeared(implicit resolution:Resolution): Unit = {
    if (resolution == Resolution.BIG){
      goToLobbyContestDescription
      logger.debug("Open contest description")
      val desc = new ContestDescriptionWindow(resolution)
      val enterContPage:EnterContestPage = new EnterContestPage(resolution, contest.id)
      desc.enterContest
      logger.debug("Click on enter contest")
      eventually (timeout(5 seconds)) {
        assert(enterContPage.isAt)
      }
    } else {
      featureNotTestableInResolution
    }
  }

  private def goToLobbyContestDescription:LobbyPage = {
    goToLobbyPage(LobbyState.DEFAULT_LOBBY)
                 .clickOnMenuAllContests
                 .clickOnMenuFreeContests
                 .clearFilters
                 .clickSortContestsByName
                 .openContestDescription(contest.nameOrder)
  }

}
