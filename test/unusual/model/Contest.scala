package unusual.model

class Contest () {

  var name = ""
  var description = ""
  var entryFee = ""
  var prize = ""
  var date = ""

  var numMatches = 0
  var numContestants = 0
  var numPrizes = 0

  var nameOrder = 0
  var entryFeeOrder = 0
  var startDateOrder = 0

  // Enter contest specifics
  var numGoalKeepersPlayers = 19
  var numDefensePlayers = 43
  var numMiddlePlayers = 55
  var numForwardPlayers = 27
  var numAllPlayers = 144

  var matchPlayers = Map[Int,Int]()

  var matchPositionMixNumPlayers = Map[Int, Map[String, Int] ]()

  var initialSalary = 90000

  var expensiveLineup:Lineup = null
  var expendAllMoney:Lineup = null

  override def toString: String = {
    name + "\n" + description + " \n" + entryFee + " - " + prize + " - " + date + "\n"
  }

}


object Contest {

  val GOAL_KEEPER = 2
  val DEFENSE = 3
  val MIDDLE = 4
  val FORWARD = 5

  // 12/06/14 08:00:39
  val TIME_0_LIST = Array(
    {
      val contest = new Contest()
      contest.name = "jue., 12 jun.++"
      contest.description = "Gratuito: 3 de 4 jugadores - Límite de salario: 80000"
      contest.entryFee = "0€"
      contest.prize = "0€"
      contest.date = "Hoy"
      // BRA-HRV MEX-CMR ESP-NLD
      contest.numMatches = 3
      contest.numContestants = 3
      contest.numPrizes = 0

      contest.nameOrder = 320
      contest.entryFeeOrder = 27
      contest.startDateOrder = 320

      // enterContest specifics
      contest.numGoalKeepersPlayers = 18
      contest.numDefensePlayers = 46
      contest.numMiddlePlayers = 44
      contest.numForwardPlayers = 30
      contest.numAllPlayers = 138

      contest.matchPlayers = Map(
        1 -> contest.numAllPlayers,
        2 -> 46,
        3 -> 46,
        4 -> 46
      )

      contest.matchPositionMixNumPlayers = Map(
        2 -> Map(
          SoccerPlayer.POS_GOAL_KEEPER -> 6,
          SoccerPlayer.POS_DEFENSE -> 15
        ),
        3 -> Map(
          SoccerPlayer.POS_GOAL_KEEPER -> 6,
          SoccerPlayer.POS_MIDDLE -> 12
        ),
        4 -> Map(
          SoccerPlayer.POS_FORWARD -> 9
        )
      )

      contest.expensiveLineup = Lineup.TIME_0_EXPENSIVE_LIST(0)

      contest.expendAllMoney = Lineup.TIME_0_EXPEND_ALL_MONEY_LIST(0)

      contest.initialSalary = 80000

      contest
    },
    {
      val contest = new Contest()
      contest.name = "jue., 12 jun!!"
      contest.description = "Gratuito: 3 de 4 jugadores - Límite de salario: 70000"
      contest.entryFee = "0€"
      contest.prize = "0€"
      contest.date = "14/06"
      // ESP-NLD ENG-ITA
      contest.numMatches = 2
      contest.numContestants = 12
      contest.numPrizes = 0

      contest.nameOrder = 321
      contest.entryFeeOrder = 25
      contest.startDateOrder = 321


      // enterContest specifics
      contest.numGoalKeepersPlayers = 12
      contest.numDefensePlayers = 29
      contest.numMiddlePlayers = 32
      contest.numForwardPlayers = 19
      contest.numAllPlayers = 92

      contest.matchPlayers = Map(
        1 -> contest.numAllPlayers,
        2 -> 46,
        3 -> 46
      )

      contest.matchPositionMixNumPlayers = Map(
        2 -> Map(
          SoccerPlayer.POS_GOAL_KEEPER -> 6,
          SoccerPlayer.POS_DEFENSE -> 15
        ),
        3 -> Map(
          SoccerPlayer.POS_FORWARD -> 10,
          SoccerPlayer.POS_MIDDLE -> 16
        )
      )


      contest.expensiveLineup = Lineup.TIME_0_EXPENSIVE_LIST(1)

      contest.expendAllMoney = Lineup.TIME_0_EXPEND_ALL_MONEY_LIST(1)

      contest.initialSalary = 70000

      contest
    },
    {
      val contest = new Contest()
      contest.name = "jue., 12 jun...."
      contest.description = "Gratuito: 4 de 5 jugadores - Límite de salario: 90000"
      contest.entryFee = "0€"
      contest.prize = "0€"
      contest.date = "Hoy"
      // BRA-HRV FRA-HND ARG-IRN KOR-BEL
      contest.numMatches = 4
      contest.numContestants = 12
      contest.numPrizes = 0

      contest.nameOrder = 319
      contest.entryFeeOrder = 26
      contest.startDateOrder = 319

      // enterContest specifics
      contest.numGoalKeepersPlayers = 25
      contest.numDefensePlayers = 61
      contest.numMiddlePlayers = 70
      contest.numForwardPlayers = 32
      contest.numAllPlayers = 188

      contest.matchPlayers = Map(
        1 -> contest.numAllPlayers,
        2 -> 46,
        3 -> 49,
        4 -> 46,
        5 -> 47
      )

      contest.matchPositionMixNumPlayers = Map(
        2 -> Map(
          SoccerPlayer.POS_GOAL_KEEPER -> 6,
          SoccerPlayer.POS_DEFENSE -> 15
        ),
        3 -> Map(
          SoccerPlayer.POS_FORWARD -> 7,
          SoccerPlayer.POS_MIDDLE -> 21
        ),
        4 -> Map(
          SoccerPlayer.POS_DEFENSE -> 15
        ),
        5 -> Map(
          SoccerPlayer.POS_MIDDLE -> 16,
          SoccerPlayer.POS_FORWARD -> 8
        )
      )


      contest.expensiveLineup = Lineup.TIME_0_EXPENSIVE_LIST(2)

      contest.expendAllMoney = Lineup.TIME_0_EXPEND_ALL_MONEY_LIST(2)

      contest.initialSalary = 90000

      contest
    }
  )

  val TIME_1_LIST = {
    val list = TIME_0_LIST
  }

}