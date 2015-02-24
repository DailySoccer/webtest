package unusual.tests.viewContestEntryTest.runner

import org.scalatest.{Suite, Sequential}
import unusual.model.pageStates.ViewContestState
import unusual.model.{Resolution, Contest}
import unusual.tests.SharedTest
import unusual.tests.contestDescriptionTest._
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController.InitializerWorldCupTest
import unusual.tests.viewContestEntryTest._


class ViewContestSequentialTestRunner extends Sequential(
  ViewContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, ViewContestState.TIME_0_LIST(0))
  , ViewContestSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, ViewContestState.TIME_0_LIST(1))
  , ViewContestSequentialTestRunner.createBunchOfTests(Resolution.SMALL, ViewContestState.TIME_0_LIST(0))
)


private object ViewContestSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: ViewContestState):Suite =
    if (resolution.enabled) {
      new ViewContestAuthTest(state, resolution)
    } else {
      new Sequential
    }
}