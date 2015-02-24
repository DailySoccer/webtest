package unusual.tests.lobbyTest.runner

import org.scalatest.Sequential
import unusual.model.{Contest, Resolution}
import unusual.model.pageStates.LobbyState
import unusual.tests.SharedTest
import unusual.tests.lobbyTest._
import unusual.tests.runner._
import unusual.tests.simulatorController._


class LobbySequentialTestRunner extends Sequential(
  LobbySequentialTestRunner.createBunchOfTests(Resolution.BIG, LobbyState.DEFAULT_LOBBY, Contest.TIME_0_LIST(0))
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, LobbyState.DEFAULT_LOBBY, Contest.TIME_0_LIST(0))
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.SMALL, LobbyState.DEFAULT_LOBBY, Contest.TIME_0_LIST(0))
)

private object LobbySequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, lobbySt: LobbyState, cont: Contest) =
    if (resolution.enabled) {
      new Sequential(
        new LobbyAuthTest_All(lobbySt, cont, resolution)
        //, new LobbyVisitorTest(lobbySt, resolution)
      )
    } else {
      new Sequential()
    }
}








