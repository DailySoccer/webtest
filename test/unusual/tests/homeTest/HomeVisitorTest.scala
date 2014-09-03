package unusual.tests.homeTest

import unusual.model.Resolution
import unusual.testTags.scala.{BigResolution, MediumResolution, SmallResolution, WIP}
import unusual.tests._

class HomeVisitorTest extends HomeTestCommon {

  before {
    status.ensureVisitor
  }

  "As visitor" must {
    /*
    def goToHome(resolution:Resolution): Unit = {
      goToHomePage
    }
    */
    "go to home. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToHome)
    }
    "go to home. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToHome)
    }
    "go to home. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToHome)
    }
    /*
    def goToHomeAndSignUp(resolution:Resolution): Unit = {
      goToHomePage.clickOnSignUp
    }
    */
    "go from home to sign up. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToHomeAndSignUp)
    }
    "go from home to sign up. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToHomeAndSignUp)
    }
    "go from home to sign up. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToHomeAndSignUp)
    }
    /*
    def goToHomeAndLogin(resolution:Resolution): Unit = {
      goToHomePage.clickOnLogin
    }
    */
    "go from home to login. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToHomeAndLogin)
    }
    "go from home to login. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToHomeAndLogin)
    }
    "go from home to login. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToHomeAndLogin)
    }
    /*
    def goThroughScreenSeparatorLinks(resolution:Resolution): Unit = {
      goToHomePage.clickOnScreenSeparator1
                  .clickOnScreenSeparator2
                  .clickOnScreenSeparator3
    }
    */
    "go down through screen separator links. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goThroughScreenSeparatorLinks)
    }
    "go down through screen separator links. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goThroughScreenSeparatorLinks)
    }
    "go down through screen separator links. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goThroughScreenSeparatorLinks)
    }
    /*
    def clickOnPlayButtons(resolution:Resolution): Unit = {
      goToHomePage.clickOnPlayButton0
      goToHomePage.clickOnPlayButton1
      goToHomePage.clickOnPlayButton2
      goToHomePage.clickOnPlayButton3
    }
    */
    "click on play button0. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(clickOnPlayButton0)
    }
    "click on play button0. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(clickOnPlayButton0)
    }
    "click on play button0. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(clickOnPlayButton0)
    }


    "click on play button1. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(clickOnPlayButton1)
    }
    "click on play button1. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(clickOnPlayButton1)
    }
    "click on play button1. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(clickOnPlayButton1)
    }


    "click on play button2. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(clickOnPlayButton2)
    }
    "click on play button2. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(clickOnPlayButton2)
    }
    "click on play button2. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(clickOnPlayButton2)
    }


    "click on play button3. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(clickOnPlayButton3)
    }
    "click on play button3. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(clickOnPlayButton3)
    }
    "click on play button3. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(clickOnPlayButton3)
    }


    "click on Help." in {
      featureNotImplemented
      // currently unabled
      // check on home page and lobby page
    }
    "click on Legal." in {
      featureNotImplemented
      // currently unabled
      // check on home page and lobby page
    }
    "click on Terms and Conditions." in {
      featureNotImplemented
      // currently unabled
      // check on home page and lobby page
    }
    "click on Privacy Policy." in {
      featureNotImplemented
      // currently unabled
      // check on home page and lobby page
    }

   }

   after {
   }
 }
