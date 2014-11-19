package unusual.tests.simulatorController

import unusual.model.Resolution
import unusual.pages.SharedPage
import unusual.pages.util.JS_Ops
import unusual.testTags.scala.WIP
import unusual.tests.SharedTest

class SimulatorController(res:Resolution) extends SharedTest(res) with JS_Ops{

  val MAX_TIMEOUT_TIME = 3000
  val INTERVAL_TIME = 1
  val WORLD_CUP = 4
  val CHAMPIONS_LEAGUE = 5
  val SPANISH_LA_LIGA = 23

  val url = SharedPage.baseUrl + "/admin"

  val URL_START = url + "/test_start"
  val URL_CURRENT_DATE = url + "/test_current_date"
  val URL_INITIAL_SETUP = url + "/test_initial_setup"
  val URL_IMPORT_SALARIES = url + "/import_salaries"
  def URL_GO_TO_DATE(day:Int, month:Int, year:Int, hour:Int, minute:Int) = url + s"/test_goto/$year/$month/$day/$hour/$minute"
  def URL_SELECT_COMPETITION(seasonId:Int, competitionId:Int) = url + s"/opta_competitions/${seasonId}-${competitionId}/activated/true"
  def URL_CREATE_TEMPLATES(mockIndex:Int) = url + s"/create_contests/$mockIndex"


  def initialTestsSetup: Unit = {
    Given("a clean browser")
    prepareInitialBrowserState
    Then("reset simulator")
    go to URL_START
    And("select World Cup competiton.")
    go to URL_SELECT_COMPETITION(2013, WORLD_CUP)
    //Then("import soccers' salaries")
    //go to URL_IMPORT_SALARIES

    When("go to date: 2014/06/10 08:00:49 UTC")
    timeShift(10,6,2014,0,0, "2014/06/10 08:00:49 UTC")
    Then("perform initial set up")
    go to URL_INITIAL_SETUP
    And("create presonalized contests")
    createTemplateContest(0)
    And("finally go to 2014/06/12 08:00:36 UTC")
    timeShift(12,6,2014,0,0, "2014/06/12 08:00:36 UTC")
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

  def prepareInitialBrowserState: Unit = {
    go to SharedPage.baseUrl
    delete all cookies
    fastCleanLocalStorage
  }

}

class InitializerTest(res:Resolution) extends SimulatorController(res) {
  if(status.resolution.enabled) "Simulator" should ("set up initial configuration" taggedAs WIP in initialTestsSetup)
}

class TimeShiftTest(res:Resolution, day:Int, month:Int, year:Int, hour:Int, minute:Int, returnedString:String) extends SimulatorController(res) {
  if(status.resolution.enabled) "Simulator" should ("perform a time shift" taggedAs WIP in timeShift(day, month, year, hour, minute, returnedString))
}