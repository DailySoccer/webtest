package unusual.tests.homeTest

import unusual.model.Resolution
import unusual.pages.{HomePage, EnterContestPage, LoginPage, SignUpPage}
import unusual.testTags.scala.{BigResolution, MediumResolution, SmallResolution, WIP}

class HomeVisitorTest(res:Resolution) extends HomeTestCommon(res) {

  before {
    status.ensureVisitor
  }

  if(status.resolution.enabled) "Auth user" when sizeTesting(homeBehavior)

  def homeBehavior: Unit = {

    "go to home" in goToHomePage

    "go to sign up" in goToHomeAndSignUp

    "go to login" in goToHomeAndLogin

    "go down through screen separator links" in goThroughScreenSeparatorLinks

    "click on play button" which {

      "is #0" in clickOnPlayButton0

      "is #1" in clickOnPlayButton1

      "is #2" in clickOnPlayButton2

      "is #3" in clickOnPlayButton3
    }

  }


/*
  "As visitor" must {
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

    def goToHomeAndSignUp(resolution:Resolution): Unit = {
      goToHomePage.clickOnSignUp
      assert(new SignUpPage(resolution).isAt, "is not sign up page")
    }

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

    def goToHomeAndLogin(resolution:Resolution): Unit = {
      goToHomePage.clickOnLogin
      assert(new LoginPage(resolution).isAt, "is not login page")
    }

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

    def goThroughScreenSeparatorLinks(resolution:Resolution): Unit = {
      if (resolution != Resolution.SMALL) {
        val home = goToHomePage
        home.clickOnScreenSeparator1
        currentUrl must be(home.SCREEN_2_URL)
        home.clickOnScreenSeparator2
        currentUrl must be(home.SCREEN_3_URL)
        home.clickOnScreenSeparator3
        currentUrl must be(home.SCREEN_4_URL)
      }
    }

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

    def clickOnPlayButton0(resolution:Resolution): Unit = {
      goToHomePage.clickOnPlayButton0
      assert(new SignUpPage(resolution).isAt, "is not sign up page")
    }

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

    def clickOnPlayButton1(resolution:Resolution): Unit = {
      goToHomePage.clickOnPlayButton1
      if (resolution != Resolution.SMALL) {
        assert(new SignUpPage(resolution).isAt, "is not sign up page")
      }
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

    def clickOnPlayButton2(resolution:Resolution): Unit = {
      goToHomePage.clickOnPlayButton2
      if (resolution != Resolution.SMALL) {
        assert(new SignUpPage(resolution).isAt, "is not sign up page")
      }
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

    def clickOnPlayButton3(resolution:Resolution): Unit = {
      goToHomePage.clickOnPlayButton3
      if (resolution != Resolution.SMALL) {
        assert(new SignUpPage(resolution).isAt, "is not sign up page")
      }
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

   }
*/

   after {
   }
 }
