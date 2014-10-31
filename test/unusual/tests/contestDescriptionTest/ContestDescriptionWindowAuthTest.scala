package unusual.tests.contestDescriptionTest

import unusual.model.{LobbyState, Contest, Resolution}
import unusual.pages._
import unusual.testTags.scala.WIP

class ContestDescriptionWindowAuthTest(cont:Contest, res:Resolution) extends ContestDescriptionWindowCommon(cont, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  "Auth user in description of contest: " + contest.name when sizeTesting(ContestDescriptionBehavior)


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

      "Scroll bar disappear when enter contest through button at information" in knownBugSequence_ScrollBarDisappeared
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
