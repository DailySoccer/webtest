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

/*
class LobbySequentialTestRunner extends Sequential(
  new InitializerTest(Resolution.ANY)
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.BIG, LobbyState.DEFAULT_LOBBY)
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, LobbyState.DEFAULT_LOBBY)
  , LobbySequentialTestRunner.createBunchOfTests(Resolution.SMALL, LobbyState.DEFAULT_LOBBY)
)


private object LobbySequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, lobbySt: LobbyState) =
    if (resolution.enabled) {
      new Sequential(
        new LobbyAuthTest_BasicInfo(lobbySt, resolution)
        , new LobbyAuthTest_Filters(lobbySt, resolution)
        , new LobbyAuthTest_OrderBy(lobbySt, resolution)
        , new LobbyAuthTest_Others(lobbySt, resolution)
        , new LobbyAuthTest_Bug(lobbySt, resolution)
      )
    } else {
      new Sequential()
    }
}
*/








