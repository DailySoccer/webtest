import pages._
import model._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium._
import org.openqa.selenium.firefox._
import org.openqa.selenium.chrome._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class MenuTest extends SharedTest {

  before {
  }


  "User in User Menu" must {

    "do log in to be able to log out from lobby. Then log out." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLoginPage.isAt.doLogin

        val menuBar = new MenuBar()
        menuBar.isAt.isLoggedBar

        menuBar.clickOnLogout

        println(res + " is ok.")
      }
    }

    "go throught user menu links" in {
      configResolution(DEFAULT_RESOLUTION)

      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)
        menuBar.clickOnMyAccount
        menuBar.clickOnTransactionHistoric
        menuBar.clickOnReferencesCenter
        menuBar.clickOnClassification
        menuBar.clickOnUserPromos
        menuBar.clickOnUserMenuAddFunds
        if (res != Resolutions.SMALL) menuBar.clickOnAddFundsButton
        println(res + " is ok.")
      }
    }

    "log in. Menu user name should be ok." in {
      configResolution(DEFAULT_RESOLUTION)

      val user :User = goToLoginPage.isAt.doLogin.DEFAULT_USER

      val menuBar :MenuBar = new MenuBar().isAt

      for (res <- RESOLUTIONS) {
        configResolution(res)
        menuBar.isUser(user)
        println(res + " is ok.")
      }

    }
  }

  "User in Game Menu" must {


    "go to my contests from lobby." in {
      goToLoginPage.isAt.doLogin
      configResolution(DEFAULT_RESOLUTION)

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage

        menuBar.clickOnMyContests

        println(res + " is ok.")
      }
    }


    "go to game promos from lobby." in {
      goToLoginPage.isAt.doLogin
      configResolution(DEFAULT_RESOLUTION)

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage

        menuBar.clickOnGamePromos

        println(res + " is ok.")
      }
    }

    "go to game promos from my contests." in {
      goToLoginPage.isAt.doLogin
      configResolution(DEFAULT_RESOLUTION)

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToMyContestsPage

        menuBar.clickOnGamePromos

        println(res + " is ok.")
      }
    }

    "go to lobby from my contests." in {
      goToLoginPage.isAt.doLogin
      configResolution(DEFAULT_RESOLUTION)

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToMyContestsPage

        menuBar.clickOnTournaments

        println(res + " is ok.")
      }
    }

    "go to lobby from game promos." in {
      goToLoginPage.isAt.doLogin
      configResolution(DEFAULT_RESOLUTION)

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToPromos

        menuBar.clickOnTournaments

        println(res + " is ok.")
      }
    }

    "go to my contests from game promos." in {
      goToLoginPage.isAt.doLogin
      configResolution(DEFAULT_RESOLUTION)

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToPromos

        menuBar.clickOnMyContests

        println(res + " is ok.")
      }
    }
  }


  after {
  }
}
