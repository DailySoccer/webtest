package unusual.tests.contestDescriptionTest.runner

import org.scalatest.Sequential
import unusual.model.{LobbyState, Resolution, Contest, EnterContestState}
import unusual.tests.SharedTest
import unusual.tests.contestDescriptionTest.{ContestDescriptionAuthTest_Bug, ContestDescriptionAuthTest_Sections, ContestDescriptionAuthTest_Header}
import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController.InitializerTest


class ContestDescriptionSequentialTestRunner extends Sequential(
  new InitializerTest(Resolution.ANY)
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.BIG, Contest.TIME_0_LIST(0))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, Contest.TIME_0_LIST(0))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.SMALL, Contest.TIME_0_LIST(0))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.BIG, Contest.TIME_0_LIST(1))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM, Contest.TIME_0_LIST(1))
  , ContestDescriptionSequentialTestRunner.createBunchOfTests(Resolution.SMALL, Contest.TIME_0_LIST(1))
)


private object ContestDescriptionSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution, state: Contest) =
    if (resolution.enabled) {
      new Sequential(
           new ContestDescriptionAuthTest_Header(state, resolution)
        ,  new ContestDescriptionAuthTest_Sections(state, resolution)
        ,  new ContestDescriptionAuthTest_Bug(state, resolution)
        ,  new ContestDescriptionAuthTest_Header(state, resolution)
        ,  new ContestDescriptionAuthTest_Sections(state, resolution)
        ,  new ContestDescriptionAuthTest_Bug(state, resolution)
      )
    } else {
      new Sequential()
    }
}

/*

class ContestDescriptionSequentialTestRunner extends Sequential(

  { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
    new InitializerTest
  }
  ,  new ContestDescriptionAuthTest_Header(Contest.TIME_0_LIST(0))
  ,  new ContestDescriptionAuthTest_Sections(Contest.TIME_0_LIST(0))
  ,  new ContestDescriptionAuthTest_Bug(Contest.TIME_0_LIST(0))
  ,  new ContestDescriptionAuthTest_Header(Contest.TIME_0_LIST(1))
  ,  new ContestDescriptionAuthTest_Sections(Contest.TIME_0_LIST(1))
  ,  new ContestDescriptionAuthTest_Bug(Contest.TIME_0_LIST(1))

  , { SharedTest.SIZES_ENABLED = SharedTest.TABLET
     new ContestDescriptionAuthTest_Header(Contest.TIME_0_LIST(0))
  }
  ,  new ContestDescriptionAuthTest_Sections(Contest.TIME_0_LIST(0))
  ,  new ContestDescriptionAuthTest_Bug(Contest.TIME_0_LIST(0))

  , { SharedTest.SIZES_ENABLED = SharedTest.SMARTPHONE
     new ContestDescriptionAuthTest_Header(Contest.TIME_0_LIST(0))
  }
  ,  new ContestDescriptionAuthTest_Sections(Contest.TIME_0_LIST(0))
  ,  new ContestDescriptionAuthTest_Bug(Contest.TIME_0_LIST(0))
/*
  , { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP | SharedTest.TABLET | SharedTest.SMARTPHONE
     new InitializerTest
  }
  , new EnterContestVisitorTest(EnterContestState.TIME_0_LIST(0))
*/
*

)


*/