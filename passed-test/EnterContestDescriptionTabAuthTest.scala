package unusual.tests.contestDescriptionTest

import unusual.model.{LobbyState, Contest, Resolution}
import unusual.pages._
import unusual.testTags.scala.WIP

class EnterContestDescriptionTabAuthTest(cont:Contest, res:Resolution) extends EnterContestDescriptionTabCommon(cont, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureAuthUser
  }

  "Auth user in enter contest description tab: " + contest.name when sizeTesting(EnterContestDescriptionBehavior)


  def EnterContestDescriptionBehavior: Unit = {

    "look at contest sections" which consistIn {

      "involved matches" in numberOfMatches

      "involved contestants" in numberOfContestants

      "prizes" in numberOfPrizes
    }

  }

}
