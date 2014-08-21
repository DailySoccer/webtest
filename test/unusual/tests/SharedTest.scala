package unusual.tests

import org.scalatest._
import org.scalatestplus.play._
import unusual.model.Resolution
import unusual.pages._

class SharedTest extends PlaySpec
  with OneServerPerSuite with OneBrowserPerSuite with SauceLabsFactory with BeforeAndAfter with BeforeAndAfterAll {

  val RESOLUTIONS = Resolution.ALL
  val DEFAULT_RESOLUTION = Resolution.BIG
  var currentResolution = DEFAULT_RESOLUTION

  override def beforeAll {
    println("Before!")  // start up your web server or whatever
    SharedPage.driver = webDriver
  }

  override def afterAll {
    println("After!")  // shut down the web server
    webDriver.quit
  }

  def goToHomePage : HomePage = {
    val homePage = new HomePage(currentResolution)
    homePage.open.isAt
  }

  def goToSignUpPage : SignUpPage = {
    val signUpPage = new SignUpPage(currentResolution)
    signUpPage.open.isAt
  }

  def goToLoginPage : LoginPage = {
    val loginPage = new LoginPage(currentResolution)
    loginPage.open.isAt
  }

  def goToLobbyPage : LobbyPage = {
    val lobbyPage = new LobbyPage(currentResolution)
    lobbyPage.open.isAt
  }

  def goToMyContestsPage : MyContestsPage = {
    val myContestsPage = new MyContestsPage(currentResolution)
    myContestsPage.open.isAt
  }

  def goToPromos : PromosPage = {
    val promosPage = new PromosPage(currentResolution)
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
    changeBrowserResolution
    println("[additional-info] " + resolution + "...")
    currentResolution = resolution
    test(resolution)
    currentResolution = DEFAULT_RESOLUTION
    println("[additional-info] " + resolution + " OK.")

    this
  }

}
