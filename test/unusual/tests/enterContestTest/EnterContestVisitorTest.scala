package unusual.tests.enterContestTest

import unusual.model._
import unusual.model.pageStates.EnterContestState

class EnterContestVisitorTest(state:EnterContestState, res:Resolution) extends EnterContestTestCommon(state, res) {

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before {
    status.ensureVisitor
  }

  "As visitor" when sizeTesting({
      enterContestPageBehavior_OrderBy
      enterContestPageBehavior_FilterBy
      enterContestPageBehavior_SelectPos
      enterContestPageBehavior_Bug
      enterContestPageBehavior_Pick
    })

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

      "fill whole lineup with five players of the same team" in pickPlayersOfSameTeam

      "fill with priceless lineup" in pickTooExpensiveLineUp

      "Then correct it" in CorrectFailLineup
    }

  }


  after {
  }

 }
