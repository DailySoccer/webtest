package unusual.tests.loginTest

import unusual.model.{User, Resolution}
import unusual.pages.{MenuBar, LobbyPage}
import unusual.tests._

class LoginTestCommon extends SharedTest {

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
  def doRightSignUp(resolution:Resolution):Unit = {
    goToSignUpPage.doSignUp(User.NEW)
  }
  /*
  def doWrongSignUp(resolution:Resolution):Unit = {
    goToSignUpPage.doSignUp(User.DEFAULT)
  }
  */

  def doLogin(resolution:Resolution): Unit = {
    goToLoginPage.doLogin(User.OTHER)
    new LobbyPage(resolution).isAt.isLoggedIn
    new MenuBar(resolution).isAt.isUser(User.OTHER)
  }

 }
