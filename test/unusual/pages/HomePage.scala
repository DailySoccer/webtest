package unusual.pages

import unusual.model.Resolution
import unusual.pages.components.{FooterBar, MenuBar}

class HomePage(res:Resolution) extends SharedPage(res) {

  val LOGIN_BUTTON  = "#loginButton"
  val SIGNUP_BUTTON = "#joinButton"

  val PLAY_BUTTON_MOBILE = "#playButtonMobile"
  val PLAY_BUTTON_1 = "#playButton1"
  val PLAY_BUTTON_2 = "#playButton2"
  val PLAY_BUTTON_3 = "#playButton3"
  val PLAY_BUTTON_4 = "#playButton4"

  val SCREEN_1 = "#screen1"
  val SCREEN_SEPARATOR_1 = SCREEN_1 + " + a"

  val SCREEN_2 = "#screen2"
  val SCREEN_2_URL = SharedPage.baseUrl + "/" + SCREEN_2
  val SCREEN_SEPARATOR_2 = SCREEN_2 + " + a"

  val SCREEN_3 = "#screen3"
  val SCREEN_3_URL = SharedPage.baseUrl + "/" + SCREEN_3
  val SCREEN_SEPARATOR_3 = SCREEN_3 + " + a"

  val SCREEN_4 = "#screen4"
  val SCREEN_4_URL = SharedPage.baseUrl + "/" + SCREEN_4


  override def isAt = {
    var _isAt = true
    /*
    _isAt = _isAt && pageTitle == TITLE
    logger.debug("page title is " + pageTitle + "should be " + TITLE, _isAt)
    val menu = new MenuBar(resolution)
    _isAt = _isAt && menu.isAt
    logger.debug("menu is at", _isAt)
    _isAt = _isAt && menu.isLoginBar
    logger.debug("menu is login bar", _isAt)
    _isAt = _isAt && new FooterBar(resolution).isAt
    logger.debug("footer is at", _isAt)
    */
    _isAt
  }

  def clickOnLogin = {
    val button = find(cssSelector(LOGIN_BUTTON))
    if (button != None) {
      click on button.get
    }
  }

  def clickOnSignUp = {
    val button = find(cssSelector(SIGNUP_BUTTON))
    if (button != None) {
      click on button.get
    }
  }

  def clickOnPlayButton0 = {
    val strCss = if (resolution == Resolution.SMALL) PLAY_BUTTON_MOBILE else PLAY_BUTTON_1
    val button = find(cssSelector(strCss))

    if (button != None) {
      click on button.get
    }

    this
  }

  def clickOnPlayButton1 = {
    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("clickOnPlayButton1")
    } else {
      val button = find(cssSelector(PLAY_BUTTON_2))
      if (button != None) {
        click on button.get
      }
    }
    this
  }

  def clickOnPlayButton2 = {
    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("clickOnPlayButton2")
    } else {
      val button = find(cssSelector(PLAY_BUTTON_3))
      if (button != None) {
        click on button.get
      }
    }
    this
  }

  def clickOnPlayButton3 = {
    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("clickOnPlayButton3")
    } else {
      val button = find(cssSelector(PLAY_BUTTON_4))
      if (button != None) {
        click on button.get
      }
    }
    this
  }

  /**************** Internal Navigation ****************/

  def clickOnScreenSeparator1 = {
    if (resolution != Resolution.SMALL) {
      val button = find(cssSelector(SCREEN_SEPARATOR_1))
      logger.debug("{" + SCREEN_SEPARATOR_1 + "} link exists", button != None)
      if (button != None) {
        click on button.get
      }
    } else {
      unavailableFunctionOnResolution("clickOnScreenSeparator1")
    }
    this
  }

  def clickOnScreenSeparator2 = {
    if (resolution != Resolution.SMALL) {
      val button = find(cssSelector(SCREEN_SEPARATOR_2))
      if (button != None) {
        click on button.get
      }
    } else {
      unavailableFunctionOnResolution("clickOnScreenSeparator2")
    }
    this
  }

  def clickOnScreenSeparator3 = {
    if (resolution != Resolution.SMALL) {
      val button = find(cssSelector(SCREEN_SEPARATOR_3))
      if (button != None) {
        click on button.get
      }
    } else {
      unavailableFunctionOnResolution("clickOnScreenSeparator3")
    }
    this
  }
}
