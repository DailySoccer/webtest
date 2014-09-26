package unusual.tests.homeTest

import unusual.model.{LobbyState, Resolution}
import unusual.pages.{EnterContestPage, LobbyPage, HomePage}
import unusual.testTags.scala.{BigResolution, MediumResolution, SmallResolution, WIP}
import unusual.tests._

class HomeAuthTest extends HomeTestCommon {

  before {
    status.ensureAuthUser
  }


  "Auth user" when sizeTesting(homeBehavior)

  def homeBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "try to go to lobby without be logged in. Page should redirect to home" in {
      new HomePage(resolution).open
      assert(new LobbyPage(resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney).isAt, "is not lobby page")
    }

  }

  after {
  }
 }
