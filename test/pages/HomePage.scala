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

  override def isAt = {
    pageTitle should be (TITLE)
    new MenuBar().isAt.isLoginBar
    new FooterBar().isAt

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

  val PLAY_BUTTON_MOBILE = "playButtonMobile"
  val PLAY_BUTTON_1 = "playButton1"
  val PLAY_BUTTON_2 = "playButton2"
  val PLAY_BUTTON_3 = "playButton3"
  val PLAY_BUTTON_4 = "playButton4"

  def clickOnPlayButtonMobile = {
    click on id(PLAY_BUTTON_MOBILE)
    new LobbyPage().isAt
  }
  def clickOnPlayButton0 = {
    click on id(PLAY_BUTTON_1)
    new LobbyPage().isAt
  }
  def clickOnPlayButton1 = {
    click on id(PLAY_BUTTON_2)
    new LobbyPage().isAt
  }
  def clickOnPlayButton2 = {
    click on id(PLAY_BUTTON_3)
    new LobbyPage().isAt
  }
  def clickOnPlayButton3 = {
    click on id(PLAY_BUTTON_4)
    new LobbyPage().isAt
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
