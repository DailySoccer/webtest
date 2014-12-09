package unusual.tests.viewContestEntryTest

import unusual.model._

class ViewContestAuthTest(state:ViewContestState, res:Resolution) extends ViewContestTestCommon(state, res) {

  before { status.ensureAuthUser }

  def consistIn = afterWord("consist in")

  if(status.resolution.enabled) s"Auth user in contest: ${state.contest.name}" when sizeTesting(ViewContestPageBehavior)

  def ViewContestPageBehavior: Unit = {

    "check the lineup" in isCorrectLineup

    "look at the players list" in checkPlayersList

    "look at the constest header" in isRightContestInfo

    "look at matches" in checkMatches

    "edit lineup" in checkEditButton

    "click on other contests" in goOtherContests

    "cancel contest entry" in cancelEntry

  }

 }




