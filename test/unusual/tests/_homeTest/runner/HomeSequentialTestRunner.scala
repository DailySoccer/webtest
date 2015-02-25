package unusual.tests._homeTest.runner

import org.scalatest.Sequential
import unusual.model.Resolution
import unusual.tests.SharedTest
import unusual.tests._homeTest.{HomeVisitorTest, HomeAuthTest}
import unusual.tests.lobbyTest.{LobbyAuthTest, LobbyVisitorTest}
import unusual.tests.runner.simulatorController._


class HomeSequentialTestRunner extends Sequential(
  new HomeAuthTest(Resolution.BIG)
  , new HomeAuthTest(Resolution.MEDIUM)
  , new HomeAuthTest(Resolution.SMALL)
  , new HomeVisitorTest(Resolution.BIG)
  , new HomeVisitorTest(Resolution.MEDIUM)
  , new HomeVisitorTest(Resolution.SMALL)

)