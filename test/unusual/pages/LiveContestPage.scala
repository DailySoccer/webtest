package unusual.pages

import unusual.model.Resolution
import unusual.model.pageStates.LiveContestState

/**
 * Created by gregorioiniestaovejero on 6/4/15.
 */
class LiveContestPage(res:Resolution) extends SharedPage(res) {

  override val url = ".*/#/live_contest/my_contests/.*"
  val MAIN_CONTAINER = "#mainContent view-contest"

  val HEADER = MAIN_CONTAINER + " contest-header"

  val TEAMS_PANEL = MAIN_CONTAINER + " teams-panel"

  val CONTEST_CONTENT = MAIN_CONTAINER + " #viewContestRoot"

  val USER_TEAM = CONTEST_CONTENT + " #userFantasyTeam"
  val USER_SOCCER_LIST = USER_TEAM + " .fantasy-team-list"
  def USER_SOCCER_SLOT(idx:Int) = USER_SOCCER_LIST + s" .fantasy-team-slot:nth-child($idx)"
  def USER_SOCCER_SCORE(idx:Int) = USER_SOCCER_SLOT(idx) + " .column-score span"

  val SHOW_MATCHES_BTT = MAIN_CONTAINER + " #teamsToggler"
  val MATCHES_PANEL = MAIN_CONTAINER + " #teamsPanel"
  def MATCH_CONTAINER(idx:Int) = MATCHES_PANEL + s" .teams-box:nth-child($idx)"
  def MATCH_PERIOD(idx:Int) = MATCH_CONTAINER(idx) + s" .period"

  val REMAINING_TIME = USER_TEAM + " .total-remaining-matches-time"
  val TOTAL_SCORE = USER_TEAM + " .score-box .content"

  val USERS_LIST = CONTEST_CONTENT + " #usersList"
  val OPPONENT_FANTASY_TEAM = CONTEST_CONTENT + " #opponentFantasyTeam"

  override def isAt = {
    var _isAt = true

    currentUrl.matches(url)

    _isAt = _isAt && isElemDisplayed(HEADER)
    logger.debug("Header should be displayed.", _isAt)

    _isAt = _isAt && isElemDisplayed(TEAMS_PANEL)
    logger.debug("Teams panel should be displayed.", _isAt)

    _isAt = _isAt && isElemDisplayed(CONTEST_CONTENT)
    logger.debug("Contest content should be displayed.", _isAt)

    _isAt = _isAt && isElemDisplayed(USER_TEAM)
    logger.debug("User Team should be displayed.", _isAt)

    val userListIsDisplayed = isElemDisplayed(USERS_LIST)
    val opponentFantasyTeamIsDisplayed = isElemDisplayed(OPPONENT_FANTASY_TEAM)
    _isAt = _isAt && (userListIsDisplayed || opponentFantasyTeamIsDisplayed)
    logger.debug(s"User list or opponent team should be displayed. [userList: $userListIsDisplayed, oppTeam:$opponentFantasyTeamIsDisplayed]", _isAt)

    _isAt
  }

  def getSoccerPlayerPunctuation(idx:Int):String = {
    find(cssSelector(USER_SOCCER_SCORE(idx))).get.text
  }

  def getRemainingTime:String = {
    find(cssSelector(REMAINING_TIME)).get.text.split(": ")(1)
  }

  def getTotalScore:String = {
    find(cssSelector(TOTAL_SCORE)).get.text
  }

}
