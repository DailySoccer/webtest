package unusual.tests.myContestTest

import unusual.model._
import unusual.model.pageStates._
import unusual.pages._
import unusual.tests._

class MyContestsTestCommon(states:Map[User, MyContestsState], res:Resolution) extends SharedTest(res) {

  var myContestState:MyContestsState = null
  var _myContestPageInstance:MyContestsPage = null

  def myContestPage:MyContestsPage = {
    if(_myContestPageInstance == null) {
      _myContestPageInstance = goToMyContestsPage
      changeMenuPositioning
    }
    myContestState = states(status.user)
    _myContestPageInstance
  }

  def pageIsLeft:Unit = {
    _myContestPageInstance = null
  }


  def lookAtLiveContestCounter:Unit = {
    myContestPage
    val expectedContestCounter = myContestState.liveTab.numberOfContests
    if (expectedContestCounter != 0) {
      val liveContestCounter = myContestPage.liveContestCounterNumber
      assert(liveContestCounter == expectedContestCounter, s"live contest counter is: $liveContestCounter, expected: $expectedContestCounter")
    } else {
      intercept[NoSuchElementException](myContestPage.liveContestCounterNumber)
    }
  }



  def upcomingTab = new UpcomingTab

  def liveTab     = new LiveTab

  def historyTab  = new HistoryTab


  abstract class TabCommonConcrete extends MyContestsTestCommon.TabCommon {
    def goToTab:Unit = pageTab

    def checkNumberOfContest:Unit = {
      assert(pageTab.countContests == tabInfo.numberOfContests)
    }
    def check_ToTheContests_Button:Unit = {
      pageTab
      if (tabInfo.numberOfContests == 0) {
        assert(pageTab.toTheContest_ButtonExists, "'to the contest' button should be displayed with no contests")
        pageTab.clickOn_ToTheContests_Button
        eventually { assert((new LobbyPage(status.resolution, 6)).isAt, "lobby has no reached") }
        pageIsLeft
      } else {
        assert(!pageTab.toTheContest_ButtonExists, "'to the contest' button shouldn't be displayed with contests")
      }
    }
  }

  class UpcomingTab extends TabCommonConcrete {
    def pageTab:MyContestsPage.SectionTab   = { myContestPage.in.upcomingTab }
    def tabInfo:MyContestsState.GeneralInfo = { myContestState.upcomingTab }

    def checkRowActionButton:Unit = {
      Given("upcoming tab, look at number of contest")
      if (tabInfo.numberOfContests > 0) {
        When("there are few contests, use action button")
        pageTab.clickOnRowActionButton(1)

        Then("View contest page should be displayed")
        val viewContest = new ViewContestPage(res, null)
        eventually { assert(viewContest.isAt, "Should be at view contest page") }
        pageIsLeft
      } else {
        When("there is no contest. do nothing")
      }
    }
  }

  class LiveTab extends TabCommonConcrete {
    def pageTab:MyContestsPage.SectionTab   = { myContestPage.in.liveTab }
    def tabInfo:MyContestsState.GeneralInfo = { myContestState.liveTab }

    def checkRowActionButton:Unit = {
      Given("upcoming tab, look at number of contest")
      if (tabInfo.numberOfContests > 0) {
        When("there are few contests, use action button")
        pageTab.clickOnRowActionButton(1)

        Then("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! TODO")
        featureNotImplemented
        //val viewContest = new ViewContestPage(res, null)
        //eventually { assert(viewContest.isAt, "Should be at view contest page") }
        pageIsLeft
      } else {
        When("there is no contest. do nothing")
      }
    }
  }

  class HistoryTab extends TabCommonConcrete {
    def pageTab:MyContestsPage.SectionTab   = { myContestPage.in.historyTab }
    def tabInfo:MyContestsState.GeneralInfo = { myContestState.historyTab }

    def checkRowActionButton:Unit = {
      Given("upcoming tab, look at number of contest")
      if (tabInfo.numberOfContests > 0) {
        When("there are few contests, use action button")
        pageTab.clickOnRowActionButton(1)

        Then("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! TODO")
        featureNotImplemented
        //val viewContest = new ViewContestPage(res, null)
        //eventually { assert(viewContest.isAt, "Should be at view contest page") }
        pageIsLeft
      } else {
        When("there is no contest. do nothing")
      }
    }
  }

}

object MyContestsTestCommon {
  abstract class TabCommon {
    def pageTab:MyContestsPage.SectionTab
    def tabInfo:MyContestsState.GeneralInfo

    def checkNumberOfContest:Unit
    def check_ToTheContests_Button:Unit

    def checkRowActionButton:Unit
  }
}