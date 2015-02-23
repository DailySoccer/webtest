package unusual

import org.openqa.selenium._
import org.openqa.selenium.chrome._
import org.openqa.selenium.safari._
import org.scalatest.concurrent._
import org.scalatestplus.play.BrowserFactory._
import play.api.test._
import org.scalatest._
import org.scalatestplus.play._
import selenium.WebBrowser

trait OneServerPerLaunch extends SuiteMixin with ServerProvider { this: Suite =>

  /**
   * An implicit instance of `FakeApplication`.
   *
   * This trait's implementation initializes this `lazy` `val` with a new instance of `FakeApplication` with
   * parameters set to their defaults. Override this `lazy` `val` if you need a `FakeApplication` created with non-default parameter values.
   */
  implicit lazy val app: FakeApplication = OneServerPerLaunch.getFakeAppInstance

  /**
   * The port used by the `TestServer`.  By default this will be set to the result returned from
   * `Helpers.testServerPort`. You can override this to provide a different port number.
   */
  lazy val port: Int = OneServerPerLaunch.getPortInstance

  /**
   * Invokes `start` on a new `TestServer` created with the `FakeApplication` provided by `app` and the
   * port number defined by `port`, places the `FakeApplication` and port number into the `ConfigMap` under the keys
   * `org.scalatestplus.play.app` and `org.scalatestplus.play.port`, respectively, to make
   * them available to nested suites; calls `super.run`; and lastly ensures the FakeApplication and test server are stopped after
   * all tests and nested suites have completed.
   *
   * @param testName an optional name of one test to run. If `None`, all relevant tests should be run.
   *                 I.e., `None` acts like a wildcard that means run all relevant tests in this `Suite`.
   * @param args the `Args` for this run
   * @return a `Status` object that indicates when all tests and nested suites started by this method have completed, and whether or not a failure occurred.
   */
  abstract override def run(testName: Option[String], args: Args): Status = {
    OneServerPerLaunch.getTestServerInstance
    //testServer.start()
    try {
      val newConfigMap = args.configMap + ("org.scalatestplus.play.app" -> app) + ("org.scalatestplus.play.port" -> port)
      val newArgs = args.copy(configMap = newConfigMap)
      val status = super.run(testName, newArgs)
      status.whenCompleted { _ => OneServerPerLaunch.close }
      status
    }
    catch { // In case the suite aborts, ensure the server is stopped
      case ex: Throwable =>
        OneServerPerLaunch.close
        // testServer.stop()
        throw ex
    }
  }
}

object OneServerPerLaunch {

  private var testServer:TestServer = null
  var shouldClose = false
  var app: FakeApplication = null
  var port: Int = -1


  def getFakeAppInstance: FakeApplication = {
    if (app == null) {
      app = new FakeApplication()
    }
    app
  }

  def getPortInstance: Int = {
    if (port == -1) {
      port = Helpers.testServerPort
    }
    port
  }

  def getTestServerInstance: TestServer = {
    if (testServer == null) {
      testServer = TestServer(getPortInstance, getFakeAppInstance)
      testServer.start()
    }

    testServer
  }

  def close: Unit = {
    if (shouldClose && testServer != null) {
      testServer.stop()
      testServer = null
    }
    shouldClose = false
  }
}

