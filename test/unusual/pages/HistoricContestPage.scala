package unusual.pages

import unusual.model.Resolution

/**
 * Created by gregorioiniestaovejero on 6/4/15.
 */
class HistoricContestPage(res:Resolution) extends SharedPage(res) {

  override val url = ".*/#/history_contest/my_contests/.*"

  val MAIN_CONTAINER = "#mainContent view-contest"

  val HEADER = MAIN_CONTAINER + " contest-header"

  val TEAMS_PANEL = MAIN_CONTAINER + " teams-panel"

  val CONTEST_CONTENT = MAIN_CONTAINER + " #viewContestRoot"
  val USER_TEAM = CONTEST_CONTENT + " #userFantasyTeam"
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


}
