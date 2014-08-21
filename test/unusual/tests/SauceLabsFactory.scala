package unusual.tests

import java.net.URL

import com.saucelabs.common.{SauceOnDemandAuthentication, SauceOnDemandSessionIdProvider}
import org.openqa.selenium._
import org.openqa.selenium.chrome._
import org.openqa.selenium.firefox._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}
import unusual.pages.SharedPage

trait SauceLabsFactory extends SauceOnDemandSessionIdProvider {
  val SAUCE_LABS_HOST   = "sauce labs"
  val SAUCE_LABS_CONFIG = List(
    Map(
      "name"    -> "DailySoccer Test",
      "os"      -> "OSX 10.9",
      "version" -> "28",
      "browser" -> "firefox"
    )
  )
  val CHROME_HOST   = "chrome"
  val FIREFOX_HOST  = "firefox"

  val host:String       = if (SharedPage.isLocalHost) FIREFOX_HOST else SAUCE_LABS_HOST
  var sessionId: String = ""

  /**
   * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
   * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
   */
  val authentication = new SauceOnDemandAuthentication("Ximo", "3b338dac-feba-4b1a-828f-dcc4e46af910")

  /**
   * Creates a new instance of a Selenium 'Remote Driver'
   *
   * @return an new instance of a Selenium `RemoteDriver` or a `BrowserFactory.UnavailableDriver`
   */
  def createWebDriver : WebDriver = {
    host match {
      case SAUCE_LABS_HOST =>
        println("RemoteDriver SauceLabs -----------")
        createSauceLabsDriver

      case CHROME_HOST =>
        println("ChromeDriver -----------")
        new ChromeDriver

      case FIREFOX_HOST =>
        println("FirefoxDriver -----------")
        new FirefoxDriver
    }
  }

  /**
   *
   * @return remote web driver
   */
  private def createSauceLabsDriver = {
    val driver = new RemoteWebDriver(urlSauceLabs, createDesiredCapabilities(SAUCE_LABS_CONFIG(0)))
    sessionId = driver.getSessionId.toString
    driver
  }

  private def urlSauceLabs = {
    new URL("http://" + authentication.getUsername + ":" + authentication.getAccessKey + "@ondemand.saucelabs.com:80/wd/hub")
  }

  private def createDesiredCapabilities (config : Map[String,String]) = {
    val capabilities = new DesiredCapabilities()
    capabilities.setCapability("name", config("name"))
    capabilities.setCapability(CapabilityType.BROWSER_NAME, config("browser"))
    if (config.contains("version")) {
      capabilities.setCapability(CapabilityType.VERSION, config("version"))
    }
    capabilities.setCapability(CapabilityType.PLATFORM, config("os"))
    capabilities
  }

  /**
   *
   * @return the value of the Sauce Job id.
   */
  var getSessionId: String = sessionId
}
