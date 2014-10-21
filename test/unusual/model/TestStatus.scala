package unusual.model

import org.scalatest.concurrent.Eventually
import unusual.pages._
import unusual.pages.components.MenuBar


class TestStatus {

  val RESOLUTIONS = Resolution.ALL
  val DEFAULT_RESOLUTION = Resolution.BIG
  var resolution = DEFAULT_RESOLUTION
  var user:User = User.DEFAULT
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
