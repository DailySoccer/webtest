package unusual.model.pageStates

import unusual.model.pageStates.LiveContestState.SubState
import unusual.model.{User, Contest}

/**
 * Created by gregorioiniestaovejero on 27/4/15.
 */
class LiveContestState {
  var contest: Contest = null
  var timeInfo: Array[SubState] = null
  var userList: Array[User] = null

}

object LiveContestState {

    class SubState {
      var totalPoints = ""
      var remainingTime = ""
      var soccerPlayerPunctuations: Array[String] = null
    }

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

  val TIME_0 = {
    val state = new LiveContestState
    state.contest = Contest.TIME_0_LIST(0)
    state.userList = completeUserList
    state.timeInfo = Array (
    {
      val s = new LiveContestState.SubState
      s.totalPoints = "90,1"
      s.remainingTime = "63%"
      s.soccerPlayerPunctuations = Array ("0",
                                          "0",
                                          "-",
                                          "-",
                                          "-",
                                          "57,5",
                                          "-",
                                          "-",
                                          "-",
                                          "32,6",
                                          "-")
      s
    }, {
      val s = new LiveContestState.SubState
      s.totalPoints = "231,5"
      s.remainingTime = "0%"
      s.soccerPlayerPunctuations = Array ("0",
                                          "0",
                                          "91,7",
                                          "0",
                                          "0",
                                          "57,5",
                                          "0",
                                          "0",
                                          "25,2",
                                          "32,6",
                                          "24,5")
      s
    })
    state
  }



}
