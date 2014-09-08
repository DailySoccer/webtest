package unusual.pages

import unusual.model.{User, Resolution}

class MenuBar(res:Resolution) extends SharedPage {

  val url = SharedPage.baseUrl
  val resolution:Resolution = res


  /********** CONSTANTS *************/

  val LOGIN_ID  = "loginButton"
  val SIGN_UP_ID = "joinButton"

  val MENU_ROOT = "#mainMenuRoot"
  val BRAND_LINK_SELECTOR = "#" + MENU_ROOT + " .navbar-brand-container a"

  val ADD_MONEY_BUTTON = "addMoney"

  // USER MENU IDs
  val USER_MENU = "userMenuCollapse"
  val USER_MENU_TOGGLE = "menuToggleUserMenu"
  val USER_MENU_TOGGLE_DROPDOWN = "userMenuToggleDropDown"
  val MY_ACCOUNT = "userMenuMyAccount"
  val USER_MENU_ADD_MONEY = "userMenuAddFunds"
  val TRANSACTION_HISTORIC = "userMenuTransactionHistoric"
  val REFERENCES_CENTER = "userMenuReferencesCenter"
  val CLASSIFICATION = "userMenuClassification"
  val USER_PROMOS = "userMenuPromos"
  val LOGOUT = "userMenuLogOut"


  // HELP MENU IDs
  val SUPPORT = "userMenuSupport"
  val HOW_TO = "userMenuHowTo"
  val RULES_RATING = "userMenuRulesAndRating"


  // GAME MENU IDs
  val GAME_MENU = "gameMenuCollapse"
  val GAME_MENU_TOGGLE = "menuToggleGameMenu"
  val TOURNAMENTS = "gameMenuTournaments"
  val MY_TOURNAMENTS = "gameMenuMyTournaments"
  val GAME_PROMOS = "gameMenuPromos"


  /**************** GENERAL METHODS ****************/

  override def isAt = {
    isElemDisplayed(MENU_ROOT) && isElemDisplayed(BRAND_LINK_SELECTOR)
  }

  def isLoginBar: Boolean = {
    var is = true
    eventually {
      val login = find(id(LOGIN_ID))
      is = is && login.isDefined && login.get.isDisplayed
      val signUp = find(id(SIGN_UP_ID))
      is = is && signUp.isDefined && signUp.get.isDisplayed


      is = is && !find(id(GAME_MENU)).isDefined
      is = is && !find(id(USER_MENU)).isDefined
      is = is && !find(id(ADD_MONEY_BUTTON)).isDefined
    }
    is
  }

  def isLoggedBar: Boolean = {
    var is = true
    eventually {
      val login = find(id(LOGIN_ID))
      is = is && !login.isDefined
      val signUp = find(id(SIGN_UP_ID))
      is = is && !signUp.isDefined


      is = is && hasUserMenu && hasHelpMenu && hasGameMenu
      is = is && find(id(ADD_MONEY_BUTTON)).isDefined
    }
    is
  }

  /**************** USER MENU METHODS ****************/

  def getUserName(): String = {
    val elem = find(id(USER_MENU_TOGGLE_DROPDOWN)).get

    if (resolution == Resolution.SMALL && !elem.isDisplayed) {
      clickOnToggleUserMenu
    }

    var name = ""
    eventually { name = elem.text }
    name
  }

  def clickOnLogout = {
    clickOnUserMenuOption(LOGOUT, new HomePage(resolution))
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
    clickOnGameMenuOption(TOURNAMENTS, new LobbyPage(resolution))
  }

  def clickOnMyContests = {
    clickOnGameMenuOption(MY_TOURNAMENTS, new MyContestsPage(resolution))
  }

  def clickOnGamePromos = {
    placeholder
    //clickOnGameMenuOption(GAME_PROMOS, new LobbyPage)
  }


  /********************* PRIVATE METHODS ************************/
  /**************************************************************/


  private def clickOnUserMenuOption(eleId: String, page: SharedPage) = {
    displayUserMenuForId(eleId)

    eventually {
      click on id(eleId)
      page.isAt
    }
    this
  }

  private def clickOnGameMenuOption(eleId: String, page: SharedPage) = {
    if ( !find(id(eleId)).get.isDisplayed ) clickOnToggleGameMenu

    eventually {
      click on id(eleId)
      page.isAt
    }
    this
  }

  private def clickOnToggleGameMenu = {
    eventually {
      click on id(GAME_MENU_TOGGLE)
    }
    this
  }

  private def clickOnToggleUserMenu = {
    eventually {
      click on id(USER_MENU_TOGGLE)
    }
    this
  }

  private def clickOnToggleUserMenuDropDown = {
    eventually {
      click on id(USER_MENU_TOGGLE_DROPDOWN)
    }
    this
  }

  private def displayUserMenuForId(e: String) = {
    eventually {
      if (!find(id(USER_MENU_TOGGLE_DROPDOWN)).get.isDisplayed) clickOnToggleUserMenu
      if (!find(id(e)).get.isDisplayed) clickOnToggleUserMenuDropDown
    }
    this
  }

  private def hasHelpMenu:Boolean = {
    var is = true
    is = is && find(id(SUPPORT)).isDefined
    is = is && find(id(HOW_TO)).isDefined
    is = is && find(id(RULES_RATING)).isDefined
    is
  }

  private def hasUserMenu:Boolean = {
    var is = true
    is = is && find(id(USER_MENU)).isDefined
    is = is && find(id(MY_ACCOUNT)).isDefined
    is = is && find(id(USER_MENU_ADD_MONEY)).isDefined
    is = is && find(id(TRANSACTION_HISTORIC)).isDefined
    is = is && find(id(REFERENCES_CENTER)).isDefined
    is = is && find(id(CLASSIFICATION)).isDefined
    is = is && find(id(USER_PROMOS)).isDefined
    is = is && find(id(LOGOUT)).isDefined
    is
  }

  private def hasGameMenu:Boolean = {
    var is = true
    is = is && find(id(GAME_MENU)).isDefined
    is = is && find(id(TOURNAMENTS)).isDefined
    is = is && find(id(MY_TOURNAMENTS)).isDefined
    is = is && find(id(GAME_PROMOS)).isDefined
    is
  }

}


