package pages

class MyContestsPage extends SharedPage {
  val TITLE   = "Daily Soccer"

  val FORM_EMAIL    = "Email"
  val FORM_PASSWORD = "Password"
  val FORM_SUBMIT = "login"

  val url = SharedPage.baseUrl + "/#/my_contests"

  val TEST_LOGIN_FORM_MAP = Map(
    "email" -> "test@test.com",
    "password" -> "private"
  )

  def open = {
    go to url
    this
  }

  def isAt = {
    currentUrl should be (url)
    pageTitle should be (TITLE)

    new MenuBar().isAt
    new FooterBar().isAt

    this
  }

}
