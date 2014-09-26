package unusual.tests.lobbyTest.runner

import org.scalatest.Sequential
import unusual.model.LobbyState
import unusual.tests.lobbyTest.{LobbyVisitorTest, LobbyAuthTest}
import unusual.tests.simulatorController._


class SequentialTestRunner extends Sequential(
  new InitializerTest
  , new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  , new LobbyVisitorTest

)