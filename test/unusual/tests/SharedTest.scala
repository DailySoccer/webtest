package unusual.tests

import org.scalatest._
import org.scalatestplus.play._
import play.api.Logger
import unusual.UnusualLogger
import unusual.model._
import unusual.pages._
import org.scalatest.time._
import unusual.testTags.scala.WIP

class SharedTest extends PlaySpec with OneServerPerSuite with OneBrowserPerSuite
    with SauceLabsFactory with BeforeAndAfter with BeforeAndAfterAll
    with BeforeAndAfterEach with SpanSugar with GivenWhenThen {

  var status:TestStatus = new TestStatus
  var logger:UnusualLogger = {
    val l = new UnusualLogger()
    l.logger = Logger(this.getClass)
    l
  }


  override def beforeAll {
    println("========= BEFORE ALL!")  // start up your web server or whatever
    SharedPage.driver = webDriver
    status.setDefault
  }

  override def afterAll {
    println("========= AFTER ALL!") // shut down the web server
    webDriver.quit
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
    assert(enterContest.isAt, "trying to access to enterContest")
    enterContest
  }

  def goToViewContest(state:ViewContestState) : ViewContestPage = {
    val viewContest:ViewContestPage = new ViewContestPage(status.resolution, state)
    viewContest.open
    assert(viewContest.isAt, "trying to access to enterContest")
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

  def changeBrowserResolution(res: Resolution) {
    webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(res.width, res.height));
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

  def sizeTesting(behavior: (Resolution) => Unit) = {

    if ((SharedTest.DESKTOP & SharedTest.SIZES_ENABLED) > 0) "use DESKTOP device" must {
      val resolution:Resolution = Resolution.BIG

      "stablish browser resolution" taggedAs(WIP) in {
        status.resolution = resolution
      }

      behave like behavior(resolution)
    }

    if ((SharedTest.TABLET & SharedTest.SIZES_ENABLED) > 0) "use TABLET device" must {
      val resolution:Resolution = Resolution.MEDIUM

      "stablish browser resolution" taggedAs(WIP) in {
        status.resolution = resolution
      }

      behave like behavior(resolution)
    }

    if ((SharedTest.SMARTPHONE & SharedTest.SIZES_ENABLED) > 0) "use SMARTPHONE device" must {
      val resolution:Resolution = Resolution.SMALL

      "stablish browser resolution" taggedAs(WIP) in {
        status.resolution = resolution
      }

      behave like behavior(resolution)
    }
  }
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

object SharedTest {

  val DESKTOP = 1
  val TABLET = 2
  val SMARTPHONE = 4

  var SIZES_ENABLED = 7
}
