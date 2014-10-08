package unusual.tests.runner

import org.scalatest.Sequential
import unusual.model.{ViewContestState, EnterContestState, LobbyState, Contest}
import unusual.tests.SharedTest

import unusual.tests.contestDescriptionTest._
import unusual.tests.enterContestTest.EnterContestAuthTest
import unusual.tests.lobbyTest._
import unusual.tests.simulatorController._
import unusual.tests.viewContestEntryTest.ViewContestAuthTest


class SequentialTestRunner extends Sequential(

  {SharedTest.SIZES_ENABLED = SharedTest.DESKTOP
   new InitializerTest
  }
  //, new LobbyVisitorTest(LobbyState.DEFAULT_LOBBY)
  //, new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  , new ContestDescriptionAuthTest(Contest.TIME_0_LIST(0))
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(0))
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(1))
  , new InitializerTest
  , new ViewContestAuthTest(ViewContestState.TIME_0_LIST(0))
  , new ViewContestAuthTest(ViewContestState.TIME_0_LIST(1))

  {SharedTest.SIZES_ENABLED = SharedTest.TABLET
    new InitializerTest
  }
  , new LobbyVisitorTest(LobbyState.DEFAULT_LOBBY)
  , new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  , new ContestDescriptionAuthTest(Contest.TIME_0_LIST(0))
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(0))
  , new InitializerTest
  , new ViewContestAuthTest(ViewContestState.TIME_0_LIST(0))

  {SharedTest.SIZES_ENABLED = SharedTest.SMARTPHONE
    new InitializerTest
  }
  , new LobbyVisitorTest(LobbyState.DEFAULT_LOBBY)
  , new LobbyAuthTest(LobbyState.DEFAULT_LOBBY)
  , new ContestDescriptionAuthTest(Contest.TIME_0_LIST(0))
  , new EnterContestAuthTest(EnterContestState.TIME_0_LIST(1))
  , new InitializerTest
  , new ViewContestAuthTest(ViewContestState.TIME_0_LIST(1))


  )