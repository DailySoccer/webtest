package unusual.tests.menuTest

import unusual.pages.components.MenuBar
import unusual.tests._
import unusual.model._
import unusual.pages._

class MenuTestCommon extends SharedTest {

  def doLogout(implicit resolution:Resolution): Unit = {
    goToLobbyPage
    val menuBar = new MenuBar(resolution)
    assert(menuBar.isAt && menuBar.isLoggedBar)

    menuBar.clickOnLogout
    status.setLoggedIn(false)

  }

  def checkUserName(implicit resolution:Resolution): Unit = {
    goToLobbyPage
    val user:User = User.DEFAULT

    val menuBar:MenuBar = new MenuBar(resolution)
    assert(menuBar.isAt)
    menuBar.getUserName must be(user.firstName + " " + user.lastName)
  }





  def goToMyContestsFromLobby(implicit resolution:Resolution): Unit = {
    goToLobbyPage
    new MenuBar(resolution).clickOnMyContests
  }

  def goToGamePromosFromLobby(implicit resolution:Resolution): Unit = {
    goToLobbyPage
    new MenuBar(resolution).clickOnGamePromos
  }

  def goToGamePromosFromMyContests(implicit resolution:Resolution): Unit = {
    goToMyContestsPage
    new MenuBar(resolution).clickOnContests
  }

  def goToLobbyFromMyContests(implicit resolution:Resolution): Unit = {
    goToMyContestsPage
    new MenuBar(resolution).clickOnGamePromos
  }

  def goToLobbyFromGamePromos(implicit resolution:Resolution): Unit = {
    goToPromos
    new MenuBar(resolution).clickOnContests
  }

  def goToMyContestsFromGamePromos(implicit resolution:Resolution): Unit = {
    goToPromos
    new MenuBar(resolution).clickOnMyContests
  }


}
