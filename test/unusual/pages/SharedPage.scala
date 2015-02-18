package unusual.pages

import unusual.UnusualLogger
import unusual.model.Resolution
import unusual.pages.components.{FooterBar, MenuBar}
import unusual.pages.util.JS_Ops
import org.scalatest.{GivenWhenThen, OptionValues, MustMatchers, Matchers}
import org.scalatest.selenium.WebBrowser
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.time.{SpanSugar}
import org.openqa.selenium.WebDriver
import play.api.Logger

class SharedPage(res:Resolution) extends WebBrowser.Page
    with WebBrowser with Matchers with OptionValues with Eventually
    with IntegrationPatience with SpanSugar with JS_Ops{
  implicit val driver : WebDriver = SharedPage.driver

  val TITLE = "Epic Eleven"
  val resolution: Resolution = res
  val url = SharedPage.baseUrl

  protected var logger:UnusualLogger = {
    val l = new UnusualLogger
    l.logger  = Logger(this.getClass)
    l
  }

  def isAt:Boolean = { true }

  def open = {

    if(currentUrl == url) {
      logger.debug("reload")
      reloadPage
    } else {
      logger.debug("go to")
      go to url
    }
    logger.debug("change menu positioning")
    changeMenuPositioning
    logger.debug("changed")
    this
  }

  protected def isWholePage: Boolean = {
    var isPage = new MenuBar(resolution).isAt
    isPage = isPage && new FooterBar(resolution).isAt

    isPage = isPage && currentUrl == url
    logger.debug("URL is " + currentUrl + ", should be " + url, isPage)
    isPage = isPage && pageTitle == TITLE
    logger.debug("Title is " + pageTitle + ", should be " + TITLE, isPage)

    isPage
  }

  protected def placeholder:Unit = {
    logger.info("\u001B[33m Placeholder function \u001B[0m")
  }

  protected def unavailableFunctionOnResolution(funcName: String) = {
    logger.info("{ " + funcName + "() } Unavailable functionality for this resolution")
    this
  }

  protected def isElemDisplayed(cssSel: String): Boolean = {
    val elem = find(cssSelector(cssSel))

    var _isDisplayed = elem != None
    logger.debug("{" + cssSel + "} exists", _isDisplayed)

    if ( _isDisplayed ) {
      _isDisplayed = _isDisplayed && elem.get.isDisplayed
      logger.debug("{" + cssSel + "} is displayed", _isDisplayed)
    }

    _isDisplayed
  }

  protected def existsElem(cssSel: String): Boolean = {
    val elem = find(cssSelector(cssSel))

    val exists = elem != None
    logger.debug("{" + cssSel + "} exists", exists)

    exists
  }


}

object SharedPage {

  protected var logger:UnusualLogger = {
    val l = new UnusualLogger
    l.logger  = Logger(this.getClass)
    l
  }

  val PAGE_HOST:Map[String, String] = Map(
    "STAGING"   -> "http://dailysoccer-staging.herokuapp.com",
    "LOCAL"     -> "http://localhost:9000"
  )

  var driver  : WebDriver = null
  var baseUrl : String = {
    val testHost:String   = scala.util.Properties.envOrElse("TEST_HOST", "LOCAL").toUpperCase
    val url: String = PAGE_HOST(scala.util.Properties.envOrElse("URL", if(testHost == "LOCAL") "LOCAL" else "STAGING").toUpperCase)
    logger.info(s"URL: {$url}")
    url
  }

  //var isLocalHost = scala.util.Properties.envOrNone("URL").isDefined
}
