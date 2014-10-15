package unusual.tests.contestDescriptionTest

import unusual.model.{LobbyState, Contest, Resolution}
import unusual.pages._
import unusual.testTags.scala.WIP


class ContestDescriptionAuthTest_Header(cont: Contest) extends ContestDescriptionAuthTest(cont){
  "(Header) Auth user" when sizeTesting(ContestDescriptionBehaviorHeader)
}

class ContestDescriptionAuthTest_Sections(cont: Contest) extends ContestDescriptionAuthTest(cont){
  "(Sections) Auth user" when sizeTesting(ContestDescriptionBehaviorSections)
}

class ContestDescriptionAuthTest_Bug(cont: Contest) extends ContestDescriptionAuthTest(cont){
  "(Bug) Auth user" when sizeTesting(ContestDescriptionBehaviorBug)
}


abstract class ContestDescriptionAuthTest(cont:Contest) extends ContestDescriptionCommon(cont) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  //"Auth user in Match: " + contest.name when sizeTesting(ContestDescriptionBehavior)


  def ContestDescriptionBehaviorHeader(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "click on tabs" in changeTabs

    "look at contest header" which consistIn {

      "name" in contestName

      "description" in contestDescription

      "entry fee" in contestEntryFee

      "prize" in contestPrize
    }

  }

  def ContestDescriptionBehaviorSections(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "look at contest sections" which consistIn {

      "involved matches" in numberOfMatches

      "involved contestants" in numberOfContestants

      "prizes" in numberOfPrizes
    }

  }

  def ContestDescriptionBehaviorBug(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "perform known BUG SEQUENCE" which causes {

      "Scroll bar disappear when enter contest through button at information. B" in knownBugSequence_ScrollBarDisappeared
    }

  }

  /**
   * Borrar si finalmente se dejan divididos
   * @deprecated
   */
  /*
  def ContestDescriptionBehavior(res:Resolution): Unit = {
    implicit val resolution: Resolution = res

    "click on tabs" in changeTabs

    "look at contest header" which consistIn {

      "name" in contestName

      "description" in contestDescription

      "entry fee" in contestEntryFee

      "prize" in contestPrize
    }

    "look at contest sections" which consistIn {

      "involved matches" in numberOfMatches

      "involved contestants" in numberOfContestants

      "prizes" in numberOfPrizes
    }

    "perform known BUG SEQUENCE" which causes {

      "Scroll bar disappear when enter contest through button at information. B" in knownBugSequence_ScrollBarDisappeared
    }

  }*/


}
