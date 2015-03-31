package unusual.model.pageStates

import unusual.model.{User, Contest}

class MyContestsState {


  var upcomingTab = new MyContestsState.GeneralInfo
  var liveTab = new MyContestsState.GeneralInfo
  var historyTab = new MyContestsState.GeneralInfo

  //var soccerPlayers

}


object MyContestsState {

  class GeneralInfo {
    var numberOfContests:Int = 0
  }

  val TIME_0_LIST:Map[User, MyContestsState] = Map(
    User.DEFAULT -> new MyContestsState,
    User.WITH_CONTEST -> {
      val state = new MyContestsState
      state.upcomingTab.numberOfContests = 6
      state.liveTab.numberOfContests = 0
      state.historyTab.numberOfContests = 0
      state
    }
  )

  val TIME_1_LIST:Map[User, MyContestsState] = Map(
    User.DEFAULT -> new MyContestsState,
    User.WITH_CONTEST -> {
      val state = new MyContestsState
      state.upcomingTab.numberOfContests = 4
      state.liveTab.numberOfContests = 5
      state.historyTab.numberOfContests = 0
      state
    }
  )

  val TIME_2_LIST:Map[User, MyContestsState] = Map(
    User.DEFAULT -> new MyContestsState,
    User.WITH_CONTEST -> {
      val state = new MyContestsState
      state.upcomingTab.numberOfContests = 6
      state.liveTab.numberOfContests = 2
      state.historyTab.numberOfContests = 4
      state
    }
  )

}