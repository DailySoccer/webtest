package unusual.tests.timeShiftTests

import unusual.model.pageStates.LobbyState
import unusual.model.{Contest, Resolution}
import unusual.tests.lobbyTest.LobbyTestCommon

class LobbyTimeShiftAuthTest(lobbySt: LobbyState, res:Resolution, day:Int, month:Int, year:Int, hour:Int, minute:Int, second:Int, returnedString:String) extends LobbyTestCommon(lobbySt, res) {

  //val contestDescription: ContestDescriptionWindowCommon = new ContestDescriptionWindowCommon(cont, res)

  def orderBy = afterWord("ORDER BY")
  def filterBy = afterWord("FILTER BY")
  def causes = afterWord("causes:")
  def consistIn = afterWord("consist in")

  before { status.ensureAuthUser }

  "Auth user" when sizeTesting({
    lobbyTestsBehavior
  })

  def lobbyTestsBehavior: Unit = {

    "look at lobby over the time" which consistIn {

      "go to lobby and do a time shift" in {
        goToLobby
        logger.debug("doing a time shift...")
        timeShift(day, month, year, hour, minute, second:Int, returnedString)
        logger.debug("time shift done!")
      }

      "look at contests names" in lookAtContestsName

      "check number of contest" in lookForDefaultState

      "filter some contests" in searchContests
    }
  }

}