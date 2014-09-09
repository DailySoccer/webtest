package unusual.pages

import unusual.model.Resolution
import unusual.pages.components.{FooterBar, MenuBar}

class MyContestsPage(res:Resolution) extends SharedPage(res) {
  override val url = SharedPage.baseUrl + "/#/my_contests"

  val FORM_EMAIL    = "Email"
  val FORM_PASSWORD = "Password"
  val FORM_SUBMIT = "login"

  val TEST_LOGIN_FORM_MAP = Map(
    "email" -> "test@test.com",
    "password" -> "private"
  )

  override def isAt = {
    var _isAt = true
    _isAt = _isAt && currentUrl == url
    _isAt = _isAt && pageTitle == TITLE

    _isAt = _isAt && new MenuBar(resolution).isAt
    _isAt = _isAt && new FooterBar(resolution).isAt

    _isAt
  }

}
