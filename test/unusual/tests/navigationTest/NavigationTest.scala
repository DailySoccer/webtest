package unusual.tests.navigationTest

import unusual.model.Resolution
import unusual.model.pageStates.LobbyState
import unusual.pages.{LobbyPage, HomePage}

/**
 * Created by gregorioiniestaovejero on 6/4/15.
 */
class NavigationTest(res:Resolution) extends NavigationCommon(res){

  before {}


  if(status.resolution.enabled)
    "Any user" when sizeTesting(navigationBehavior)

  def navigationBehavior: Unit = {

    //"" which {

    "check login and sign up links" in loginAndSignUpLinks

    "play as visitor" in playUnLogged

    "try to go to my contests page without be logged in" in tryGoMyContestsUnLogged

    "play as logged user" in playLogged

    "go to my contests page logged in" in goMyContestsLogged

    "visit how it works logged in" in goHowItWorks

    "check menu navigation logged in" in useMenuMultiplesTimes

  }

  after {}
}
