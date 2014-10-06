package unusual.tests.viewContestEntryTest

import unusual.model._

class ViewContestAuthTest(state:ViewContestState) extends ViewContestTestCommon(state) {

  before { status.ensureAuthUser }

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")

  s"Auth user in contest: ${state.contest.name}" when sizeTesting(enterContestPageBehavior)


  def enterContestPageBehavior(res:Resolution): Unit = {
    implicit val resolution:Resolution = res

    "check the lineup" in isCorrectLineup

    "look at the players list" in checkPlayersList

    "check himself is at the players list" in checkHimselfAtPlayersList
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
