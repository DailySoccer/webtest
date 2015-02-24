package unusual.tests.runner

import unusual.OneServerPerLaunch
import unusual.model.Resolution
import unusual.pages.SharedPage
import unusual.pages.util.JS_Ops
import unusual.testTags.scala.WIP
import unusual.tests.{OneServerPerLaunch, OneBrowserPerLaunch, SharedTest}

class ServerLaunchingController(res:Resolution) extends SharedTest(res) with JS_Ops{

  def createNewTestServer: Unit = {
    closeTestServer
  }

  def closeTestServer: Unit = {
    OneBrowserPerLaunch.closeDriver = true
    OneBrowserPerLaunch.cleanup(false)

    OneServerPerLaunch.shouldClose = true
    OneServerPerLaunch.close

    status.setLoggedIn(false)
  }

}

class CreateNewTestServer(res:Resolution) extends ServerLaunchingController(res) {
  if(status.resolution.enabled) "(CREATE) Server Controller" should ("Create new test server" taggedAs WIP in createNewTestServer)
}

class CloseTestServer(res:Resolution) extends ServerLaunchingController(res) {
  if(status.resolution.enabled) "(CLOSE) Server Controller" should ("Close test server" taggedAs WIP in closeTestServer)
}
