package unusual.tests.loginTest

import unusual.model.pageStates.LobbyState
import unusual.model.{User, Resolution}
import unusual.pages.LobbyPage
import unusual.pages.components.MenuBar
import unusual.tests._

class LoginTestCommon(res:Resolution) extends SharedTest(res) {

  //val NEW_USER : User = new User("First", "Last", "new@test.com", "The newbie", "private")
  //val DEFAULT_USER : User = new User("Test", "Test", "test@test.com", "Test", "private")
  //val WRONG_USER : User = new User("Test", "Test", "worng@test.com", "asd", "private")
  /*
  val TEST_SINGUP_FORM_MAP = Map(
    "firstName" -> "First",
    "lastName" -> "Last",
    "email" -> "test@test.com",
    "nick" -> "nick",
    "password" -> "private"
  )

  val TEST_LOGIN_FORM_MAP = Map(
    "email" -> "test@test.com",
    "password" -> "private"
  )
  */
  def doRightSignUp:Unit = {
    goToSignUpPage.doSignUp(User.NEW)
  }

  def doLogin: Unit = {
    goToLoginPage.doLogin(User.OTHER)
    val lobby = new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney )
    assert(lobby.isAt && lobby.isLoggedIn)
    val menu = new MenuBar(status.resolution)
    assert(menu.isAt)
    menu.getUserName must be(User.OTHER.firstName)
  }

 }
