import pages._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium._
import org.openqa.selenium.firefox._
import org.openqa.selenium.chrome._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class NavigationTest extends SharedTest {

  before {
  }

  "User" must {

    "go to home" in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToHomePage
        println(res + " is ok.")
      }
    }

    "go from home to sign up" in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToHomePage.clickOnSignUp
        println(res + " is ok.")
      }
    }

    "go from home to login" in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToHomePage.clickOnLogin
        println(res + " is ok.")
      }
    }

    "go down through screen separator links" in {
      for (res <- RESOLUTIONS) {
        if (res == BIG || res == MEDIUM) {
          configResolution(res)
          goToHomePage.clickOnScreenSeparator1.isAt
            .clickOnScreenSeparator2.isAt
            .clickOnScreenSeparator3.isAt
        }
        println(res + " is ok.")
      }
    }


    "click on play buttons." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        if (res == BIG || res == MEDIUM) {
          goToHomePage.clickOnPlayButton0
          goToHomePage.clickOnPlayButton1
          goToHomePage.clickOnPlayButton2
          goToHomePage.clickOnPlayButton3
        } else {
          goToHomePage.clickOnPlayButtonMobile
        }
        println(res + " is ok.")
      }

    }

    "click on Help." in {
      // currently unabled
      // check on home page and lobby page
    }
    "click on Legal." in {
      // currently unabled
      // check on home page and lobby page
    }
    "click on Terms and Conditions." in {
      // currently unabled
      // check on home page and lobby page
    }
    "click on Privacy Policy." in {
      // currently unabled
      // check on home page and lobby page
    }

    "go to lobby without be logged in." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.isAt.isNotLoggedIn
        println(res + " is ok.")
      }
    }

    "go to lobby logged in." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLoginPage.doLogin
        goToLobbyPage.isAt.isLoggedIn
        println(res + " is ok.")
      }
    }




  }

  after {
  }
}
