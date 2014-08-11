package pages

class LobbyPage extends SharedPage {
  val TITLE   = "Daily Soccer"

  val url = SharedPage.baseUrl + "/#/lobby"

  def open = {
    go to url
    this
  }

  def isAt = {
    currentUrl should be (url)
    pageTitle should be (TITLE)

    new MenuBar().isAt
    //eventually { find(tagName("legend")).get.text should be (LEGEND) }
    //find(name(FORM_EMAIL)) should be ('defined)
    //find(name(FORM_PASSWORD)) should be ('defined)

    //find(cssSelector("form input[name='Email']")) should be ('defined)
    //find(cssSelector("form input[name='Password']")) should be ('defined)

    this
  }

  def isNotLoggedIn = {
    new MenuBar().isLoginBar
  }
  def isLoggedIn = {
    new MenuBar().isLoggedBar
  }

}