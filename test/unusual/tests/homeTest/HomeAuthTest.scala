package unusual.tests.homeTest

import unusual.model.Resolution
import unusual.pages.{LobbyPage, HomePage}
import unusual.testTags.scala.{BigResolution, MediumResolution, SmallResolution, WIP}
import unusual.tests._

class HomeAuthTest extends HomeTestCommon {

  before {
    status.ensureAuthUser
  }

  "Auth User" must {

    def goToHome(resolution:Resolution): Unit = {
      new HomePage(resolution).open
      new LobbyPage(resolution).isAt
    }

    "go to home. System should go to lobby. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToHome)
    }
    "go to home. System should go to lobby. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToHome)
    }
    "go to home. System should go to lobby. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToHome)
    }
  }

   after {
   }
 }
