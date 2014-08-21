import unusual.pages._
import unusual.testTags.scala._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium._
import org.openqa.selenium.firefox._
import org.openqa.selenium.chrome._
import java.util.concurrent._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class EnterContestTest extends SharedTest {

  before {
  }

  "User logged" must {

    "select GoalKeeper." taggedAs(WIPTest) in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        new EnterContestPage(resolution).selectGoalKeeperOnMyTeam
      })

      /*
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.playFirstContest
        val page = new EnterContestPage(res)
        //page.setSoccerPlayerPositionFilter(page.DEFENSE)
        //page.setSoccerPlayerMatchFilter(3)
        page.selectGoalKeeperOnMyTeam
        println(res + " is ok.")
      }
      */

    }
/*
    "select Defense." taggedAs(WIPTest) in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.playFirstContest
        val page = new EnterContestPage(res)
        page.selectDefenseOnMyTeam(1)
        println(res + " is ok.")
      }

    }

    "select Middle." taggedAs(WIPTest) in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.playFirstContest
        val page = new EnterContestPage(res)
        page.selectMiddleOnMyTeam(1)
        println(res + " is ok.")
      }

    }

    "select Forward." taggedAs(WIPTest) in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage.playFirstContest
        val page = new EnterContestPage(res)
        page.selectForwardOnMyTeam(1)

        println(res + " is ok.")
      }

    }
*/


  }

  after {
  }
}
