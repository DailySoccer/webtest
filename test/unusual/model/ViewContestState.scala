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
    new User("AntonioTest", "", "", "", ""),
    new User("BelenTest", "", "", "", ""),
    new User("FedeTest", "", "", "", ""),
    new User("FlacoTest", "", "", "", ""),
    new User("FranTest", "", "", "", ""),
    new User("GoyoTest", "", "", "", ""),
    new User("JaviTest", "", "", "", ""),
    new User("MachusTest", "", "", "", ""),
    new User("MariaTest", "", "", "", ""),
    new User("NeoTest", "", "", "", ""),
    new User("ReveloTest", "", "", "", ""),
    new User("ZincoontrinTest", "", "", "", ""),
    new User("XimoTest", "", "", "", ""),
    new User("Test", "", "", "", "")
  )

  val TIME_0_LIST = Array({
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(0)
    state.contest.description = "Gratuito: 14 de 100 jugadores - Límite de salario: 65000"
    state.userList = completeUserList
    state
  }, {
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(1)
    state.contest.description = "Gratuito: 14 de 200 jugadores - Límite de salario: 60000"
    state.userList = completeUserList
    state
  }, {
    val state = new ViewContestState
    state.contest = Contest.TIME_0_LIST(2)
    state.contest.description = "Gratuito: 14 de 200 jugadores - Límite de salario: 70000"
    state.userList = completeUserList
    state
  })
}