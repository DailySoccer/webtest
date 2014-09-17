package unusual.model

class User (fName: String, lName: String, mail: String, nick: String, pass: String ) {

  var firstName = fName
  var lastName = lName
  var email = mail
  var nickname = nick
  var password = pass

}


object User {

  // Signed up user
  val DEFAULT:User = new User("Test", "Test", "test@test.com", "Test", "private")
  val OTHER:User = new User("Goyo", "Iniesta", "goyo@test.com", "Goyo", "private")
  val SIGNED_UP_WRONG_PASS:User = new User("First", "Last", "new@test.com", "The newbie", "asd")

  // Not signed up users
  val NEW:User = new User("New", "User", "newUser@test.com", "newbie", "private")
  val NOT_SINGED_UP:User = new User("notSignedUp", "notSignedUp", "notSignedUp@test.com", "notSignedUp", "notSignedUp")

  val WRONG_MAIL_1:User = new User("Test", "Test", "wrong@testcom", "asd", "private")
  val WRONG_MAIL_2:User = new User("Test", "Test", "wrongtest.com", "asd", "private")
  val WRONG_MAIL_3:User = new User("Test", "Test", "@wrongtest.com", "asd", "private")

  val WRONG_PASS_1:User = new User("Test", "Test", "wrong@test.com", "asd", "1")
  val WRONG_PASS_2:User = new User("Test", "Test", "wrong@test.com", "asd", "1234567")

}