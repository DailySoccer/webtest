package unusual.tests.navigationTest

import unusual.model.pageStates.LobbyState
import unusual.model.{User, Resolution}
import unusual.pages._
import unusual.pages.components._
import unusual.tests.SharedTest

/**
 * Created by gregorioiniestaovejero on 6/4/15.
 */
class NavigationCommon(res:Resolution) extends SharedTest(res){

  def loginAndSignUpLinks:Unit = {
    goHomeUnLogged.goLogin.goSignUp.
                   goLogin.goRecoverPass.
                   close.goRecoverPass.goSignUp
  }

  def playUnLogged:Unit = {
    goHomeUnLogged.goPlay.closeHelpWindow.playAContest
  }

  def tryGoMyContestsUnLogged:Unit = {
    intercept[Exception] {
      goHomeUnLogged.goPlay.closeHelpWindow.menu.goMyContests
    }
  }


  // LOGGED

  def playLogged:Unit = {
    var theLogin:NavLogin = null
    eventually { theLogin = goHomeUnLogged.goLogin }

    var theLobby:NavLobby = null
    eventually { theLobby = theLogin.doLogin }
    eventually { theLobby.playAContest }
  }

  def goMyContestsLogged:Unit = {
    eventually { goLobbyLogged.menu.goMyContests }
  }

  def goHowItWorks:Unit = {
    goLobbyLogged.menu.goHowItWorks
  }

  def useMenuMultiplesTimes:Unit = {
    val p:NavPage[LobbyPage] = goLobbyLogged
    eventually { p.menu.goLobby }
    eventually { p.menu.goMyContests }
    eventually { p.menu.goHowItWorks }
    eventually { p.menu.goHowItWorks }
    eventually { p.menu.goMyContests }
    eventually { p.menu.goHowItWorks }
    eventually { p.menu.goLobby }
    eventually { p.menu.goMyContests }
    eventually { p.menu.goLobby }
  }

  /******************* PRIVATE METHODS *******************/

  private def goLobbyLogged = {
    status.ensureAuthUser
    goToLobbyPage(LobbyState.DEFAULT_LOBBY)
    new NavLobby
  }

  private def goHomeUnLogged:NavHome = {
    status.ensureVisitor
    goToHomePage
    new NavHome
  }

  private def goHome:NavHome = {
    goToHomePage
    new NavHome
  }

  private class NavHome extends NavPage[HomePage](new HomePage(res)) {

    def goLogin = {
      logger.debug("go login")
      page.clickOnLogin
      new NavLogin
    }

    def goPlay = {
      logger.debug("go play")
      page.clickOnPlayButton
      new NavLobby
    }

  }

  private class NavLogin extends NavPage[LoginPage](new LoginPage(res)) {

    def doLogin = {
      logger.debug("go login")
      eventually {
        page.doLogin(User.DEFAULT)
        status.setLoggedIn(true)
        new NavLobby
      }
    }

    def goSignUp = {
      logger.debug("go sign up")
      page.clickRegister
      new NavSignUp
    }

    def goRecoverPass = {
      logger.debug("go recover")
      page.clickRecoverPass
      new NavRecoverPass
    }

    def close = {
      logger.debug("close")
      page.clickClose
      new NavHome
    }

  }

  private class NavSignUp extends NavPage[SignUpPage](new SignUpPage(res)) {

    def goLogin = {
      logger.debug("go login")
      page.clickLogin
      new NavLogin
    }

    def doSignUp = {

    }

  }

  private class NavRecoverPass extends NavPage[RecoverPassPage](new RecoverPassPage(res)) {

    def close = {
      logger.debug("click on close")
      page.clickClose
      new NavLogin
    }

    def goSignUp = {
      logger.debug("go register")
      page.goRegister
      new NavSignUp
    }

  }

  private class NavLobby extends NavPage[LobbyPage](new LobbyPage(res, 6)) {

    def closeHelpWindow = {
      logger.debug("close help modal")
      page.closeHelpModal
      this
    }

    def playAContest = {
      logger.debug("playing a contest")
      eventually {
        page.playContestNumber(1)
        if (!status.isLoggedIn) new EnterContestPage(res, null).closeHelpModal
        new NavEnterContests
      }
    }

  }

  private class NavMyContests extends NavPage[MyContestsPage](new MyContestsPage(res)) {
    // END
  }

  private class NavEnterContests extends NavPage[EnterContestPage](new EnterContestPage(res, null)) {
    // END
  }

  private class NavHowItWorks extends NavPage[HowItWorksPage](new HowItWorksPage(res)) {
    // END
  }

  private class NavPage[T <: SharedPage](p:T) {
    val page:T = p
    val menuBar:MenuBar = new MenuBar(res)
    val footer:FooterBar = new FooterBar(res)

    logger.debug(s"Verifying to be at ${page.getClass.getSimpleName}")
    eventually (timeout(7 seconds)){ assert(page.isAt, s"${page.getClass.getSimpleName} is not at") }
    logger.debug(s"Verifyed")

    object menu {
      def goMyContests: NavMyContests = {
        logger.debug("click on menu my contests")
        menuBar.clickOnMyContests
        new NavMyContests
      }

      def goLobby: NavLobby = {
        logger.debug("click on menu lobby")
        menuBar.clickOnContests
        new NavLobby
      }

      def goHowItWorks: NavHowItWorks = {
        logger.debug("click on menu How it works")
        menuBar.clickOnHowItWorks
        new NavHowItWorks
      }
    }
  }


}
