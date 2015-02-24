package unusual.tests._homeTest

import unusual.model.Resolution
import unusual.pages.{LoginPage, SignUpPage}
import unusual.tests._

class HomeTestCommon(res:Resolution) extends SharedTest(res) {

  def goToHomeAndSignUp: Unit = {
    goToHomePage.clickOnSignUp
    assert(new SignUpPage(status.resolution).isAt, "is not sign up page")
  }

  def goToHomeAndLogin: Unit = {
    goToHomePage.clickOnLogin
    assert(new LoginPage(status.resolution).isAt, "is not login page")
  }

  def goThroughScreenSeparatorLinks: Unit = {
    if (status.resolution != Resolution.SMALL) {
      val home = goToHomePage
      home.clickOnScreenSeparator1
      currentUrl must be(home.SCREEN_2_URL)
      home.clickOnScreenSeparator2
      currentUrl must be(home.SCREEN_3_URL)
      home.clickOnScreenSeparator3
      currentUrl must be(home.SCREEN_4_URL)
    }
  }

  def clickOnPlayButton0: Unit = {
    goToHomePage.clickOnPlayButton0
    assert(new SignUpPage(status.resolution).isAt, "is not sign up page")
  }

  def clickOnPlayButton1: Unit = {
    goToHomePage.clickOnPlayButton1
    if (status.resolution != Resolution.SMALL) {
      assert(new SignUpPage(status.resolution).isAt, "is not sign up page")
    }
  }

  def clickOnPlayButton2: Unit = {
    goToHomePage.clickOnPlayButton2
    if (status.resolution != Resolution.SMALL) {
      assert(new SignUpPage(status.resolution).isAt, "is not sign up page")
    }
  }

  def clickOnPlayButton3: Unit = {
    goToHomePage.clickOnPlayButton3
    if (status.resolution != Resolution.SMALL) {
      assert(new SignUpPage(status.resolution).isAt, "is not sign up page")
    }
  }
}
