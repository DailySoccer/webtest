package unusual.pages

import unusual.model.Resolution

class ContestDescriptionWindow(res:Resolution) extends SharedPage {
  val url = SharedPage.baseUrl
  val resolution: Resolution = res

  val TITLE   = "Daily Soccer"

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    new MenuBar(resolution).isAt
    new FooterBar(resolution).isAt

    this
  }

}
