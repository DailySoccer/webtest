package unusual.tests.runner

import org.scalatest.Sequential
import unusual.model.{ViewContestState, EnterContestState, LobbyState, Contest}
import unusual.tests.SharedTest

import unusual.tests.contestDescriptionTest._
import unusual.tests.contestDescriptionTest.runner.ContestDescriptionSequentialTestRunner
import unusual.tests.enterContestTest.EnterContestAuthTest
import unusual.tests.lobbyTest._
import unusual.tests.enterContestTest.runner.EnterContestSequentialTestRunner
import unusual.tests.simulatorController._
import unusual.tests.viewContestEntryTest.ViewContestAuthTest
import unusual.tests.lobbyTest.runner._


class SequentialTestRunner extends Sequential(
  new LobbySequentialTestRunner
  , new EnterContestSequentialTestRunner
  , new ContestDescriptionSequentialTestRunner

  )