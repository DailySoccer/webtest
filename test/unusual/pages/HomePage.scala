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
    var _isAt = true

    _isAt = _isAt && pageTitle == TITLE
    val menu = new MenuBar(resolution)
    _isAt = _isAt && menu.isAt
    _isAt = _isAt && menu.isLoginBar
    _isAt = _isAt && new FooterBar(resolution).isAt

    _isAt
  }

  def clickOnLogin = {
    click on id(LOGIN_ID)
  }

  def clickOnSignUp = {
    click on id(SIGNUP_ID)
  }

  val PLAY_BUTTON_MOBILE = "playButtonMobile"
  val PLAY_BUTTON_1 = "playButton1"
  val PLAY_BUTTON_2 = "playButton2"
  val PLAY_BUTTON_3 = "playButton3"
  val PLAY_BUTTON_4 = "playButton4"

  def clickOnPlayButton0 = {
    if (resolution == Resolution.SMALL) {
      click on id(PLAY_BUTTON_MOBILE)
    } else {
      click on id(PLAY_BUTTON_1)
    }
    this
  }
  def clickOnPlayButton1 = {
    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("clickOnPlayButton1")
    } else {
      click on id(PLAY_BUTTON_2)
    }
    this
  }
  def clickOnPlayButton2 = {
    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("clickOnPlayButton2")
    } else {
      click on id (PLAY_BUTTON_3)
    }
    this
  }
  def clickOnPlayButton3 = {
    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("clickOnPlayButton3")
    } else {
      click on id (PLAY_BUTTON_4)
    }
    this
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
    if (resolution != Resolution.SMALL) {
      click on id(SCREEN_SEPARATOR_1_ID)
      currentUrl should be(SCREEN_2_URL)
    } else {
      unavailableFunctionOnResolution("clickOnScreenSeparator1")
    }
    this
  }

  def clickOnScreenSeparator2 = {
    if (resolution != Resolution.SMALL) {
      click on id(SCREEN_SEPARATOR_2_ID)
      currentUrl should be(SCREEN_3_URL)
    } else {
      unavailableFunctionOnResolution("clickOnScreenSeparator2")
    }
    this
  }

  def clickOnScreenSeparator3 = {
    if (resolution != Resolution.SMALL) {
      click on id(SCREEN_SEPARATOR_3_ID)
      currentUrl should be(SCREEN_4_URL)
    } else {
      unavailableFunctionOnResolution("clickOnScreenSeparator3")
    }
    this
  }
}
