package unusual.pages

import unusual.model.Resolution

class HomePage(res:Resolution) extends SharedPage {
  val url = SharedPage.baseUrl
  val resolution: Resolution = res


  val TITLE   = "Daily Soccer"
  val LOGIN_ID  = "loginButton"
  val SIGNUP_ID = "joinButton"

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    new MenuBar(resolution).isAt.isLoginBar
    new FooterBar(resolution).isAt

    this
  }

  def clickOnLogin = {
    click on id(LOGIN_ID)
    new LoginPage(resolution).isAt
  }

  def clickOnSignUp = {
    click on id(SIGNUP_ID)
    new SignUpPage(resolution).isAt
  }

  val PLAY_BUTTON_MOBILE = "playButtonMobile"
  val PLAY_BUTTON_1 = "playButton1"
  val PLAY_BUTTON_2 = "playButton2"
  val PLAY_BUTTON_3 = "playButton3"
  val PLAY_BUTTON_4 = "playButton4"

  def clickOnPlayButtonMobile = {
    click on id(PLAY_BUTTON_MOBILE)
    new LobbyPage(resolution).isAt
  }
  def clickOnPlayButton0 = {
    click on id(PLAY_BUTTON_1)
    new LobbyPage(resolution).isAt
  }
  def clickOnPlayButton1 = {
    click on id(PLAY_BUTTON_2)
    new LobbyPage(resolution).isAt
  }
  def clickOnPlayButton2 = {
    click on id(PLAY_BUTTON_3)
    new LobbyPage(resolution).isAt
  }
  def clickOnPlayButton3 = {
    click on id(PLAY_BUTTON_4)
    new LobbyPage(resolution).isAt
  }

  /**** Internal Navigation ****/
  val SCREEN_SEPARATOR_1_ID = "screenSeparator1"
  val SCREEN_2_ID = "#screen2"
  val SCREEN_2_URL = SharedPage.baseUrl + "/" + SCREEN_2_ID

  val SCREEN_SEPARATOR_2_ID = "screenSeparator2"
  val SCREEN_3_ID = "#screen3"
  val SCREEN_3_URL = SharedPage.baseUrl + "/" + SCREEN_3_ID

  val SCREEN_SEPARATOR_3_ID = "screenSeparator3"
  val SCREEN_4_ID = "#screen4"
  val SCREEN_4_URL = SharedPage.baseUrl + "/" + SCREEN_4_ID

  def clickOnScreenSeparator1 = {
    click on id(SCREEN_SEPARATOR_1_ID)
    currentUrl should be(SCREEN_2_URL)
    this
  }

  def clickOnScreenSeparator2 = {
    click on id(SCREEN_SEPARATOR_2_ID)
    currentUrl should be(SCREEN_3_URL)
    this
  }

  def clickOnScreenSeparator3 = {
    click on id(SCREEN_SEPARATOR_3_ID)
    currentUrl should be(SCREEN_4_URL)
    this
  }
}
