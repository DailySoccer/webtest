package unusual.tests.enterContestTest.runner

import org.scalatest.Sequential
import unusual.model.{LobbyState, EnterContestState}
import unusual.tests.SharedTest
import unusual.tests.enterContestTest._
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController.InitializerTest


class EnterContestSequentialTestRunner extends Sequential(

  { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
    new InitializerTest
    //new EnterContestAuthTest_OrderBy(EnterContestState.TIME_0_LIST(0))
  }
  ,  new EnterContestAuthTest_OrderBy(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_FilterBy(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_SelectPos(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_Bug(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_Pick(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_OrderBy(EnterContestState.TIME_0_LIST(1))
  ,  new EnterContestAuthTest_FilterBy(EnterContestState.TIME_0_LIST(1))
  ,  new EnterContestAuthTest_SelectPos(EnterContestState.TIME_0_LIST(1))
  ,  new EnterContestAuthTest_Bug(EnterContestState.TIME_0_LIST(1))
  ,  new EnterContestAuthTest_Pick(EnterContestState.TIME_0_LIST(1))

  , { SharedTest.SIZES_ENABLED = SharedTest.TABLET
     new InitializerTest
  }
  ,  new EnterContestAuthTest_OrderBy(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_FilterBy(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_SelectPos(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_Bug(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_Pick(EnterContestState.TIME_0_LIST(0))

  , { SharedTest.SIZES_ENABLED = SharedTest.SMARTPHONE
     new InitializerTest
  }
  ,  new EnterContestAuthTest_OrderBy(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_FilterBy(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_SelectPos(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_Bug(EnterContestState.TIME_0_LIST(0))
  ,  new EnterContestAuthTest_Pick(EnterContestState.TIME_0_LIST(0))

/*
  , { SharedTest.SIZES_ENABLED = SharedTest.DESKTOP | SharedTest.TABLET | SharedTest.SMARTPHONE
     new InitializerTest
  }
  , new EnterContestVisitorTest(EnterContestState.TIME_0_LIST(0))
*/

)