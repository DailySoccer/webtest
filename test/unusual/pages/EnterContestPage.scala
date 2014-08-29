package unusual.pages

import unusual.model.{SoccerPlayer, Resolution}

class EnterContestPage(res: Resolution) extends SharedPage {
  val TITLE   = "Daily Soccer"

  val resolution:Resolution = res

  val url = SharedPage.baseUrl

  val SOCCER_PLAYER_POSITION_FILTER_TAB = Map(
    SoccerPlayer.POS_ALL -> 1,
    SoccerPlayer.POS_GOAL_KEEPER -> 2,
    SoccerPlayer.POS_DEFENSE -> 3,
    SoccerPlayer.POS_MIDDLE -> 4,
    SoccerPlayer.POS_FORWARD -> 5
  )

  val FILTER_POSITION_CLASS = ".filter-by-position"
  val DESKTOP_VERSION_ID = "enterContestDesktop"
  val NON_DESKTOP_VERSION_ID = "enterContestNotDesktop"
  val VERSION = if (resolution != Resolution.BIG) NON_DESKTOP_VERSION_ID else DESKTOP_VERSION_ID


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

      val cssSelCombo = "#" + FILTER_MATCH_MOBILE_ID
      singleSel(FILTER_MATCH_MOBILE_ID).value = "" + matchOrder
      //eventually { click on matchComboOpt }
      //unavailableFunctionOnResolution("setSoccerPlayerMatchFilter")
    } else {
      val cssSel = "#" + FILTER_MATCH_DESKTOP_ID + "  button:nth-child(" + matchOrder + ")"
      val bttNonMobile = find(cssSelector(cssSel)).get
      click on bttNonMobile
    }

    this
  }

  def setSoccerPlayerPositionFilter(pos : String) = {

    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("setSoccerPlayerPositionFilter")
    } else if (resolution == Resolution.MEDIUM) {
      val cssSel = FILTER_POSITION_CLASS + " button:nth-of-type(" + SOCCER_PLAYER_POSITION_FILTER_TAB(pos) + ")"
      eventually { click on find(cssSelector(cssSel)).get }
    } else {
      val cssSel = FILTER_POSITION_CLASS + " button:nth-of-type(" + SOCCER_PLAYER_POSITION_FILTER_TAB(pos) + ")"
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

  def getNumberOfSoccerPlayers:Int = {
    var n = 0
    eventually (timeout(2 seconds)) {
      n = fastCountByCssSelector(".soccer-players-list .soccer-players-list-slot")
    }
    n
  }

  def getLineUpSalary:Int = {
    var cssSel = ""
    if (resolution == Resolution.SMALL) {
      cssSel = ".enter-contest-actions-wrapper .total-salary-money"
    } else {
      cssSel = ".enter-contest-lineup-wrapper .total-salary-money"
    }
    var salary = find(cssSelector(cssSel)).get.text
    salary = salary.substring(0, salary.length() -1)
    Integer.parseInt(salary)
  }

  def cancelSoccerPlayerSelection = {
    if(resolution == Resolution.SMALL) {
      eventually { click on find(id("cancelSoccerPlayerSelection")).get }
    }
    this
  }

  def getSoccerPlayerFromList(index: Int) : SoccerPlayer= {
    val cssSelRow = ".soccer-players-list .soccer-players-list-slot:nth-child(" + index + ")"
    var player = new SoccerPlayer("", "", 0)

    eventually {
      player.name = find(cssSelector(cssSelRow + " .soccer-player-name")).get.text
      player.position = getSoccerPlayerPositionFromList(index)
      val salary = find(cssSelector(cssSelRow + " .column-salary")).get.text
      player.salary = Integer.parseInt(salary.substring(0, salary.length - 1))
    }

    player
  }

  def getSoccerPlayerFromLineUp(index: Int) : SoccerPlayer= {
    var player:SoccerPlayer = null
    val cssSelRow = ".enter-contest-lineup .lineup-selector-slot:nth-child(" + index + ")"

    if (find(cssSelector(cssSelRow + " .column-empty-slot")) == None) {
      player = createPlayerFromLineUp(index)
    } else {
      player = SoccerPlayer.EmptyPlayer(find(cssSelector(cssSelRow + " .column-fieldpos")).get.text)
    }

    player
  }

  def addSoccerPlayerFromList(index:Int) = {
    val cssSel = ".soccer-players-list .soccer-players-list-slot:nth-child(" + index + ") .column-add button"
    //var player = new SoccerPlayer("", "", 0)

    eventually { click on find(cssSelector(cssSel)).get }

    this
  }

  def manyClicksOnAddSoccer(index:Int) = {
    val cssSel = ".soccer-players-list .soccer-players-list-slot:nth-child(" + index + ") .column-add button"
    fastClicksByCssSelector(4, cssSel)
  }

  def setSoccerPlayerNameFilterSearch(name:String) = {
    val cssSel = ".name-player-input-filter"
    textField(cssSelector(cssSel)).value = name
    this
  }

  def clearLineupList = {
    val cssSel = ".btn-clean-lineup-list"
    click on find(cssSelector(cssSel)).get

    this
  }

  def removeSoccerPlayerFromLineUp(index: Int) = {
    var player:SoccerPlayer = null
    val cssSelRow = ".enter-contest-lineup .lineup-selector-slot:nth-child(" + index + ")"
    click on find(cssSelector(cssSelRow + " .column-action a")).get
    this
  }

  def isOverSalaryErrorShown:Boolean = {
    var cssSel = LINEUP_SELECTOR_ID + " .alert"
    find(cssSelector(cssSel)).get.isDisplayed
  }

  def confirmLineup ={
    var cssSel = ".btn-confirm-lineup-list"
    click on find(cssSelector(cssSel)).get
    this
  }

  private def selectSoccerPlayerOnMyTeam(order: Int) ={
    var cssSel = LINEUP_SELECTOR_ID + " .lineup-selector-slot:nth-child(" + order + ")"
    eventually { click on find(cssSelector(cssSel)).get }
    this
  }

  /**
   * Aunque el usuario no ve una clase, si ve el color de fondo y
   * lo relaciona con su posicion en el campo de juego.
   * Utilizo la clase para relacionarla con la posicion.
   * @param index indice del player
   * @return
   */
  private def getSoccerPlayerPositionFromList(index: Int) = {
    val cssSelElem = ".soccer-players-list .soccer-players-list-slot:nth-child(" + index + ")"
    var pos:String = ""
    find(cssSelector(cssSelElem)).get.attribute("class").get.split(" ").map((cssClass:String) => {
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

  private def createPlayerFromLineUp(index: Int):SoccerPlayer = {
    val player = new SoccerPlayer("", "", 0)
    val cssSelRow = ".enter-contest-lineup .lineup-selector-slot:nth-child(" + index + ")"

    eventually {
      player.name = find(cssSelector(cssSelRow + " .soccer-player-name")).get.text
      player.position = find(cssSelector(cssSelRow + " .column-fieldpos")).get.text
      val salary = find(cssSelector(cssSelRow + " .column-salary")).get.text
      player.salary = Integer.parseInt(salary.substring(0, salary.length - 1))
    }
    player
  }

}

/*
object EnterContestPage {
  val ALL = "TODOS"
  val GOAL_KEEPER = "POR"
  val DEFENSE = "DEF"
  val MIDDLE = "MED"
  val FORWARD = "DEL"
}
*/


