package unusual.tests.contestDescriptionTest

import unusual.model.{Contest, Resolution}
import unusual.pages._
import unusual.testTags.scala.WIP

class ContestDescriptionAuthTest extends ContestDescriptionCommon {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  "Auth user" when sizeTesting(ContestDescriptionBehavior)

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

  }


}
