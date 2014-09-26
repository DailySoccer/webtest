package unusual.pages

import unusual.model.{Contest, SoccerPlayer, Resolution}
import unusual.pages.components.{FooterBar, MenuBar}

class EnterContestPage(res: Resolution, contest: Contest) extends SharedPage(res) {

  //override val url = SharedPage.baseUrl + "/#/lobby/enter_contest/"

  val SOCCER_PLAYER_POSITION_FILTER_TAB = Map(
    SoccerPlayer.POS_ALL -> 1,
    SoccerPlayer.POS_GOAL_KEEPER -> 2,
    SoccerPlayer.POS_DEFENSE -> 3,
    SoccerPlayer.POS_MIDDLE -> 4,
    SoccerPlayer.POS_FORWARD -> 5
  )

  val ACTIVE_ELEMENT = ".active"

  def FILTER_MATCH_DESKTOP(idx:Int) = "#matchTeamsFilterContainer button:nth-child(" + idx + ")"
  val FILTER_MATCH_MOBILE = "#match-filter"

  val FILTER_SOCCER_PLAYER_NAME = ".name-player-input-filter"

  def FILTER_POSITION_DESKTOP(idx:Int) = "button.button-filtro-position:nth-of-type(" + idx + ")"

  val LINEUP_SALARY_DESKTOP = ".enter-contest-lineup-wrapper .total-salary-money"
  val LINEUP_SALARY_MOBILE = ".enter-contest-actions-wrapper .total-salary-money"
  val LINEUP_SALARY = if (resolution == Resolution.SMALL) LINEUP_SALARY_MOBILE else LINEUP_SALARY_DESKTOP

  val BUTTON_CANCEL_SOCCER_PLAYER_SELECTION_MOBILE = "#cancelSoccerPlayerSelection"

  val BUTTON_CLEAN_LINEUP = ".btn-clean-lineup-list"
  val BUTTON_CONFIRM_LINEUP = ".btn-confirm-lineup-list"
  val BUTTON_CLOSE_CONTEST = ".close-contest button"

  val LINEUP_SELECTOR = "lineup-selector"
  val LINEUP_SELECTOR_ALERT = "lineup-selector .alert"

  val ENTER_CONTEST_TABS = ".enter-contest-tabs"
  def ENTER_CONTEST_TAB(idx:Int) = ENTER_CONTEST_TABS + " li:nth-of-type(" + idx + ")"
  def ENTER_CONTEST_TAB_LINK(idx:Int) = ENTER_CONTEST_TAB(idx) + " a"

  val SOCCER_PLAYER_LIST_SLOT:String = ".soccer-players-list .soccer-players-list-slot"
  def SOCCER_PLAYER_LIST_SLOT(idx:Int):String            = SOCCER_PLAYER_LIST_SLOT + ":nth-child(" + idx + ")"
  def SOCCER_PLAYER_LIST_SLOT_NAME(idx:Int):String       = SOCCER_PLAYER_LIST_SLOT(idx) + " .soccer-player-name"
  def SOCCER_PLAYER_LIST_SLOT_SALARY(idx:Int):String     = SOCCER_PLAYER_LIST_SLOT(idx) + " .column-salary"
  def SOCCER_PLAYER_LIST_SLOT_ADD_BUTTON(idx:Int):String = SOCCER_PLAYER_LIST_SLOT(idx) + " .column-add button"
  //def SOCCER_PLAYER_LIST_SLOT_ADD_BUTTON(idx:Int):String = SOCCER_PLAYER_LIST_SLOT(idx) + " .column-add button"

  val SOCCER_PLAYER_LINEUP_SLOT:String = ".enter-contest-lineup .lineup-selector-slot"
  def SOCCER_PLAYER_LINEUP_SLOT(idx:Int):String          = SOCCER_PLAYER_LINEUP_SLOT + ":nth-child(" + idx + ")"
  def SOCCER_PLAYER_LINEUP_SLOT_EMPTY(idx:Int):String    = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-empty-slot"
  def SOCCER_PLAYER_LINEUP_SLOT_POSITION(idx:Int):String = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-fieldpos"
  def SOCCER_PLAYER_LINEUP_SLOT_NAME(idx:Int):String     = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .soccer-player-name"
  def SOCCER_PLAYER_LINEUP_SLOT_SALARY(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-salary"
  def SOCCER_PLAYER_LINEUP_SLOT_REMOVE(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-action a"


  override def isAt = {
    var _isAt = true
    logger.debug("Contest id: " + contest.nameOrder + ". " + contest.name)
    _isAt = _isAt && pageTitle == TITLE

    _isAt = _isAt && new MenuBar(resolution).isAt
    logger.debug("Menu Bar ", _isAt)
    _isAt = _isAt && new FooterBar(resolution).isAt
    logger.debug("Footer Bar ", _isAt)

    _isAt = _isAt && isElemDisplayed(BUTTON_CLEAN_LINEUP)
    _isAt = _isAt && isElemDisplayed(BUTTON_CONFIRM_LINEUP)

    _isAt = _isAt && isElemDisplayed(BUTTON_CLOSE_CONTEST)

    if (resolution != Resolution.SMALL) {
      _isAt = _isAt && isElemDisplayed(FILTER_POSITION_DESKTOP(1))
      _isAt = _isAt && isElemDisplayed(FILTER_POSITION_DESKTOP(2))
      _isAt = _isAt && isElemDisplayed(FILTER_POSITION_DESKTOP(3))
      _isAt = _isAt && isElemDisplayed(FILTER_POSITION_DESKTOP(4))
      _isAt = _isAt && isElemDisplayed(FILTER_POSITION_DESKTOP(5))


      _isAt = _isAt && isElemDisplayed(FILTER_MATCH_DESKTOP(1))
      _isAt = _isAt && isElemDisplayed(FILTER_MATCH_DESKTOP(2))
    }

    _isAt = _isAt && getNumberOfSoccerPlayers > 0

    _isAt
  }

  override def open = {
    val lobby = new LobbyPage(resolution, 6)
    lobby.open
    lobby.clickOnMenuAllContests.clickOnMenuFreeContests.clearFilters
    lobby.playContestNumber(contest.nameOrder)
    this
  }


  def isDefaultState(totalPlayers:Int, initialSalary: Int): Boolean = {
    var versionCheck = true

    if (resolution == Resolution.SMALL) {
      versionCheck = find(cssSelector(ENTER_CONTEST_TAB(1) + ACTIVE_ELEMENT)) != None
    } else if (resolution == Resolution.MEDIUM) {
      versionCheck = getNumberOfSoccerPlayers == totalPlayers &&
                        isOrderedByPos &&
                        find(cssSelector(FILTER_POSITION_DESKTOP(1) + ACTIVE_ELEMENT)) != None &&
                        find(cssSelector(FILTER_MATCH_DESKTOP(1)+ ACTIVE_ELEMENT)) != None &&
                        find(cssSelector(ENTER_CONTEST_TAB(1) + ACTIVE_ELEMENT)) != None
    } else {
      val numSoccerPlayers = getNumberOfSoccerPlayers
      versionCheck = numSoccerPlayers == totalPlayers
      logger.debug("Soccer players are " + numSoccerPlayers + " should be: " + totalPlayers, versionCheck)
      versionCheck = versionCheck && isOrderedByPos
      logger.debug("Is ordered by pos ", versionCheck)
      versionCheck = versionCheck && find(cssSelector(FILTER_POSITION_DESKTOP(1) + ACTIVE_ELEMENT)) != None
      logger.debug("Position filter should be ''All'' ", versionCheck)
      versionCheck = versionCheck && find(cssSelector(FILTER_MATCH_DESKTOP(1)+ ACTIVE_ELEMENT)) != None
      logger.debug("Match filter should be ''All'' ", versionCheck)
    }



    versionCheck = versionCheck && isLineupEmpty
    logger.debug("Line up should be empty", versionCheck)
    versionCheck = versionCheck && isCleanLineupDisabled
    logger.debug("Clean line up should be empty", versionCheck)
    versionCheck = versionCheck && isConfirmLineupDisabled
    logger.debug("Confirm line up should be disabled", versionCheck)
    val salary = getLineUpSalary
    versionCheck = versionCheck && salary == initialSalary
    logger.debug("Line up salary is " + salary + " should be " + initialSalary, versionCheck)

    versionCheck
  }

  def clickOnCloseButton = {
    click on find(cssSelector(BUTTON_CLOSE_CONTEST)).get
    this
  }

  def isCleanLineupDisabled: Boolean = {
    find(cssSelector(BUTTON_CLEAN_LINEUP)).get.attribute("disabled") != None
  }

  def isConfirmLineupDisabled: Boolean = {
    find(cssSelector(BUTTON_CONFIRM_LINEUP)).get.attribute("disabled") != None
  }

  def isLineupEmpty: Boolean = {
    var isEmpty = true
    for (i <- 1 to 11){
      isEmpty = isEmpty && getSoccerPlayerFromLineUp(i).isEmpty
    }
    isEmpty
  }

  def isOrderedByPos: Boolean = {
/*
    // 25 segundos de test
    var isOrdered = true
    var i = 1
    var position = ""
    var positionValue = 0
    var oldPositionValue = 0

    var positionValues:Map[String, Int] = Map[String, Int] (
      SoccerPlayer.POS_GOAL_KEEPER -> 1,
      SoccerPlayer.POS_DEFENSE -> 2,
      SoccerPlayer.POS_MIDDLE -> 3,
      SoccerPlayer.POS_FORWARD -> 4,
      "" -> 0
    )

    try {
      position = getSoccerPlayerPositionFromList(i)
    } catch { case _: Throwable => { position = ""} }

    positionValue = positionValues(position)
    isOrdered = positionValue >= oldPositionValue

    while(positionValue != 0 && isOrdered){
      isOrdered = positionValue >= oldPositionValue
      oldPositionValue = positionValue
      i += 1

      try {
        position = getSoccerPlayerPositionFromList(i)
      } catch { case _: Throwable => { position = "" }}

      positionValue = positionValues(position)
    }
    isOrdered
*/
    // menos de 1 segundo
    fastEnterContest_PlayerAreOrderedByPos
  }

  def isOrderedByName: Boolean = {
    fastEnterContest_PlayerAreOrderedByName
  }

  def isOrderedByDFP: Boolean = {
    fastEnterContest_PlayerAreOrderedByDFP
  }

  def isOrderedByPlayed: Boolean = {
    fastEnterContest_PlayerAreOrderedByPlayed
  }

  def isOrderedBySalary: Boolean = {
    fastEnterContest_PlayerAreOrderedBySalary
  }

  def orderByPosition = {
    click on find(cssSelector(".soccer-player-list-header-table .filterOrderPos a")).get
    this
  }
  def orderByName = {
    click on find(cssSelector(".soccer-player-list-header-table .filterOrderName a")).get
    this
  }
  def orderByDFP = {
    click on find(cssSelector(".soccer-player-list-header-table .filterOrderDFP a")).get
    this
  }
  def orderByPlayed = {
    click on find(cssSelector(".soccer-player-list-header-table .filterOrderPlayed a")).get
    this
  }
  def orderBySalary = {
    click on find(cssSelector(".soccer-player-list-header-table .filterOrderSalary a")).get
    this
  }

  /**
   *
   * @param matchOrder 1 es todos los partidos
   * @return
   */
  def setSoccerPlayerMatchFilter(matchOrder: Int) = {

    if (resolution == Resolution.SMALL) {
      singleSel(cssSelector(FILTER_MATCH_MOBILE)).value = "" + matchOrder
    } else {
      click on find(cssSelector( FILTER_MATCH_DESKTOP(matchOrder) )).get
    }

    this
  }

  def setSoccerPlayerPositionFilter(pos: String) = {

    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("setSoccerPlayerPositionFilter")
    } else {
      val cssSel = FILTER_POSITION_DESKTOP(SOCCER_PLAYER_POSITION_FILTER_TAB(pos))
      eventually { click on find(cssSelector(cssSel)).get }
    }

    this
  }

  def selectGoalKeeperFromLineup = {
    selectSoccerPlayerFromLineup(1)
    this
  }

  def selectDefenseFromLineup(order: Int) = {
    selectSoccerPlayerFromLineup(1 + order)
    this
  }

  def selectMiddleFromLineup(order: Int) = {
    selectSoccerPlayerFromLineup(5 + order)
    this
  }

  def selectForwardFromLineup(order: Int) = {
    selectSoccerPlayerFromLineup(9 + order)
    this
  }

  def getNumberOfSoccerPlayers: Int = {
    var n = 0
    eventually (timeout(2 seconds)) {
      n = fastCountByCssSelector(SOCCER_PLAYER_LIST_SLOT)
    }
    n
  }

  def getLineUpSalary: Int = {

    var salary = find(cssSelector(LINEUP_SALARY)).get.text
    salary = salary.substring(0, salary.length() -1)
    Integer.parseInt(salary)

  }

  def cancelSoccerPlayerSelection = {
    if(resolution == Resolution.SMALL) {
      eventually { click on find(cssSelector(BUTTON_CANCEL_SOCCER_PLAYER_SELECTION_MOBILE)).get }
    }
    this
  }

  def getSoccerPlayerFromList(index: Int): SoccerPlayer= {
    val player = new SoccerPlayer("", "", 0)

    eventually {
      player.name = find(cssSelector(SOCCER_PLAYER_LIST_SLOT_NAME(index))).get.text
      player.position = getSoccerPlayerPositionFromList(index)
      val salary = find(cssSelector(SOCCER_PLAYER_LIST_SLOT_SALARY(index))).get.text
      player.salary = Integer.parseInt(salary.substring(0, salary.length - 1))
    }

    player
  }

  def getSoccerPlayerFromLineUp(index: Int): SoccerPlayer= {
    var player:SoccerPlayer = null

    if (find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_EMPTY(index))) == None) {
      player = createPlayerFromLineUp(index)
    } else {
      val pos = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_POSITION(index))).get.text
      player = SoccerPlayer.EmptyPlayer(pos)
    }

    player
  }

  def addSoccerPlayerFromList(index: Int) = {

    eventually {
      click on find(cssSelector(SOCCER_PLAYER_LIST_SLOT_ADD_BUTTON(index))).get
    }

    this
  }

  def setSoccerPlayerNameFilterSearch(name:String) = {
    textField(cssSelector(FILTER_SOCCER_PLAYER_NAME)).value = name
    this
  }

  def clearLineupList = {
    click on find(cssSelector(BUTTON_CLEAN_LINEUP)).get

    this
  }

  def removeSoccerPlayerFromLineUp(index: Int) = {
    click on find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_REMOVE(index))).get
    this
  }

  def isOverSalaryErrorShown: Boolean = {
    find(cssSelector(LINEUP_SELECTOR_ALERT)).get.isDisplayed
  }

  def confirmLineup = {
    click on find(cssSelector(BUTTON_CONFIRM_LINEUP)).get
    this
  }

  def openContestDescription = {
    click on find(cssSelector(".enter-contest-tabs li:nth-child(2)")).get
    this
  }

  private def selectSoccerPlayerFromLineup(index: Int) = {
    eventually { click on find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT(index))).get }
    this
  }

  def manyClicksOnAddSoccer(index: Int) = {
    fastClicksByCssSelector(4, SOCCER_PLAYER_LIST_SLOT_ADD_BUTTON(index))
  }

  def manyClicksOnConfirm = {
    fastClicksByCssSelector(4, BUTTON_CONFIRM_LINEUP)
  }


  /**
   * Aunque el usuario no ve una clase, si ve el color de fondo y
   * lo relaciona con su posicion en el campo de juego.
   * Utilizo la clase para relacionarla con la posicion.
   * @param index indice del player
   * @return
   */
  private def getSoccerPlayerPositionFromList(index: Int) = {
    var pos:String = ""
    find(cssSelector(SOCCER_PLAYER_LIST_SLOT(index))).get.attribute("class").get.split(" ").map((cssClass:String) => {
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

  private def createPlayerFromLineUp(index: Int): SoccerPlayer = {
    val player = new SoccerPlayer("", "", 0)

    eventually {
      player.name = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_NAME(index))).get.text
      player.position = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_POSITION(index))).get.text
      val salary = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_SALARY(index))).get.text
      player.salary = Integer.parseInt(salary.substring(0, salary.length - 1))
    }
    player
  }

}


