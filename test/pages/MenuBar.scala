package pages

class MenuBar extends SharedPage {
  val url = SharedPage.baseUrl

  val LOGIN_ID  = "loginButton"
  val SIGN_UP_ID = "joinButton"

  val MENU_ID = "mainMenuRoot"
  val BRAND_LINK_SELECTOR = "#" + MENU_ID + " .navbar-brand-container a"

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


  private def hasUserMenu = {
    find(id(USER_MENU)) should be('defined)
    find(id(MY_ACCOUNT)) should be('defined)
    find(id(USER_MENU_ADD_MONEY)) should be('defined)
    find(id(TRANSACTION_HISTORIC)) should be('defined)
    find(id(REFERENCES_CENTER)) should be('defined)
    find(id(CLASSIFICATION)) should be('defined)
    find(id(USER_PROMOS)) should be('defined)
    find(id(LOGOUT)) should be('defined)
    this
  }

  override def isAt = {
    eventually {
      find(id(MENU_ID)) should be ('defined)
      find(cssSelector(BRAND_LINK_SELECTOR)) should be ('defined)
    }

    this
  }

  def isLoginBar = {
    eventually {
      find(id(LOGIN_ID)) should be ('defined)
      find(id(SIGN_UP_ID)) should be ('defined)
      find(id(GAME_MENU)) should not be ('defined)
      find(id(USER_MENU)) should not be ('defined)
      find(id(ADD_MONEY_BUTTON)) should not be ('defined)
    }
    this
  }

  def hasGameMenu = {
    find(id(GAME_MENU)) should be ('defined)
    find(id(TOURNAMENTS)) should be('defined)
    find(id(MY_TOURNAMENTS)) should be('defined)
    find(id(GAME_PROMOS)) should be('defined)
    this
  }

  def isLoggedBar = {
    eventually {
      find(id(LOGIN_ID)) should not be ('defined)
      find(id(SIGN_UP_ID)) should not be ('defined)
      hasUserMenu
      hasHelpMenu
      hasGameMenu
      find(id(ADD_MONEY_BUTTON)) should be ('defined)
    }
    this
  }

  def clickOnLogout = {
    clickOnUserMenuOption(LOGOUT, new HomePage)
  }

  def clickOnMyAccount = {
    clickOnUserMenuOption(MY_ACCOUNT, new LobbyPage)
  }
  def clickOnTransactionHistoric = {
    clickOnUserMenuOption(TRANSACTION_HISTORIC, new LobbyPage)
  }
  def clickOnReferencesCenter = {
    clickOnUserMenuOption(REFERENCES_CENTER, new LobbyPage)
  }
  def clickOnClassification = {
    clickOnUserMenuOption(CLASSIFICATION, new LobbyPage)
  }
  def clickOnUserPromos = {
    clickOnUserMenuOption(USER_PROMOS, new LobbyPage)
  }
  def clickOnAddFundsButton = {
    clickOnUserMenuOption(ADD_MONEY_BUTTON, new LobbyPage)
  }
  def clickOnUserMenuAddFunds = {
    clickOnUserMenuOption(USER_MENU_ADD_MONEY, new LobbyPage)
    /*if ( !find(id(ADD_FUNDS)).get.isDisplayed )


    eventually {
      click on id(ADD_FUNDS)
      new LobbyPage().isAt
    }*/
  }

  def clickOnTournaments = {
    clickOnGameMenuOption(TOURNAMENTS, new LobbyPage)
  }

  def clickOnMyContests = {
    clickOnGameMenuOption(MY_TOURNAMENTS, new MyContestsPage)
  }

  def clickOnGamePromos = {
    clickOnGameMenuOption(GAME_PROMOS, new LobbyPage)
  }

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

  private def hasHelpMenu = {
    find(id(SUPPORT)) should be('defined)
    find(id(HOW_TO)) should be('defined)
    find(id(RULES_RATING)) should be('defined)
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

}


