package unusual.model

class EnterContestState {

  var contest = new Contest()
  //var soccerPlayers

}


object EnterContestState {

  val TIME_0_LIST = Array({
    val state = new EnterContestState
    state.contest = Contest.TIME_0_LIST(0)
    state
  }, {
    val state = new EnterContestState
    state.contest = Contest.TIME_0_LIST(1)
    state
  }, {
    val state = new EnterContestState
    state.contest = Contest.TIME_0_LIST(2)
    state
  })

}