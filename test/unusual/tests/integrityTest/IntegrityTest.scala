package unusual.tests.integrityTest

import unusual.model.Resolution
import unusual.pages.SharedPage
import unusual.testTags.scala.{DoesNotWorkYet, WIP}
import unusual.tests.SharedTest

class IntegrityTest extends SharedTest(Resolution.ANY) {
  val url = SharedPage.baseUrl + "/admin"
  val URL_VERIFY_PRIZES = url + "/contests/verify_prizes"

  before {
  }

  "verify Prizes" in {
    go to URL_VERIFY_PRIZES
    find(cssSelector("pre")).get.text must be("OK")
  }
}

