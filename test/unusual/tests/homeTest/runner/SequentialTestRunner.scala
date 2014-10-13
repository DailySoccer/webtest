package unusual.tests.homeTest.runner

import org.scalatest.Sequential
import unusual.model.LobbyState
import unusual.tests.SharedTest
import unusual.tests.homeTest.{HomeVisitorTest, HomeAuthTest}
import unusual.tests.lobbyTest.{LobbyAuthTest, LobbyVisitorTest}
import unusual.tests.runner.simulatorController._


class SequentialTestRunner extends Sequential(
    new InitializerTest
  , new HomeAuthTest
  , new HomeVisitorTest

)