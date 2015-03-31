package unusual.tests.timeShiftTests

import unusual.model.{User, Resolution}
import unusual.model.pageStates.{MyContestsState, LobbyState}
import unusual.tests.lobbyTest.LobbyTestCommon
import unusual.tests.myContestTest.MyContestsTestCommon

/**
 * Created by gregorioiniestaovejero on 31/3/15.
 */
class MyContestTimeShiftAuthTest(state:Map[User, MyContestsState], res:Resolution, day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int, returnedString:String) extends MyContestsTestCommon(state, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before { status.ensureAuthUser }

  "Auth user" when sizeTesting(myContestPageBehavior)

  def myContestPageBehavior: Unit = {

    def genericTabBehavior(tab: MyContestsTestCommon.TabCommon):Unit = {

      "check number of contest" in tab.checkNumberOfContest

      "check 'to the contests' button" in tab.check_ToTheContests_Button

      "check row action button functionality" in tab.checkRowActionButton
    }

    "look at lobby over the time" which consistIn {

      "do logout and do login" in {
        status.ensureVisitor
        status.user = User.WITH_CONTEST
      }

      "go upcoming tab" in upcomingTab.goToTab

      "go to lobby and do a time shift" in {
        //goToMyContestsPage
        logger.debug("doing a time shift...")
        timeShift(day, month, year, hour, minute, second, returnedString)
        logger.debug("time shift done!")
      }

      "look at live contest counter" in {
        eventually { lookAtLiveContestCounter }
      }

      "check upcoming tab" which consistIn{ eventually { genericTabBehavior(upcomingTab)} }

      "check live tab" which consistIn{ genericTabBehavior(liveTab) }

      "check history tab" which consistIn{ genericTabBehavior(historyTab) }

      "do logout" in status.ensureVisitor
    }
  }
}
