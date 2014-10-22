package unusual.tests.lobbyTest.runner

import org.scalatest.Sequential
import unusual.model.{Resolution, LobbyState}
import unusual.tests.SharedTest
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController._


class LobbySequentialTestRunner extends Sequential(
  new InitializerTest(Resolution.BIG)
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

/*
class LobbySequentialTestRunner extends Sequential(
  { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
     //new InitializerTest
     new LobbyAuthTest_BasicInfo(LobbyState.DEFAULT_LOBBY)
  }
  ,  new LobbyAuthTest_BasicInfo(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Filters(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_OrderBy(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Others(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Bug(LobbyState.DEFAULT_LOBBY)

  , { SharedTest.SIZES_ENABLED = SharedTest.TABLET
     new LobbyAuthTest_BasicInfo(LobbyState.DEFAULT_LOBBY)
  }
  ,  new LobbyAuthTest_Filters(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_OrderBy(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Others(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Bug(LobbyState.DEFAULT_LOBBY)

  , { SharedTest.SIZES_ENABLED = SharedTest.SMARTPHONE
     new LobbyAuthTest_BasicInfo(LobbyState.DEFAULT_LOBBY)
  }
  ,  new LobbyAuthTest_Filters(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_OrderBy(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Others(LobbyState.DEFAULT_LOBBY)
  ,  new LobbyAuthTest_Bug(LobbyState.DEFAULT_LOBBY)

  , { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP | SharedTest.TABLET | SharedTest.SMARTPHONE
      new LobbyVisitorTest
  }
)*/








