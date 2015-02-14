package unusual.model

import unusual.model.FieldPos._

class SoccerPlayer(n: String, pos: FieldPos, sal: Int) {
  var name = n
  var position = pos
  var salary = sal

  def ==(other:SoccerPlayer) = {
    shortName == other.shortName &&
    position == other.position &&
    salary == other.salary
  }

  def shortName = name.take(17).toLowerCase

  def !=(other:SoccerPlayer) = !(this == other)

  def isEmpty:Boolean = name == "" && salary == 0

  override def toString:String = "{Name: " + name + ", Position: " + position + ", Salary: " + salary + "}"
}

object SoccerPlayer {

  def EmptyPlayer(pos:FieldPos) : SoccerPlayer = {
    pos match {
      case POS_GOAL_KEEPER => new SoccerPlayer("", POS_GOAL_KEEPER, 0)
      case POS_DEFENSE => new SoccerPlayer("", POS_DEFENSE, 0)
      case POS_MIDDLE => new SoccerPlayer("", POS_MIDDLE, 0)
      case POS_FORWARD => new SoccerPlayer("", POS_FORWARD, 0)
      case _ => throw new Exception(s"FieldPos $pos unknown creating a EmptyPlayer")
    }
  }
}