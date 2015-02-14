package unusual.model


object FieldPos extends Enumeration {
  type FieldPos = Value
  val POS_GOAL_KEEPER, POS_DEFENSE, POS_MIDDLE, POS_FORWARD, POS_ALL = Value


  def fromUiText(uiText: String) : FieldPos = {

     // Mapea la FieldPos a los posibles valores en distintos idiomas que pueden aparecer en el UI
     Map(POS_GOAL_KEEPER -> List("POR", "GK"),
         POS_DEFENSE -> List("DEF"),
         POS_MIDDLE -> List("MED", "MID"),
         POS_FORWARD -> List("DEL", "FWD"),
         POS_ALL -> List("TODOS", "ALL"))

     // Buscamos la primera entrada del mapa (tuple) en cuya lista este el uiText
     .find(tuple => tuple._2.contains(uiText))

     // Si la hemos encontrado, la devolvemos. Si no, excepcion.
     match {
       case Some(v) => v._1
       case _ => throw new Exception(s"FieldPos position not found for uiText $uiText")
    }
  }

  def fromCss(cssClasses: String) : FieldPos = {

    // Mapeo de una cssClass a una posicion. Buscamos entre todas las que nos pueden pasar
    Map("posPOR" -> POS_GOAL_KEEPER,
        "posDEF" -> POS_DEFENSE,
        "posMED" -> POS_MIDDLE,
        "posDEL" -> POS_FORWARD)
      .find(tuple => cssClasses.contains(tuple._1))
      match {
        case Some(pos) => pos._2
        case _ =>  throw new Exception(s"FieldPos position not found for cssClasses $cssClasses")
    }
  }
}