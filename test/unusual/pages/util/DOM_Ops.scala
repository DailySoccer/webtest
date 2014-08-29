package unusual.pages.util

import org.openqa.selenium.WebDriver
import org.scalatest.selenium.WebBrowser

trait DOM_Ops {

  def fastCountByCssSelector(cssSel:String)(implicit driver:WebDriver):Int = {
      //implicit val webDriver:WebDriver = driver
    WebBrowser.executeScript("return $('" + cssSel + "').length").asInstanceOf[Long].toInt
  }

  def fastClicksByCssSelector(numOfClicks:Int, cssSel:String)(implicit driver:WebDriver) = {
    //implicit val webDriver:WebDriver = driver
    var script = "$('" + cssSel + "')"
    for( a <- 1 to numOfClicks){
      script = script + ".click()"
    }
    WebBrowser.executeScript(script)
  }
}


