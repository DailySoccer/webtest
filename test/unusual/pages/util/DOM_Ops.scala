package unusual.pages.util

import org.openqa.selenium.WebDriver
import org.scalatest.selenium.WebBrowser

trait DOM_Ops {

  def fastCountByCssSelector(cssSel:String)(implicit driver:WebDriver):Int = {
      //implicit val webDriver:WebDriver = driver
    WebBrowser.executeScript("return $('" + cssSel + "').length").asInstanceOf[Long].toInt
  }

  def fastClicksByCssSelector(numOfClicks:Int, cssSel:String)(implicit driver:WebDriver) = {
    //implicit val webDriver:WebDriver = driver
    var script = "$('" + cssSel + "')"
    for( a <- 1 to numOfClicks){
      script = script + ".click()"
    }
    WebBrowser.executeScript(script)
  }

  def fastEnterContest_PlayerAreOrderedByPos()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    var script = """
        return (function () {
          var val = 0,
              oldVal = 0,
              isOrdered = true,
              i = 1,
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ')'),
              positionValues = function (elem) {
                return  elem.hasClass('posPOR')? 1 :
                        elem.hasClass('posDEF')? 2 :
                        elem.hasClass('posMED')? 3 :
                                                 4
              };

          while (jQElem.length > 0 && isOrdered) {
            val = positionValues(jQElem);
            isOrdered = val >= oldVal;
            oldVal = val;

            i++;
            jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ')');
          }

          return isOrdered;
        })()
    """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastEnterContest_PlayerAreOrderedByName()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    var script = """
        return (function () {
          var val = "",
              oldVal = "",
              isOrdered = true,
              i = 1,
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .soccer-player-name');

          while (jQElem.length > 0 && isOrdered) {
            val = jQElem.text();
            isOrdered = val >= oldVal;
            oldVal = val;

            i++;
            jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .soccer-player-name');
          }

          return isOrdered;
        })()
    """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastEnterContest_PlayerAreOrderedByDFP()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    var script = """
        return (function () {
          var val = 0,
              oldVal = 0,
              isOrdered = true,
              i = 1,
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-dfp');

          while (jQElem.length > 0 && isOrdered) {
            val = parseInt(jQElem.text());
            isOrdered = val >= oldVal;
            oldVal = val;

            i++;
            jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-dfp');
          }

          return isOrdered;
        })()
    """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastEnterContest_PlayerAreOrderedByPlayed()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    var script = """
        return (function () {
          var val = 0,
              oldVal = 0,
              isOrdered = true,
              i = 1,
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-played');

          while (jQElem.length > 0 && isOrdered) {
            val = parseInt(jQElem.text());
            isOrdered = val >= oldVal;
            oldVal = val;

            i++;
            jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-played');
          }

          return isOrdered;
        })()
                 """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastEnterContest_PlayerAreOrderedBySalary()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    var script = """
        return (function () {
          var val = 0,
              oldVal = 0,
              isOrdered = true,
              i = 1,
              salaryText = "",
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-salary');

          while (jQElem.length > 0 && isOrdered) {
            salaryText = jQElem.text();
            val = parseInt(salaryText.trim().substring(0, salaryText.length - 1));
            isOrdered = val >= oldVal;
            oldVal = val;

            i++;
            jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-salary');
          }

          return isOrdered;
        })()
                 """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }
}
