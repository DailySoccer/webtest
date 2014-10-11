package unusual.tests.lobbyTest.runner

import org.scalatest.Sequential
import unusual.model.LobbyState
import unusual.tests.SharedTest
import unusual.tests.lobbyTest.{LobbyVisitorTest, LobbyAuthTest}
import unusual.tests.simulatorController._


class SequentialTestRunner extends Sequential(
   new InitializerTest
 , new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
 , new LobbyVisitorTest
   /* { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
      new InitializerTest
  },  new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)

  , { SharedTest.SIZES_ENABLED = SharedTest.TABLET
      new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  }

  , { SharedTest.SIZES_ENABLED = SharedTest.SMARTPHONE
      new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  }

  , { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP | SharedTest.TABLET | SharedTest.SMARTPHONE
      new LobbyVisitorTest
  }*/

)