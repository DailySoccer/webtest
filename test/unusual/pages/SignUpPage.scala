package unusual.pages

import unusual.model.Resolution
import unusual.model.User

class SignUpPage(res:Resolution) extends SharedPage(res) {
  override val url = SharedPage.baseUrl + "/#/join"

  val FORM_NAME             = "nickName"
  val FORM_EMAIL            = "email"
  val FORM_PASSWORD         = "password"
  val FORM_REPEAT_PASSWORD  = "rePassword"
  val FORM_SUBMIT           = "joinNow"


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
    fillAndSubmitForm(usr)
    this
  }

  private def fillAndSubmitForm(user:User) = {
    textField(FORM_NAME).value = user.firstName
    emailField(FORM_EMAIL).value = user.email
    pwdField(FORM_PASSWORD).value = user.password
    pwdField(FORM_REPEAT_PASSWORD).value = user.password
    submit
    this
  }

}