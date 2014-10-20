package unusual.tests.viewContestEntryTest

import unusual.model._


/*
class ViewContestAuthTest_Header(state:ViewContestState) extends ViewContestAuthTest(state) {
  "(Header) Auth user" when sizeTesting(ViewContestPageBehaviorHeader)
}

class ViewContestAuthTest_UserSpecific(state:ViewContestState) extends ViewContestAuthTest(state) {
  "(UserSpecific) Auth user" when sizeTesting(ViewContestPageBehaviorUserSpecific)
}
*/

class ViewContestAuthTest(state:ViewContestState) extends ViewContestTestCommon(state) {

  before { status.ensureAuthUser }

  def consistIn = afterWord("consist in")

  s"Auth user in contest: ${state.contest.name}" when sizeTesting(ViewContestPageBehavior)

/*
  def ViewContestPageBehaviorHeader(res:Resolution): Unit = {

    "look at contest header" which consistIn {

      "name" in isRightContestName

      "description" in isRightContestDescription

      "matches" in checkMatches

      "entry and prize" in checkEntryAndPrize
    }

  }

  def ViewContestPageBehaviorUserSpecific(res:Resolution): Unit = {

    "check the lineup" in isCorrectLineup

    "check himself is at the players list" in checkHimselfAtPlayersList

    "look at the players list" in checkPlayersList

    "there is edit, cancel, close and other contest buttons" in checkButtons

  }
*/

  def ViewContestPageBehavior(res:Resolution): Unit = {
    implicit val resolution:Resolution = res

    "check the lineup" in isCorrectLineup

    "look at the players list" in checkPlayersList

    "there is edit, cancel, close and other contest buttons" in checkButtons

    "look at contest header" which consistIn {

      "name" in isRightContestName

      "description" in isRightContestDescription

      "matches" in checkMatches

      "entry and prize" in checkEntryAndPrize
    }

/*
    "check the lineup" which orderBy {

//      "position" in orderByPosition

    }
*//*
    "perform known BUG SEQUENCE" which causes {

//      "Duplicated players at delete all" in knownBugSequence_DuplicatedPlayersAtDeleteAll

    }
*/
  }

   after {
   }
 }
