package unusual.tests.contestDescriptionTest

import unusual.model.{Contest, Resolution}
import unusual.pages._

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

    "look at involved matches" in numberOfMatches

    "look at involved contestants" in numberOfContestants

    "look at prizes" in numberOfPrizes

    "perform known BUG SEQUENCE" which causes {

      "Scroll bar disappear when enter contest through button at information. B" in knownBugSequence_ScrollBarDisappeared
    }

  }


}
