package unusual.tests.runner

import org.scalatest._
import unusual.tests.contestDescriptionTest.runner.ContestDescriptionSequentialTestRunner
import unusual.tests.enterContestTest.runner.EnterContestSequentialTestRunner
import unusual.tests.lobbyTest.runner._


class SequentialTestRunner extends Sequential(
  SequentialTestRunner.lobbyTests
  , SequentialTestRunner.contestDescriptionTests
  , SequentialTestRunner.enterContestTests
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

  def lobbyTests: Suite = if(enabledTestList.isEmpty || enabledTestList.contains("LOBBY")){
                            new LobbySequentialTestRunner
                          } else {
                            new Sequential()
                          }

  def contestDescriptionTests: Suite =  if(enabledTestList.isEmpty || enabledTestList.contains("CONTEST_DESCRIPTION")){
                                          new ContestDescriptionSequentialTestRunner
                                        } else {
                                          new Sequential()
                                        }

  def enterContestTests: Suite =  if(enabledTestList.isEmpty || enabledTestList.contains("ENTER_CONTEST")){
                                    new EnterContestSequentialTestRunner
                                  } else {
                                    new Sequential()
                                  }
/*
  def lobbyTests: Suite = if(enabledTestList.isEmpty || enabledTestList.contains("LOBBY")){
    new LobbySequentialTestRunner
  } else {
    new Sequential()
  }
*/
}