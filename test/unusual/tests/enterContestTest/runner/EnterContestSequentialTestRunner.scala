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
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, EnterContestState.TIME_0_LIST(1))
  , new InitializerWorldCupTest(Resolution.MEDIUM)
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, EnterContestState.TIME_0_LIST(0))
  , new InitializerWorldCupTest(Resolution.SMALL)
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.SMALL, EnterContestState.TIME_0_LIST(1))
)

private object EnterContestSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: EnterContestState) =
    if (resolution.enabled) {
      new EnterContestAuthTest_All(state, resolution)
    } else {
      new Sequential
    }
}
/*
class EnterContestSequentialTestRunner extends Sequential(
  new InitializerTest(Resolution.BIG)
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, EnterContestState.TIME_0_LIST(0))
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, EnterContestState.TIME_0_LIST(1))
  , new InitializerTest(Resolution.MEDIUM)
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, EnterContestState.TIME_0_LIST(0))
  , new InitializerTest(Resolution.SMALL)
  , EnterContestSequentialTestRunner.createBunchOfTests(Resolution.SMALL, EnterContestState.TIME_0_LIST(1))
)


private object EnterContestSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: EnterContestState) =
    if (resolution.enabled) {
      new Sequential(
          new EnterContestAuthTest_OrderBy(state, resolution)
        , new EnterContestAuthTest_FilterBy(state, resolution)
        , new EnterContestAuthTest_SelectPos(state, resolution)
        , new EnterContestAuthTest_Bug(state, resolution)
        , new EnterContestAuthTest_Pick(state, resolution)
      )
    } else {
      new Sequential()
    }
}
*/

