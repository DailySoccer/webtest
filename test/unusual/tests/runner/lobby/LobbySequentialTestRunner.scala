package unusual.tests.runner.lobby

import org.scalatest.Sequential
import unusual.model.LobbyState
import unusual.tests.SharedTest
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController._


class LobbySequentialTestRunner extends Sequential(
  { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
      new InitializerTest
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

)