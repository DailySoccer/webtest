package unusual.model

import org.scalatest.concurrent.Eventually
import unusual.pages._
import unusual.pages.components.MenuBar


class TestStatus {

  private var defaultResolution = Resolution.BIG
  private var _loggedIn : Boolean = false

  val RESOLUTIONS = Resolution.ALL
  var resolution = defaultResolution
  var user:User = User.DEFAULT

  def setDefault : TestStatus = {
    ensureVisitor
    resolution = defaultResolution
    this
  }

  def setLoggedIn(log: Boolean) = {
    _loggedIn = log
    this
  }

  def ensureAuthUser : TestStatus = {
    if (!_loggedIn) { doLogin }
    this
  }

  def setBaseResolution(res: Resolution) = {
    defaultResolution = res
    resolution = res
  }

  def ensureVisitor : TestStatus = {
    if (_loggedIn) { doLogout }
    this
  }

  def doLogin : TestStatus = {
    val login = new LoginPage(resolution)

    login.open
    assert(login.isAt)

    login.ensureDoLogin(user)

    _loggedIn = true
    this
  }

  def doLogout : TestStatus = {
    val menu = new MenuBar(resolution)
    assert(menu.isAt)
    menu.clickOnLogout
    _loggedIn = false
    this
  }

}
