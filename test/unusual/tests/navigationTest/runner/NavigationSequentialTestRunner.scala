package unusual.tests.navigationTest.runner

import org.scalatest.{Sequential, Suite}
import unusual.model.Resolution
import unusual.tests.navigationTest.NavigationTest

/**
 * Created by gregorioiniestaovejero on 6/4/15.
 */
class NavigationSequentialTestRunner extends Sequential(
  NavigationSequentialTestRunner.createBunchOfTests(Resolution.BIG)
  , NavigationSequentialTestRunner.createBunchOfTests(Resolution.MEDIUM)
  , NavigationSequentialTestRunner.createBunchOfTests(Resolution.SMALL)
)

private object NavigationSequentialTestRunner {
  def createBunchOfTests(resolution:Resolution):Suite =
    if (resolution.enabled) {
      new NavigationTest(resolution)
    } else {
      new Sequential
    }
}
