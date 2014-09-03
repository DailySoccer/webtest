package unusual.tests.lobbyTest

import unusual.model.Resolution
import unusual.pages.LobbyPage
import unusual.testTags.scala._
import unusual.tests.SharedTest

class LobbyAuthTest extends LobbyTestCommon {

  before {
    status.ensureAuthUser
  }

  "Auth User" must {

    def goToLobby(resolution:Resolution): Unit = {
      super.goToLobby(resolution)
      new LobbyPage(resolution).isLoggedIn
    }

    "go to lobby logged in. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(goToLobby)
    }
    "go to lobby logged in. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(goToLobby)
    }
    "go to lobby logged in. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(goToLobby)
    }

    "go to lobby and change resolutions. B" taggedAs(DoesNotWorkYet, BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(changeResolutionTests)
    }
    "go to lobby and change resolutions. M" taggedAs(DoesNotWorkYet, MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(changeResolutionTests)
    }
    "go to lobby and change resolutions. S" taggedAs(DoesNotWorkYet, SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(changeResolutionTests)
    }
    /*
    def checkClearFiltersButton(resolution:Resolution): Unit = {
      super.checkClearFiltersButton(resolution)
    }
    */
    "clear filters control. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(checkClearFiltersButton)
    }
    "clear filters control. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(checkClearFiltersButton)
    }
    "clear filters control. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(checkClearFiltersButton)
    }
    /*
    def lookForDefaultContests(resolution:Resolution): Unit = {
      super.lookForDefaultContests(resolution)
    }
    */
    "look for default contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(lookForDefaultContests)
    }
    "look for default contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(lookForDefaultContests)
    }
    "look for default contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(lookForDefaultContests)
    }
    /*
    def filterByFreeContests(resolution:Resolution): Unit = {
      super.filterByFreeContests(resolution)
    }
    */
    "filter by free contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByFreeContests)
    }
    "filter by free contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByFreeContests)
    }
    "filter by free contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByFreeContests)
    }
    /*
    def filterByLeagueContests(resolution:Resolution): Unit = {
      goToLobbyPage.leagueContests
    }
    */
    "filter by league contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByLeagueContests)
    }
    "filter by league contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByLeagueContests)
    }
    "filter by league contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByLeagueContests)
    }
    /*
    def filterByFiftyFiftyContests(resolution:Resolution): Unit = {
      goToLobbyPage.fiftyFiftyContests
    }
    */
    "filter by fifty fifty contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByFiftyFiftyContests)
    }
    "filter by fifty fifty contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByFiftyFiftyContests)
    }
    "filter by fifty fifty contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByFiftyFiftyContests)
    }
    /*
    def filterByHeadToHeadContests(resolution:Resolution): Unit = {
      goToLobbyPage.headToHeadContests
    }
    */
    "filter by head to head contests. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByHeadToHeadContests)
    }
    "filter by head to head contests. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByHeadToHeadContests)
    }
    "filter by head to head contests. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByHeadToHeadContests)
    }
    /*
    def filterByFreeContestsWithMinFilter(resolution:Resolution): Unit = {
      super.filterByFreeContestsWithMinFilter
    }
    */
    "filter by free contests with min filter applied. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByFreeContestsWithMinFilter)
    }
    "filter by free contests with min filter applied. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByFreeContestsWithMinFilter)
    }
    "filter by free contests with min filter applied. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByFreeContestsWithMinFilter)
    }
    /*
    def checkEntryFeeFilterCtrl(resolution:Resolution): Unit = {
      super.checkEntryFeeFilterCtrl(resolution)
    }
    */
    "use entry fee filter control. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(checkEntryFeeFilterCtrl)
    }
    "use entry fee filter control. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(checkEntryFeeFilterCtrl)
    }
    "use entry fee filter control. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(checkEntryFeeFilterCtrl)
    }
    /*
    def playFirstContest(resolution:Resolution): Unit = {
      super.playFirstContest(resolution)
    }
    */
    "press play button of first contest to select team. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(playFirstContest)
    }
    "press play button of first contest to select team. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(playFirstContest)
    }
    "press play button of first contest to select team. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(playFirstContest)
    }
    /*
    def searchContest(resolution:Resolution): Unit = {
      super.searchContest(resolution)
    }
    */
    "search contest. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(searchContest)
    }
    "search contest. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(searchContest)
    }
    "search contest. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(searchContest)
    }

    "filter by entry fee. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(filterByEntryFee)
    }
    "filter by entry fee. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(filterByEntryFee)
    }
    "filter by entry fee. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(filterByEntryFee)
    }



    "check pager main functionality. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(paginatorMainFunctionality)
    }
    "check pager main functionality. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(paginatorMainFunctionality)
    }
    "check pager main functionality. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(paginatorMainFunctionality)
    }

    "see pager displayed when necessary. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(paginatorIsDisplayedWhenNecessary)
    }
    "see pager displayed when necessary. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(paginatorIsDisplayedWhenNecessary)
    }
    "see pager displayed when necessary. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(paginatorIsDisplayedWhenNecessary)
    }



    "check known bug sequence: Disappeared pager on contests filter. B" taggedAs(BigResolution) in {
      implicit val resolution:Resolution = Resolution.BIG
      callTest(knownBugSequence_DisappearedPaginatorOnFilter)
    }
    "check known bug sequence: Disappeared pager on contests filter. M" taggedAs(MediumResolution) in {
      implicit val resolution:Resolution = Resolution.MEDIUM
      callTest(knownBugSequence_DisappearedPaginatorOnFilter)
    }
    "check known bug sequence: Disappeared pager on contests filter. S" taggedAs(SmallResolution) in {
      implicit val resolution:Resolution = Resolution.SMALL
      callTest(knownBugSequence_DisappearedPaginatorOnFilter)
    }
  }

}
