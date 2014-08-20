package pages

class ContestDescriptionWindow extends SharedPage {
  val TITLE   = "Daily Soccer"

  val url = SharedPage.baseUrl

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    new MenuBar().isAt
    new FooterBar().isAt

    this
  }

}
