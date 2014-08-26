package unusual.pages.util

import org.openqa.selenium.WebDriver
import org.scalatest.selenium.WebBrowser

trait DOM_Ops {

  def fastCountByCssSelector(cssSel:String)(implicit driver:WebDriver) : Long = {
      //implicit val webDriver:WebDriver = driver
    WebBrowser.executeScript("return $('" + cssSel + "').length").asInstanceOf[Long]
  }

}


