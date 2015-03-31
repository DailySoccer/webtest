package unusual.tests.myContestTest.runner

import org.scalatest.{Sequential, Suite}
import unusual.model.{User, Resolution}
import unusual.model.pageStates.MyContestsState
import unusual.tests.myContestTest
import unusual.tests.myContestTest._
import unusual.tests.runner.simulatorController.InitializerWorldCupTest


class MyContestsSequentialTestRunner extends Sequential(
  MyContestsSequentialTestRunner.createBunchOfTests(Resolution.BIG, MyContestsState.TIME_0_LIST)
  , MyContestsSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, MyContestsState.TIME_0_LIST)
  , MyContestsSequentialTestRunner.createBunchOfTests(Resolution.SMALL, MyContestsState.TIME_0_LIST)
)


private object MyContestsSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, states:Map[User, MyContestsState]):Suite =
    if (resolution.enabled) {
      new Sequential (
        new MyContestsAuthWithContestsTest(states, resolution)
        , new MyContestsAuthNoContestsTest(states, resolution)
      )
    } else {
      new Sequential
    }
}
