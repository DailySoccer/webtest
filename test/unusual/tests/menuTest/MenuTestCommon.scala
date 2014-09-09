package unusual.tests.menuTest

import unusual.pages.components.MenuBar
import unusual.tests._
import unusual.model._
import unusual.pages._

class MenuTestCommon extends SharedTest {

  def doLogout(resolution:Resolution): Unit = {
    goToLobbyPage
    val menuBar = new MenuBar(resolution)
    assert(menuBar.isAt && menuBar.isLoggedBar)

    menuBar.clickOnLogout
    status.setLoggedIn(false)

  }

  def checkUserName(resolution:Resolution): Unit = {
    goToLobbyPage
    val user:User = User.DEFAULT

    val menuBar:MenuBar = new MenuBar(resolution)
    assert(menuBar.isAt)
    menuBar.getUserName must be(user.firstName + " " + user.lastName)
  }





  def goToMyContestsFromLobby(resolution:Resolution): Unit = {
    goToLobbyPage
    new MenuBar(resolution).clickOnMyContests
  }

  def goToGamePromosFromLobby(resolution:Resolution): Unit = {
    goToLobbyPage
    new MenuBar(resolution).clickOnGamePromos
  }

  def goToGamePromosFromMyContests(resolution:Resolution): Unit = {
    goToMyContestsPage
    new MenuBar(resolution).clickOnContests
  }

  def goToLobbyFromMyContests(resolution:Resolution): Unit = {
    goToMyContestsPage
    new MenuBar(resolution).clickOnGamePromos
  }

  def goToLobbyFromGamePromos(resolution:Resolution): Unit = {
    goToPromos
    new MenuBar(resolution).clickOnContests
  }

  def goToMyContestsFromGamePromos(resolution:Resolution): Unit = {
    goToPromos
    new MenuBar(resolution).clickOnMyContests
  }


}
