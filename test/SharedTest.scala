import pages._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class SharedTest extends PlaySpec
  with OneServerPerSuite with OneBrowserPerSuite with SauceLabsFactory with BeforeAndAfter with BeforeAndAfterAll {

  val BIG:String   = "big"
  val MEDIUM:String  = "medium"
  val SMALL:String  = "small"
  val RESOLUTIONS: List[String] = List(
    BIG,
    MEDIUM,
    SMALL)

  override def beforeAll {
    println("Before!")  // start up your web server or whatever
    SharedPage.driver = webDriver
  }

  override def afterAll {
    println("After!")  // shut down the web server
    webDriver.quit
  }

  def goToHomePage : HomePage = {
    val homePage = new HomePage
    homePage.open.isAt
  }

  def goToSignUpPage : SignUpPage = {
    val signupPage = new SignUpPage
    signupPage.open.isAt
  }

  def goToLoginPage : LoginPage = {
    val loginPage = new LoginPage
    loginPage.open.isAt
  }

  def goToLobbyPage : LobbyPage = {
    val lobbyPage = new LobbyPage
    lobbyPage.open.isAt
  }

  def goToMyContestsPage : MyContestsPage = {
    val myContestsPage = new MyContestsPage
    myContestsPage.open.isAt
  }

  def goToPromos : PromosPage = {
    val promosPage = new PromosPage
    promosPage.open.isAt
  }



  def configResolution(res: String) {
    res match {
      case SMALL =>
        webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(300, 500));

      case MEDIUM =>
        webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(850, 720));

      case BIG =>
        webDriver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
    }
  }
}
