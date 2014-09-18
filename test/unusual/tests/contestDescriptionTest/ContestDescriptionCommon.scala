package unusual.tests.contestDescriptionTest

import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
import org.scalatest.exceptions.StackDepthException
import org.scalatest.selenium.WebBrowser
import unusual.model.Resolution
import unusual.pages.components.{ContestDescriptionWindow, PaginatorControl}
import unusual.testTags.scala._
import unusual.tests._
import unusual.pages._
import unusual.model._

class ContestDescriptionCommon extends SharedTest {

  var contest = new Contest("vie., 13 jun.", "Free: 5 de 3 jugadores - LIM. SAL.: 90000", "0€", "0€")
  contest.numMatches = 3
  contest.numContestants = 5
  contest.numPrizes = 0
  var contestOrder = 1

  def changeTabs(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
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
      goToLobbyContest.openContestDescription(contestOrder)
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
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB)

      assert(contestDescription.countContestants == contest.numContestants)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).countContestants == contest.numMatches)
    }
  }

  def numberOfPrizes(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.PRIZES_TAB)

      assert(contestDescription.countPrizes == contest.numPrizes)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).countPrizes == contest.numMatches)
    }
  }

  def contestName(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestName == contest.name)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).getContestName == contest.numMatches)
    }
  }

  def contestDescription(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestDescription == contest.description)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).getContestDescription == contest.description)
    }
  }

  def contestEntryFee(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestEntryFee == contest.entryFee)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).getContestEntryFee == contest.entryFee)
    }
  }

  def contestPrize(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)

      assert(contestDescription.getContestPrize == contest.prize)
    } else {
      eventually {
        goToEnterContest(contest.id).openContestDescription
      }
      assert(new ContestDescriptionWindow(resolution).getContestPrize == contest.prize)
    }
  }

  def knownBugSequence_ScrollBarDisappeared(implicit resolution:Resolution): Unit = {
    if (resolution == Resolution.BIG){
      goToLobbyContest.openContestDescription(contestOrder)
      logger.debug("Open contest description")
      val desc = new ContestDescriptionWindow(resolution)
      val enterContPage:EnterContestPage = new EnterContestPage(resolution)
      desc.enterContest
      logger.debug("Click on enter contest")
      eventually (timeout(5 seconds)) {
        assert(enterContPage.isAt)
      }
    } else {
      featureNotTestableInResolution
    }
  }

  private def goToLobbyContest:LobbyPage = {
    goToLobbyPage.clickOnMenuAllContests.clickOnMenuFreeContests.clearFilters
  }

}
