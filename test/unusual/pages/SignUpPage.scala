package unusual.pages

import unusual.model.Resolution
import unusual.model.User

class SignUpPage(res:Resolution) extends SharedPage {
  val resolution: Resolution = res
  val url = SharedPage.baseUrl + "/#/join"

  val TITLE   = "Daily Soccer"

  val FORM_NAME     = "#nickName"
  val FORM_EMAIL    = "#email"
  val FORM_PASSWORD = "#password"
  val FORM_SUBMIT   = "#joinNow"

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

  override def isAt:Boolean = {

    /*
    var _isAt = true
    _isAt = _isAt && pageTitle == TITLE
    logger.debug("Title should be " + TITLE, _isAt)

    _isAt = _isAt && isElemDisplayed(FORM_NAME)
    _isAt = _isAt && isElemDisplayed(FORM_EMAIL)
    _isAt = _isAt && isElemDisplayed(FORM_PASSWORD)
    _isAt = _isAt && isElemDisplayed(FORM_SUBMIT)

    _isAt = _isAt && new FooterBar(resolution).isAt
    _isAt
    */
    placeholder
    true
  }

  def doSignUp(usr:User) = {
    fillAndSubmitForm(TEST_SINGUP_FORM_MAP)
    this
  }

  def fillAndSubmitForm(params: Map[String,String]) = {
    textField(FORM_NAME).value = params("firstName")
    emailField(FORM_EMAIL).value = params("email")
    pwdField(FORM_PASSWORD).value = params("password")
    submit
    this
  }

}