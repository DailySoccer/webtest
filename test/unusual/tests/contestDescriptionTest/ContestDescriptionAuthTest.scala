package unusual.tests.contestDescriptionTest

import unusual.model.{LobbyState, Contest, Resolution}
import unusual.pages._
import unusual.testTags.scala.WIP


class ContestDescriptionAuthTest_Header(cont: Contest, res:Resolution) extends ContestDescriptionAuthTest(cont, res){
  s"(Header) Auth user in contest: ${contest.name}" when sizeTesting(ContestDescriptionBehaviorHeader)
}

class ContestDescriptionAuthTest_Sections(cont: Contest, res:Resolution) extends ContestDescriptionAuthTest(cont, res){
  s"(Sections) Auth user in contest: ${contest.name}" when sizeTesting(ContestDescriptionBehaviorSections)
}

class ContestDescriptionAuthTest_Bug(cont: Contest, res:Resolution) extends ContestDescriptionAuthTest(cont, res){
  s"(Bug) Auth user in contest: ${contest.name}" when sizeTesting(ContestDescriptionBehaviorBug)
}


abstract class ContestDescriptionAuthTest(cont:Contest, res:Resolution) extends ContestDescriptionCommon(cont, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  //"Auth user in Match: " + contest.name when sizeTesting(ContestDescriptionBehavior)


  def ContestDescriptionBehaviorHeader: Unit = {

    "click on tabs" in changeTabs

    "look at contest header" which consistIn {

      "name" in contestName

      "description" in contestDescription

      "entry fee" in contestEntryFee

      "prize" in contestPrize
    }

  }

  def ContestDescriptionBehaviorSections: Unit = {
    

    "look at contest sections" which consistIn {

      "involved matches" in numberOfMatches

      "involved contestants" in numberOfContestants

      "prizes" in numberOfPrizes
    }

  }

  def ContestDescriptionBehaviorBug: Unit = {
    

    "perform known BUG SEQUENCE" which causes {

      "Scroll bar disappear when enter contest through button at information. B" in knownBugSequence_ScrollBarDisappeared
    }

  }

  /**
   * Borrar si finalmente se dejan divididos
   * @deprecated
   */
  /*
  def ContestDescriptionBehavior: Unit = {
    

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
