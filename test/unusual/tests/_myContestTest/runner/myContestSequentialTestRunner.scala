package unusual.tests._myContestTest.runner

import org.scalatest.{Sequential, Suite}
import unusual.model.Resolution
import unusual.model.pageStates.ViewContestState
import unusual.tests._myContestTest
import unusual.tests.runner.simulatorController.InitializerWorldCupTest


class MyContestSequentialTestRunner extends Sequential(
  new InitializerWorldCupTest(Resolution.ANY)
  , MyContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, ViewContestState.TIME_0_LIST(0))
  , MyContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, ViewContestState.TIME_0_LIST(1))
  , MyContestSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, ViewContestState.TIME_0_LIST(0))
  , MyContestSequentialTestRunner.createBunchOfTests(Resolution.SMALL, ViewContestState.TIME_0_LIST(0))
)


private object MyContestSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: ViewContestState):Suite =
    if (resolution.enabled) {
      new _myContestTest.MyContestsAuthTest(state, resolution)
    } else {
      new Sequential
    }
}
