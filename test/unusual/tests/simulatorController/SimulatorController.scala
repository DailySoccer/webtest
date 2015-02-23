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
  val PREMIER_LEAGUE = 8
  val SPANISH_LA_LIGA = 23

  val url = SharedPage.baseUrl + "/admin"

  val URL_START = url + "/test_start"
  val URL_CURRENT_DATE = url + "/test_current_date"
  val URL_INITIAL_SETUP = url + "/test_initial_setup"
  val URL_IMPORT_SALARIES = url + "/import_salaries"
  def URL_GO_TO_DATE(day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int) = url + s"/test_goto/$year/$month/$day/$hour/$minute/$second"
  def URL_SELECT_COMPETITION(seasonId:Int, competitionId:Int) = url + s"/opta_competitions/${seasonId}-${competitionId}/activated/true"
  def URL_CREATE_TEMPLATES(mockIndex:Int) = url + s"/create_contests/$mockIndex"
  def URL_START_BOTS = url + s"/start_bot_actors"
  def URL_STOP_BOTS = url + s"/stop_bot_actors"
  def URL_CREATING_TEMPLATE_CONTESTS_ENABLED = url + s"/template_contest/enable_creating/true"
  def URL_ADD_MONEY_TO_BOTS(amount:Int) = url + s"/add_money_to_bots/${amount}"
  def URL_BERSERKER_BOTS = url + s"/berserker_bot_actors"

  def initialWorldCupTestsSetup: Unit = {
    Given("a clean browser")
    prepareInitialBrowserState
    Then("reset simulator")
    go to URL_START
    And("select World Cup competition")
    go to URL_SELECT_COMPETITION(2013, WORLD_CUP)
    //Then("import soccers' salaries")
    //go to URL_IMPORT_SALARIES

    When("go to date: 2014/06/10 08:00:49 UTC")
    timeShift(10,6,2014,8,0,49, "2014/06/10 08:00:49 UTC")
    Then("perform initial set up")
    go to URL_INITIAL_SETUP
    And("create personalized contests")
    createTemplateContest(0)
    And("finally go to 2014/06/12 08:00:36 UTC")
    timeShift(12,6,2014,8,0,36, "2014/06/12 08:00:36 UTC")
  }

  def initialLeaguesTestsSetup: Unit = {
    When("reset simulator")
    go to URL_START
    And("select Leagues competitions")
    go to URL_SELECT_COMPETITION(2014, CHAMPIONS_LEAGUE)
    go to URL_SELECT_COMPETITION(2014, PREMIER_LEAGUE)
    go to URL_SELECT_COMPETITION(2014, SPANISH_LA_LIGA)
    And("start bot actors")
    go to URL_START_BOTS
    And("creating template contests enabled")
    go to URL_CREATING_TEMPLATE_CONTESTS_ENABLED
    And("go to 2014/08/01 08:00:36 UTC")
    timeShift(1,8,2014,8,0,36, "2014/08/01 08:00:36 UTC")
    And("give funds to bots")
    go to URL_ADD_MONEY_TO_BOTS(1000)
    And("finally berseker bots!!!")
    go to URL_BERSERKER_BOTS
  }

  def timeShift(day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int, returnedString:String): Unit ={
    go to URL_GO_TO_DATE(day, month, year, hour, minute, second)

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
    status.setLoggedIn(false)
  }

}

class InitializerWorldCupTest(res:Resolution) extends SimulatorController(res) {
  if(status.resolution.enabled) "Simulator" should ("set up initial WoldCup configuration" taggedAs WIP in initialWorldCupTestsSetup)
}

class InitializerLeaguesTest(res:Resolution) extends SimulatorController(res) {
  if(status.resolution.enabled) "Simulator" should ("set up initial Leagues configuration" taggedAs WIP in initialLeaguesTestsSetup)
}

class TimeShiftTest(res:Resolution, day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int, returnedString:String, description:String = "perform a time shift") extends SimulatorController(res) {
  if(status.resolution.enabled) "Simulator" should (description taggedAs WIP in timeShift(day, month, year, hour, minute, second:Int, returnedString))
}