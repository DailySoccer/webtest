package pages

class MenuBar extends SharedPage {
  val url = SharedPage.baseUrl

  val LOGIN_ID  = "loginButton"
  val SIGNUP_ID = "joinButton"

  val MENU_ID = "mainMenuRoot"
  val BRAND_LINK_SELECTOR = "#" + MENU_ID + " .navbar-brand-container a"

  val GAME_MENU = "gameMenuCollapse"
  val USER_MENU = "userMenuCollapse"
  val ADD_MONEY_BUTTON = "addMoney"

  def isAt = {
    eventually {
      find(id(MENU_ID)) should be ('defined)
      find(cssSelector(BRAND_LINK_SELECTOR)) should be ('defined)
    }

    this
  }

  def isLoginBar = {
    eventually {
      find(id(LOGIN_ID)) should be ('defined)
      find(id(SIGNUP_ID)) should be ('defined)
      find(id(GAME_MENU)) should not be ('defined)
      find(id(USER_MENU)) should not be ('defined)
      find(id(ADD_MONEY_BUTTON)) should not be ('defined)
    }
    this
  }

  def isLoggedBar = {
    eventually {
      find(id(LOGIN_ID)) should not be ('defined)
      find(id(SIGNUP_ID)) should not be ('defined)
      find(id(GAME_MENU)) should be ('defined)
      find(id(USER_MENU)) should be ('defined)
      find(id(ADD_MONEY_BUTTON)) should be ('defined)
    }
    this
  }

}


