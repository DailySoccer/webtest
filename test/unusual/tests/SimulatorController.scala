package unusual.tests

import org.scalatest.selenium.WebBrowser.go
import unusual.pages.SharedPage


class SimulatorController {


  val url = SharedPage.baseUrl + "/admin"

  val RESET_DB = url + "/reset_db"
  val GO_TO_DATE = url + "/goto_date"
  val INITIAL_SETUP = url + "/reset_db"



  def initialTestsSetup(): Unit = {
    go to url + "/reset_db"
    go to url + "/reset_db"
    go to url + "/initial_setup"
  }

  def timeShift( date:_): Unit ={
    go to ""
  }


}
