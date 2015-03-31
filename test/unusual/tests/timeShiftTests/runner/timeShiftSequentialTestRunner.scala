package unusual.tests.timeShiftTests.runner

import org.scalatest.Sequential
import unusual.model.pageStates.{MyContestsState, LobbyState}
import unusual.model.{User, Contest, Resolution}
import unusual.tests.lobbyTest._
import unusual.tests.runner.SequentialTestRunner
import unusual.tests.runner.simulatorController.TimeShiftTest
import unusual.tests.timeShiftTests.{MyContestTimeShiftAuthTest, LobbyTimeShiftAuthTest}


class TimeShiftSequentialTestRunner extends Sequential(
  TimeShiftSequentialTestRunner.createBunchOfTests(Resolution.BIG, LobbyState.TIME_1_LOBBY, MyContestsState.TIME_1_LIST)
  , TimeShiftSequentialTestRunner.createMyContestTests(Resolution.BIG, MyContestsState.TIME_2_LIST)
)

private object TimeShiftSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, lobbySt: LobbyState, myContestsSt:Map[User, MyContestsState]) =
    if (resolution.enabled) {
      new Sequential(
        new LobbyTimeShiftAuthTest(lobbySt, resolution,  13, 6, 2014, 0, 0, 0, "2014/06/13 00:00:00 UTC")
        , new MyContestTimeShiftAuthTest(myContestsSt, resolution,  13, 6, 2014, 0, 0, 0, "2014/06/13 00:00:00 UTC")
      )
    } else {
      new Sequential()
    }
  def createMyContestTests(resolution:Resolution, myContestsSt:Map[User, MyContestsState]) =
    if (resolution.enabled) {
        new MyContestTimeShiftAuthTest(myContestsSt, resolution,  14, 6, 2014, 0, 0, 0, "2014/06/14 00:00:00 UTC")
    } else {
      new Sequential()
    }
}








