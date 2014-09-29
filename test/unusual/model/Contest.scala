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

  var expensiveLineup = Array[String]()
  var expendAllMoney = Array[String]()

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
      contest.description = "Free: 3 de 4 jugadores - LIM. SAL.: 80000"
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

      contest.expensiveLineup = Array[String](
        "Guille",
        "miguel Lay",
        "maxwell",
        "Henrique",
        "Thiago",
        "paulinho",
        "sammir",
        "Willi",
        "Oscar",
        "Robin van persie",
        "Ante Rebic"
      )

      contest.expendAllMoney = Array[String](
        "Guille",
        "miguel Lay",
        "maxwell",
        "Henrique",
        "Gordon Schil",
        "paulinho",
        "sammir",
        "Willi",
        "Ognjen",
        "Robin van persie",
        "Ante Rebic"
      )

      contest.initialSalary = 80000

      contest
    },
    {
      val contest = new Contest()
      contest.name = "jue., 12 jun!!"
      contest.description = "Free: 3 de 4 jugadores - LIM. SAL.: 70000"
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


      contest.expensiveLineup = Array[String](
        "Mattia perin",
        "juan",
        "ron vlaar",
        "stefan de vrij",
        "Terence",
        "thiago",
        "koke",
        "juan mata",
        "Francesc F",
        "danny welbeck",
        "ciro"
      )

      contest.expendAllMoney = Array[String](
        "Mattia perin",
        "juan",
        "albiol",
        "velt",
        "Terence",
        "thiago",
        "koke",
        "juan mata",
        "Francesc F",
        "danny welbeck",
        "ciro"
      )

      contest.initialSalary = 70000

      contest
    },
    {
      val contest = new Contest()
      contest.name = "jue., 12 jun...."
      contest.description = "Free: 4 de 5 jugadores - LIM. SAL.: 90000"
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


      contest.expensiveLineup = Array[String](
        "Sergio romero",
        "dante",
        "maxwell",
        "marcos rojo",
        "Maicon",
        "koo ja",
        "luka mo",
        "javad nekou",
        "han kook",
        "messi",
        "Kevin mirallas"
      )

      contest.expendAllMoney = Array[String](
        "Sergio romero",
        "dante",
        "maxwell",
        "henrique",
        "Maicon",
        "koo ja",
        "luka mo",
        "javad nekou",
        "han kook",
        "alireza jahan",
        "Kevin mirallas"
      )

      contest.initialSalary = 90000

      contest
    }
  )

  val TIME_1_LIST = {
    val list = TIME_0_LIST
  }

}