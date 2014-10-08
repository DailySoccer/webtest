package unusual.pages

import unusual.model._
import unusual.pages.components.{FooterBar, MenuBar}

class ViewContestPage(res: Resolution, state: ViewContestState) extends SharedPage(res) {


  val SOCCER_PLAYER_LINEUP_WRAPPER:String = ".fantasy-team-wrapper"
  val SOCCER_PLAYER_LINEUP_HEADER:String = ".fantasy-team-header"
  val SOCCER_PLAYER_LINEUP_LIST:String = ".fantasy-team-list"

  val SOCCER_PLAYER_LINEUP_SLOT:String = SOCCER_PLAYER_LINEUP_LIST + " .fantasy-team-slot"
  def SOCCER_PLAYER_LINEUP_SLOT(idx:Int):String            = SOCCER_PLAYER_LINEUP_SLOT + s":nth-child($idx)"
  def SOCCER_PLAYER_LINEUP_SLOT_NAME(idx:Int):String       = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .soccer-player-name"
  def SOCCER_PLAYER_LINEUP_SLOT_SALARY(idx:Int):String     = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-salary"
  def SOCCER_PLAYER_LINEUP_SLOT_POSITION(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-fieldpos"

  val USER_PLAYER_LIST_WRAPPER:String = "#usersListRoot"
  val USER_PLAYER_LIST_HEADER:String = ".users-header-next"
  val USER_PLAYER_LIST_TABLE_HEADER:String = ".users-table-header"
  val USER_PLAYER_LIST_TABLE_ROWS:String = ".users-table-rows"

  val USER_PLAYER_SLOT:String = ".users-table-rows .user-row"
  def USER_PLAYER_SLOT(idx:Int):String       = USER_PLAYER_SLOT + s":nth-child($idx)"
  def USER_PLAYER_SLOT_NAME(idx:Int):String  = USER_PLAYER_SLOT(idx) + " .name"
  def USER_PLAYER_SLOT_SCORE(idx:Int):String = USER_PLAYER_SLOT(idx) + " .score"


  override def isAt = {
    var _isAt = true

    _isAt = new MenuBar(resolution).isAt
    logger.debug("Menu bar: ", _isAt)
    _isAt = _isAt && new FooterBar(resolution).isAt
    logger.debug("Footer bar: ", _isAt)

    _isAt = _isAt && pageTitle == TITLE
    logger.debug("Title is " + pageTitle + ", should be " + TITLE, _isAt)

    _isAt = _isAt && isElemDisplayed(SOCCER_PLAYER_LINEUP_WRAPPER)
    _isAt = _isAt && isElemDisplayed(SOCCER_PLAYER_LINEUP_HEADER)
    _isAt = _isAt && isElemDisplayed(SOCCER_PLAYER_LINEUP_LIST)
    if (resolution != Resolution.SMALL) {
      _isAt = _isAt && isElemDisplayed(USER_PLAYER_LIST_WRAPPER)
      _isAt = _isAt && isElemDisplayed(USER_PLAYER_LIST_HEADER)
      _isAt = _isAt && isElemDisplayed(USER_PLAYER_LIST_TABLE_HEADER)
      _isAt = _isAt && isElemDisplayed(USER_PLAYER_LIST_TABLE_ROWS)
    }


    _isAt
  }

  override def open = {
    val enterContestState = new EnterContestState
    enterContestState.contest = state.contest
    val enterContest = new EnterContestPage(resolution, enterContestState)
    enterContest.open.pickWholeLineup(state.contest.expendAllMoney)
    enterContest.confirmLineup
    this
  }

  def getSoccerPlayer(index:Integer) : SoccerPlayer = {
    val player = new SoccerPlayer("", "", 0)

    eventually {
      logger.debug(s"trying to get player name $index ${SOCCER_PLAYER_LINEUP_SLOT_NAME(index)}")
      player.name = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_NAME(index))).get.text
      logger.debug("trying to get player position")
      player.position = getSoccerPlayerPositionFromList(index)
      logger.debug("trying to get player salary")
      val salary = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_SALARY(index))).get.text
      player.salary = Integer.parseInt(salary.substring(0, salary.length - 1))
    }

    logger.debug(player.position)
    logger.debug(player.toString)

    player
  }

  def isUserPlayerAtList(user:User) : Boolean = {
    var isUserAt = false

    //for()
    true
  }



  private def getSoccerPlayerPositionFromList(index: Int) = {
    var pos:String = ""
    val posTxt:String = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_POSITION(index))).get.text
    logger.debug(posTxt)
    posTxt match {
        case "POR" => pos = SoccerPlayer.POS_GOAL_KEEPER
        case "DEF" => pos = SoccerPlayer.POS_DEFENSE
        case "MED" => pos = SoccerPlayer.POS_MIDDLE
        case "DEL" => pos = SoccerPlayer.POS_FORWARD
        case _ =>
      }

    logger.debug(pos)
    pos
  }

}
