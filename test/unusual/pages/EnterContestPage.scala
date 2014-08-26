package unusual.pages

import unusual.model.Resolution

class EnterContestPage(res: Resolution) extends SharedPage {
  val TITLE   = "Daily Soccer"

  val resolution:Resolution = res

  val url = SharedPage.baseUrl

  val ALL = "TODOS"
  val GOAL_KEEPER = "POR"
  val DEFENSE = "DEF"
  val MIDDLE = "MID"
  val FORWARD = "DEL"
  val SOCCER_PLAYER_POSITION_FILTER_TAB = Map(
    ALL -> 1,
    GOAL_KEEPER -> 2,
    DEFENSE -> 3,
    MIDDLE -> 4,
    FORWARD -> 5
  )

  val FILTER_POSITION_CLASS = ".filter-by-position"
  val DESKTOP_VERSION_ID = "enterContestDesktop"
  val NON_DESKTOP_VERSION_ID = "enterContestNotDesktop"

  val FILTER_MATCH_DESKTOP_ID = "matchTeamsFilterContainer"
  val FILTER_MATCH_MOBILE_ID = "match-fliter"

  val LINEUP_SELECTOR_ID = "lineup-selector"



  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    new MenuBar(resolution).isAt
    new FooterBar(resolution).isAt

    this
  }

  /**
   *
   * @param matchOrder 1 es todos los partidos
   * @return
   */
  def setSoccerPlayerMatchFilter(matchOrder : Int) = {

    if (resolution == Resolution.SMALL) {
      /*
      val cssSelMobile = "#" + FILTER_MATCH_MOBILE_ID //+ "  option:nth-child(" + matchOrder + ")"
      val cssSelMobileOpt = "#" + FILTER_MATCH_MOBILE_ID + "  option:nth-child(" + matchOrder + ")"
      val bttMobile = find(cssSelector(cssSelMobile)).get
      val bttMobileOpt = find(cssSelector(cssSelMobileOpt)).get

      click on bttMobile

      eventually {
        click on bttMobileOpt
      }
      */
      unavailableFunctionOnResolution("setSoccerPlayerMatchFilter()")
    } else {
      val cssSel = "#" + FILTER_MATCH_DESKTOP_ID + "  button:nth-child(" + matchOrder + ")"
      val bttNonMobile = find(cssSelector(cssSel)).get
      click on bttNonMobile
    }

    this
  }

  def setSoccerPlayerPositionFilter(pos : String) = {

    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("setSoccerPlayerPositionFilter()")
    } else if (resolution == Resolution.MEDIUM) {
      val cssSel = "#" + DESKTOP_VERSION_ID + " " + FILTER_POSITION_CLASS + " button:nth-of-type(" + SOCCER_PLAYER_POSITION_FILTER_TAB(pos) + ")"
      eventually { click on find(cssSelector(cssSel)).get }
    } else {
      val cssSel = "#" + DESKTOP_VERSION_ID + " " + FILTER_POSITION_CLASS + " button:nth-of-type(" + SOCCER_PLAYER_POSITION_FILTER_TAB(pos) + ")"
      eventually { click on find(cssSelector(cssSel)).get }
    }

    this
  }

  def selectGoalKeeperOnMyTeam = {
    selectSoccerPlayerOnMyTeam(1)
    this
  }

  def selectDefenseOnMyTeam(order: Int) = {
    selectSoccerPlayerOnMyTeam(1 + order)
    this
  }

  def selectMiddleOnMyTeam(order: Int) = {
    selectSoccerPlayerOnMyTeam(5 + order)

    this
  }

  def selectForwardOnMyTeam(order: Int) = {
    selectSoccerPlayerOnMyTeam(9 + order)
    this
  }






  private def selectSoccerPlayerOnMyTeam(order: Int) ={
    var cssSel = ""
    if (resolution != Resolution.BIG) {
      cssSel = "#" + NON_DESKTOP_VERSION_ID + " " + LINEUP_SELECTOR_ID + " .lineup-selector-slot:nth-child(" + order + ")"
    } else {
      cssSel = "#" + DESKTOP_VERSION_ID + " " + LINEUP_SELECTOR_ID + " .lineup-selector-slot:nth-child(" + order + ")"
    }

    eventually { click on find(cssSelector(cssSel)).get }
    this
  }



}
