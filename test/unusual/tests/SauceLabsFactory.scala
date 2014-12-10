package unusual.tests

import java.net.URL
import java.util.concurrent.TimeUnit

import com.saucelabs.common.{SauceOnDemandAuthentication, SauceOnDemandSessionIdProvider}
import org.openqa.selenium._
import org.openqa.selenium.chrome._
import org.openqa.selenium.firefox._
import org.openqa.selenium.safari._
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}
import unusual.pages.SharedPage

trait SauceLabsFactory extends SauceOnDemandSessionIdProvider {

  val SAUCE_LABS_HOST   = "sauce labs"
  val HOUR = 3600
  val MAX_DURATION = 3 * HOUR
  val SAUCE_LABS_CONFIG:Map[String, DesiredCapabilities] = Map(
    // FIREFOX
    "FF_12" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 12")
                 cap.setCapability(CapabilityType.VERSION, "12")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_13" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 13")
                 cap.setCapability(CapabilityType.VERSION, "13")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_14" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 14")
                 cap.setCapability(CapabilityType.VERSION, "14")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_15" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 15")
                 cap.setCapability(CapabilityType.VERSION, "15")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_16" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 16")
                 cap.setCapability(CapabilityType.VERSION, "16")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_17" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 17")
                 cap.setCapability(CapabilityType.VERSION, "17")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_18" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 18")
                 cap.setCapability(CapabilityType.VERSION, "18")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_19" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 19")
                 cap.setCapability(CapabilityType.VERSION, "19")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_20" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 20")
                 cap.setCapability(CapabilityType.VERSION, "20")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_21" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 21")
                 cap.setCapability(CapabilityType.VERSION, "21")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_22" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 22")
                 cap.setCapability(CapabilityType.VERSION, "22")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_23" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 23")
                 cap.setCapability(CapabilityType.VERSION, "23")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_24" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 24")
                 cap.setCapability(CapabilityType.VERSION, "24")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_25" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 25")
                 cap.setCapability(CapabilityType.VERSION, "25")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_26" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 26")
                 cap.setCapability(CapabilityType.VERSION, "26")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_27" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 27")
                 cap.setCapability(CapabilityType.VERSION, "27")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_28" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 28")
                 cap.setCapability(CapabilityType.VERSION, "28")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_29" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 29")
                 cap.setCapability(CapabilityType.VERSION, "29")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_30" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 30")
                 cap.setCapability(CapabilityType.VERSION, "30")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_31" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 31")
                 cap.setCapability(CapabilityType.VERSION, "31")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_32" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 32")
                 cap.setCapability(CapabilityType.VERSION, "32")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },
    "FF_33" -> { val cap = DesiredCapabilities.firefox()
                 cap.setCapability("name", "DFS FF 33")
                 cap.setCapability(CapabilityType.VERSION, "33")
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                 cap
               },

    // INTERNET EXPLORER
    "IE_7" -> { val cap = DesiredCapabilities.internetExplorer()
                 cap.setCapability("name", "DFS IE 7")
                 cap.setCapability(CapabilityType.VERSION, "7")
                 cap.setCapability(CapabilityType.PLATFORM, Platform.ANY)
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap
               },
    "IE_8" -> { val cap = DesiredCapabilities.internetExplorer()
                 cap.setCapability("name", "DFS IE 8")
                 cap.setCapability(CapabilityType.VERSION, "8")
                 cap.setCapability(CapabilityType.PLATFORM, Platform.ANY)
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap
               },
    "IE_9" -> { val cap = DesiredCapabilities.internetExplorer()
                 cap.setCapability("name", "DFS IE 9")
                 cap.setCapability(CapabilityType.VERSION, "9")
                 cap.setCapability(CapabilityType.PLATFORM, Platform.ANY)
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
    "IE_11" -> { val cap = DesiredCapabilities.internetExplorer()
                 cap.setCapability("name", "DFS IE 11")
                 cap.setCapability(CapabilityType.VERSION, "11")
                 cap.setCapability(CapabilityType.PLATFORM, Platform.ANY)
                 cap.setCapability("max-duration", MAX_DURATION)
                 cap
               },

    // CHROME
    "CHROME_26" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 26")
                     cap.setCapability(CapabilityType.VERSION, "26")
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_27" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 27")
                     cap.setCapability(CapabilityType.VERSION, "27")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_28" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 28")
                     cap.setCapability(CapabilityType.VERSION, "28")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_29" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 29")
                     cap.setCapability(CapabilityType.VERSION, "29")
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_30" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 30")
                     cap.setCapability(CapabilityType.VERSION, "30")
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_31" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 31")
                     cap.setCapability(CapabilityType.VERSION, "31")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_32" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 32")
                     cap.setCapability(CapabilityType.VERSION, "32")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_33" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 33")
                     cap.setCapability(CapabilityType.VERSION, "33")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_34" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 34")
                     cap.setCapability(CapabilityType.VERSION, "34")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_35" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 35")
                     cap.setCapability(CapabilityType.VERSION, "35")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_36" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 36")
                     cap.setCapability(CapabilityType.VERSION, "36")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_37" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 37")
                     cap.setCapability(CapabilityType.VERSION, "37")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_38" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 38")
                     cap.setCapability(CapabilityType.VERSION, "38")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },
    "CHROME_39" -> { val cap = DesiredCapabilities.chrome()
                     cap.setCapability("name", "DFS CHROME 39")
                     cap.setCapability(CapabilityType.VERSION, "39")
                     cap.setCapability(CapabilityType.PLATFORM, Platform.MAC)
                     cap.setCapability("max-duration", MAX_DURATION)
                     cap
                   },

    // SAFARI VERSIONS
    "SAFARI_5" -> { val cap = DesiredCapabilities.safari()
                    cap.setCapability("name", "DFS SAFARI 5")
                    cap.setCapability(CapabilityType.VERSION, "5")
                    cap.setCapability("max-duration", MAX_DURATION)
                    cap
                  },
    "SAFARI_6" -> { val cap = DesiredCapabilities.safari()
                    cap.setCapability("name", "DFS SAFARI 6")
                    cap.setCapability(CapabilityType.VERSION, "6")
                    cap.setCapability("max-duration", MAX_DURATION)
                    cap
                  },
    "SAFARI_7" -> { val cap = DesiredCapabilities.safari()
                    cap.setCapability("name", "DFS SAFARI 7")
                    cap.setCapability(CapabilityType.VERSION, "7")
                    cap.setCapability("max-duration", MAX_DURATION)
                    cap
                  },
    "SAFARI_8" -> { val cap = DesiredCapabilities.safari()
                    cap.setCapability("name", "DFS SAFARI 8")
                    cap.setCapability(CapabilityType.VERSION, "8")
                    cap.setCapability("max-duration", MAX_DURATION)
                    cap
                  }
  )

  val CHROME_HOST   = "chrome"
  val FIREFOX_HOST  = "firefox"
  val SAFARI_HOST   = "safari"
  val PHANTOMJS_HOST  = "phantom"

  val host:String       = if (SharedPage.isLocalHost) scala.util.Properties.envOrElse("BROWSER", FIREFOX_HOST).toLowerCase else SAUCE_LABS_HOST
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
    var webDriver:WebDriver = null
    host match {
      case SAUCE_LABS_HOST =>
        println("RemoteDriver SauceLabs -----------")
        webDriver = createSauceLabsDriver

      case CHROME_HOST =>
        println("ChromeDriver -----------")
        webDriver = new ChromeDriver

      case FIREFOX_HOST =>
        println("FirefoxDriver -----------")
        webDriver = new FirefoxDriver

      case PHANTOMJS_HOST =>
        println("PhantomJS Driver ----------")
        webDriver = new PhantomJSDriver

      case SAFARI_HOST =>
        println("Safari Driver ----------")
        webDriver = new SafariDriver
    }
    //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    webDriver
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
