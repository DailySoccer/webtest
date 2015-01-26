package unusual.tests.myContestTest

import unusual.model._
import unusual.model.pageStates.ViewContestState

class MyContestsAuthTest(state:ViewContestState, res:Resolution) extends MyContestTestCommon(state, res) {

  before { status.ensureAuthUser }

  def consistIn = afterWord("consist in")

  if(status.resolution.enabled) s"Auth user in contest: ${state.contest.name}" when sizeTesting(MyContestPageBehavior)

  def MyContestPageBehavior: Unit = {

    "check the lineup" in isCorrectLineup

    "look at the players list" in checkPlayersList

    "look at the constest header" in isRightContestInfo

    "look at matches" in checkMatches

    "edit lineup" in checkEditButton

    "click on other contests" in goOtherContests

    "cancel contest entry" in cancelEntry

  }

 }




