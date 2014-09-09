package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.SharedPage

class ContestDescriptionWindow(res:Resolution) extends SharedPage {
  val url = SharedPage.baseUrl
  val resolution: Resolution = res

  val TITLE   = "Daily Soccer"

  def open = {
    go to url
    this
  }

  override def isAt = {
    placeholder
    true
  }

}
