package unusual.pages

import unusual.model.{User, Resolution}

class LoginPage(res:Resolution) extends SharedPage {

  val url = SharedPage.baseUrl + "/#/login"
  val resolution: Resolution = res


  val TITLE   = "Daily Soccer"
  //val LEGEND  = "Login"
  val FORM_EMAIL    = "Email"
  val FORM_PASSWORD = "Password"
  val FORM_SUBMIT = "login"

  val DEFAULT_USER : User = new User("Test", "Test", "test@test.com", "Test", "private")

  def open = {
    go to url
    this
  }

  override def isAt = {
    pageTitle should be (TITLE)
    eventually {
      //find(tagName("legend")).get.text should be (LEGEND)
      find(id(FORM_EMAIL)) should be ('defined)
      find(id(FORM_PASSWORD)) should be ('defined)
      find(id(FORM_SUBMIT)) should be ('defined)
    }
    new FooterBar(resolution).isAt

    this
  }

  def doLogin = {
    fillAndSubmitForm(DEFAULT_USER)
    this
  }

  def fillAndSubmitForm(u: User) = {
    emailField(FORM_EMAIL).value = u.email
    pwdField(FORM_PASSWORD).value = u.password
    submit
    this
  }

  //Eventually example with timeout
/*
  def eventuallyTesting {
    var a = 0
    eventually (timeout(20 seconds), interval(2 seconds)) {
      a += 1
      println(a)
      find(id("asd")) should be ('defined)
    }
  }
*/

}
