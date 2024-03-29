package unusual.pages

import unusual.model.{User, Resolution}
import unusual.pages.components.FooterBar

class LoginPage(res:Resolution) extends SharedPage(res) {

  override val url = SharedPage.baseUrl + "/#/login"

  //val LEGEND        = "Login"
  val LOGIN_FORM    = "#loginForm"
  val FORM_EMAIL    = "login-mail"
  val FORM_PASSWORD = "login-password"
  val FORM_SUBMIT   = "btnSubmit"
  val FORM_CANCEL   = "btnCancelLogin"
  val GO_REGISTER   = LOGIN_FORM + " #gotoRegisterLink"
  val GO_RECOVER_PASS = LOGIN_FORM + " #rememberPasswordLink"
  val CLOSE_BUTTON  = LOGIN_FORM + " button.close"

  //val DEFAULT_USER : User = new User("Test", "Test", "test@test.com", "Test", "private")

  override def isAt = {
    eventually {
      pageTitle should be (TITLE)

      find(id(FORM_EMAIL))    should be('defined)
      find(id(FORM_PASSWORD)) should be('defined)
      find(id(FORM_SUBMIT))   should be('defined)
      find(id(FORM_CANCEL))   should be('defined)
    }
    new FooterBar(resolution).isAt

    true
  }

  def clickRegister = {
    click on find(cssSelector(GO_REGISTER)).get
    this
  }

  def clickRecoverPass = {
    click on find(cssSelector(GO_RECOVER_PASS)).get
    this
  }

  def clickClose = {
    click on find(cssSelector(CLOSE_BUTTON)).get
    this
  }


  /**
   * Try to do a login
   * @param usr may not exists
   * @return
   */
  def doLogin(usr: User) = {
    logger.debug("Filling form")
    fillAndSubmitForm(usr)
    logger.debug("Form filled")
    this
  }

  /**
   * Try to do a login ensuring to be logged in
   * @param usr that should exists
   * @return
   */
  def ensureDoLogin(usr: User) = {
    logger.debug("Ensuring to be loggued")
    val lobby = new LobbyPage(resolution, 6)
    eventually (timeout(20 seconds)) {
      logger.debug("Filling form")
      fillAndSubmitForm(usr)
      logger.debug("Form filled")
      eventually (timeout(7 seconds)) {
        val is = lobby.isAt
        logger.debug("Is already at lobby", is)
        assert(is)
      }
      logger.debug("Is at lobby right now")
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
