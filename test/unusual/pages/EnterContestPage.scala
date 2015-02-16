package unusual.pages

import unusual.model.FieldPos._
import unusual.model._
import unusual.model.pageStates.EnterContestState
import unusual.pages.components.{FooterBar, MenuBar}

class EnterContestPage(res: Resolution, state: EnterContestState) extends SharedPage(res) {

  //override val url = SharedPage.baseUrl + "/#/lobby/enter_contest/"

  val SOCCER_PLAYER_POSITION_FILTER_TAB = Map(
    POS_ALL -> 1,
    POS_GOAL_KEEPER -> 2,
    POS_DEFENSE -> 3,
    POS_MIDDLE -> 4,
    POS_FORWARD -> 5
  )

  val ACTIVE_ELEMENT = ".active"

  def FILTER_MATCH_DESKTOP(idx:Int) = s"#matchesFilterButtons .button-filter-wrapper:nth-child($idx) button"
  val FILTER_MATCH_MOBILE = "#match-filter"

  val FILTER_SOCCER_PLAYER_NAME = ".name-player-input-filter"

  def FILTER_POSITION_DESKTOP(idx:Int) = s"button.button-filter-position:nth-of-type($idx)"

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
  def ENTER_CONTEST_TAB(idx:Int) = ENTER_CONTEST_TABS + s" li:nth-of-type($idx)"
  def ENTER_CONTEST_TAB_LINK(idx:Int) = ENTER_CONTEST_TAB(idx) + " a"

  val SOCCER_PLAYER_LIST_SLOT:String = ".soccer-players-list .soccer-players-list-slot"
  def SOCCER_PLAYER_LIST_SLOT(idx:Int):String            = SOCCER_PLAYER_LIST_SLOT + s":nth-child($idx)"
  def SOCCER_PLAYER_LIST_SLOT_NAME(idx:Int):String       = SOCCER_PLAYER_LIST_SLOT(idx) + " .soccer-player-name"
  def SOCCER_PLAYER_LIST_SLOT_SALARY(idx:Int):String     = SOCCER_PLAYER_LIST_SLOT(idx) + " .column-salary"
  def SOCCER_PLAYER_LIST_SLOT_ADD_BUTTON(idx:Int):String = SOCCER_PLAYER_LIST_SLOT(idx) + " .column-action button.add"
  def SOCCER_PLAYER_LIST_SLOT_REMOVE_BUTTON(idx:Int):String = SOCCER_PLAYER_LIST_SLOT(idx) + " .column-action button.remove"

  val SOCCER_PLAYER_LINEUP_SLOT:String = ".enter-contest-lineup .lineup-selector-slot"
  def SOCCER_PLAYER_LINEUP_SLOT(idx:Int):String          = SOCCER_PLAYER_LINEUP_SLOT + s":nth-child($idx)"
  def SOCCER_PLAYER_LINEUP_SLOT_EMPTY(idx:Int):String    = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-empty-slot"
  def SOCCER_PLAYER_LINEUP_SLOT_POSITION(idx:Int):String = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-fieldpos"
  def SOCCER_PLAYER_LINEUP_SLOT_NAME(idx:Int):String     = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .soccer-player-name"
  def SOCCER_PLAYER_LINEUP_SLOT_SALARY(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-salary"
  def SOCCER_PLAYER_LINEUP_SLOT_REMOVE(idx:Int):String   = SOCCER_PLAYER_LINEUP_SLOT(idx) + " .column-action a"


  override def isAt = {
    var _isAt = true
    logger.debug(s"Contest id: ${state.contest.nameOrder}. ${state.contest.name}")
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
    lobby.filters.clear
    if (resolution == Resolution.SMALL) {
      lobby.playContestNumber(state.contest.startDateOrder)
    } else {
      lobby.filters.search(state.contest.name)
      lobby.playContestNumber(1)
    }
    this
  }


  def isDefaultState(totalPlayers:Int, initialSalary: Int): Boolean = {
    var versionCheck = true

    versionCheck = find(cssSelector(ENTER_CONTEST_TAB(1) + ACTIVE_ELEMENT)) != None
    logger.debug("EnterContest tab should be ''Your Lineup'' ", versionCheck)

    if (resolution != Resolution.SMALL) {
      val numSoccerPlayers = getNumberOfSoccerPlayers
      versionCheck = numSoccerPlayers == totalPlayers
      logger.debug(s"Soccer players are $numSoccerPlayers should be: $totalPlayers", versionCheck)
      versionCheck = versionCheck && isOrderedByPos
      logger.debug("Is ordered by pos ", versionCheck)
      versionCheck = versionCheck && find(cssSelector(FILTER_POSITION_DESKTOP(1) + ACTIVE_ELEMENT)) != None
      logger.debug("Position filter should be ''ALL'' ", versionCheck)
      versionCheck = versionCheck && find(cssSelector(FILTER_MATCH_DESKTOP(1) + ACTIVE_ELEMENT)) != None
      logger.debug("Match filter should be ''All'' ", versionCheck)
      //versionCheck = versionCheck && find(cssSelector(ENTER_CONTEST_TAB(1) + ACTIVE_ELEMENT)) != None
      //logger.debug("EnterContest tab should be ''Your Lineup'' ", versionCheck)
    }


    versionCheck = versionCheck && isLineupEmpty
    logger.debug("Line up should be empty", versionCheck)
    versionCheck = versionCheck && isCleanLineupDisabled
    logger.debug("Clean line up should be empty", versionCheck)
    versionCheck = versionCheck && isConfirmLineupDisabled
    logger.debug("Confirm line up should be disabled", versionCheck)
    val salary = getLineUpSalary
    versionCheck = versionCheck && salary == initialSalary
    logger.debug(s"Line up salary is $salary should be $initialSalary", versionCheck)

    versionCheck
  }

  def clickOnCloseButton = {
    click on find(cssSelector(BUTTON_CLOSE_CONTEST)).get
    this
  }

  def changeToLineupTab = {
    click on find(cssSelector(ENTER_CONTEST_TAB_LINK(1))).get
    this
  }

  def changeToInfoTab = {
    click on find(cssSelector(ENTER_CONTEST_TAB_LINK(2))).get
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

  def isLineupFull: Boolean = {
    var isFull = true
    for (i <- 1 to 11){
      isFull &&= !getSoccerPlayerFromLineUp(i).isEmpty
    }
    isFull
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

  def setSoccerPlayerPositionFilter(pos: FieldPos) = {

    if (resolution == Resolution.SMALL) {
      unavailableFunctionOnResolution("setSoccerPlayerPositionFilter")
    } else {
      val cssSel = FILTER_POSITION_DESKTOP(SOCCER_PLAYER_POSITION_FILTER_TAB(pos))
      eventually { click on find(cssSelector(cssSel)).get }
    }

    this
  }

  def selectGoalKeeperFromLineup = {
    logger.debug("Selecting GoalKeeper from lineup")
    selectSoccerPlayerFromLineup(1)
    logger.debug("Selected")
    this
  }

  def selectDefenseFromLineup(order: Int) = {
    logger.debug("Selecting Defense from lineup")
    selectSoccerPlayerFromLineup(1 + order)
    logger.debug("Selected")
    this
  }

  def selectMiddleFromLineup(order: Int) = {
    logger.debug("Selecting Middle from lineup")
    selectSoccerPlayerFromLineup(5 + order)
    logger.debug("Selected")
    this
  }

  def selectForwardFromLineup(order: Int) = {
    logger.debug("Selecting Forward from lineup")
    selectSoccerPlayerFromLineup(9 + order)
    logger.debug("Selected")
    this
  }

  def getNumberOfSoccerPlayers: Int = {
    logger.debug(s"Getting number of players")
    val n = fastCountByCssSelector(SOCCER_PLAYER_LIST_SLOT)
    logger.debug(s"Number of players is $n")
    n
  }

  def getLineUpSalary: Int = {
    var salary = find(cssSelector(LINEUP_SALARY)).get.text
    salary = salary.substring(0, salary.length() -1)
    Integer.parseInt(salary)

  }

  def cancelSoccerPlayerSelection = {
    if(resolution == Resolution.SMALL) {
      logger.debug(s"Request cancel soccer player selection")
      click on cssSelector(BUTTON_CANCEL_SOCCER_PLAYER_SELECTION_MOBILE)
      logger.debug(s"Canceled")
      /*
      //eventually (timeout(2 seconds)) {
        logger.debug(s"checking ${SOCCER_PLAYER_LINEUP_SLOT(1)} displayed")
        if ( !find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT(1))).get.isDisplayed ) {
          logger.debug("is not displayed")
          click on find(cssSelector(BUTTON_CANCEL_SOCCER_PLAYER_SELECTION_MOBILE)).get
        }
        val check = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT(1))).get.isDisplayed
        logger.debug(s"${SOCCER_PLAYER_LINEUP_SLOT(1)} is displayed", check)
        //assert( check )
      }
*/}
    //}

    this
  }

  def getSoccerPlayerFromList(index: Int): SoccerPlayer= {
    var player = new SoccerPlayer("", POS_ALL, 0)

    eventually {
      player = new SoccerPlayer(find(cssSelector(SOCCER_PLAYER_LIST_SLOT_NAME(index))).get.text,
                                getSoccerPlayerPositionFromList(index),
                                find(cssSelector(SOCCER_PLAYER_LIST_SLOT_SALARY(index))).get.text.dropRight(1).toInt)
    }

    player
  }

  def getSoccerPlayerFromLineUp(index: Int): SoccerPlayer= {
    var player:SoccerPlayer = null

    if (find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_EMPTY(index))) == None) {
      player = createPlayerFromLineUp(index)
    } else {
      val pos = find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_POSITION(index))).get.text
      player = new SoccerPlayer(FieldPos.fromUiText(pos))
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
    click on cssSelector(SOCCER_PLAYER_LINEUP_SLOT(index))
    this
  }

  def manyClicksOnAddSoccer(index: Int) = {
    fastClicksByCssSelector(4, SOCCER_PLAYER_LIST_SLOT_ADD_BUTTON(index))
  }

  def manyClicksOnConfirm = {
    fastClicksByCssSelector(4, BUTTON_CONFIRM_LINEUP)
  }

  def pickWholeLineup(lineup:Lineup):Unit = {
    val list = lineup.soccerPlayerList

    logger.debug("pick middle")
    selectMiddleFromLineup(1) // MIDDLE
    setSoccerPlayerNameFilterSearch(list(5).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick defense")
    selectDefenseFromLineup(1) // DEFENSE
    setSoccerPlayerNameFilterSearch(list(1).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick forward")
    selectForwardFromLineup(1) // FORWARD
    setSoccerPlayerNameFilterSearch(list(9).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick middle")
    selectMiddleFromLineup(2) // MIDDLE
    setSoccerPlayerNameFilterSearch(list(6).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick goalkeeper")
    selectGoalKeeperFromLineup // GOALKEEPER
    setSoccerPlayerNameFilterSearch(list(0).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick defense")
    selectDefenseFromLineup(2) // DEFENSE
    setSoccerPlayerNameFilterSearch(list(2).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick middle")
    selectMiddleFromLineup(3) // MIDDLE
    setSoccerPlayerNameFilterSearch(list(7).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick forward")
    selectForwardFromLineup(2) // FORWARD
    setSoccerPlayerNameFilterSearch(list(10).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick middle")
    selectMiddleFromLineup(4) // MIDDLE
    setSoccerPlayerNameFilterSearch(list(8).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick defense")
    selectDefenseFromLineup(3) // DEFENSE
    setSoccerPlayerNameFilterSearch(list(3).name)
    addSoccerPlayerFromList(1)

    logger.debug("pick defense")
    selectDefenseFromLineup(4) // DEFENSE
    setSoccerPlayerNameFilterSearch(list(4).name)
    addSoccerPlayerFromList(1)
  }

  /**
   * Aunque el usuario no ve una clase, si ve el color de fondo y
   * lo relaciona con su posicion en el campo de juego.
   * Utilizo la clase para relacionarla con la posicion.
   * @param index indice del player
   * @return
   */
  private def getSoccerPlayerPositionFromList(index: Int) : FieldPos = {
    FieldPos.fromCss(find(cssSelector(SOCCER_PLAYER_LIST_SLOT(index))).get.attribute("class").get)
  }

  private def createPlayerFromLineUp(index: Int): SoccerPlayer = {
    var player: SoccerPlayer = new SoccerPlayer("", POS_ALL, 0)

    eventually {
      player = new SoccerPlayer(find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_NAME(index))).get.text,
                                FieldPos.fromUiText(find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_POSITION(index))).get.text),
                                find(cssSelector(SOCCER_PLAYER_LINEUP_SLOT_SALARY(index))).get.text.dropRight(1).toInt)
    }

    player
  }


}


