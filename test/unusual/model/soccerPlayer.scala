package unusual.model

import unusual.model.FieldPos._

class SoccerPlayer(var name: String, var position: FieldPos, var salary: Int) {

  def this(pos: FieldPos) {
    this("", pos, 0)  // EmptyPlayer
  }

  def ==(other:SoccerPlayer) = {
    shortName == other.shortName &&
    position == other.position &&
    salary == other.salary
  }

  def shortName = name.take(17).toLowerCase

  def isEmpty:Boolean = name == "" && salary == 0

  override def toString:String = "{Name: " + name + ", Position: " + position + ", Salary: " + salary + "}"
}