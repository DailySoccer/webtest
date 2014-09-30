package unusual.tests.simulatorController

import unusual.pages.SharedPage
import unusual.testTags.scala.WIP
import unusual.tests.SharedTest

class SimulatorController extends SharedTest {

  val MAX_TIMEOUT_TIME = 3000
  val INTERVAL_TIME = 1
  val WORLD_CUP = 4
  val CHAMPIONS_LEAGUE = 5
  val SPANISH_LA_LIGA = 23

  val url = SharedPage.baseUrl + "/admin"

  val URL_START = url + "/test_start"
  val URL_CURRENT_DATE = url + "/test_current_date"
  val URL_INITIAL_SETUP = url + "/test_initial_setup"
  def URL_GO_TO_DATE(day:Int, month:Int, year:Int, hour:Int, minute:Int) = url + s"/test_goto/$year/$month/$day/$hour/$minute"
  def URL_SELECT_COMPETITION(seasonId:Int, competitionId:Int) = url + s"/opta_competitions/${seasonId}-${competitionId}/activated/true"
  def URL_CREATE_TEMPLATES(mockIndex:Int) = url + s"/create_contests/$mockIndex"


  def initialTestsSetup: Unit = {
    go to URL_START
    go to URL_SELECT_COMPETITION(2013, WORLD_CUP)
    timeShift(10,6,2014,0,0, "14/06/10 08:00:49 UTC")
    go to URL_INITIAL_SETUP
    createTemplateContest(0)
    timeShift(12,6,2014,0,0, "14/06/12 08:00:36 UTC")
  }

  def timeShift(day:Int, month:Int, year:Int, hour:Int, minute:Int, returnedString:String): Unit ={
    go to URL_GO_TO_DATE(day, month, year, hour, minute)

    eventually (timeout(MAX_TIMEOUT_TIME seconds), interval(INTERVAL_TIME seconds)){
      go to URL_CURRENT_DATE
      find(cssSelector("pre")).get.text must be(returnedString)
    }
  }

  def createTemplateContest(mockIndex:Int): Unit = {
      go to URL_CREATE_TEMPLATES(mockIndex)
  }

}

class InitializerTest extends SimulatorController {
  "Simulator" should ("set up initial configuration" taggedAs WIP in initialTestsSetup)
}

class TimeShiftTest(day:Int, month:Int, year:Int, hour:Int, minute:Int, returnedString:String) extends SimulatorController {
  "Simulator" should ("perform a time shift" taggedAs WIP in timeShift(day, month, year, hour, minute, returnedString))
}