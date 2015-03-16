package unusual.tests.viewContestEntryTest

import unusual.model.Resolution
import unusual.model.pageStates.ViewContestState

/**
 * Created by gregorioiniestaovejero on 16/3/15.
 */
class ViewContestVisitorTest (state:ViewContestState, res:Resolution) extends ViewContestTestCommon(state, res) {

  before { status.ensureVisitor }

  def consistIn = afterWord("consist in")

  if(status.resolution.enabled) s"Auth user in contest: ${state.contest.name}" when sizeTesting(ViewContestPageBehavior)

  def ViewContestPageBehavior: Unit = {

    "go to view contest logged out" in goToViewContestLoggedOut

    "check the lineup" in isCorrectLineup

    "look at the constest header" in isRightContestInfo

    "look at matches" in checkMatches

    "edit lineup" in checkEditButton

    "click on other contests" in goOtherContests

    "cancel contest entry" in cancelEntry

    "log out with the new user" in { status.doLogout }
  }

}