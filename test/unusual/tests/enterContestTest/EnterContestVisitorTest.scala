package unusual.tests.enterContestTest

import unusual.model._
import unusual.pages._
import unusual.testTags.scala._

class EnterContestVisitorTest(state:EnterContestState, res:Resolution) extends EnterContestTestCommon(state, res) {

  before {
    status.ensureVisitor
  }

  if(status.resolution.enabled) "As visitor" when sizeTesting(enterContestPageBehavior)


  def enterContestPageBehavior: Unit = {

    "try to go to contest. Page should redirect to home" in {
      val enterC = new EnterContestPage(status.resolution, enterContestState)
      val home = new HomePage(status.resolution)
      enterC.open
      home.isAt
    }

  }

  after {
  }

 }
