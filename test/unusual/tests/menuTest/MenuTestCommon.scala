package unusual.tests.menuTest

import unusual.model.pageStates.LobbyState
import unusual.pages.components.MenuBar
import unusual.tests._
import unusual.model._
import unusual.pages._

class MenuTestCommon(res:Resolution) extends SharedTest(res) {

  def doLogout: Unit = {
    goToLobbyPage(LobbyState.DEFAULT_LOBBY)
    val menuBar = new MenuBar(status.resolution)
    assert(menuBar.isAt && menuBar.isLoggedBar)

    menuBar.clickOnLogout
    status.setLoggedIn(false)

  }

  def checkUserName: Unit = {
    goToLobbyPage(LobbyState.DEFAULT_LOBBY)
    val user:User = User.DEFAULT

    val menuBar:MenuBar = new MenuBar(status.resolution)
    assert(menuBar.isAt)
    menuBar.getUserName must be(user.firstName + " " + user.lastName)
  }





  def goToMyContestsFromLobby: Unit = {
    goToLobbyPage(LobbyState.DEFAULT_LOBBY)
    new MenuBar(status.resolution).clickOnMyContests
  }

  def goToGamePromosFromLobby: Unit = {
    goToLobbyPage(LobbyState.DEFAULT_LOBBY)
    new MenuBar(status.resolution).clickOnGamePromos
  }

  def goToGamePromosFromMyContests: Unit = {
    goToMyContestsPage
    new MenuBar(status.resolution).clickOnContests
  }

  def goToLobbyFromMyContests: Unit = {
    goToMyContestsPage
    new MenuBar(status.resolution).clickOnGamePromos
  }

  def goToLobbyFromGamePromos: Unit = {
    goToPromos
    new MenuBar(status.resolution).clickOnContests
  }

  def goToMyContestsFromGamePromos: Unit = {
    goToPromos
    new MenuBar(status.resolution).clickOnMyContests
  }


}
