package unusual.model

class Contest (n: String = "", desc: String = "", fee: String = "",
               priz: String = "", when: String = "") {

  var name = n
  var entryFee = fee
  var prize = priz
  var date = when
  var description = desc

  var numMatches = 3
  var numContestants = 5
  var numPrizes = 0

  override def toString: String = {
    name + "\n" + description + " \n" + entryFee + " - " + prize + " - " + date + "\n"
  }

}