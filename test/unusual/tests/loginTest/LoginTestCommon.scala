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

  def doFailLogin: Unit = {
    status.ensureVisitor
    val loginPage = goToLoginPage.doLogin(User.NOT_SINGED_UP)
    val lobby = new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney )
    intercept[Exception] {
      eventually (timeout(5 seconds)) {
        assert(lobby.isAt && lobby.isLoggedIn)
      }
    }

    loginPage.doLogin(User.SIGNED_UP_WRONG_PASS)
    intercept[Exception] {
      eventually (timeout(5 seconds)) {
        assert(lobby.isAt && lobby.isLoggedIn)
      }
    }

  }

  def doLogin: Unit = {
    status.ensureVisitor
    goToLoginPage.doLogin(User.OTHER)

    logger.debug("logged in")
    val lobby = new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney )
    eventually { assert(lobby.isAt) }
    logger.debug("lobby is at")
    eventually { assert(lobby.isLoggedIn) }
    logger.debug("lobby is logged in")

    val menu = new MenuBar(status.resolution)
    menu.getUserName must be(User.OTHER.nickname)
    logger.debug("user name confirmed")

    menu.clickOnLogout
  }

  def doFailSignUp: Unit = {
    status.ensureVisitor
    val MAX_TIMEOUT = 3
    val lobby = new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney )

    val signUpPage = goToSignUpPage.doSignUp(User.WRONG_MAIL_1)
    withClue(s"trying to do a sign up with ${User.WRONG_MAIL_1.email} was successful") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }

    signUpPage.doSignUp(User.WRONG_MAIL_2)
    withClue(s"trying to do a sign up with ${User.WRONG_MAIL_2.email} was successful") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }

    signUpPage.doSignUp(User.WRONG_MAIL_3)
    withClue(s"trying to do a sign up with ${User.WRONG_MAIL_3.email} was successful") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }

    signUpPage.doSignUp(User.WRONG_PASS_1)
    withClue(s"trying to do a sign up with ${User.WRONG_PASS_1.email} was successful") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }

    signUpPage.doSignUp(User.WRONG_PASS_2)
    withClue(s"trying to do a sign up with ${User.WRONG_PASS_2.email} was successful") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }
/*
    signUpPage.doSignUp(User.DEFAULT)
    withClue(s"trying to do a sign up with existing user: ${User.DEFAULT.firstName}") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }

    signUpPage.doSignUp(User.OTHER)
    withClue(s"trying to do a sign up with existing user: ${User.OTHER.firstName}") {
      intercept[Exception] {
        eventually(timeout(MAX_TIMEOUT seconds)) {
          assert(lobby.isAt && lobby.isLoggedIn)
        }
      }
    }
*/

  }
/*
  def doRightSignUp:Unit = {
    status.ensureVisitor
    goToSignUpPage.doSignUp(User.NEW)
  }
*/

}
