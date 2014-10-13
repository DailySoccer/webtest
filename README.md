Preparation
===========

You need first to prepare both backend and webclient projects. If you want to test locally what you're doing.

Afterwards, when you can access the project through http://localhost:9000 or any local URL reachable by a normal web
browser, you need to install Selenium's firefox webdriver or chrome webdriver.

For firefox, sbt downloads every needed lib and scalatest connects to firefox.

For the second one, "brew install chromedriver" works like a charm.

You will need to set your driver accordingly in test/SauceLabsFactory.scala (CHROME or FIREFOX)

Once you have that, to run the tests locally, run in your terminal:

    $ export URL="http://localhost:9000"
    $ play test

To run on SauceLabs just do not export the URL or unset it.


Logger configuration
====================

You can change log level by changing /conf/application-logger.xml
logger tag named as unusual refers to logger calls in our package (unusual). If u set level to TRACE, every log should be
displayed.


Test filtering and running
==========================

You may want to filter test to run only one. In /build.sbt in testOptions, we can select suites package, included tag
and excluded tags on tagged test.
    
    ```sbt
    testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner" // Paquete de tests
                                                                    , "-n", "unusual.testTags.java.WIP" // Incluidas
                                                                    , "-l", "unusual.testTags.java.DoesNotWorkYet" // Excluidas
                                                                    , "-eIKNCHLPQ"
                                                                    )`
    ```                                                                

   - `-w` indicates test package to run, in each test package should be a runner package that runs that test package sequentially
   - `-n` indicates included test tags. Each test should indicates their tags with `taggedAs XXXXX` for example `taggedAs WIP`
   - `-w` indicates excluded test tags.

If some argument does not appear, is ignored and it does what is expected. For example: if `-n` is commented this way `//, "-n", "unusual.testTags.java.WIP" // Incluidas`, then all test are included.


Test filter tuning
------------------

### Filter by test groups

In each test runner the list of tests can be find. Each test which should not be executed should be commented.


### Filter by concrete test

In each test class (`LobbyAuthTest`, `LobbyVisitorTest`, `EnterContestAuthTest`...) there are tests like:
    
    ```scala
    
        "test group" which doAnAction {
    
          "concrete test 1" in concreteTest1
    
          "concrete test 2" in concreteTest2
    
          "concrete test 3" in concreteTest3
          
          ...
        }
    ```

To execute only some tests, a tag should be added to the tests. That tag should be included in build.sbt file (`-n` argument as explained before).
To execute all test but some, a tag should be added too. That tag should be included in `-l` argument this time. That tests will be ignored.
Multiple tags could be added.


For example:

With this test suite configuration:

    ```scala
    
        "test group" which doAnAction {
    
          "concrete test 1" taggedAs WIP in concreteTest1
    
          "concrete test 2" taggedAs(DoesNotWorkYet, WIP) in concreteTest2
    
          "concrete test 3" in concreteTest3
          
          ...
        }
    ```

This sbt will execute concreteTest1 and concreteTest2
```sbt
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner" // Paquete de tests
                                                                , "-n", "unusual.testTags.java.WIP" // Incluidas
                                                                //, "-l", "unusual.testTags.java.DoesNotWorkYet" // Excluidas
                                                                , "-eIKNCHLPQ"
                                                                )`
```     

This sbt will execute concreteTest1
```sbt
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner" // Paquete de tests
                                                                , "-n", "unusual.testTags.java.WIP" // Incluidas
                                                                , "-l", "unusual.testTags.java.DoesNotWorkYet" // Excluidas
                                                                , "-eIKNCHLPQ"
                                                                )`
```     


This sbt will execute all test
```sbt
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner" // Paquete de tests
                                                                //, "-n", "unusual.testTags.java.WIP" // Incluidas
                                                                //, "-l", "unusual.testTags.java.DoesNotWorkYet" // Excluidas
                                                                , "-eIKNCHLPQ"
                                                                )`
```     




