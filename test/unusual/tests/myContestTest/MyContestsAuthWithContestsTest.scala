package unusual.tests.myContestTest

import unusual.model._
import unusual.model.pageStates.{MyContestsState, ViewContestState}

class MyContestsAuthWithContestsTest(states:Map[User, MyContestsState], res:Resolution) extends MyContestsTestCommon(states, res) {

  before { status.ensureAuthUser }

  def consistIn = afterWord("consist in")

  if(status.resolution.enabled) {
    s"Auth user with contests" when sizeTesting(myContestPageBehavior)
  }

  def myContestPageBehavior: Unit = {

    def genericTabBehavior(tab: MyContestsTestCommon.TabCommon):Unit = {

      "check number of contest" in tab.checkNumberOfContest

      "check 'to the contests' button" in tab.check_ToTheContests_Button

      "check row action button functionality" in tab.checkRowActionButton
    }

    "do logout and do login" in {
      status.ensureVisitor
      status.user = User.WITH_CONTEST
    }

    "look at live contest counter" in { lookAtLiveContestCounter }

    "check upcoming tab" which consistIn{ genericTabBehavior(upcomingTab) }

    "check live tab" which consistIn{ genericTabBehavior(liveTab) }

    "check history tab" which consistIn{ genericTabBehavior(historyTab) }

    "do logout" in status.ensureVisitor

  }


 }




