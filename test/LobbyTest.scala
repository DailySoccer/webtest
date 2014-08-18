import pages._
import testTags.scala._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium._
import org.openqa.selenium.firefox._
import org.openqa.selenium.chrome._
import java.util.concurrent._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class LobbyTest extends SharedTest {

  before {
  }

  "User" must {

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


    "test default contests." in {
      //configResolution(BIG)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)

        val list = goToLobbyPage.allContest
      }
      /*var s: String = ""

      for(l <- list){
        s += l.toString + "\n"
      }

      println(s)
      */
      //println(res + " is ok.")
    }

    "test free contests." taggedAs(WIPTest) in {
      //configResolution(BIG)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)

        val list = goToLobbyPage.freeContests
      }
      /*var s: String = ""

      for(l <- list){
        s += l.toString + "\n"
      }

      println(s)
      */
      //println(res + " is ok.")
    }
  }

  after {
  }
}
