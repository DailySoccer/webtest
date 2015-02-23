package unusual.tests.runner

import org.scalatest._
import unusual.model.Resolution
import unusual.tests.contestDescriptionTest.runner.ContestDescriptionSequentialTestRunner
import unusual.tests.enterContestTest.runner.EnterContestSequentialTestRunner
import unusual.tests.lobbyTest.runner._
import unusual.tests.integrityTest.runner.IntegritySequentialTestRunner
import unusual.tests.simulatorController.{TimeShiftTest, InitializerLeaguesTest, InitializerWorldCupTest}
import unusual.tests.viewContestEntryTest.runner.ViewContestSequentialTestRunner


class SequentialTestRunner extends Sequential(
  new InitializerWorldCupTest(Resolution.ANY)
  , SequentialTestRunner.lobbyTests
  //, SequentialTestRunner.contestDescriptionTests
  , SequentialTestRunner.enterContestTests
  , SequentialTestRunner.viewContestTests

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

  val shouldExecuteLobby = enabledTestList.isEmpty || enabledTestList.contains("LOBBY")
  val shouldExecuteContestDescription = enabledTestList.isEmpty || enabledTestList.contains("CONTEST_DESCRIPTION")
  val shouldExecuteEnterContest = enabledTestList.isEmpty || enabledTestList.contains("ENTER_CONTEST")
  val shouldExecuteViewContest = enabledTestList.isEmpty || enabledTestList.contains("VIEW_CONTEST")
  val shouldExecuteIntegrity = enabledTestList.contains("INTEGRITY")


  def lobbyTests: Suite = if(shouldExecuteLobby || shouldExecuteContestDescription){
                            new LobbySequentialTestRunner()
                          } else {
                            new Sequential
                          }
/*
  def contestDescriptionTests: Suite = if(shouldExecuteContestDescription){
                                         new ContestDescriptionSequentialTestRunner
                                       } else {
                                         new Sequential
                                       }
*/
  def enterContestTests: Suite = if(shouldExecuteEnterContest){
                                   new EnterContestSequentialTestRunner
                                 } else {
                                   new Sequential
                                 }

  def viewContestTests: Suite = if(shouldExecuteViewContest){
                                  new ViewContestSequentialTestRunner
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