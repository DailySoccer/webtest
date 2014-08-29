package unusual.tests

import org.scalatest._
import org.scalatestplus.play._
import play.api.Logger
import unusual.model._
import unusual.pages._

class SharedTest extends PlaySpec
    with OneServerPerSuite with OneBrowserPerSuite with SauceLabsFactory
    with BeforeAndAfter with BeforeAndAfterAll {

  var status:TestStatus = new TestStatus

  ///// STATUS
  // logged
  // resolution

  override def beforeAll {
    println("Before!")  // start up your web server or whatever
    SharedPage.driver = webDriver
    status.setDefault
  }

  override def afterAll {
    println("After!")  // shut down the web server
    webDriver.quit
  }

  def goToHomePage : HomePage = {
    val homePage = new HomePage(status.resolution)
    homePage.open.isAt
  }

  def goToSignUpPage : SignUpPage = {
    val signUpPage = new SignUpPage(status.resolution)
    signUpPage.open.isAt
  }

  def goToLoginPage : LoginPage = {
    val loginPage = new LoginPage(status.resolution)
    loginPage.open.isAt
  }

  def goToLobbyPage : LobbyPage = {
    val lobbyPage = new LobbyPage(status.resolution)
    lobbyPage.open.isAt
  }

  def goToMyContestsPage : MyContestsPage = {
    val myContestsPage = new MyContestsPage(status.resolution)
    myContestsPage.open.isAt
  }

  def goToPromos : PromosPage = {
    val promosPage = new PromosPage(status.resolution)
    promosPage.open.isAt
  }

  def featureNotImplemented = {
    println("[FUTURE TEST]\u001B[33m This test is a placeholder for a future implementation \u001B[0m")

    this
  }

  def changeBrowserResolution()(implicit res: Resolution) {
    webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(res.width, res.height));
  }

  def callTest(test: (Resolution) => Unit)(implicit resolution:Resolution) = {
    status.resolution = resolution
    changeBrowserResolution
    println("[additional-info] " + resolution + "...")
    test(resolution)
    println("[additional-info] " + resolution + " OK.")
    this
  }

}
