package unusual.tests.lobbyTest

import unusual.model.{LobbyState, Resolution}
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests.SharedTest

class LobbyVisitorTest(lobbySt:LobbyState = LobbyState.DEFAULT_LOBBY) extends LobbyTestCommon(lobbySt) {

  before {
    status.ensureVisitor
  }

  "As visitor" when {
    behave like sizeTesting(lobbyBehavior)
  }

  def lobbyBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "try to go to lobby. Page should redirect to home" in {
      val lobby = new LobbyPage(resolution, lobbyState.maxEntryMoney).open
      val home = new HomePage(resolution)
      lobby.open
      assert(home.isAt, "Is not at home.")
    }

  }

}
