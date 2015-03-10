package unusual.tests.timeShiftTests.runner

import org.scalatest.Sequential
import unusual.model.pageStates.LobbyState
import unusual.model.{Contest, Resolution}
import unusual.tests.lobbyTest._
import unusual.tests.runner.SequentialTestRunner
import unusual.tests.runner.simulatorController.TimeShiftTest
import unusual.tests.timeShiftTests.LobbyTimeShiftAuthTest


class TimeShiftSequentialTestRunner extends Sequential(
  TimeShiftSequentialTestRunner.createLobbyTests(Resolution.BIG, LobbyState.TIME_1_LOBBY)
)

private object TimeShiftSequentialTestRunner {
  def createLobbyTests(resolution:Resolution, lobbySt: LobbyState) =
    if (resolution.enabled) {
      new LobbyTimeShiftAuthTest(lobbySt, resolution,  13, 6, 2014, 0, 0, 0, "2014/06/13 00:00:00 UTC")
    } else {
      new Sequential()
    }
}








