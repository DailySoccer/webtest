package unusual.tests.viewContestEntryTest.runner

import org.scalatest.Sequential
import unusual.model.{LobbyState, Resolution, ViewContestState, Contest}
import unusual.tests.SharedTest
import unusual.tests.contestDescriptionTest.ContestDescriptionAuthTest_Header
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController.InitializerTest
import unusual.tests.viewContestEntryTest._


class ViewContestSequentialTestRunner extends Sequential(
  new InitializerTest(Resolution.BIG)
  , ViewContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, ViewContestState.TIME_0_LIST(0))
  , ViewContestSequentialTestRunner.createBunchOfTests(Resolution.BIG, ViewContestState.TIME_0_LIST(1))
  , new InitializerTest(Resolution.MEDIUM)
  , ViewContestSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, ViewContestState.TIME_0_LIST(0))
  , new InitializerTest(Resolution.SMALL)
  , ViewContestSequentialTestRunner.createBunchOfTests(Resolution.SMALL, ViewContestState.TIME_0_LIST(0))
)


private object ViewContestSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: ViewContestState) = new Sequential(
    new ViewContestAuthTest(state, resolution)
  )
}
/*
class ViewContestSequentialTestRunner extends Sequential(

  { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
     new InitializerTest
  }
  ,  new ViewContestAuthTest(ViewContestState.TIME_0_LIST(0))
  ,  new ViewContestAuthTest(ViewContestState.TIME_0_LIST(1))

  , { SharedTest.SIZES_ENABLED = SharedTest.TABLET
     new InitializerTest
  }
  ,  new ViewContestAuthTest(ViewContestState.TIME_0_LIST(0))

  , { SharedTest.SIZES_ENABLED = SharedTest.SMARTPHONE
      new ViewContestAuthTest(ViewContestState.TIME_0_LIST(1))
  }
/*
  , { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP | SharedTest.TABLET | SharedTest.SMARTPHONE
     new InitializerTest
  }
  , new EnterContestVisitorTest(EnterContestState.TIME_0_LIST(0))
*/

)
*/