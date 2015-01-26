package unusual.tests.homeTest.runner

import org.scalatest.Sequential
import unusual.model.Resolution
import unusual.tests.SharedTest
import unusual.tests.homeTest.{HomeVisitorTest, HomeAuthTest}
import unusual.tests.lobbyTest.{LobbyAuthTest, LobbyVisitorTest}
import unusual.tests.simulatorController._


class HomeSequentialTestRunner extends Sequential(
  new HomeAuthTest(Resolution.BIG)
  , new HomeAuthTest(Resolution.MEDIUM)
  , new HomeAuthTest(Resolution.SMALL)
  , new HomeVisitorTest(Resolution.BIG)
  , new HomeVisitorTest(Resolution.MEDIUM)
  , new HomeVisitorTest(Resolution.SMALL)

)