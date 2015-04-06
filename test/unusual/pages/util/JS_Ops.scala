package unusual.pages.util

import org.openqa.selenium.WebDriver
import org.scalatest.selenium.WebBrowser
import unusual.model.Resolution
import unusual.pages.components.MenuBar

trait JS_Ops {

  def changeMenuPositioning()(implicit driver:WebDriver): Unit = {
    val cssSel = new MenuBar(Resolution.BIG).MENU_ROOT
    val script = s"document.querySelector('$cssSel').style.position = 'relative';"

    WebBrowser.executeScript(script)
  }

  def sliderSetVal(min:Int, max:Int, sliderId:String)(implicit driver:WebDriver): Unit = {
    //val cssSel = (Resolution.BIG)).MENU_ROOT
    //println("$('" + sliderId + "').css('position', 'relative');")
    //Thread.sleep(10000)

    WebBrowser.executeScript("$('" + s"$sliderId').val([$min, $max], true); ")
  }

  def fastCleanLocalStorage()(implicit driver:WebDriver): Unit = {
    //implicit val webDriver:WebDriver = driver
    WebBrowser.executeScript("window.localStorage.clear();")
  }

  def fastCountByCssSelector(cssSel:String)(implicit driver:WebDriver): Int = {
    //implicit val webDriver:WebDriver = driver
    WebBrowser.executeScript("return $('" + cssSel + "').length").asInstanceOf[Long].toInt
  }

  def fastClicksByCssSelector(numOfClicks:Int, cssSel:String)(implicit driver:WebDriver) = {
    //implicit val webDriver:WebDriver = driver
    var script = "$('" + cssSel + "')"
    for (a <- 1 to numOfClicks) {
      script = script + ".click()"
    }
    WebBrowser.executeScript(script)
  }
/*
  def fastClicksByCssSelector(numOfClicks:Int, cssSel:String, childIndex:Int)(implicit driver:WebDriver) = {
    //implicit val webDriver:WebDriver = driver
    var script = "$('" + cssSel + s"').eq($childIndex)"
    for( a <- 1 to numOfClicks){
      script = script + ".click()"
    }
    WebBrowser.executeScript(script)
  }
*/
  def fastLobby_ContestAreOrderedByStartTime()(implicit driver:WebDriver): Boolean = {
    //implicit val webDriver:WebDriver = driver
    val script = """
        return (function () {
          var val = '',
              oldVal = '',
              isOrdered = true,
              i = 1,
              jQElemDay = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-start-date .column-start-date-day'),
              jQElemHour = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-start-date .column-start-date-hour'),
              valueOf = function(strDay, strHour) {
                            var strDayArr = strDay.split('/')
                                day = (strDayArr[0] == 'Today')? 0 : parseInt(strDayArr[0]),
                                month = (strDayArr[0] == 'Today')? 0 : parseInt(strDayArr[1]),
                                hour = parseInt(strHour.split(':')[0]),
                                minute = parseInt(strHour.split(':')[1].split('h')[0]),
                                value = ((month * 31 + day) * 24 + hour) * 60 + minute;
                            console.log(day+"/"+month);
                            return value;
                          };

          while (jQElemDay && jQElemHour && isOrdered) {
            val = valueOf(jQElemDay.innerHTML, jQElemHour.innerHTML);
            isOrdered = val >= oldVal;
            console.log(oldVal + ' .. ' + val + ' | is ordered:' + isOrdered);
            oldVal = val;

            i++;
            jQElemDay = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-start-date .column-start-date-day');
            jQElemHour = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-start-date .column-start-date-hour');
          }

          return isOrdered;
        })()
    """
    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastLobby_ContestAreOrderedByEntryFee()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    val script = """
        return (function () {
          var val = 0,
              oldVal = 0,
              isOrdered = true,
              i = 1,
              jQElem = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-price .column-contest-price-content');

          while (jQElem && isOrdered) {
            val = parseInt(jQElem.innerHTML.split('$')[1]);
            isOrdered = val >= oldVal;
            console.log(oldVal + ' .. ' + val + ' | is ordered:' + isOrdered);
            oldVal = val;

            i++;
            jQElem = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-price .column-contest-price-content');
          }

          return isOrdered;
        })()
                 """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastLobby_ContestAreOrderedByName()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    val script = """
        return (function () {
          var val = '',
              oldVal = '',
              isOrdered = true,
              i = 1,
              jQElem = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-name .column-name'),
              normalize = (function() {
                             var from = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇç",
                                 to   = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunncc",
                                 map = {};
                             for (var i = 0, j = from.length; i < j; i++ ) {
                               map[ from.charAt(i) ] = to.charAt(i);
                             }

                             return function(str) {
                               var ret = '', c = '';
                               for( var i = 0, j = str.length; i < j; i++ ) {
                                 c = str.charAt(i);
                                 ret += map.hasOwnProperty(c) ? map[c] : c;
                               }
                               return ret;
                             }

                           })();

          while (jQElem && isOrdered) {
            val = normalize(jQElem.innerHTML).toLowerCase();
            isOrdered = val >= oldVal;
            console.log(oldVal + ' .. ' + val + ' | is ordered:' + isOrdered);
            oldVal = val;

            i++;
            jQElem = document.querySelector('.contest-row:nth-child(' + i + ') .column-contest-name .column-name');
          }

          return isOrdered;
        })()
                 """

    WebBrowser.executeScript(script).asInstanceOf[Boolean]
  }

  def fastEnterContest_PlayerAreOrderedByPos()(implicit driver:WebDriver):Boolean = {
    //implicit val webDriver:WebDriver = driver
    val script = """
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
    val script = """
        return (function () {
          var val = "",
              oldVal = "",
              isOrdered = true,
              i = 1,
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .soccer-player-name'),
              normalize = (function() {
                             var from = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇç",
                                 to   = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunncc",
                                 map = {};

                             for (var i = 0, j = from.length; i < j; i++ ) {
                               map[ from.charAt(i) ] = to.charAt(i);
                             }

                             return function(str) {
                               var ret = '', c = '';
                               for( var i = 0, j = str.length; i < j; i++ ) {
                                 c = str.charAt(i);
                                 ret += map.hasOwnProperty(c) ? map[c] : c;
                               }
                               return ret;
                             }

                           })();

          while (jQElem.length > 0 && isOrdered) {
            val = normalize(jQElem.text()).toLowerCase();
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
    val script = """
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
    val script = """
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
    // Ignoramos el símbolo del money ($amount)
    val script = """
        return (function () {
          var val = 0,
              oldVal = 0,
              isOrdered = true,
              i = 1,
              salaryText = "",
              jQElem = $('.soccer-players-list .soccer-players-list-slot:nth-child(' + i + ') .column-salary');


          oldVal = parseInt(jQElem.text().trim().substring(1, jQElem.text().length));
          while (jQElem.length > 0 && isOrdered) {
            salaryText = jQElem.text();
            val = parseInt(salaryText.trim().substring(1, salaryText.length));
            isOrdered = val <= oldVal;
            console.log(oldVal + ' .. ' + val + ' | is ordered:' + isOrdered)
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
