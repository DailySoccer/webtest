package pages

import org.scalatest.{GivenWhenThen, OptionValues, MustMatchers, Matchers}
import org.scalatest.selenium.WebBrowser
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.openqa.selenium.WebDriver

trait SharedPage extends WebBrowser.Page
  with WebBrowser with Matchers with OptionValues with Eventually with IntegrationPatience {
  implicit val driver : WebDriver = SharedPage.driver
  def isAt  = { this }
}

object SharedPage {
  var driver  : WebDriver = null
  var baseUrl : String = {
    val url: String = scala.util.Properties.envOrElse("URL", "http://dailysoccer-staging.herokuapp.com")
    play.Logger.info("URL: {}", url)
    url
  }
  var isLocalHost = scala.util.Properties.envOrNone("URL").isDefined
}
