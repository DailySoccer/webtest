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

class LobbyTest extends SharedTest {

  before {
  }

  "As visitor" must {

    "go to lobby without be logged in." in {
      callResolutionsTest((resolution) => {
        goToLobbyPage.isNotLoggedIn
      })
    }

    "use filter control" in {

      callResolutionsTest((resolution) => {

        goToLobbyPage.checkEntryMinFeeFilterControl
                     .checkEntryMaxFeeFilterControl
                     .checkEntryBothFeeFilterControl

      })
    }

    "look at default contests." in {

      callResolutionsTest((resolution) => {
        goToLobbyPage.allContest
      })
    }

    "filter by free contests." in {

      callResolutionsTest((resolution) => {
        goToLobbyPage.freeContests
      })
    }

    "filter by league contests." in {

      callResolutionsTest((resolution) => {
        goToLobbyPage.leagueContests
      })
    }

    "filter by fifty fifty contests." in {

      callResolutionsTest((resolution) => {
        goToLobbyPage.fiftyFiftyContests
      })
    }

    "filter by head to head contests." in {

      callResolutionsTest((resolution) => {
        goToLobbyPage.headToHeadContests
      })
    }

    "filter by free contests with min filter applied." in {

      callResolutionsTest((resolution) => {
        goToLobbyPage.freeContestsWithMinFilter
      })
    }

  }

  "User logged" must {

    "go to lobby logged in." in {
      callResolutionsTest((resolution) => {
        goToLoginPage.doLogin
        goToLobbyPage.isLoggedIn
        
      })
    }

    "use filter control" in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {

        goToLobbyPage.checkEntryMinFeeFilterControl
                     .checkEntryMaxFeeFilterControl
                     .checkEntryBothFeeFilterControl

        
      })
    }

    "look at default contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.allContest
        
      })
    }

    "filter by free contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.freeContests
        
      })
    }

    "filter by league contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.leagueContests
        
      })
    }

    "filter by fifty fifty contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.fiftyFiftyContests
      })
    }

    "filter by head to head contests." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.headToHeadContests
      })
    }

    "filter by free contests with min filter applied." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.freeContestsWithMinFilter
        
      })
    }

    ////////
    "press play button of first contest to select team." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        goToLobbyPage.playFirstContest
        
      })
    }

    "type on search contest to filter." in {
      configResolution(DEFAULT_RESOLUTION)
      goToLoginPage.doLogin

      callResolutionsTest((resolution) => {
        if(res != Resolutions.SMALL) {
          goToLobbyPage.searchContest
        } else {
          println("No se puede filtrar en version Movil")
        }
        
      })
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
        
      }

    }
*/


  }

  after {
  }
}
