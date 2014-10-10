package unusual.tests.enterContestTest.runner

import org.scalatest.Sequential
import unusual.model.EnterContestState
import unusual.tests.enterContestTest.{EnterContestVisitorTest, EnterContestAuthTest}
import unusual.tests.simulatorController.InitializerTest


class SequentialTestRunner extends Sequential(
    new InitializerTest
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(0))
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(1))
  , new InitializerTest
  , new EnterContestVisitorTest(EnterContestState.TIME_0_LIST(0))
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(1))

)