package unusual.tests.timeShiftTests

import unusual.model.Resolution
import unusual.model.pageStates.LiveContestState
import unusual.tests.liveContestTest.LiveContestTestCommon

/**
 * Created by gregorioiniestaovejero on 27/4/15.
 */
class LiveContestTimeShiftAuthTest(liveContestSt:LiveContestState, res:Resolution) extends LiveContestTestCommon(liveContestSt, res){

  def consistIn = afterWord("consist in")

  before { status.ensureAuthUser }

  "Auth user" when sizeTesting(liveContestPageBehavior)

  def liveContestPageBehavior: Unit = {

    def livePageChecks(timeIdx:Int):Unit = {
      "check soccer player punctuations" in checkPunctuations(timeIdx)

      "check remaining time" in checkTime(timeIdx)

      "check live change" in checkScore(timeIdx)
    }

    def liveTimeShift(day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int, returnedString:String):Unit = {
      "go to lobby and do a time shift" in {
        //goToMyContestsPage
        logger.debug("doing a time shift...")
        timeShift(day, month, year, hour, minute, second, returnedString)
        logger.debug("time shift done!")
      }
    }

    "reset database" in {
      logger.debug("reset...")
      initialWorldCupTestsSetup
      logger.debug("reset done!")
      reloadPage()
    }

    "register on a Contest" in registerOnAContest

    "advance to get the contest living" in timeShift(13, 6, 2014, 0, 0, 0, "2014/06/13 00:00:00 UTC")

    "go to living contest" in goToLiveContestPage

    "check live page at initial time" which consistIn{ livePageChecks(0) }

    "making a time shift, eventually live page should change" which consistIn {
      "pre time shift..." in timeShift(13, 6, 2014, 17, 40, 0, "2014/06/13 17:40:00 UTC")
      "slow time shift..." in timeShift(13, 6, 2014, 22, 0, 0, "2014/06/13 22:00:00 UTC", 180)

      livePageChecks(1)
    }

  }

}
