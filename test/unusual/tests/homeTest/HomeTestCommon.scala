package unusual.tests.homeTest

import unusual.model.Resolution
import unusual.pages._
import unusual.testTags.scala.{BigResolution, MediumResolution, SmallResolution, WIP}
import unusual.tests._

class HomeTestCommon extends SharedTest {

  def goToHome(resolution:Resolution): Unit = {
    goToHomePage
  }

  def goToHomeAndSignUp(resolution:Resolution): Unit = {
    goToHomePage.clickOnSignUp
    new SignUpPage(resolution).isAt
  }

  def goToHomeAndLogin(resolution:Resolution): Unit = {
    goToHomePage.clickOnLogin
    new LoginPage(resolution).isAt
  }

  def goThroughScreenSeparatorLinks(resolution:Resolution): Unit = {
    goToHomePage.clickOnScreenSeparator1
                .clickOnScreenSeparator2
                .clickOnScreenSeparator3
    new HomePage(resolution).isAt
  }

  def clickOnPlayButton0(resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton0
    new LobbyPage(resolution).isAt
  }

  def clickOnPlayButton1(resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton1
    if (resolution != Resolution.SMALL)
      new LobbyPage(resolution).isAt
  }

  def clickOnPlayButton2(resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton2
    if (resolution != Resolution.SMALL)
      new LobbyPage(resolution).isAt
  }

  def clickOnPlayButton3(resolution:Resolution): Unit = {
    goToHomePage.clickOnPlayButton3
    if (resolution != Resolution.SMALL)
      new LobbyPage(resolution).isAt
  }

 }
