package unusual.tests.enterContestTest.runner

import org.scalatest.Sequential
import unusual.model.pageStates.EnterContestState
import unusual.model.Resolution
import unusual.tests.SharedTest
import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController.InitializerWorldCupTest

class EnterContestSequentialTestRunner extends Sequential(
  EnterContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, EnterContestState.TIME_0_LIST(0))
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, EnterContestState.TIME_0_LIST(0))
  //, new InitializerWorldCupTest(Resolution.SMALL)
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.SMALL, EnterContestState.TIME_0_LIST(0))
  //, new InitializerWorldCupTest(Resolution.ANY)
)

private object EnterContestSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: EnterContestState) =
    if (resolution.enabled) {
      new EnterContestAuthTest_All(state, resolution)
    } else {
      new Sequential
    }
}