package unusual.model

import unusual.pages._


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

  def setLoggedIn(log: Boolean) {
    _loggedIn = log;
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
    new LoginPage(resolution).open.isAt.doLogin(User.DEFAULT)
    _loggedIn = true
    this
  }
  private def doLogout : TestStatus = {
    new MenuBar(resolution).isAt.clickOnLogout
    _loggedIn = false
    this
  }

}
