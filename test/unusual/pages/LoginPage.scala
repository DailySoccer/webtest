package unusual.pages

import unusual.model.{User, Resolution}
import unusual.pages.components.FooterBar

class LoginPage(res:Resolution) extends SharedPage(res) {

  override val url = SharedPage.baseUrl + "/#/login"

  //val LEGEND        = "Login"
  val FORM_EMAIL    = "login-mail"
  val FORM_PASSWORD = "login-password"
  val FORM_SUBMIT   = "btnSubmit"
  val FORM_CANCEL   = "btnCancelLogin"

  //val DEFAULT_USER : User = new User("Test", "Test", "test@test.com", "Test", "private")

  override def isAt = {
    eventually {
      pageTitle should be (TITLE)
      //find(tagName("legend")).get.text should be (LEGEND)
      find(id(FORM_EMAIL))    should be ('defined)
      find(id(FORM_PASSWORD)) should be ('defined)
      find(id(FORM_SUBMIT))   should be ('defined)
      find(id(FORM_CANCEL))   should be ('defined)
    }
    new FooterBar(resolution).isAt

    true
  }

  /**
   * Try to do a login
   * @param usr may not exists
   * @return
   */
  def doLogin(usr: User) = {
    fillAndSubmitForm(usr)
    this
  }

  /**
   * Try to do a login ensuring to be logged in
   * @param usr that should exists
   * @return
   */
  def ensureDoLogin(usr: User) = {
    val lobby = new LobbyPage(resolution, 6)
    eventually {
      fillAndSubmitForm(usr)
      eventually (timeout(3 seconds)) { assert(lobby.isAt) }
    }

    this
  }

  private def fillAndSubmitForm(u: User) = {
    emailField(FORM_EMAIL).value  = u.email
    pwdField(FORM_PASSWORD).value = u.password
    submit
    this
  }

  //Eventually example with timeout
/*
  def eventuallyTesting {
    var a = 0
    eventually (timeout(20 seconds), interval(2 seconds)) {
      a += 1
      println(a)
      find(id("asd")) should be ('defined)
    }
  }
*/

}
