package unusual.tests

import java.net.URL

import com.saucelabs.common.{SauceOnDemandAuthentication, SauceOnDemandSessionIdProvider}
import org.openqa.selenium._
import org.openqa.selenium.chrome._
import org.openqa.selenium.firefox._
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}
import unusual.pages.SharedPage

trait SauceLabsFactory extends SauceOnDemandSessionIdProvider {

  val SAUCE_LABS_HOST   = "sauce labs"
  val HOUR = 3600
  val MAX_DURATION = 3 * HOUR
  val SAUCE_LABS_CONFIG:Map[String, DesiredCapabilities] = Map(
    "FF_32" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 32")
                 cap.setCapability(CapabilityType.VERSION, "32")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap
               },
    "IE_10" -> { val cap = DesiredCapabilities.internetExplorer()
                 cap.setCapability("name", "DFS IE 10")
                 cap.setCapability(CapabilityType.VERSION, "10")
                 cap.setCapability(CapabilityType.PLATFORM, Platform.ANY)
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap
               },
    "CHROME_38" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 38")
                     cap.setCapability(CapabilityType.VERSION, "38")
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   }
  )

  val CHROME_HOST   = "chrome"
  val FIREFOX_HOST  = "firefox"
  val PHANTOMJS_HOST  = "phantom"

  val host:String       = if (SharedPage.isLocalHost) FIREFOX_HOST else SAUCE_LABS_HOST
  var sessionId: String = ""

  /**
   * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
   * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
   */
  val authentication = new SauceOnDemandAuthentication("korgan00", "dd8d5996-55dd-47c9-bd9c-bce939d18232")

  //val authentication = new SauceOnDemandAuthentication("victormendiluce1", "jQ8keLwu3V2PuXx9BGA4")
  //val authentication = new SauceOnDemandAuthentication("sreveloc", "add73570-deea-44c2-82e2-331d7d0e69eb")
  //val authentication = new SauceOnDemandAuthentication("Ximo", "3b338dac-feba-4b1a-828f-dcc4e46af910")

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

      case PHANTOMJS_HOST =>
        println("PhantomJS Driver ----------")
        new PhantomJSDriver
    }
  }

  /**
   *
   * @return remote web driver
   */
  private def createSauceLabsDriver = {
    val driver = new RemoteWebDriver(urlSauceLabs, SAUCE_LABS_CONFIG(scala.util.Properties.envOrElse("BROWSER", "FF_32").toUpperCase))
    sessionId = driver.getSessionId.toString
    driver
  }

  private def urlSauceLabs = {
    new URL("http://" + authentication.getUsername + ":" + authentication.getAccessKey + "@ondemand.saucelabs.com:80/wd/hub")
    //new URL("http://" + authentication.getUsername + ":" + authentication.getAccessKey +  "@hub.browserstack.com/wd/hub")
  }

  /**
   *
   * @return the value of the Sauce Job id.
   */
  var getSessionId: String = sessionId
}
