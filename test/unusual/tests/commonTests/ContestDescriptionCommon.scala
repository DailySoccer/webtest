package unusual.tests.commonTests

import org.scalatest.exceptions.StackDepthException
import unusual.model.Resolution
import unusual.pages.components.PaginatorControl
import unusual.testTags.scala._
import unusual.tests._
import unusual.pages._

trait ContestDescriptionCommon extends SharedTest {

  def changeTabs(resolution: Resolution): Unit = {
    //assert(goToLobbyPage.isDefaultState(N_CONTESTS_NO_FILTER))
  }



}
