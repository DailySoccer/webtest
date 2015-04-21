package unusual.tests.loginTest.runner

import org.scalatest.{Sequential, Suite}
import unusual.model.{Contest, Resolution}
import unusual.tests.loginTest.LoginVisitorTest
import unusual.tests.contestDescriptionTest._


class LoginSignUpSequentialTestRunner extends Sequential(
  LoginSignUpSequentialTestRunner.createBunchOfTests(Resolution.BIG)
  , LoginSignUpSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM)
  , LoginSignUpSequentialTestRunner.createBunchOfTests(Resolution.SMALL)
)

private object LoginSignUpSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution):Suite =
      if (resolution.enabled) {
        new LoginVisitorTest(resolution)
      } else {
        new Sequential
      }
}
