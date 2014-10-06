package unusual.pages

import unusual.model._
import unusual.pages.components.{FooterBar, MenuBar}

class ViewContestPage(res: Resolution, state: ViewContestState) extends SharedPage(res) {

  val SOCCER_PLAYER_LINEUP_SLOT:String = ".fantasy-team-list .fantasy-team-slot"
  def SOCCER_PLAYER_LINEUP_SLOT(idx:Int):String            = SOCCER_PLAYER_LINEUP_SLOT + s":nth-child($idx)"
  def SOCCER_PLAYER_LINEUP_SLOT_NAME(idx:Int):String       = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .soccer-player-name"
  def SOCCER_PLAYER_LINEUP_SLOT_SALARY(idx:Int):String     = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-salary"
  def SOCCER_PLAYER_LINEUP_SLOT_POSITION(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-fieldpos"


  val USER_PLAYER_SLOT:String = ".users-table-rows .user-row"
  def USER_PLAYER_SLOT(idx:Int):String       = USER_PLAYER_SLOT + s":nth-child($idx)"
  def USER_PLAYER_SLOT_NAME(idx:Int):String  = USER_PLAYER_SLOT(idx) + " .name"
  def USER_PLAYER_SLOT_SCORE(idx:Int):String = USER_PLAYER_SLOT(idx) + " .score"


  override def isAt = {
    var _isAt = true

    _isAt
  }

  override def open = {
    val enterContestState = new EnterContestState
    enterContestState.contest = state.contest
    val enterContest = new EnterContestPage(resolution, enterContestState)
    enterContest.open.pickWholeLineup(state.contest.expendAllMoney)
    this
  }

  def getSoccerPlayer(index:Integer) : SoccerPlayer = {
    val player = new SoccerPlayer("", "", 0)

    eventually {
      player.name = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_NAME(index))).get.text
      player.position = getSoccerPlayerPositionFromList(index)
      val salary = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_SALARY(index))).get.text
      player.salary = Integer.parseInt(salary.substring(0, salary.length - 1))
    }

    player
  }

  def isUserPlayerAtList(user:User) : Boolean = {
    var isUserAt = false

    //for()
    true
  }



  private def getSoccerPlayerPositionFromList(index: Int) = {
    var pos:String = ""
    find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT(index))).get.attribute("class").get.split(" ").map((cssClass:String) => {
      cssClass match {
        case "posPOR" => pos = SoccerPlayer.POS_GOAL_KEEPER
        case "posDEF" => pos = SoccerPlayer.POS_DEFENSE
        case "posMED" => pos = SoccerPlayer.POS_MIDDLE
        case "posDEL" => pos = SoccerPlayer.POS_FORWARD
        case _ =>
      }
    })

    pos
  }

}
