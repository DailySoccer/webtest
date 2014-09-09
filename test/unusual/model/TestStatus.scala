package unusual.model

import unusual.pages._
import unusual.pages.components.MenuBar


class TestStatus {

  val RESOLUTIONS = Resolution.ALL
  val DEFAULT_RESOLUTION = Resolution.BIG
  var resolution = DEFAULT_RESOLUTION
  private var _loggedIn : Boolean = false

  def setDefault : TestStatus = {
    ensureVisitor
    resolution = DEFAULT_RESOLUTION
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

  def ensureVisitor : TestStatus = {
    if (_loggedIn) { doLogout }
    this
  }

  private def doLogin : TestStatus = {
    val login = new LoginPage(resolution)
    login.open
    assert(login.isAt)
    login.doLogin(User.DEFAULT)
    _loggedIn = true
    this
  }
  private def doLogout : TestStatus = {
    val menu = new MenuBar(resolution)
    assert(menu.isAt)
    menu.clickOnLogout
    _loggedIn = false
    this
  }

}
