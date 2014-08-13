package pages

class PromosPage extends SharedPage {

  val TITLE   = "Daily Soccer"
  val url = SharedPage.baseUrl + "/#"

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    this
  }

}