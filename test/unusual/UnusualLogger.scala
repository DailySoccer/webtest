package unusual

import play.api.Logger

class UnusualLogger(className: String = "Unusual") {

  def this(clas: Class[_]) = this(clas.getSimpleName)

  //var logger = Logger("Unusual")

  def error(msg: String): Unit = if(UnusualLogger.isErrorEnabled) { println("\u001B[31m[u-error] " + s"$className - " + msg + "\u001B[0m") }

  def error(msg: String, t:Throwable): Unit = if(UnusualLogger.isErrorEnabled) { println("\u001B[31m[u-error] " + s"$className - " + msg + "\u001B[0m", t) }

  def info(msg: String): Unit = if(UnusualLogger.isInfoEnabled) { println("[u-info]  \u001B[32m" + s"$className - " + msg + "\u001B[0m") }

  def info(msg: String, t:Throwable): Unit = if(UnusualLogger.isInfoEnabled) { println("[u-info]  \u001B[32m" + s"$className - " + msg + "\u001B[0m", t) }

  def trace(msg: String): Unit = if(UnusualLogger.isTraceEnabled) { println("[u-trace] " + s"$className - " + msg) }

  def trace(msg: String, t:Throwable): Unit = if(UnusualLogger.isTraceEnabled) { println("[u-trace] " + s"$className - " + msg, t) }

  def warn(msg: String): Unit = if(UnusualLogger.isWarnEnabled) { println("\u001B[33m[u-warn]  " + s"$className - " + msg + "\u001B[0m") }

  def warn(msg: String, t:Throwable): Unit = if(UnusualLogger.isWarnEnabled) { println("\u001B[33m[u-warn]  " + s"$className - " + msg + "\u001B[0m", t) }

  def debug(msg: String): Unit = if(UnusualLogger.isDebugEnabled) { println("\u001B[36m[u-debug]\u001B[0m " + s"$className - " + msg) }

  def debug(msg: String, result:Boolean): Unit = {
    if(UnusualLogger.isDebugEnabled) {
      println("\u001B[36m[u-debug]\u001B[0m " + s"$className - " + String.format("%-75s", msg) + (": " + result).toUpperCase)
    }
  }

  def debug(msg: String, t:Throwable): Unit = if(UnusualLogger.isDebugEnabled) { println("\u001B[36m[u-debug]\u001B[0m " + s"$className - " + msg, t) }

}

object  UnusualLogger {
  private val logLevel = scala.util.Properties.envOrElse("LOG_LEVEL", "ERROR").toUpperCase match {
    case "ERROR" => 1
    case "WARNING" => 2
    case "INFO" => 3
    case "DEBUG" => 4
    case "TRACE" => 5
  }

  var isErrorEnabled = logLevel >= 1
  var isWarnEnabled  = logLevel >= 2
  var isInfoEnabled  = logLevel >= 3
  var isDebugEnabled = logLevel >= 4
  var isTraceEnabled = logLevel == 5
}