package unusual.pages

import org.scalatest.{GivenWhenThen, OptionValues, MustMatchers, Matchers}
import org.scalatest.selenium.WebBrowser
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.time.{SpanSugar}
import org.openqa.selenium.WebDriver

trait SharedPage extends WebBrowser.Page
  with WebBrowser with Matchers with OptionValues with Eventually with IntegrationPatience with SpanSugar {
  implicit val driver : WebDriver = SharedPage.driver
  def isAt = { this }
  def placeholder:Unit = { println("[F]\u001B[33m Placeholder function \u001B[0m") }
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
