package unusual.tests.viewContestEntryTest

import unusual.model._
import unusual.model.pageStates.ViewContestState

class ViewContestAuthTest(state:ViewContestState, res:Resolution) extends ViewContestTestCommon(state, res) {

  before { status.ensureAuthUser }

  def consistIn = afterWord("consist in")

  if(status.resolution.enabled) s"Auth user in contest: ${state.contest.name}" when sizeTesting(ViewContestPageBehavior)

  def ViewContestPageBehavior: Unit = {

    "check the lineup" in isCorrectLineup

    "look at the players list" in checkPlayersList

    "look at the constest header" in isRightContestInfo

    "edit lineup" in simpleCheckEditButton

    "cancel contest entry" in cancelEntry

  }

 }




