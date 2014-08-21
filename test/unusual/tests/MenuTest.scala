package unusual.tests

import unusual.model.{Resolution, User}
import unusual.pages._
import unusual.testTags.scala._

class MenuTest extends SharedTest {

  before {
  }

  "Visitor" must {

  }

  "Auth User in User Menu" must {

    def doLogout(resolution:Resolution): Unit = {
      goToLoginPage.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar

      menuBar.clickOnLogout
    }

    "loggout from lobby. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(doLogout)
    }
    "loggout from lobby. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(doLogout)
    }
    "loggout from lobby. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(doLogout)
    }


    "go throught user menu links" taggedAs(DoesNotWorkYet) in {
      featureNotImplemented
      // TODO: Cambiar cuando proceda
      /*
      changeBrowserResolution(DEFAULT_RESOLUTION)

      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar()
      menuBar.isAt.isLoggedBar

      callResolutionsTest((resolution) => {
        goToLoginPage.isAt.doLogin

        val menuBar = new MenuBar()
        menuBar.isAt.isLoggedBar

        menuBar.clickOnMyAccount
        menuBar.clickOnTransactionHistoric
        menuBar.clickOnReferencesCenter
        menuBar.clickOnClassification
        menuBar.clickOnUserPromos
        menuBar.clickOnUserMenuAddFunds

        if (resolution != Resolutions.SMALL) menuBar.clickOnAddFundsButton
      })
      */
    }


    def checkUserName(resolution:Resolution): Unit = {
      val user:User = goToLoginPage.doLogin.DEFAULT_USER

      val menuBar:MenuBar = new MenuBar(resolution).isAt
      menuBar.isAt.isUser(user)
    }


    "look at his name. User name at menu should be ok. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(checkUserName)
    }
    "look at his name. User name at menu should be ok. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(checkUserName)
    }
    "look at his name. User name at menu should be ok. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(checkUserName)
    }
  }

  "Auth User in Game Menu" must {

    def goToMyContestsFromLobby(resolution:Resolution): Unit = {
      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar
      goToLobbyPage
      menuBar.clickOnMyContests
    }

    "go to my contests from lobby. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToMyContestsFromLobby)
    }
    "go to my contests from lobby. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToMyContestsFromLobby)
    }
    "go to my contests from lobby. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToMyContestsFromLobby)
    }



    def goToGamePromosFromLobby(resolution:Resolution): Unit = {
      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar

      goToLobbyPage

      menuBar.clickOnGamePromos
    }

    "go to game promos from lobby. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToGamePromosFromLobby)
    }
    "go to game promos from lobby. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToGamePromosFromLobby)
    }
    "go to game promos from lobby. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToGamePromosFromLobby)
    }



    def goToGamePromosFromMyContests(resolution:Resolution): Unit = {
      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar

      goToMyContestsPage

      menuBar.clickOnGamePromos
    }

    "go to game promos from my contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToGamePromosFromMyContests)
    }
    "go to game promos from my contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToGamePromosFromMyContests)
    }
    "go to game promos from my contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToGamePromosFromMyContests)
    }


    def goToLobbyFromMyContests(resolution:Resolution): Unit = {
      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar

      goToMyContestsPage

      menuBar.clickOnTournaments
    }

    "go to lobby from my contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToLobbyFromMyContests)
    }
    "go to lobby from my contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToLobbyFromMyContests)
    }
    "go to lobby from my contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToLobbyFromMyContests)
    }


    def goToLobbyFromGamePromos(resolution:Resolution): Unit = {
      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar

      goToPromos

      menuBar.clickOnTournaments
    }

    "go to lobby from game promos. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToLobbyFromGamePromos)
    }
    "go to lobby from game promos. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToLobbyFromGamePromos)
    }
    "go to lobby from game promos. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToLobbyFromGamePromos)
    }


    def goToMyContestsFromGamePromos(resolution:Resolution): Unit = {
      goToLoginPage.isAt.doLogin

      val menuBar = new MenuBar(resolution)
      menuBar.isAt.isLoggedBar

      goToPromos

      menuBar.clickOnTournaments
    }

    "go to my contests from game promos. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToLobbyFromGamePromos)
    }
    "go to my contests from game promos. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToLobbyFromGamePromos)
    }
    "go to my contests from game promos. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToLobbyFromGamePromos)
    }

  }

  after {
  }
}
