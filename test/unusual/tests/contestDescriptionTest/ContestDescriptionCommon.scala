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
    }
  }

  def numberOfMatches(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.INFO_TAB)

      assert(contestDescription.countMatches == contest.numMatches)
    }
  }

  def numberOfContestants(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.CONTESTANTS_TAB)

      assert(contestDescription.countContestants == contest.numContestants)
    }
  }

  def numberOfPrizes(implicit resolution: Resolution): Unit = {
    if (resolution == Resolution.BIG) {
      goToLobbyContest.openContestDescription(contestOrder)
      val contestDescription = new ContestDescriptionWindow(resolution)
      contestDescription.changeToTab(ContestDescriptionWindow.PRIZES_TAB)

      assert(contestDescription.countPrizes == contest.numPrizes)
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
    }
  }

  private def goToLobbyContest:LobbyPage = {
    goToLobbyPage.clickOnMenuAllContests.clickOnMenuFreeContests.clearFilters
  }

}
