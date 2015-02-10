package unusual.pages

import unusual.model._
import unusual.model.pageStates.{ViewContestState, EnterContestState}
import unusual.pages.components.{FooterBar, MenuBar}

class ViewContestPage(res: Resolution, state: ViewContestState) extends SharedPage(res) {

  val CONTEST_HEADER:String = "contest-header"

  val TEAMS_TOGGLER:String = "#teamsToggler"
  val CONTEST_TEAMS_PANEL:String = "#teamsPanelRoot"
  val CONTEST_TEAMS_PANEL_BOX:String = CONTEST_TEAMS_PANEL + " .teams-box"

  val CONTEST_NAME:String = CONTEST_HEADER + " .contest-name"
  val CONTEST_DESCRIPTION:String = CONTEST_HEADER + " .contest-explanation"
  val CONTEST_ENTRY:String = CONTEST_HEADER + " .contest-price span"
  val CONTEST_PRIZE:String = CONTEST_HEADER + " .contest-prize span"
  //val CONTEST_NAME:String = CONTEST_HEADER + " .contest-name"

  val SOCCER_PLAYER_LINEUP_WRAPPER:String = ".fantasy-team-wrapper"
  val SOCCER_PLAYER_LINEUP_HEADER:String = ".fantasy-team-header"
  val SOCCER_PLAYER_LINEUP_LIST:String = ".fantasy-team-list"

  val SOCCER_PLAYER_LINEUP_SLOT:String = SOCCER_PLAYER_LINEUP_LIST + " .fantasy-team-slot"
  private def SOCCER_PLAYER_LINEUP_SLOT(idx:Int):String            = SOCCER_PLAYER_LINEUP_SLOT + s":nth-child($idx)"
  private def SOCCER_PLAYER_LINEUP_SLOT_NAME(idx:Int):String       = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .soccer-player-name"
  private def SOCCER_PLAYER_LINEUP_SLOT_SALARY(idx:Int):String     = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-salary"
  private def SOCCER_PLAYER_LINEUP_SLOT_POSITION(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-fieldpos"

  val USER_PLAYER_LIST_WRAPPER:String = "#usersListRoot"
  val USER_PLAYER_LIST_HEADER:String = ".users-header-next"
  val USER_PLAYER_LIST_TABLE_HEADER:String = ".users-table-header"
  val USER_PLAYER_LIST_TABLE_ROWS:String = ".users-table-rows"

  val USER_PLAYER_SLOT:String = ".users-table-rows .user-row"
  private def USER_PLAYER_SLOT(idx:Int):String       = USER_PLAYER_SLOT + s":nth-child($idx)"
  private def USER_PLAYER_SLOT_NAME(idx:Int):String  = USER_PLAYER_SLOT(idx) + " .name"
  private def USER_PLAYER_SLOT_SCORE(idx:Int):String = USER_PLAYER_SLOT(idx) + " .score"

  val OTHER_CONTESTS_BUTTON:String = "#viewContestEntry .view-contest-entry-actions-wrapper .ok-button"
  val CANCEL_CONTEST_ENTRY_BUTTON:String = "#viewContestEntry .view-contest-entry-actions-wrapper .cancel-button"
  val EDIT_TEAM_BUTTON:String = "fantasy-team .edit-team button.btn-edit-team"

  val OK_BUTTON_ON_MODAL_ALERT:String = "#modalRoot .panel-body .autocentered-buttons-wrapper .ok-button"

  private def TABS(order:Int):String = s"#viewContestEntryTab li:nth-child($order) a"
  val LINEUP_TAB:String = TABS(1)
  val USERS_TAB:String = TABS(2)


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
    enterContest.open.pickWholeLineup(state.contest.affordableLineup)
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

  def getUserName(idx:Int):String = {
    find(cssSelector(USER_PLAYER_SLOT_NAME(idx))).get.text
  }

  def getContestName:String = {
    find(cssSelector(CONTEST_NAME)).get.text
  }
  def getContestDescription:String = {
    find(cssSelector(CONTEST_DESCRIPTION)).get.text
  }
  def getContestEntry:String = {
    find(cssSelector(CONTEST_ENTRY)).get.text
  }
  def getContestPrize:String = {
    find(cssSelector(CONTEST_PRIZE)).get.text
  }

  def getNumMatches:Int = {
    if (resolution == Resolution.SMALL) {
      eventually { assert( find(cssSelector(CONTEST_TEAMS_PANEL + " .collapsing")) == None ) }

      if ( find(cssSelector(CONTEST_TEAMS_PANEL + " .in")) == None ) { toggleTeams }

      eventually { assert( find(cssSelector(CONTEST_TEAMS_PANEL + " .collapsing")) == None ) }
    }

    findAll(cssSelector(CONTEST_TEAMS_PANEL_BOX)).length
  }

  def changeToLineUpTab = {
    if (resolution == Resolution.SMALL) { click on find(cssSelector(LINEUP_TAB)).get }
    this
  }

  def changeToUsersTab = {
    if (resolution == Resolution.SMALL) { click on find(cssSelector(USERS_TAB)).get }
    this
  }

  def goEditTeam = {
    click on find(cssSelector(EDIT_TEAM_BUTTON)).get
    this
  }

  def goOtherContests = {
    click on find(cssSelector(OTHER_CONTESTS_BUTTON)).get
    this
  }
  def cancelContestEntry = {
    click on find(cssSelector(CANCEL_CONTEST_ENTRY_BUTTON)).get
    //Ahora hay que confirmar que queremos salir
    click on find(cssSelector(OK_BUTTON_ON_MODAL_ALERT)).get
    this
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

  private def toggleTeams = {
    click on find(cssSelector(TEAMS_TOGGLER)).get
    this
  }

}
