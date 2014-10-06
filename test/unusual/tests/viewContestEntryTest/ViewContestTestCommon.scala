package unusual.tests.viewContestEntryTest

import unusual.model._
import unusual.pages._
import unusual.tests._
import unusual.tests.enterContestTest.EnterContestTestCommon

class ViewContestTestCommon(state: ViewContestState) extends SharedTest {

  val viewContestState = state

  def isCorrectLineup:Unit = {
    val page = goToViewContest(viewContestState)
    val playerLineup = state.contest.expendAllMoney.soccerPlayerList

    for(i <- 0 to 10) {
      val player = page.getSoccerPlayer(i)
      assert(player == playerLineup(i), s"SoccerPlayer #$i does not match {page = $player, state = ${page.getSoccerPlayer(i)}" )
    }
  }

  def checkHimselfAtPlayersList:Unit = {
    
  }

  def checkPlayersList:Unit = {

  }

}
