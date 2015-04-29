package unusual.tests.liveContestTest

import unusual.model.pageStates.{LiveContestState, EnterContestState}
import unusual.model.{Resolution}
import unusual.pages.{LiveContestPage, EnterContestPage}
import unusual.tests.SharedTest

/**
 * Created by gregorioiniestaovejero on 27/4/15.
 */
class LiveContestTestCommon(state:LiveContestState, res:Resolution) extends SharedTest(res) {


  def goToLiveContestPage = {
    val tab = goToMyContestsPage.in.liveTab
    tab.clickOnRowActionButton(1)
  }

  def registerOnAContest:Unit = {
    val enterContestState = new EnterContestState
    enterContestState.contest = state.contest
    val enterContest = new EnterContestPage(res, enterContestState)

    enterContest.open
    eventually { enterContest.isAt }
    enterContest.pickWholeLineup(state.contest.affordableLineup).confirmLineup
  }

  def checkPunctuations(timeIdx:Int):Unit = {
    val livePage = new LiveContestPage(res)

    //val puncts = state.soccerPlayerPunctuations
    var i = 1
    for(pts <- state.timeInfo(timeIdx).soccerPlayerPunctuations) {
      livePage.getSoccerPlayerPunctuation(i) must be(pts)
      i+=1
    }

  }
  def checkTime(timeIdx:Int):Unit = {
    val livePage = new LiveContestPage(res)

    livePage.getRemainingTime must be(s"${state.timeInfo(timeIdx).remainingTime}")
  }

  def checkScore(timeIdx:Int):Unit = {
    val livePage = new LiveContestPage(res)

    livePage.getTotalScore must be(s"${state.timeInfo(timeIdx).totalPoints}")
  }

}
