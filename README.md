Preparation
===========

You need first to prepare both backend and webclient projects. If you want to test locally what you're doing.

Afterwards, when you can access the project through http://localhost:9000 or any local URL reachable by a normal web
browser, you need to install Selenium's firefox webdriver or chrome webdriver.

For firefox, sbt downloads every needed lib and scalatest connects to firefox.

For the second one, "brew install chromedriver" works like a charm.

You will need to set your driver accordingly in test/SauceLabsFactory.scala (CHROME or FIREFOX)

Once you have that, to run the tests locally, run in your terminal:

export URL="http://localhost:9000"
play

To run on SauceLabs just do not export URL or unset it.

Test Filtering
==============

You may want to filter test to run only one. In /build.sbt in testOptions, we can select suites package, included tag
and excluded tags on tagged test.

Logger configuration
====================

You can change log level by changing /conf/application-logger.xml
logger tag named as unusual refers to logger calls in our package (unusual). If u set level to TRACE, every log should be
displayed.
