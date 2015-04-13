package unusual.pages

import unusual.model.Resolution
import unusual.pages.components.{FooterBar, MenuBar}

class HomePage(res:Resolution) extends SharedPage(res) {

  val LOGIN_BUTTON  = "#loginButton"

  val PLAY_BUTTON_MOBILE = "#playButtonMobile"
  val PLAY_BUTTON_1 = "#playButton1"


  override def isAt = {
    var _isAt = true

    _isAt = _isAt && isElemDisplayed(LOGIN_BUTTON)
    logger.debug("Login button ", _isAt)
    _isAt = _isAt && (isElemDisplayed(PLAY_BUTTON_MOBILE) || res != Resolution.SMALL) &&
                     (isElemDisplayed(PLAY_BUTTON_1)      || res == Resolution.SMALL)
    logger.debug("Play button ", _isAt)
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


  def clickOnPlayButton = {
    val strCss = if (resolution == Resolution.SMALL) PLAY_BUTTON_MOBILE else PLAY_BUTTON_1
    click on find(cssSelector(strCss)).get
    this
  }

/*
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
*/
  /**************** Internal Navigation ****************/
/*
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
  */
}
