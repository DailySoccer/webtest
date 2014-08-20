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

  "As visitor" must {

    "go to lobby without be logged in." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.isNotLoggedIn
        println(res + " is ok.")
      }
    }

    "use filter control" in {

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage.checkEntryMinFeeFilterControl
                     .checkEntryMaxFeeFilterControl
                     .checkEntryBothFeeFilterControl

        println(res + " is ok.")
      }
    }

    "look at default contests." in {

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.allContest
        println(res + " is ok.")
      }
    }

    "filter by free contests." in {

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.freeContests
        println(res + " is ok.")
      }
    }

    "filter by league contests." in {

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.leagueContests
        println(res + " is ok.")
      }
    }

    "filter by fifty fifty contests." in {

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.fiftyFiftyContests
        println(res + " is ok.")
      }
    }

    "filter by head to head contests." in {

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.headToHeadContests
        println(res + " is ok.")
      }
    }

    "filter by free contests with min filter applied." in {

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.freeContestsWithMinFilter
        println(res + " is ok.")
      }
    }

  }

  "User logged" must {

    "go to lobby logged in." in {
      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLoginPage.doLogin
        goToLobbyPage.isLoggedIn
        println(res + " is ok.")
      }
    }

    "use filter control" in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage.checkEntryMinFeeFilterControl
                     .checkEntryMaxFeeFilterControl
                     .checkEntryBothFeeFilterControl

        println(res + " is ok.")
      }
    }

    "look at default contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.allContest
        println(res + " is ok.")
      }
    }

    "filter by free contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.freeContests
        println(res + " is ok.")
      }
    }

    "filter by league contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.leagueContests
        println(res + " is ok.")
      }
    }

    "filter by fifty fifty contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage.fiftyFiftyContests
        println(res + " is ok.")
      }
    }

    "filter by head to head contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)

        goToLobbyPage.headToHeadContests
        println(res + " is ok.")
      }
    }

    "filter by free contests with min filter applied." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.freeContestsWithMinFilter
        println(res + " is ok.")
      }
    }

    ////////
    "press play button of first contest to select team." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.playFirstContest
        println(res + " is ok.")
      }
    }

    "type on search contest to filter." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        if(res != Resolutions.SMALL) {
          goToLobbyPage.searchContest
        } else {
          println("No se puede filtrar en version Movil")
        }
        println(res + " is ok.")
      }
    }
/*
    "select Team." taggedAs(WIPTest) in {
      configResolution(BIG)
      goToLoginPage.doLogin

      for (res <- RESOLUTIONS) {
        configResolution(res)
        goToLobbyPage.playFirstContest
        val page = new EnterContestPage
        //page.setSoccerPlayerPositionFilter(page.DEFENSE)
        //page.setSoccerPlayerMatchFilter(3)
        page.selectGoalKeeperOnMyTeam
        println(res + " is ok.")
      }

    }
*/


  }

  after {
  }
}
