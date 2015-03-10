package unusual.tests.lobbyTest.runner

import org.scalatest.Sequential
import unusual.model.{Contest, Resolution}
import unusual.model.pageStates.LobbyState
import unusual.tests.SharedTest
import unusual.tests.lobbyTest._
import unusual.tests.runner._
import unusual.tests.runner.simulatorController._


class LobbySequentialTestRunner extends Sequential(
  LobbySequentialTestRunner.createBunchOfTests(Resolution.BIG, LobbyState.DEFAULT_LOBBY)
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, LobbyState.DEFAULT_LOBBY)
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.SMALL, LobbyState.DEFAULT_LOBBY)
)

private object LobbySequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, lobbySt: LobbyState) =
    if (resolution.enabled) {
      new Sequential(
        new LobbyAuthTest_All(lobbySt, resolution)
        , new LobbyVisitorTest(lobbySt, resolution)
      )
    } else {
      new Sequential()
    }
}








