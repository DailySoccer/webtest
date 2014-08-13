package pages

class SignUpPage extends SharedPage {
  val TITLE   = "Daily Soccer"
  val LEGEND  = "Start Playing"
  val FORM_FIRST_NAME_ID = "firstName"
  val FORM_LAST_NAME_ID  = "lastName"
  val FORM_EMAIL_ID      = "email"
  val FORM_NICKNAME_ID   = "nickName"
  val FORM_PASSWORD_ID   = "password"

  val url = SharedPage.baseUrl + "/#/join"

  val TEST_SINGUP_FORM_MAP = Map(
    "firstName" -> "First",
    "lastName" -> "Last",
    "email" -> "test@test.com",
    "nick" -> "nick",
    "password" -> "private"
  )

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    eventually {
      find(tagName("legend")).get.text should be (LEGEND)
      find(id(FORM_FIRST_NAME_ID)) should be ('defined)
      find(id(FORM_LAST_NAME_ID)) should be ('defined)
      find(id(FORM_EMAIL_ID)) should be ('defined)
      find(id(FORM_NICKNAME_ID)) should be ('defined)
      find(id(FORM_PASSWORD_ID)) should be ('defined)
    }
    find(cssSelector("form input[id='" + FORM_FIRST_NAME_ID + "']")) should be ('defined)
    find(cssSelector("form input[id='" + FORM_FIRST_NAME_ID + "']")) should be ('defined)
    find(cssSelector("form input[id='" + FORM_FIRST_NAME_ID + "']")) should be ('defined)
    find(cssSelector("form input[id='" + FORM_FIRST_NAME_ID + "']")) should be ('defined)
    find(cssSelector("form input[id='" + FORM_FIRST_NAME_ID + "']")) should be ('defined)

    new FooterBar().isAt
    this
  }

  def doSingup = {
    fillAndSubmitForm(TEST_SINGUP_FORM_MAP)
    this
  }

  def fillAndSubmitForm(params: Map[String,String]) = {
    textField(FORM_FIRST_NAME_ID).value = params("firstName")
    textField(FORM_LAST_NAME_ID).value = params("lastName")
    emailField(FORM_EMAIL_ID).value = params("email")
    textField(FORM_NICKNAME_ID).value = params("nick")
    pwdField(FORM_PASSWORD_ID).value = params("password")
    submit
    this
  }

  def fillAndSubmitForm(paramsOrdered: String*) {
    findAll(tagName("input"))
  }
}