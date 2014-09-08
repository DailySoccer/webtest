package unusual.tests

import org.scalatest._
import org.scalatestplus.play._
import play.api.Logger
import unusual.UnusualLogger
import unusual.model._
import unusual.pages._
import org.scalatest.time._

class SharedTest extends PlaySpec
    with OneServerPerSuite with OneBrowserPerSuite with SauceLabsFactory
    with BeforeAndAfter with BeforeAndAfterAll with SpanSugar {

  var status:TestStatus = new TestStatus
  var logger:UnusualLogger = {
    val l = new UnusualLogger()
    l.logger = Logger(this.getClass)
    l
  }

  ///// STATUS
  // logged
  // resolution

  override def beforeAll {
    //println("Before!")  // start up your web server or whatever
    SharedPage.driver = webDriver
    status.setDefault
  }

  override def afterAll {
    //println("After!")  // shut down the web server
    webDriver.quit
  }

  def goToHomePage : HomePage = {
    val homePage = new HomePage(status.resolution)
    assert(homePage.open.isAt)
    homePage
  }

  def goToSignUpPage : SignUpPage = {
    val signUpPage = new SignUpPage(status.resolution)
    assert(signUpPage.open.isAt)
    signUpPage
  }

  def goToLoginPage : LoginPage = {
    val loginPage = new LoginPage(status.resolution)
    assert(loginPage.open.isAt)
    loginPage
  }

  def goToLobbyPage : LobbyPage = {
    val lobbyPage = new LobbyPage(status.resolution)
    assert(lobbyPage.open.isAt)
    lobbyPage
  }

  def goToMyContestsPage : MyContestsPage = {
    val myContestsPage = new MyContestsPage(status.resolution)
    assert(myContestsPage.open.isAt)
    myContestsPage
  }

  def goToPromos : PromosPage = {
    val promosPage = new PromosPage(status.resolution)
    assert(promosPage.open.isAt)
    promosPage
  }

  def featureNotImplemented = {
    logger.info("[FUTURE TEST]\u001B[33m This test is a placeholder for a future implementation \u001B[0m")

    this
  }

  def featureNotTestableInResolution = {
    logger.info("This test is not runnable in current resolution")
    this
  }

  def changeBrowserResolution()(implicit res: Resolution) {
    webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(res.width, res.height));
  }

  def explicitChangeBrowserResolution(res: Resolution) {
    webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(res.width, res.height));
  }

  def callTest(test: (Resolution) => Unit)(implicit resolution:Resolution) = {
    status.resolution = resolution
    changeBrowserResolution
    logger.info(resolution + "...")
    test(resolution)
    logger.info(resolution + " OK.")
    this
  }

}
