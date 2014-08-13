package pages

class LoginPage extends SharedPage {
  val TITLE   = "Daily Soccer"
  //val LEGEND  = "Login"
  val FORM_EMAIL    = "Email"
  val FORM_PASSWORD = "Password"
  val FORM_SUBMIT = "login"

  val url = SharedPage.baseUrl + "/#/login"

  val TEST_LOGIN_FORM_MAP = Map(
    "email" -> "test@test.com",
    "password" -> "private"
  )

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    eventually {
      //find(tagName("legend")).get.text should be (LEGEND)
      find(id(FORM_EMAIL)) should be ('defined)
      find(id(FORM_PASSWORD)) should be ('defined)
      find(id(FORM_SUBMIT)) should be ('defined)
    }
    new FooterBar().isAt

    this
  }

  def doLogin = {
    fillAndSubmitForm(TEST_LOGIN_FORM_MAP)
    this
  }

  def fillAndSubmitForm(params: Map[String,String]) = {
    emailField(FORM_EMAIL).value = params("email")
    pwdField(FORM_PASSWORD).value = params("password")
    submit
    this
  }

  def fillAndSubmitForm(paramsOrdered: String*) {
    /*
    textField("Email").value = "email"
    fill("input").`with`(paramsOrdered)
    click("#joinNow")
    */
  }
}
