package unusual.pages

import unusual.model.Resolution

class MyContestsPage(res:Resolution) extends SharedPage {
  val url = SharedPage.baseUrl + "/#/my_contests"
  val resolution: Resolution = res

  val TITLE   = "Daily Soccer"

  val FORM_EMAIL    = "Email"
  val FORM_PASSWORD = "Password"
  val FORM_SUBMIT = "login"

  val TEST_LOGIN_FORM_MAP = Map(
    "email" -> "test@test.com",
    "password" -> "private"
  )

  def open = {
    go to url
    this
  }

  override def isAt = {
    var _isAt = true
    _isAt = _isAt && currentUrl == url
    _isAt = _isAt && pageTitle == TITLE

    _isAt = _isAt && new MenuBar(resolution).isAt
    _isAt = _isAt && new FooterBar(resolution).isAt

    _isAt
  }

}
