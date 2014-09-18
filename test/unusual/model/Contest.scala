package unusual.model

class Contest () {

  var name = ""
  var description = ""
  var entryFee = ""
  var prize = ""
  var date = ""
  var id = ""

  var numMatches = 0
  var numContestants = 0
  var numPrizes = 0

  var nameOrder = 0
  var entryFeeOrder = 0
  var startDateOrder = 0


  override def toString: String = {
    name + " - " + id + "\n" + description + " \n" + entryFee + " - " + prize + " - " + date + "\n"
  }

}
