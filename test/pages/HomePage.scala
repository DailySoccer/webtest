package pages

class HomePage extends SharedPage {
  val TITLE   = "Daily Soccer"
  val LOGIN_ID  = "loginButton"
  val SIGNUP_ID = "joinButton"

  val url = SharedPage.baseUrl

  def open = {
    go to url
    this
  }

  def isAt = {
    pageTitle should be (TITLE)

    eventually { find(id(LOGIN_ID)) should be ('defined) }
    //eventually { find(linkText(SIGNUP_TEXT)) should be ('defined) }

    this
  }

  def clickOnLogin = {
    click on id(LOGIN_ID)
    new LoginPage().isAt
  }

  def clickOnSignUp = {
    click on id(SIGNUP_ID)
    new SignUpPage().isAt
  }
}
