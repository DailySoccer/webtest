import pages._
import model._
import org.scalatestplus.play._
import org.scalatest._
import org.scalatest.{GivenWhenThen, ShouldMatchers}
import org.openqa.selenium._
import org.openqa.selenium.firefox._
import org.openqa.selenium.chrome._
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities, RemoteWebDriver}

class LoginTest extends SharedTest {

  val TEST_SINGUP_FORM_MAP = Map(
    "firstName" -> "First",
    "lastName" -> "Last",
    "email" -> "test@test.com",
    "nick" -> "nick",
    "password" -> "private"
  )

  val TEST_LOGIN_FORM_MAP = Map(
    "email" -> "test@test.com",
    "password" -> "private"
  )

  before {
  }

  "User" must {

    "signup" in {
      callResolutionsTest((resolution) => {
        goToSignUpPage.doSingup
      })
    }

    "login" in {
      callResolutionsTest((resolution) => {
        goToLoginPage.doLogin
        val lobby = new LobbyPage
        eventually { lobby.isAt.isLoggedIn }
      })
    }

  }


  after {
  }
}
