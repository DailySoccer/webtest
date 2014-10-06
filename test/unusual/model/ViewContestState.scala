package unusual.model

/**
 * Created by gregorioiniestaovejero on 29/09/14.
 */
class ViewContestState {
  var contest:Contest = null
}

object ViewContestState {

  val TIME_0_LIST = Array({
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(0)
    state
  }, {
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(1)
    state
  }, {
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(2)
    state
  })
}