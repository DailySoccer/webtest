import pages._
import fantasyTags.scala._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium._
import org.openqa.selenium.firefox._
import org.openqa.selenium.chrome._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class LobbyTest extends SharedTest {

  before {
  }

  "User" must {
/*
    "go to lobby without be logged in." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.isNotLoggedIn
        println(res + " is ok.")
      }
    }

    "go to lobby logged in." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLoginPage.doLogin
        goToLobbyPage.isLoggedIn
        println(res + " is ok.")
      }
    }
*/
/*
    "test content list." taggedAs(DbTest) in {

      configResolution(BIG)
      goToLoginPage.doLogin

      val list = goToLobbyPage.getContentList

      //println(res + " is ok.")
    }
*/

    "test content list." taggedAs(DbTest) in {
      println("YUJU =============================")
      println("YUJU =============================")
      println("YUJU =============================")
    }
  }

  after {
  }
}
