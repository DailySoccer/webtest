package unusual.tests.menuTest

import unusual.testTags.scala._
import unusual.tests._
import unusual.model._
import unusual.pages._


class MenuVisitorTest extends MenuTestCommon {

  before {
    status.ensureVisitor
  }


  "Visitor does not" when {
    behave like sizeTesting(menuBehavior)
  }

  def menuBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "log out from lobby" in {
      intercept[Exception] {
        super.doLogout(resolution)
      }
    }

    "look at his name. User name at menu should be ok" in {
      intercept[Exception] {
        super.checkUserName(resolution)
      }
    }

  }

  /*
  "Visitor does not " must {

    def doLogout(resolution:Resolution): Unit = {
      intercept[Exception] {
        super.doLogout(resolution)
      }
    }

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

    def checkUserName(resolution:Resolution): Unit = {
      intercept[Exception] {
        super.checkUserName(resolution)
      }
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

  }
  */


  after {
  }
}
