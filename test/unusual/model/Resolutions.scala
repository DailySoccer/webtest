package unusual.model

import unusual.UnusualLogger

class Resolution(w: Int, h: Int, alias: String, able: Boolean) {
  val width: Int = w
  val height: Int = h
  val enabled: Boolean = able
  private val _alias: String = alias

  new UnusualLogger().info(s"$this: $enabled")

  override def toString = _alias
}

object Resolution {

  private val PARAM_SIZE = scala.util.Properties.envOrElse("RESOLUTION", "ALL").toUpperCase

  val BIG:Resolution = new Resolution(1920, 1080, "DESKTOP", PARAM_SIZE == "ALL" || PARAM_SIZE == "DESKTOP" || PARAM_SIZE == "BIG")
  val MEDIUM:Resolution = new Resolution(850, 720, "TABLET", PARAM_SIZE == "ALL" || PARAM_SIZE == "TABLET" || PARAM_SIZE == "MEDIUM")
  val SMALL:Resolution = new Resolution(400, 600, "SMARTPHONE", PARAM_SIZE == "ALL" || PARAM_SIZE == "SMARTPHONE" || PARAM_SIZE == "SMALL")
  val ALL: List[Resolution] = List(
    BIG,
    MEDIUM,
    SMALL
  )

}
