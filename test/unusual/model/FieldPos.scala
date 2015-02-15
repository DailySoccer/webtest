package unusual.model


object FieldPos extends Enumeration {
  type FieldPos = Value
  val POS_GOAL_KEEPER, POS_DEFENSE, POS_MIDDLE, POS_FORWARD, POS_ALL = Value


  def fromUiText(uiText: String) : FieldPos = {
    uiText match {
      case "POR" | "GK"    => POS_GOAL_KEEPER
      case "DEF"           => POS_DEFENSE
      case "MED" | "MID"   => POS_MIDDLE
      case "DEL" | "FWD"   => POS_FORWARD
      case "TODOS" | "ALL" => POS_ALL
      case _ => throw new Exception(s"FieldPos position not found for uiText $uiText")
    }
  }

  def fromCss(cssClasses: String) : FieldPos = {
    cssClasses match {
      case c if c.contains("posPOR") => POS_GOAL_KEEPER
      case c if c.contains("posDEF") => POS_DEFENSE
      case c if c.contains("posMED") => POS_MIDDLE
      case c if c.contains("posDEL") => POS_FORWARD
      case _ => throw new Exception(s"FieldPos position not found for cssClasses $cssClasses")
    }
  }
}