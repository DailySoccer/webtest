package unusual.tests.loginTest

import unusual.model.Resolution
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests._

class LoginVisitorTest(res:Resolution) extends LoginTestCommon(res) {
/*
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
*/
  before {
    status.ensureVisitor
  }

  "Auth user" when {
    behave like sizeTesting(loginBehavior)
  }

  def loginBehavior: Unit = {

    "Try to fail on sign ups" in doFailSignUp

    //"sign up" in doRightSignUp

    "Try to fail on login" in doFailLogin

    "right login" in doLogin

  }

  /*
  "Visitor" must {
    /*
     def doRightSignUp(resolution:Resolution): Unit = {
       goToSignUpPage.doSingup
     }
    */
     "sign up. B" taggedAs(BigResolution, DoesNotWorkYet) in {
       implicit val resolution:Resolution = Resolution.BIG
       featureNotImplemented
       callTest(doRightSignUp)
     }
     "sign up. M" taggedAs(MediumResolution, DoesNotWorkYet) in {
       implicit val resolution:Resolution = Resolution.MEDIUM
       featureNotImplemented
       callTest(doRightSignUp)
     }
     "sign up. S" taggedAs(SmallResolution, DoesNotWorkYet) in {
       implicit val resolution:Resolution = Resolution.SMALL
       featureNotImplemented
       callTest(doRightSignUp)
     }


    /*
    def doLogin(resolution:Resolution): Unit = {
      goToLoginPage.doLogin
      new LobbyPage(resolution).isAt.isLoggedIn
    }
    */
    "right login. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(doLogin)
    }
    "right login. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(doLogin)
    }
    "right login. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(doLogin)
    }

  }

*/

  after {
  }
 }
