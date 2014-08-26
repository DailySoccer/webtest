package unusual.tests.enterContestTest

import unusual.model._
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests._

class EnterContestAuthTest extends SharedTest {

  before {
    status.ensureAuthUser
  }

  "User logged" must {

    // - Darle a seleccionar delantero, que se filtre por delanteros,
    // cambiar el filtro a porteros y seleccionar uno. Comprobar que no se puede añadir

    // - Intentar meter varias veces el mismo jugador

    /*
    def selectGoalKeeper(resolution:Resolution): Unit = {
      new EnterContestPage(resolution).selectGoalKeeperOnMyTeam
    }

    "select GoalKeeper. B" taggedAs(WIP, BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(selectGoalKeeper)
    }
    "select GoalKeeper. M" taggedAs(WIP, MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(selectGoalKeeper)
    }
    "select GoalKeeper. S" taggedAs(SmallResolution, DoesNotWorkYet) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(selectGoalKeeper)
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
   }
 */



   after {
   }
 }
