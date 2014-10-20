package unusual.model

/**
 * Created by gregorioiniestaovejero on 29/09/14.
 */
class ViewContestState {
  var contest:Contest = null
  var userList:Array[User] = null

}

object ViewContestState {

  private val completeUserList = Array (
    new User("Antonio", "", "", "", ""),
    new User("Belen", "", "", "", ""),
    new User("Fede", "", "", "", ""),
    new User("Flaco", "", "", "", ""),
    new User("Fran", "", "", "", ""),
    new User("Goyo", "", "", "", ""),
    new User("Javi", "", "", "", ""),
    new User("Machus", "", "", "", ""),
    new User("Neo", "", "", "", ""),
    new User("Revelo", "", "", "", ""),
    new User("Zincoontrin", "", "", "", ""),
    new User("Ximo", "", "", "", ""),
    new User("Test", "", "", "", "")
  )

  val TIME_0_LIST = Array({
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(0)
    state.userList = completeUserList
    state
  }, {
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(1)
    state.userList = completeUserList
    state
  }, {
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(2)
    state.userList = completeUserList
    state
  })
}