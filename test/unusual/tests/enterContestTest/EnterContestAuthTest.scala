package unusual.tests.enterContestTest

import unusual.model._
import unusual.testTags.scala.{DoesNotWorkYet, WIP}

class EnterContestAuthTest extends EnterContestTestCommon {

  before {
    status.ensureAuthUser
  }

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")

  "Auth user" when sizeTesting(enterContestPageBehavior)


  def enterContestPageBehavior(res:Resolution): Unit = {
    implicit val resolution:Resolution = res

    "look at default state" in checkDefaultState

    "use headers" which orderBy {

      "position" in orderByPosition

      "name" taggedAs DoesNotWorkYet in orderByName

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
      /*
      "(match) All" in filterByMatch_None

      "(match) Cle-Aus" in filterByMatch_CleAus

      "(match) Col-Grc" in filterByMatch_ColGrc

      "(match) Ury-Cri" in filterByMatch_UryCri


      "GoalKeeper and Ury-Cri" in filterMix_GoalKeeper_UryCri

      "GoalKeeper and Cle-Aus" in filterMix_GoalKeeper_CleAus

      "Defense and Ury-Cri" in filterMix_Defense_UryCri

      "Middle and Cle-Aus" in filterMix_Middle_CleAus

      "Forward and Col-Grc" in filterMix_Forward_ColGrc
      */
    }

    "use lineup elements" which filterBy {

      "GoalKeeper" in selectGoalKeeper

      "Defense" in selectDefense

      "Middle" in selectMiddle

      "Forward" in selectForward

      "Random" in multipleRandomSelection
    }

    "pick soccer players from list" which {

      "add first goal keeper" in addFirstGoalKeeperFromList

      "add fourth defense" in addFourthDefenseFromList

      "fill whole lineup, then clear it" in pickAndClearWholeLineup

      "fill with priceless lineup" in pickTooExpensiveLineUp

      "fill with priceless lineup. Then correct it to submit" taggedAs WIP in pickFailLineupAndCorrectIt
    }

    "perform known BUG SEQUENCE" which causes {

      "Duplicated players at delete all" in knownBugSequence_DuplicatedPlayersAtDeleteAll

      "Duplicated players at insert" in knownBugSequence_DuplicatedPlayersAtInsert

      "Disappeared players" in knownBugSequence_DisappearedPlayers

      "Add a forward as goalkeeper" in knownBugSequence_AddForwardAsGoalKeeper

      //"Try to confirm multiple times" in tryToConfirmMultipleTimes
    }

  }

/*
  "Auth user" must {


    "look at default state" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(checkDefaultState)
    }
    "look at default state. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(checkDefaultState)
    }
    "look at default state. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(checkDefaultState)
    }

    "order by position. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(orderByPosition)
    }
    "order by position. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(orderByPosition)
    }
    "order by position. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(orderByPosition)
    }

    "order by name. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(orderByName)
    }
    "order by name. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(orderByName)
    }
    "order by name. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(orderByName)
    }

    "order by DFP. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(orderByDFP)
    }
    "order by DFP. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(orderByDFP)
    }
    "order by DFP. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(orderByDFP)
    }

    "order by Played. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(orderByPlayed)
    }
    "order by Played. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(orderByPlayed)
    }
    "order by Played. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(orderByPlayed)
    }

    "order by Salary. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(orderBySalary)
    }
    "order by Salary. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(orderBySalary)
    }
    "order by Salary. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(orderBySalary)
    }

    "filter by GoalKeepers. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByGoalKeeper)
    }
    "filter by GoalKeepers. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByGoalKeeper)
    }
    "filter by GoalKeepers. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByGoalKeeper)
    }

    "filter by Defense. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByDefense)
    }
    "filter by Defense. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByDefense)
    }
    "filter by Defense. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByDefense)
    }

    "filter by Middle. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByMiddle)
    }
    "filter by Middle. M" taggedAs(MediumResolution) in {
      implicit val resolution: Resolution = Resolution.MEDIUM
      callTest(filterByMiddle)
    }
    "filter by Middle. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByMiddle)
    }

    "filter by Forward. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByForward)
    }
    "filter by Forward. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByForward)
    }
    "filter by Forward. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByForward)
    }

    "filter by All. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByAll)
    }
    "filter by All. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByAll)
    }
    "filter by All. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByAll)
    }

    "select GoalKeeper. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(selectGoalKeeper)
    }
    "select GoalKeeper. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(selectGoalKeeper)
    }
    "select GoalKeeper. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(selectGoalKeeper)
    }

    "select Defense. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(selectDefense)
    }
    "select Defense. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(selectDefense)
    }
    "select Defense. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(selectDefense)
    }

    "select Middle. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(selectMiddle)
    }
    "select Middle. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(selectMiddle)
    }
    "select Middle. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(selectMiddle)
    }

    "select Forward. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(selectForward)
    }
    "select Forward. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(selectForward)
    }
    "select Forward. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(selectForward)
    }

    "multiple random select. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(multipleRandomSelection)
    }
    "multiple random select. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(multipleRandomSelection)
    }
    "multiple random select. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(multipleRandomSelection)
    }

    "filter by All Matches. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByMatch_None)
    }
    "filter by All Matches. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByMatch_None)
    }
    "filter by All Matches. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByMatch_None)
    }

    "filter by Match Cle-Aus. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByMatch_CleAus)
    }
    "filter by Match Cle-Aus. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByMatch_CleAus)
    }
    "filter by Match Cle-Aus. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByMatch_CleAus)
    }

    "filter by Match Col-Grc. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByMatch_ColGrc)
    }
    "filter by Match Col-Grc. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByMatch_ColGrc)
    }
    "filter by Match Col-Grc. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByMatch_ColGrc)
    }

    "filter by Match Ury-Cri. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByMatch_UryCri)
    }
    "filter by Match Ury-Cri. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByMatch_UryCri)
    }
    "filter by Match Ury-Cri. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByMatch_UryCri)
    }


    "mix filter GoalKeeper Ury-Cri. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterMix_GoalKeeper_UryCri)
    }
    "mix filter GoalKeeper Ury-Cri. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterMix_GoalKeeper_UryCri)
    }
    "mix filter GoalKeeper Ury-Cri. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterMix_GoalKeeper_UryCri)
    }

    "mix filter GoalKeeper Cle-Aus. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter GoalKeeper Cle-Aus. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter GoalKeeper Cle-Aus. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterMix_GoalKeeper_CleAus)
    }

    "mix filter Defense Ury-Cri. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter Defense Ury-Cri. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter Defense Ury-Cri. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterMix_GoalKeeper_CleAus)
    }

    "mix filter Middle Cle-Aus. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter Middle Cle-Aus. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter Middle Cle-Aus. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterMix_GoalKeeper_CleAus)
    }

    "mix filter Forward Col-Grc. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter Forward Col-Grc. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterMix_GoalKeeper_CleAus)
    }
    "mix filter Forward Col-Grc. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterMix_GoalKeeper_CleAus)
    }

    "add first goal keeper from list. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(addFirstGoalKeeperFromList)
    }
    "add first goal keeper from list. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(addFirstGoalKeeperFromList)
    }
    "add first goal keeper from list. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(addFirstGoalKeeperFromList)
    }

    "add fourth defense from list. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(addFourthDefenseFromList)
    }
    "add fourth defense from list. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(addFourthDefenseFromList)
    }
    "add fourth defense from list. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(addFourthDefenseFromList)
    }

    "pick and clear whole lineup. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(pickAndClearWholeLineup)
    }
    "pick and clear whole lineup. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(pickAndClearWholeLineup)
    }
    "pick and clear whole lineup. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(pickAndClearWholeLineup)
    }


    "pick very expensive line up. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(pickTooExpensiveLineUp)
    }
    "pick very expensive line up. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(pickTooExpensiveLineUp)
    }
    "pick very expensive line up. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(pickTooExpensiveLineUp)
    }

    "pick very expensive line up. Then correct it to submit. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(pickFailLineupAndCorrectIt)
    }
    "pick very expensive line up. Then correct it to submit. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(pickFailLineupAndCorrectIt)
    }
    "pick very expensive line up. Then correct it to submit. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(pickFailLineupAndCorrectIt)
    }

    "check known bug sequence: Duplicated players at delete all. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_DuplicatedPlayersAtDeleteAll)
    }
    "check known bug sequence: Duplicated players at delete all. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_DuplicatedPlayersAtDeleteAll)
    }
    "check known bug sequence: Duplicated players at delete all. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_DuplicatedPlayersAtDeleteAll)
    }

    "check known bug sequence: duplicated players at insert. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_DuplicatedPlayersAtInsert)
    }
    "check known bug sequence: duplicated players at insert. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_DuplicatedPlayersAtInsert)
    }
    "check known bug sequence: duplicated players at insert. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_DuplicatedPlayersAtInsert)
    }

    "check known bug sequence: disappeared players. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_DisappearedPlayers)
    }
    "check known bug sequence: disappeared players. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_DisappearedPlayers)
    }
    "check known bug sequence: disappeared players. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_DisappearedPlayers)
    }

    "check known bug sequence: add a forward as goalkeeper. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_AddForwardAsGoalKeeper)
    }
    "check known bug sequence: add a forward as goalkeeper. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_AddForwardAsGoalKeeper)
    }
    "check known bug sequence: add a forward as goalkeeper. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_AddForwardAsGoalKeeper)
    }


    "try to confirm multiple times. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(tryToConfirmMultipleTimes)
    }
    "try to confirm multiple times. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(tryToConfirmMultipleTimes)
    }
    "try to confirm multiple times. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(tryToConfirmMultipleTimes)
    }

  }
*/

   after {
   }
 }
