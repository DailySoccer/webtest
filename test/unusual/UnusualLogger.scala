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
  var isErrorEnabled = true
  var isInfoEnabled  = true
  var isTraceEnabled = true
  var isWarnEnabled  = true
  var isDebugEnabled = true
}