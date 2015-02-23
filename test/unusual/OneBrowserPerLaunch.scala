package unusual

import org.openqa.selenium._
import org.openqa.selenium.chrome._
import org.openqa.selenium.safari._
import org.scalatest._
import org.scalatest.concurrent._
import org.scalatest.selenium._
import org.scalatestplus.play.BrowserFactory._
import org.scalatestplus.play._

trait OneBrowserPerLaunch extends SuiteMixin with WebBrowser with Eventually with IntegrationPatience with BrowserFactory { this: Suite with ServerProvider =>

  /**
   * An implicit instance of `WebDriver`, created by calling `createWebDriver`.
   * If there is an error when creating the `WebDriver`, `UnavailableDriver` will be assigned
   * instead.
   */
  implicit lazy val webDriver: WebDriver = {
    if (OneBrowserPerLaunch.commonWebDriver == null)
      OneBrowserPerLaunch.commonWebDriver = createWebDriver()

    OneBrowserPerLaunch.commonWebDriver
  }

  /**
   * Automatically cancels tests with an appropriate error message when the `webDriver` field is a `UnavailableDriver`,
   * else calls `super.withFixture(test)`
   */
  abstract override def withFixture(test: NoArgTest): Outcome = {
    webDriver match {
      case UnavailableDriver(ex, errorMessage) =>
        ex match {
          case Some(e) => cancel(errorMessage, e)
          case None => cancel(errorMessage)
        }
      case _ => super.withFixture(test)
    }
  }

  /**
   * Places the `WebDriver` provided by `webDriver` into the `ConfigMap` under the key
   * `org.scalatestplus.play.webDriver` to make
   * it available to nested suites; calls `super.run`; and lastly ensures the `WebDriver` is stopped after
   * all tests and nested suites have completed.
   *
   * @param testName an optional name of one test to run. If `None`, all relevant tests should be run.
   *                 I.e., `None` acts like a wildcard that means run all relevant tests in this `Suite`.
   * @param args the `Args` for this run
   * @return a `Status` object that indicates when all tests and nested suites started by this method have completed, and whether or not a failure occurred.
   */
  abstract override def run(testName: Option[String], args: Args): Status = {

    try {
      val newConfigMap = args.configMap + ("org.scalatestplus.play.webDriver" -> webDriver)
      val newArgs = args.copy(configMap = newConfigMap)
      val status = super.run(testName, newArgs)
      status.whenCompleted(OneBrowserPerLaunch.cleanup)
      status
    } catch {
      case ex: Throwable =>
        OneBrowserPerLaunch.cleanup(false)
        throw ex
    }
  }
}

object OneBrowserPerLaunch {

  var commonWebDriver: WebDriver = null

  var closeDriver: Boolean = false

  val cleanup: Boolean => Unit = { _ =>
    if(OneBrowserPerLaunch.closeDriver) {
      commonWebDriver match {
        case _: UnavailableDriver => // do nothing for UnavailableDriver
        case safariDriver: SafariDriver => safariDriver.quit()
        case chromeDriver: ChromeDriver => chromeDriver.quit()
        case _ => commonWebDriver.close()
      }
      OneBrowserPerLaunch.commonWebDriver = null
      OneBrowserPerLaunch.closeDriver = false
    }
  }

/*
  def getWebDriver: WebDriver = {
    if (commonWebDriver == null)
      commonWebDriver = createWebDriver()

    commonWebDriver
  }
*/

}