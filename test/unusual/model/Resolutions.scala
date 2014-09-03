package unusual.model

class Resolution(w: Int, h: Int, alias: String) {
  val width: Int = w
  val height: Int = h
  private val _alias: String = alias

  override def toString = {
    _alias
  }
}

object Resolution {

  val BIG:Resolution = new Resolution(1920, 1080, "BIG")
  val MEDIUM:Resolution = new Resolution(850, 720, "MEDIUM")
  val SMALL:Resolution = new Resolution(400, 600, "SMALL")
  val ALL: List[Resolution] = List(
    BIG,
    MEDIUM,
    SMALL
  )

}
