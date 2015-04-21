package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.{HomePage, LobbyPage, MyContestsPage, SharedPage}

class MenuBar(res:Resolution) extends SharedPage(res) {

  /********** CONSTANTS *************/

  val LOGIN_ID  = "#loginButton"
  val SIGN_UP_ID = "#joinButton"

  val MENU_ROOT = "#mainMenu"
  val BRAND_LINK = MENU_ROOT + " .navbar-brand"

  //val ADD_MONEY_BUTTON = "addMoney"

  val MENU_TOGGLE = ".navbar-header button.navbar-toggle"
  val MENU_OFFCANVAS = "#menuSlide"

  // USER MENU IDs
  //val USER_MENU = "userMenuCollapse"
  val USER_MENU_TOGGLE = "#menuUser"
  val USER_MENU_TOGGLE_DROPDOWN = ".username-dropdown-toggle .dropdown-menu"
  val MY_ACCOUNT = "#menuUserMyAccount"
  val USER_MENU_ADD_MONEY_XS = "#menuUserAddFunds-xs"
  val USER_MENU_ADD_MONEY_SM = "#menuUserAddFunds-sm"
  val TRANSACTION_HISTORIC = "#menuUserHistory"
  //val REFERENCES_CENTER = "userMenuReferencesCenter"
  //val CLASSIFICATION = "userMenuClassification"
  //val USER_PROMOS = "userMenuPromos"
  val LOGOUT = "#menuUserLogOut"


  // GAME MENU IDs
  val TOURNAMENTS = "#menuLobby"
  val MY_TOURNAMENTS = "#menuMyContests"
  val HOW_IT_WORKS = "#menuHowItWorks"
  val GAME_PROMOS = "#gameMenuPromos"


  /**************** GENERAL METHODS ****************/

  override def isAt = {
    //isElemDisplayed(MENU_ROOT) && isElemDisplayed(BRAND_LINK)
    true
  }

  override def open = {
    logger.error("Trying to open a control.")
    this
  }

  def isLoginBar: Boolean = {
    var is = true
    eventually {
      val login = find(cssSelector(LOGIN_ID))
      is = is && login.isDefined && login.get.isDisplayed
      val signUp = find(cssSelector(SIGN_UP_ID))
      is = is && signUp.isDefined && signUp.get.isDisplayed


      //is = is && !find(id(USER_MENU)).isDefined
      //is = is && !find(id(ADD_MONEY_BUTTON)).isDefined
    }
    is
  }

  def isLoggedBar: Boolean = {
    var is = true
    eventually {
      val login = find(cssSelector(LOGIN_ID))
      is = is && !login.isDefined
      logger.debug("Login is not defined", is)
      val signUp = find(cssSelector(SIGN_UP_ID))
      is = is && !signUp.isDefined
      logger.debug("sign up is not defined", is)


      is = is && hasUserMenu && hasGameMenu
      //is = is && find(id(ADD_MONEY_BUTTON)).isDefined
    }
    is
  }

  /**************** USER MENU METHODS ****************/

  def getUserName(): String = {
    val elem = find(cssSelector(USER_MENU_TOGGLE)).get

    if (resolution == Resolution.SMALL && !elem.isDisplayed) {
      clickOnToggleMenu
    }

    var name = ""
    eventually { name = elem.text }
    name
  }

  def clickOnLogout = {
    clickOnUserMenuOption(LOGOUT)
  }

  def clickOnMyAccount = {
    placeholder
    //clickOnUserMenuOption(MY_ACCOUNT, new LobbyPage)
  }
  def clickOnTransactionHistoric = {
    placeholder
    //clickOnUserMenuOption(TRANSACTION_HISTORIC, new LobbyPage)
  }
  def clickOnReferencesCenter = {
    placeholder
    //clickOnUserMenuOption(REFERENCES_CENTER, new LobbyPage)
  }
  def clickOnClassification = {
    placeholder
    //clickOnUserMenuOption(CLASSIFICATION, new LobbyPage)
  }
  def clickOnUserPromos = {
    placeholder
    //clickOnUserMenuOption(USER_PROMOS, new LobbyPage)
  }
  def clickOnAddFundsButton = {
    placeholder
    //clickOnUserMenuOption(ADD_MONEY_BUTTON, new LobbyPage)
  }
  def clickOnUserMenuAddFunds = {
    placeholder
    //clickOnUserMenuOption(USER_MENU_ADD_MONEY, new LobbyPage)
  }

  /**************** GAME MENU METHODS ****************/

  def clickOnContests = {
    clickOnGameMenuOption(TOURNAMENTS)
  }

  def clickOnMyContests = {
    clickOnGameMenuOption(MY_TOURNAMENTS)
  }

  def clickOnHowItWorks = {
    clickOnGameMenuOption(HOW_IT_WORKS)
  }

  def clickOnGamePromos = {
    placeholder
    //clickOnGameMenuOption(GAME_PROMOS, new LobbyPage)
  }


  /********************* PRIVATE METHODS ************************/
  /**************************************************************/


  private def clickOnUserMenuOption(cssSel: String) = {
    logger.debug("enter clickOnUserMenuOption")
    displayUserMenuOption(cssSel)

    logger.debug(s"click on ($cssSel) clickOnUserMenuOption")
    eventually { click on find(cssSelector(cssSel)).get }
    this
  }

  private def clickOnGameMenuOption(eleId: String) = {
    if ( !find(cssSelector(eleId)).get.isDisplayed ) {
      clickOnToggleMenu
    }

    eventually (timeout(3 seconds)){ click on cssSelector(eleId) }
    this
  }

  private def clickOnToggleMenu = {
    eventually { click on cssSelector(MENU_TOGGLE) }
    this
  }

  private def clickOnToggleUserMenuDropDown = {
    eventually { click on cssSelector(USER_MENU_TOGGLE) }
    this
  }

  private def displayUserMenuOption(cssSel: String) = {
    if (resolution == Resolution.SMALL) {
      if (!isElemDisplayed(MENU_OFFCANVAS)){
        logger.debug(s"offCanvasMenu is not displayed, click on it")
        clickOnToggleMenu
        logger.debug(s"clicked")
      }
      eventually { assert(isElemDisplayed(MENU_OFFCANVAS), "Off-canvas is still not displayed.") }
    }

    eventually {
      if (!isElemDisplayed(USER_MENU_TOGGLE_DROPDOWN)) {
        logger.debug(s"userMenuDropdown is not displayed, click on it")
        clickOnToggleUserMenuDropDown
      }
    }
    this
  }
/*
  private def hasHelpMenu:Boolean = {
    var is = true
    is = is && find(id(SUPPORT)).isDefined
    logger.debug(s"$SUPPORT is defined", is)
    is = is && find(id(HOW_TO)).isDefined
    logger.debug(s"$HOW_TO is defined", is)
    is = is && find(id(RULES_RATING)).isDefined
    logger.debug(s"$RULES_RATING is defined", is)
    is
  }
*/
  private def hasUserMenu:Boolean = {
    var is = true
    //is = is && find(id(USER_MENU)).isDefined
    is = is && find(cssSelector(MY_ACCOUNT)).isDefined
    logger.debug(s"$MY_ACCOUNT is defined", is)
    is = is && find(cssSelector(USER_MENU_ADD_MONEY_XS)).isDefined
    logger.debug(s"$USER_MENU_ADD_MONEY_XS is defined", is)
    is = is && find(cssSelector(USER_MENU_ADD_MONEY_SM)).isDefined
    logger.debug(s"$USER_MENU_ADD_MONEY_SM is defined", is)
    is = is && find(cssSelector(TRANSACTION_HISTORIC)).isDefined
    logger.debug(s"$TRANSACTION_HISTORIC is defined", is)
    /*is = is && find(id(REFERENCES_CENTER)).isDefined
    logger.debug(s"$REFERENCES_CENTER is defined", is)
    is = is && find(id(CLASSIFICATION)).isDefined
    logger.debug(s"$CLASSIFICATION is defined", is)
    is = is && find(id(USER_PROMOS)).isDefined
    logger.debug(s"$USER_PROMOS is defined", is)*/
    is = is && find(cssSelector(LOGOUT)).isDefined
    logger.debug(s"$LOGOUT is defined", is)
    is
  }

  private def hasGameMenu:Boolean = {
    var is = true
    is = is && find(cssSelector(TOURNAMENTS)).isDefined
    logger.debug(s"$TOURNAMENTS is defined", is)
    is = is && find(cssSelector(MY_TOURNAMENTS)).isDefined
    logger.debug(s"$MY_TOURNAMENTS is defined", is)
    is = is && find(cssSelector(HOW_IT_WORKS)).isDefined
    logger.debug(s"$HOW_IT_WORKS is defined", is)
    is
  }

}


