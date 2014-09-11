package unusual.tests.runner

import org.scalatest.Sequential

import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.menuTest._
import unusual.tests.homeTest._


class SequentialTestRunner extends Sequential(
  new EnterContestAuthTest,
  new MenuVisitorTest,
  new LobbyAuthTest,
  new LobbyVisitorTest,
  new HomeAuthTest,
  new MenuVisitorTest

  //new FutureJump,

  )
