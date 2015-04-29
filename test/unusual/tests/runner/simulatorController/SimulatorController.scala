package unusual.tests.runner.simulatorController

import play.api.libs.ws.{Response, WS}
import unusual.model.Resolution
import unusual.pages.SharedPage
import unusual.pages.util.JS_Ops
import unusual.testTags.scala.WIP
import unusual.tests.SharedTest
import org.scalatest._
import org.scalatestplus.play._
import play.api.test._

import scala.concurrent.{Await, Future}

trait SimulatorController { this: SharedTest =>

  private lazy val res: Resolution = status.resolution

  private val MAX_TIMEOUT_TIME = 3000
  private val INTERVAL_TIME = 1
  private val WORLD_CUP = 4
  private val CHAMPIONS_LEAGUE = 5
  private val PREMIER_LEAGUE = 8
  private val SPANISH_LA_LIGA = 23

  private val url = SharedPage.baseUrl + "/admin"

  private val URL_START = url + "/test_start"
  private val URL_CURRENT_DATE = url + "/test_current_date"
  private val URL_INITIAL_SETUP = url + "/test_initial_setup"
  private val URL_IMPORT_SALARIES = url + "/import_salaries"
  private def URL_GO_TO_DATE(day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int) = url + s"/test_goto/$year/$month/$day/$hour/$minute/$second"
  private def URL_GO_TO_DATE_SPEED(speed:Int) = url + s"/simulator_speed/$speed"

  private def URL_SELECT_COMPETITION(seasonId:Int, competitionId:Int) = url + s"/opta_competitions/$seasonId-$competitionId/activated/true"
  private def URL_CREATE_TEMPLATES(mockIndex:Int) = url + s"/create_contests/$mockIndex"
  private def URL_CREATE_PROMOS(mockIndex:Int) = url + s"/create_promos/$mockIndex"
  private val URL_START_BOTS = url + s"/start_bot_actors"
  private val URL_STOP_BOTS = url + s"/stop_bot_actors"
  private val URL_CREATING_TEMPLATE_CONTESTS_ENABLED = url + s"/template_contest/enable_creating/true"
  private def URL_ADD_MONEY_TO_BOTS(amount:Int) = url + s"/add_money_to_bots/$amount"
  private val URL_BERSERKER_BOTS = url + s"/berserker_bot_actors"

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
    And("create some promo")
    createPromo(0)
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
    And("finally berserker bots!!!")
    go to URL_BERSERKER_BOTS
  }

  def finalLeaguesTests: Unit = {
    When("stop bot actors")
    go to URL_STOP_BOTS
  }

  def timeShift(day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int, returnedString:String, speed:Int = -1): Unit = {
    goToHeadlessURL(URL_GO_TO_DATE_SPEED(speed))

    val response:String = goToHeadlessURL(URL_GO_TO_DATE(day, month, year, hour, minute, second))
    logger.info(s"Requested to go to: '$returnedString' ---- Response: '$response'")
    assert(response == "OK", s"Go to date '$returnedString' does not respond OK")

    var dateInfo:String = ""
    eventually (timeout(MAX_TIMEOUT_TIME seconds), interval(INTERVAL_TIME seconds)) {
      find(cssSelector("body")) //keep alive
      dateInfo = goToHeadlessURL(URL_CURRENT_DATE)
      logger.info(s"Current date: '$dateInfo' waiting for '$returnedString'")
      dateInfo must be (returnedString)
    }

    goToHeadlessURL(URL_GO_TO_DATE_SPEED(-1))

  }

  def createTemplateContest(mockIndex:Int): Unit = {
    go to URL_CREATE_TEMPLATES(mockIndex)
  }

  def createPromo(mockIndex:Int): Unit = {
    go to URL_CREATE_PROMOS(mockIndex)
  }

  def prepareInitialBrowserState: Unit = {
    go to SharedPage.baseUrl
    delete all cookies
    fastCleanLocalStorage
    status.setLoggedIn(false)
  }

  private def goToHeadlessURL(url:String): String = {
    Await.result[Response]( WS.url(url).get(), 15 seconds).body
  }
}

class InitializerWorldCupTest(res:Resolution) extends SharedTest(res) {
  if(status.resolution.enabled) "Simulator" should ("set up initial WoldCup configuration" taggedAs WIP in initialWorldCupTestsSetup)
}

class InitializerLeaguesTest(res:Resolution) extends SharedTest(res) {
  if(status.resolution.enabled) "Simulator" should ("set up initial Leagues configuration" taggedAs WIP in initialLeaguesTestsSetup)
}

class TimeShiftTest(res:Resolution, day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int,
                    returnedString:String, description:String = "perform a time shift") extends SharedTest(res) {
  if(status.resolution.enabled) "Simulator" should (description taggedAs WIP in timeShift(day, month, year, hour, minute, second:Int, returnedString))
}

class FinisherLeaguesTest(res:Resolution) extends SharedTest(res) {
  if(status.resolution.enabled) "Simulator" should ("finisher Leagues" taggedAs WIP in finalLeaguesTests)
}
