package unusual.tests.homeTest

import unusual.model.{LobbyState, Resolution}
import unusual.pages.{EnterContestPage, LobbyPage, HomePage}
import unusual.testTags.scala.{BigResolution, MediumResolution, SmallResolution, WIP}
import unusual.tests._

class HomeAuthTest(res:Resolution) extends HomeTestCommon(res) {

  before {
    status.ensureAuthUser
  }


  if(status.resolution.enabled) "Auth user" when sizeTesting(homeBehavior)

  def homeBehavior: Unit = {

    "try to go to lobby without be logged in. Page should redirect to home" in {
      new HomePage(status.resolution).open
      assert(new LobbyPage(status.resolution, LobbyState.DEFAULT_LOBBY.maxEntryMoney).isAt, "is not lobby page")
    }

  }

  after {
  }
 }
