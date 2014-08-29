package unusual.tests.enterContestTest

import unusual.model._
import unusual.pages._
import unusual.testTags.scala._
import unusual.tests._

class EnterContestAuthTest extends EnterContestTestCommon {

  before {
    status.ensureAuthUser
  }

  "Auth user" must {

    // - Darle a seleccionar delantero, que se filtre por delanteros,
    // cambiar el filtro a porteros y seleccionar uno. Comprobar que no se puede añadir

    // - Intentar meter varias veces el mismo jugador


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

    def pickFailLineupAndCorrectIt(resolution:Resolution): Unit = {
      super.pickFailLineupAndCorrectIt(resolution)
      new LobbyPage(resolution).isAt
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

    "known bug sequence: duplicated players at insert. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_DuplicatedPlayersAtInsert)
    }
    "known bug sequence: duplicated players at insert. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_DuplicatedPlayersAtInsert)
    }
    "known bug sequence: duplicated players at insert. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_DuplicatedPlayersAtInsert)
    }

    "known bug sequence: disappeared players. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_DisappearedPlayers)
    }
    "known bug sequence: disappeared players. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_DisappearedPlayers)
    }
    "known bug sequence: disappeared players. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_DisappearedPlayers)
    }

  }



   after {
   }
 }
