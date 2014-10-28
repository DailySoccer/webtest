package unusual.tests.runner

import org.scalatest.Sequential
import unusual.tests.contestDescriptionTest.runner.ContestDescriptionSequentialTestRunner
import unusual.tests.enterContestTest.runner.EnterContestSequentialTestRunner
import unusual.tests.lobbyTest.runner._


class SequentialTestRunner extends Sequential(
  new LobbySequentialTestRunner
  , new EnterContestSequentialTestRunner
  , new ContestDescriptionSequentialTestRunner
  )