package unusual.tests.lobbyTest

import unusual.model.Resolution
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests.SharedTest

class LobbyVisitorTest extends LobbyTestCommon {

  before {
    status.ensureVisitor
  }

  "As visitor" when {
    behave like sizeTesting(lobbyBehavior)
  }

  def lobbyBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "try to go to lobby. Page should redirect to home" taggedAs(DoesNotWorkYet) in {
      val lobby = new LobbyPage(resolution).open
      val home = new HomePage(resolution)
      lobby.open
      assert(home.isAt, "Is not at home.")
    }

  }

}
