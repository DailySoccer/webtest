package unusual.pages

import unusual.model.Resolution
import unusual.pages.components.{PaginatorControl, FooterBar, MenuBar}

class MyContestsPage(res:Resolution) extends SharedPage(res) {
  override val url = SharedPage.baseUrl + "/#/my_contests/live/"

  val LIVE_CONTEST_COUNTER = in.LIVE_CONTEST_TAB + " .contest-count"

  override def isAt = {
    var _isAt = true
    _isAt = _isAt && currentUrl == url
    _isAt = _isAt && pageTitle == TITLE

    _isAt = _isAt && new MenuBar(resolution).isAt
    _isAt = _isAt && new FooterBar(resolution).isAt

    _isAt
  }

  def liveContestCounterNumber: Int = {
    Integer.parseInt(find(cssSelector(LIVE_CONTEST_COUNTER)).get.text)
  }

  object in {
    val LIVE_CONTEST_TAB = "#live-contest-tab a"

    val live = new SectionTabConcrete("#live-contest-tab a", "#live-contest-content")
    val upcoming = new SectionTabConcrete("#waiting-contest-tab a", "#waiting-contest-content")
    val history = new SectionTabConcrete("#history-contest-tab a", "#history-contest-content")

    def liveTab: MyContestsPage.SectionTab = live.setActive
    def upcomingTab: MyContestsPage.SectionTab = upcoming.setActive
    def historyTab: MyContestsPage.SectionTab = history.setActive

    class SectionTabConcrete(tab:String, content:String) extends MyContestsPage.SectionTab {
      val TAB = tab
      val TAB_CONTENT = content
      val NO_CONTEST_LAYER = TAB_CONTENT + " .no-contests-wrapper"
      val CONTEST_LIST_ROOT = TAB_CONTENT + " .contests-list-root"
      val CONTEST_ROW_CONTAINER = CONTEST_LIST_ROOT + " .contest-row"
      def CONTEST_ROW_ACTION_BUTTON(ordinal:Int) = CONTEST_ROW_CONTAINER + s":nth-child($ordinal) .column-contest-action .action-button"
      val NO_CONTEST_ROOT = TAB_CONTENT + " .no-contests-wrapper"
      val TO_THE_CONTESTS_BUTTON = NO_CONTEST_ROOT + " .btn-go-to-contest"
      val ROWS_PER_PAGE = 10

      def setActive: MyContestsPage.SectionTab = {
        click on find(cssSelector(tab)).get
        this
      }

      def countContests: Integer = {
        var count = 0
        if (!isElemDisplayed(NO_CONTEST_LAYER)) {

          val paginator = new PaginatorControl(resolution, TAB_CONTENT)
          val currPage = paginator.getCurrentPage
          logger.debug(s"currentPage is $currPage")

          var pages = 0
          eventually {
            pages = paginator.getNumberOfPages
            logger.debug(s"Number of pages is $pages")
            logger.debug("Goes to last page")
            paginator.goToLastPage
            assert(paginator.getCurrentPage == pages, "paginator does not go to last page")
          }

          // Esta parece la unica rapida y efectiva
          val lastPageRows = fastCountByCssSelector(CONTEST_ROW_CONTAINER)
          logger.debug(s"Number of Rows are: $lastPageRows")
          paginator.goToPage(currPage)
          logger.debug(s"Goes to page $currPage")

          // return
          count = (pages - 1) * ROWS_PER_PAGE + lastPageRows
        }
        count
      }

      def toTheContest_ButtonExists: Boolean = {
        isElemDisplayed(TO_THE_CONTESTS_BUTTON)
      }
      def clickOn_ToTheContests_Button: Unit = {
        click on find(cssSelector(TO_THE_CONTESTS_BUTTON)).get
      }
      def clickOnRowActionButton(idx:Int): Unit = {
        click on find(cssSelector(CONTEST_ROW_ACTION_BUTTON(idx))).get
      }

    }
  }




}


object MyContestsPage {

  abstract class SectionTab {
    def countContests:Integer
    def toTheContest_ButtonExists: Boolean
    def clickOn_ToTheContests_Button: Unit

    def clickOnRowActionButton(idx:Int): Unit
  }

}