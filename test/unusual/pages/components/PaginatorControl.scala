package unusual.pages.components

import unusual.model.Resolution
import unusual.pages.SharedPage

class PaginatorControl(res:Resolution, prefix: String) extends SharedPage(res) {

  val PAGER_BOX = prefix + " .paginator-box"

  override def isAt = {
    isElemDisplayed(PAGER_BOX)
  }

  override def open = {
    logger.error("Trying to open a control.")
    this
  }

  def isDisplayed:Boolean = fastCountByCssSelector(PAGER_BOX + " .pagination") != 0

  def goToLastPage = {
    if ( isDisplayed ) {
      eventually { click on find(cssSelector(PAGER_BOX + " .go-to-last-page  a")).get }
    }
    this
  }

  def goToFirstPage = {
    if ( isDisplayed ) {
      eventually { click on find(cssSelector(PAGER_BOX + " .go-to-first-page a")).get }
    }
    this
  }

  def goToPreviousPage = {
    if ( isDisplayed ) {
      eventually { click on find(cssSelector(PAGER_BOX + " .go-to-prev-page a")).get }
    }
    this
  }

  def goToNextPage = {
    if ( isDisplayed ) {
      eventually { click on find(cssSelector(PAGER_BOX + " .go-to-next-page a")).get }
    }
    this
  }

  def goToPage(index:Int) = {
    val numOfPages = getNumberOfPages
    if (numOfPages < index) { throw new Exception("Page index is greater than number of pages.") }
    if (1 > index) { throw new Exception("Page index should be greater than 0.") }
    if (index == numOfPages) {
      goToLastPage
    } else if (index == 1) {
      goToFirstPage
    } else if ( isDisplayed ) {
      val cssSel = PAGER_BOX + " .pagination .page-link:nth-child(" + (index + 3) + ") a"
      val currPage = getCurrentPage
      var attempts = 0
      var isBttDisplayed:Boolean = false
      eventually { isBttDisplayed = find(cssSelector(cssSel)).get.isDisplayed }

      while (!isBttDisplayed) {
        if (currPage < index) {
          goToNextPage
        } else {
          goToPreviousPage
        }

        eventually { isBttDisplayed = find(cssSelector(cssSel)).get.isDisplayed }
        attempts += 1
        if (attempts == numOfPages) {
          throw new Exception("page cannot be found")
        }
      }
      eventually { click on find(cssSelector(cssSel)).get }
    }
    this
  }

  def getNumberOfPages:Int = {
    Math.max(fastCountByCssSelector(PAGER_BOX + " .pagination .page-link"), 1)
  }

  def getCurrentPage:Int = {
    var curr:Int = 1
    if ( isDisplayed ) {
      eventually {
        curr = Integer.parseInt(find(cssSelector(PAGER_BOX + " .pagination .page-link.active")).get.text)
      }
    }
    curr
  }

}

object PaginatorControl {
  val ELEMENTS_PER_PAGE = 10
}