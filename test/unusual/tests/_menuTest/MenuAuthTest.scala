package unusual.tests._menuTest

import unusual.tests._
import unusual.model._
import unusual.pages._
import unusual.testTags.scala._


class MenuAuthTest(res:Resolution) extends MenuTestCommon(res) {

  before {
    status.ensureAuthUser
  }

  if(status.resolution.enabled) "Auth User in User Menu" when {
    behave like sizeTesting(userMenuBehavior)
  }

  if(status.resolution.enabled) "Auth User in Game Menu" when {
    behave like sizeTesting(userMenuBehavior)
  }

  def userMenuBehavior: Unit = {

    "log out from lobby" in doLogout

    "look at his name. User name at menu should be ok" in checkUserName

    "go through user menu links" in featureNotImplemented
  }

  def gameMenuBehavior: Unit = {

    "go to my contests from lobby" in goToMyContestsFromLobby

    "go to game promos from lobby" in goToGamePromosFromLobby

    "go to game promos from my contests" in goToGamePromosFromMyContests

    "go to lobby from my contests" in goToLobbyFromMyContests

    "go to lobby from game promos" in goToLobbyFromGamePromos

    "go to my contests from game promos" in goToMyContestsFromGamePromos
  }

/*
  "Auth User in User Menu" must{

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


  }

  "Auth User in Game Menu" must {

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
*/

  after {
  }
}
