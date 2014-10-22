package unusual.tests.enterContestTest

import unusual.model._
import unusual.testTags.scala.{DoesNotWorkYet, WIP}


class EnterContestAuthTest_OrderBy(state:EnterContestState, res:Resolution) extends EnterContestAuthTest(state, res) {
  s"(OrderBy) Auth user in contest: ${enterContestState.contest.name}" when sizeTesting(enterContestPageBehavior_OrderBy)
}
class EnterContestAuthTest_FilterBy(state:EnterContestState, res:Resolution) extends EnterContestAuthTest(state, res) {
  s"(FilterBy) Auth user in contest: ${enterContestState.contest.name}" when sizeTesting(enterContestPageBehavior_FilterBy)
}
class EnterContestAuthTest_SelectPos(state:EnterContestState, res:Resolution) extends EnterContestAuthTest(state, res) {
  s"(SelectPos) Auth user in contest: ${enterContestState.contest.name}" when sizeTesting(enterContestPageBehavior_SelectPos)
}
class EnterContestAuthTest_Bug(state:EnterContestState, res:Resolution) extends EnterContestAuthTest(state, res) {
  s"(Bug) Auth user in contest: ${enterContestState.contest.name}" when sizeTesting(enterContestPageBehavior_Bug)
}
class EnterContestAuthTest_Pick(state:EnterContestState, res:Resolution) extends EnterContestAuthTest(state, res) {
  s"(Pick) Auth user in contest: ${enterContestState.contest.name}" when sizeTesting(enterContestPageBehavior_Pick)
}

abstract class EnterContestAuthTest(state:EnterContestState, res:Resolution) extends EnterContestTestCommon(state, res) {

  before {
    status.ensureAuthUser
  }

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")


  def enterContestPageBehavior_OrderBy: Unit = {
    
    "look at default state" in checkDefaultState

    "use headers" which orderBy {

      "position" in orderByPosition

      "name" in orderByName

      "DFP" in orderByDFP

      "played" in orderByPlayed

      "salary" in orderBySalary
    }

  }

  def enterContestPageBehavior_FilterBy: Unit = {

    "use filters" which filterBy {

      "(soccer) GoalKeepers" in filterByGoalKeeper

      "(soccer) Defense" in filterByDefense

      "(soccer) Middle" in filterByMiddle

      "(soccer) Forward" in filterByForward

      "(soccer) All" in filterByAll

      "match" in filterByMatch

      "Mixed filters" in filterMix_Position_Match
    }

  }

  def enterContestPageBehavior_SelectPos: Unit = {

    "use lineup elements" which filterBy {

      "GoalKeeper" in selectGoalKeeper

      "Defense" in selectDefense

      "Middle" in selectMiddle

      "Forward" in selectForward

      "Random" in multipleRandomSelection
    }

  }

  def enterContestPageBehavior_Bug: Unit = {

    "perform known BUG SEQUENCE" which causes {

      "Duplicated players at delete all" in knownBugSequence_DuplicatedPlayersAtDeleteAll

      "Duplicated players at insert" in knownBugSequence_DuplicatedPlayersAtInsert

      "Disappeared players" in knownBugSequence_DisappearedPlayers

      "Add a forward as goalkeeper" in knownBugSequence_AddForwardAsGoalKeeper

      //"Try to confirm multiple times" in tryToConfirmMultipleTimes
    }
  }

  def enterContestPageBehavior_Pick: Unit = {

    "pick soccer players from list" which {

      "add first goal keeper" in addFirstGoalKeeperFromList

      "add fourth defense" in addFourthDefenseFromList

      "fill whole lineup, then clear it" in pickAndClearWholeLineup

      "fill with priceless lineup" in pickTooExpensiveLineUp

      "fill with priceless lineup. Then correct it to submit" in pickFailLineupAndCorrectIt
    }

  }


  /**
   * Borrar si finalmente se dejan divididos
   * @deprecated
   */
  /*
  def enterContestPageBehavior: Unit = {
    implicit val resolution:Resolution = res

    "look at default state" in checkDefaultState

    "use headers" which orderBy {

      "position" in orderByPosition

      "name" in orderByName

      "DFP" in orderByDFP

      "played" in orderByPlayed

      "salary" in orderBySalary
    }

    "use filters" which filterBy {

      "(soccer) GoalKeepers" in filterByGoalKeeper

      "(soccer) Defense" in filterByDefense

      "(soccer) Middle" in filterByMiddle

      "(soccer) Forward" in filterByForward

      "(soccer) All" in filterByAll

      "match" in filterByMatch

      "Mixed filters" in filterMix_Position_Match
    }

    "use lineup elements" which filterBy {

      "GoalKeeper" in selectGoalKeeper

      "Defense" in selectDefense

      "Middle" in selectMiddle

      "Forward" in selectForward

      "Random" in multipleRandomSelection
    }

    "perform known BUG SEQUENCE" which causes {

      "Duplicated players at delete all" in knownBugSequence_DuplicatedPlayersAtDeleteAll

      "Duplicated players at insert" in knownBugSequence_DuplicatedPlayersAtInsert

      "Disappeared players" in knownBugSequence_DisappearedPlayers

      "Add a forward as goalkeeper" in knownBugSequence_AddForwardAsGoalKeeper

      //"Try to confirm multiple times" in tryToConfirmMultipleTimes
    }

    "pick soccer players from list" which {

      "add first goal keeper" in addFirstGoalKeeperFromList

      "add fourth defense" in addFourthDefenseFromList

      "fill whole lineup, then clear it" in pickAndClearWholeLineup

      "fill with priceless lineup" in pickTooExpensiveLineUp

      "fill with priceless lineup. Then correct it to submit" in pickFailLineupAndCorrectIt
    }

  }*/

 }
