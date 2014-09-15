package unusual.tests.homeTest

import unusual.model.Resolution
import unusual.pages.{LoginPage, SignUpPage}
import unusual.tests._

class HomeTestCommon extends SharedTest {

  def goToHomeAndSignUp(implicit resolution:Resolution): Unit = {
    goToHomePage.clickOnSignUp
    assert(new SignUpPage(resolution).isAt, "is not sign up page")
  }

  def goToHomeAndLogin(implicit resolution:Resolution): Unit = {
    goToHomePage.clickOnLogin
    assert(new LoginPage(resolution).isAt, "is not login page")
  }

  def goThroughScreenSeparatorLinks(implicit resolution:Resolution): Unit = {
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

  def clickOnPlayButton0(implicit resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton0
    assert(new SignUpPage(resolution).isAt, "is not sign up page")
  }

  def clickOnPlayButton1(implicit resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton1
    if (resolution != Resolution.SMALL) {
      assert(new SignUpPage(resolution).isAt, "is not sign up page")
    }
  }

  def clickOnPlayButton2(implicit resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton2
    if (resolution != Resolution.SMALL) {
      assert(new SignUpPage(resolution).isAt, "is not sign up page")
    }
  }

  def clickOnPlayButton3(implicit resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton3
    if (resolution != Resolution.SMALL) {
      assert(new SignUpPage(resolution).isAt, "is not sign up page")
    }
  }
}
