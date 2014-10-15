package unusual.model

class SoccerPlayer (n: String, pos: String, sal: Int) {
  var name = n
  var position = pos
  var salary = sal

  def ==(other:SoccerPlayer) = {
    val name1 = (if (name.toLowerCase.length > 17) name.substring(0, 17) else name).toLowerCase
    val name2 = (if (other.name.toLowerCase.length > 17) other.name.substring(0, 17) else other.name).toLowerCase

    name1 == name2 &&
    position.toLowerCase == other.position.toLowerCase &&
    salary == other.salary
  }

  def !=(other:SoccerPlayer) = !(this == other)

  def isEmpty:Boolean = name == "" && salary == 0

  override def toString:String = "{Name: " + name + ", Position: " + position + ", Salary: " + salary + "}"
}

object SoccerPlayer {
  val EMPTY_GOAL_KEEPER = new SoccerPlayer("", POS_GOAL_KEEPER, 0)
  val EMPTY_DEF = new SoccerPlayer("", POS_DEFENSE, 0)
  val EMPTY_MIDDLE = new SoccerPlayer("", POS_MIDDLE, 0)
  val EMPTY_FORWARD = new SoccerPlayer("", POS_FORWARD, 0)

  val POS_GOAL_KEEPER = "POR"
  val POS_DEFENSE = "DEF"
  val POS_MIDDLE = "MED"
  val POS_FORWARD = "DEL"
  val POS_ALL = "TODOS"

  def EmptyPlayer(pos:String) : SoccerPlayer = {
    pos match {
      case POS_GOAL_KEEPER => EMPTY_GOAL_KEEPER
      case POS_DEFENSE => EMPTY_DEF
      case POS_MIDDLE => EMPTY_MIDDLE
      case POS_FORWARD => EMPTY_FORWARD
    }
  }
}