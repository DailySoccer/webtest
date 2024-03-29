package unusual.tests.loginTest

import unusual.model.Resolution
import unusual.pages.{HomePage, EnterContestPage}
import unusual.tests._
import unusual.testTags.scala._

class LoginAuthTest(res:Resolution) extends LoginTestCommon(res) {
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

  before { status.ensureAuthUser }

  if (status.resolution.enabled) "Auth user" when { behave like sizeTesting(loginBehavior) }

  def loginBehavior: Unit = {

    "sign up" taggedAs(WIP, DoesNotWorkYet) in featureNotImplemented

    "right login" taggedAs WIP in doLogin

  }

  /*
  "Auth User" must {


    "sign up. B" taggedAs(BigResolution, DoesNotWorkYet) in {
      implicit val resolution:Resolution = Resolution.BIG
      featureNotImplemented
    }
    "sign up. M" taggedAs(MediumResolution, DoesNotWorkYet) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      featureNotImplemented
    }
    "sign up. S" taggedAs(SmallResolution, DoesNotWorkYet) in {
      implicit val resolution:Resolution = Resolution.SMALL
      featureNotImplemented
    }


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
