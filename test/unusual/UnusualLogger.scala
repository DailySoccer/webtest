package unusual

import play.api.Logger

class UnusualLogger {

  var logger = Logger("Unusual")

  def error(msg: String): Unit = if(UnusualLogger.isErrorEnabled) { logger.error(msg) }

  def error(msg: String, t:Throwable): Unit = if(UnusualLogger.isErrorEnabled) { logger.error(msg, t) }

  def info(msg: String): Unit = if(UnusualLogger.isInfoEnabled) { logger.info(msg) }

  def info(msg: String, t:Throwable): Unit = if(UnusualLogger.isInfoEnabled) { logger.info(msg, t) }

  def trace(msg: String): Unit = if(UnusualLogger.isTraceEnabled) { logger.trace(msg) }

  def trace(msg: String, t:Throwable): Unit = if(UnusualLogger.isTraceEnabled) { logger.trace(msg, t) }

  def warn(msg: String): Unit = if(UnusualLogger.isWarnEnabled) { logger.warn(msg) }

  def warn(msg: String, t:Throwable): Unit = if(UnusualLogger.isWarnEnabled) { logger.warn(msg, t) }

  def debug(msg: String): Unit = if(UnusualLogger.isDebugEnabled) { logger.debug(msg) }

  def debug(msg: String, result:Boolean): Unit = {
    if(UnusualLogger.isDebugEnabled) {
      logger.debug(String.format("%-75s", msg) + (": " + result).toUpperCase)
    }
  }

  def debug(msg: String, t:Throwable): Unit = if(UnusualLogger.isDebugEnabled) { logger.debug(msg, t) }

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