package unusual.tests.contestDescriptionTest.runner

import org.scalatest.Sequential
import org.scalatest.Suite
import unusual.model.{Resolution, Contest}
import unusual.tests.SharedTest
import unusual.tests.contestDescriptionTest._
import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController.InitializerWorldCupTest


class ContestDescriptionSequentialTestRunner extends Sequential(
  ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.BIG, Contest.TIME_0_LIST(0))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, Contest.TIME_0_LIST(1))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.SMALL, Contest.TIME_0_LIST(0))
)

private object ContestDescriptionSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: Contest):Suite =
      if (resolution.enabled) {
        new ContestDescriptionAuthTest(state, resolution)
      } else {
        new Sequential
      }
}
