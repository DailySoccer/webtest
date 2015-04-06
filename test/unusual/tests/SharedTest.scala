package unusual.tests

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.safari.SafariDriver
import org.scalatest._
import org.scalatest.concurrent.{IntegrationPatience, Eventually}
import org.scalatest.selenium.WebBrowser
import org.scalatestplus.play.BrowserFactory.UnavailableDriver
import org.scalatestplus.play._
import play.api.Logger
import play.api.mvc.RequestHeader
import unusual._
import unusual.model._
import unusual.model.pageStates.{ViewContestState, LobbyState, EnterContestState}
import unusual.pages._
import org.scalatest.time._
import unusual.pages.util.JS_Ops
import unusual.testTags.scala.WIP
import unusual.tests.runner.simulatorController.SimulatorController


class SharedTest(resolution:Resolution) extends PlaySpec
    with OneServerPerLaunch with OneBrowserPerLaunch
    with WebDriverFactory with BeforeAndAfter with BeforeAndAfterAll
    with BeforeAndAfterEach with SpanSugar with GivenWhenThen with JS_Ops
    with SimulatorController {

  var status:TestStatus = new TestStatus
  status.setBaseResolution(resolution)
  var logger:UnusualLogger = new UnusualLogger(this.getClass)

  // TODO: INTERESANTE
  // override val port = 5646
  // app.global.onError


  override def beforeAll {
    logger.debug("========= BEFORE ALL!")  // start up your web server or whatever
    SharedPage.driver = webDriver
    status.setDefault
  }

  override def afterAll {
    logger.debug("========= AFTER ALL!") // shut down the web server
    //webDriver.quit
  }

  def goToHomePage : HomePage = {
    val homePage = new HomePage(status.resolution)
    assert(homePage.open.isAt, "trying to access to homePage")
    homePage
  }

  def goToSignUpPage : SignUpPage = {
    val signUpPage = new SignUpPage(status.resolution)
    assert(signUpPage.open.isAt, "trying to access to signUpPage")
    signUpPage
  }

  def goToLoginPage : LoginPage = {
    val loginPage = new LoginPage(status.resolution)
    assert(loginPage.open.isAt, "trying to access to loginPage")
    loginPage
  }

  def goToLobbyPage(lobbyState:LobbyState = LobbyState.DEFAULT_LOBBY) : LobbyPage = {
    val lobbyPage = new LobbyPage(status.resolution, lobbyState.maxEntryMoney)
    assert(lobbyPage.open.isAt, "trying to access to lobbyPage")
    lobbyPage
  }

  def goToMyContestsPage : MyContestsPage = {
    val myContestsPage = new MyContestsPage(status.resolution)
    assert(myContestsPage.open.isAt, "trying to access to myContestsPage")
    myContestsPage
  }

  def goToPromos : PromosPage = {
    val promosPage = new PromosPage(status.resolution)
    assert(promosPage.open.isAt, "trying to access to promosPage")
    promosPage
  }

  def goToEnterContest(state:EnterContestState) : EnterContestPage = {
    val enterContest:EnterContestPage = new EnterContestPage(status.resolution, state)
    enterContest.open
    //eventually { assert(enterContest.isAt, "trying to access to enterContest") }
    enterContest
  }

  def goToViewContest(state:ViewContestState) : ViewContestPage = {
    val viewContest:ViewContestPage = new ViewContestPage(status.resolution, state)
    viewContest.open
    eventually { assert(viewContest.isAt, "trying to access to enterContest") }
    viewContest
  }

  def featureNotImplemented = {
    logger.info("[FUTURE TEST]\u001B[33m This test is a placeholder " +
      "for a future implementation \u001B[0m")

    this
  }

  def featureNotTestableInResolution = {
    logger.info("This test is not runnable in current resolution")
    this
  }

  def testSkippedBecauseIsSafari = {
    logger.info("This test skipped because it uses navigation in history with Safari")
    this
  }

  def changeBrowserResolution(res: Resolution) {
    webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(res.width, res.height))
  }

  def setTestResolution(res:Resolution) = {
    status.resolution = res
    this
  }
/*
  def explicitChangeBrowserResolution(res: Resolution) {
    webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(res.width, res.height));
  }
*/
  override def beforeEach() {
    //implicit val resolution: Resolution = status.resolution
    logger.debug("Changing browser resolution: " + status.resolution)
    changeBrowserResolution(status.resolution)
    //reloadPage
  }

  def sizeTesting(behavior: => Unit) = {
    String.format("%-75s", s"use ${status.resolution} device") + status.resolution must {
      behave like behavior
    }
  }

  /*
  def sizeTesting(behavior: (Resolution) => Unit) = {

    if ((SharedTest.DESKTOP & SharedTest.SIZES_ENABLED) > 0) {
      String.format("%-75s", "use DESKTOP device") + ("DESKTOP") must {
        val resolution:Resolution = Resolution.BIG

        "stablish browser resolution" taggedAs(WIP) in {
          status.resolution = resolution
        }

        behave like behavior(resolution)
      }
    } else {
      logger.info("DESKTOP IS DISABLED")
    }

    if ((SharedTest.TABLET & SharedTest.SIZES_ENABLED) > 0) {
      String.format("%-75s", "use TABLET device") + ("TABLET") must {
        val resolution: Resolution = Resolution.MEDIUM

        "stablish browser resolution" taggedAs (WIP) in {
          status.resolution = resolution
        }

        behave like behavior(resolution)
      }
    } else {
      logger.info("TABLET IS DISABLED")
    }

    if ((SharedTest.SMARTPHONE & SharedTest.SIZES_ENABLED) > 0) {
      String.format("%-75s", "use SMARTPHONE device") + ("SMARTPHONE") must {
        val resolution:Resolution = Resolution.SMALL

        "stablish browser resolution" taggedAs(WIP) in {
          status.resolution = resolution
        }

        behave like behavior(resolution)
      }
    } else {
      logger.info("SMARTPHONE IS DISABLED")
    }
  }
*/

/*
  def callTest(test: (Resolution) => Unit)(implicit resolution:Resolution) = {
    status.resolution = resolution
    changeBrowserResolution
    reloadPage
    test(resolution)
    this
  }
*/
}


/*
object SharedTest {

  private val PARAM_SIZE = scala.util.Properties.envOrElse("RESOLUTION", "ALL").toUpperCase

  val DESKTOP = if (PARAM_SIZE == "ALL" || PARAM_SIZE == "DESKTOP") 1 else 0
  val TABLET = if (PARAM_SIZE == "ALL" || PARAM_SIZE == "TABLET") 2 else 0
  val SMARTPHONE = if (PARAM_SIZE == "ALL" || PARAM_SIZE == "SMARTPHONE") 4 else 0

  var SIZES_ENABLED = 7
}
*/