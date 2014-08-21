package unusual.pages

import java.util

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
    ALL -> 2,
    GOAL_KEEPER -> 3,
    DEFENSE -> 4,
    MIDDLE -> 5,
    FORWARD -> 6
  )

  val FILTER_POSITION_CLASS = ".filter-by-position"
  val DESKTOP_VERSION_ID = "enterContestDesktop"
  val NON_DESKTOP_VERSION_ID = "enterContestNotDesktop"

  val FILTER_MATCH_DESKTOP_ID = "matchTeamsFilterContainer"
  val FILTER_MATCH_MOBILE_ID = "match-fliter"

  val LINEUP_SELECTOR_ID = "lineupSelector"



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
    val cssSelNonMobile = "#" + FILTER_MATCH_DESKTOP_ID + "  button:nth-child(" + matchOrder + ")"

    println(cssSelNonMobile)
    eventually {
      val bttNonMobile = find(cssSelector(cssSelNonMobile)).get

      if (bttNonMobile.isDisplayed) {
        click on bttNonMobile
      } else {
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
      }
    }


    this
  }

  def setSoccerPlayerPositionFilter(pos : String) = {
    val cssSelDesktop = "#" + DESKTOP_VERSION_ID + " " + FILTER_POSITION_CLASS + " button:nth-child(" + SOCCER_PLAYER_POSITION_FILTER_TAB(pos) + ")"
    val cssSelNonDesktop = "#" + NON_DESKTOP_VERSION_ID + " " + FILTER_POSITION_CLASS + " button:nth-child(" + SOCCER_PLAYER_POSITION_FILTER_TAB(pos) + ")"

    eventually {
      val bttDesktop = find(cssSelector(cssSelDesktop)).get
      val bttNonDesktop = find(cssSelector(cssSelNonDesktop)).get

      if (bttDesktop.isDisplayed) {
        click on bttDesktop
      } else if (bttNonDesktop.isDisplayed) {
        click on bttNonDesktop
      } else {
        println("Version movil")
      }
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

    val cssSelDesktop = "#" + DESKTOP_VERSION_ID + " #" + LINEUP_SELECTOR_ID + " .lineup-selector-slot:nth-child(" + order + ")"
    val cssSelNonDesktop = "#" + NON_DESKTOP_VERSION_ID + " #" + LINEUP_SELECTOR_ID + " .lineup-selector-slot:nth-child(" + order + ")"

    println(cssSelDesktop)

    println(cssSelNonDesktop)
    eventually {
      val bttDesktop = find(cssSelector(cssSelDesktop)).get
      val bttNonDesktop = find(cssSelector(cssSelNonDesktop)).get

      if (bttDesktop.isDisplayed) {
        click on bttDesktop
      } else {
        assert(bttNonDesktop.isDisplayed)
        click on bttNonDesktop
      }
    }

    this
  }

}
