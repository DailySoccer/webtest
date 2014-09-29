package unusual.tests.enterContestTest

import unusual.model._
import unusual.pages._
import unusual.testTags.scala._

class EnterContestVisitorTest(state:EnterContestState) extends EnterContestTestCommon(state) {

  before {
    status.ensureVisitor
  }

  "As visitor" when sizeTesting(enterContestPageBehavior)


  def enterContestPageBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "try to go to contest. Page should redirect to home" in {
      val enterC = new EnterContestPage(resolution, enterContestState.contest)
      val home = new HomePage(resolution)
      enterC.open
      home.isAt
    }

  }

  after {
  }

 }
