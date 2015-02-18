package unusual.tests.lobbyTest

import unusual.model.Resolution
import unusual.model.pageStates.LobbyState
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests.SharedTest

class LobbyVisitorTest(lobbySt:LobbyState, res:Resolution) extends LobbyTestCommon(lobbySt, null, res) {

  before {
    status.ensureVisitor
  }

  "As visitor" when sizeTesting(lobbyBehavior)

  def lobbyBehavior: Unit = {

    "try to go to lobby. Page should redirect to home" in {
      val lobby = new LobbyPage(status.resolution, lobbyState.maxEntryMoney).open
      val home = new HomePage(status.resolution)
      lobby.open
      assert(home.isAt, "Is not at home.")
    }

  }

}
