package unusual.tests.runner

import org.scalatest._
import unusual.model.Resolution
import unusual.tests.loginTest.runner.LoginSignUpSequentialTestRunner
import unusual.tests.contestDescriptionTest.runner.ContestDescriptionSequentialTestRunner
import unusual.tests.enterContestTest.runner.EnterContestSequentialTestRunner
import unusual.tests.lobbyTest.runner._
import unusual.tests.integrityTest.runner.IntegritySequentialTestRunner
import unusual.tests.myContestTest.runner.MyContestsSequentialTestRunner
import unusual.tests.navigationTest.runner.NavigationSequentialTestRunner
import unusual.tests.runner.simulatorController.{TimeShiftTest, InitializerLeaguesTest, InitializerWorldCupTest}
import unusual.tests.timeShiftTests.runner.TimeShiftSequentialTestRunner
import unusual.tests.viewContestEntryTest.runner.ViewContestSequentialTestRunner


class SequentialTestRunner extends Sequential(
  new InitializerWorldCupTest(Resolution.ANY)
  , SequentialTestRunner.navigationTests
  , SequentialTestRunner.loginTests
  , SequentialTestRunner.lobbyTests
  , SequentialTestRunner.contestDescriptionTests
  , SequentialTestRunner.enterContestTests
  , SequentialTestRunner.viewContestTests
  , SequentialTestRunner.myContestsTests
  , SequentialTestRunner.timeShiftContestTests
  , SequentialTestRunner.integrityTests
  , new CloseTestServer(Resolution.ANY)
)


object SequentialTestRunner {

  var enabledTestList:Array[String] = {
    val envVar:String = scala.util.Properties.envOrElse("TEST_SUITE", "")

    if (envVar != "") {
      envVar.toUpperCase.split(" ")
    } else {
      new Array[String](0)
    }
  }

  val shouldExecuteNavigation         = enabledTestList.isEmpty || enabledTestList.contains("NAVIGATION")
  val shouldExecuteLogin              = enabledTestList.isEmpty || enabledTestList.contains("LOGIN_SIGNUP") ||
                                        enabledTestList.contains("LOGIN") || enabledTestList.contains("SIGNUP")
  val shouldExecuteLobby              = enabledTestList.isEmpty || enabledTestList.contains("LOBBY")
  val shouldExecuteContestDescription = enabledTestList.isEmpty || enabledTestList.contains("CONTEST_DESCRIPTION")
  val shouldExecuteEnterContest       = enabledTestList.isEmpty || enabledTestList.contains("ENTER_CONTEST")
  val shouldExecuteMyContest          = enabledTestList.isEmpty || enabledTestList.contains("MY_CONTESTS")
  val shouldExecuteViewContest        = enabledTestList.isEmpty || enabledTestList.contains("VIEW_CONTEST")
  val shouldExecuteTimeShift          = enabledTestList.isEmpty || enabledTestList.contains("TIME_SHIFT")
  val shouldExecuteIntegrity          = enabledTestList.contains("INTEGRITY")


  def navigationTests: Suite = if(shouldExecuteNavigation){
                            new NavigationSequentialTestRunner
                          } else {
                            new Sequential
                          }

  def loginTests: Suite = if(shouldExecuteLogin){
                            new LoginSignUpSequentialTestRunner
                          } else {
                            new Sequential
                          }

  def lobbyTests: Suite = if(shouldExecuteLobby){
                            new LobbySequentialTestRunner
                          } else {
                            new Sequential
                          }

  def contestDescriptionTests: Suite = if(shouldExecuteContestDescription){
                                         new ContestDescriptionSequentialTestRunner
                                       } else {
                                         new Sequential
                                       }

  def enterContestTests: Suite = if(shouldExecuteEnterContest){
                                   new EnterContestSequentialTestRunner
                                 } else {
                                   new Sequential
                                 }

  def myContestsTests: Suite = if(shouldExecuteMyContest){
                                new MyContestsSequentialTestRunner
                              } else {
                                new Sequential
                              }

  def viewContestTests: Suite = if(shouldExecuteViewContest){
                                  new ViewContestSequentialTestRunner
                                } else {
                                  new Sequential
                                }


  def timeShiftContestTests: Suite = if(shouldExecuteTimeShift){
                                      new TimeShiftSequentialTestRunner
                                    } else {
                                      new Sequential
                                    }

  def integrityTests: Suite = if(shouldExecuteIntegrity){
                                new IntegritySequentialTestRunner
                              } else {
                                new Sequential
                              }

/*
  def lobbyTests: Suite = if(enabledTestList.isEmpty || enabledTestList.contains("LOBBY")){
    new LobbySequentialTestRunner
  } else {
    new Sequential()
  }
*/
}