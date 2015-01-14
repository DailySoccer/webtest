Preparation
===========

If you want to test locally and not only on SauceLabs, you have to compile both the backend and webclient projects.
Don't forget to compile the webclient with "pub build" to make sure you are testing the latest version.

Launch the backend normally. You must be able to access the game through http://localhost:9000.

If you want to use **Chrome**, install Chrome's webdriver extension. "brew install chromedriver" works like a charm.

If you want to use **Safari**, should do a little trick finded in: https://code.google.com/p/selenium/issues/detail?id=7933
    
    1. Download: http://central.maven.org/maven2/org/seleniumhq/selenium/selenium-safari-driver/2.43.1/selenium-safari-driver-2.43.1.jar
    2. Unzip it
    3. Go to "./selenium-safari-driver-2.43.1/org/openqa/selenium"
    4. Execute "SafariDriver.safariextz"
    5. Ensure that extension is enabled. Open Safari preferences and extensions. Web driver checkbox should be on.

For Firefox you don't need anything. SBT downloads some libs that enable ScalaTest to connect with Firefox directly.


Running locally
===============

To run the tests locally, run in your terminal (RESOLUTION is optional):

    $ export RESOLUTION="{smartphone|desktop|tablet|all}"
    $ export URL="https://backend.epiceleven.localhost:9000"
    $ play test
    
Or via script:

    $ ./playTest [{-r|--resolution} {smartphone|desktop|tablet|all}] {-l|--localhost}
    
The default browser is Firefox. You can change it in test/SauceLabsFactory.scala (CHROME or FIREFOX).


Running remotely
================

Running remotely on SauceLabs is the default if you don't export the URL or unset it. Just run (RESOLUTION is optional):

    $ export RESOLUTION="{smartphone|desktop|tablet|all}"
    $ play test
    
Or via script:

    $ ./playTest [{-r|--resolution} {smartphone|desktop|tablet}]

Logger configuration
====================

You can change the log level by changing /conf/application-logger.xml.

The logger tag named 'unusual' refers to logging calls in our package (unusual). If u set the level to TRACE all logs
will be displayed.


Test filtering and running
==========================

You may want to filter out all tests except one. In /build.sbt, using testOptions we can select suites packages and
include/exclude tests based on tags.
```Scala
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner" // Paquete de tests
                                                                , "-n", "unusual.testTags.java.WIP" // Incluidas
                                                                , "-l", "unusual.testTags.java.DoesNotWorkYet" // Excluidas
                                                                , "-eIKNCHLPQ"
                                                                )
```

   - `-w` Test package to run. There must be a 'runner' in each test package that runs its tests sequentially.
   - `-n` Included test tags. Each test defines its own tags with `taggedAs XXXXX`. For example `taggedAs WIP`.
   - `-w` Excluded test tags.

If some argument does not appear, it is ignored and it works as expected. For example: if `-n` is commented out in this way 
`//, "-n", "unusual.testTags.java.WIP"`
then all test are included.


Test filter tuning
------------------

### Filter by test groups

You can find the list of test to run in each test runner. Each test not be executed must be commented out.


### Filter by concrete test

In each test class (`LobbyAuthTest`, `LobbyVisitorTest`, `EnterContestAuthTest`...) there are tests like:
```Scala
"test group" which doAnAction {

  "concrete test 1" in concreteTest1

  "concrete test 2" in concreteTest2

  "concrete test 3" in concreteTest3
  
  ...
}
```

    * To execute only some tests, a tag should be added to those tests and included in the `-n` argument.

    * To execute all tests except one, add a tag to it and include that tag in `-l`. That test will be ignored.

    * Multiple tags could be added.


Example:

Given this tests package:
```Scala
"test group" which doAnAction {

  "concrete test 1" taggedAs WIP in concreteTest1

  "concrete test 2" taggedAs(DoesNotWorkYet, WIP) in concreteTest2

  "concrete test 3" in concreteTest3
}
```

This sbt will execute concreteTest1 and concreteTest2
```Scala
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner"
                                                                , "-n", "unusual.testTags.java.WIP"
                                                                //, "-l", "unusual.testTags.java.DoesNotWorkYet"
                                                                , "-eIKNCHLPQ"
                                                                )
```     

This sbt will execute concreteTest1
```Scala
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner"
                                                                , "-n", "unusual.testTags.java.WIP"
                                                                , "-l", "unusual.testTags.java.DoesNotWorkYet"
                                                                , "-eIKNCHLPQ"
                                                                )
```  

This sbt will execute all tests
```Scala
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner"
                                                                //, "-n", "unusual.testTags.java.WIP"
                                                                //, "-l", "unusual.testTags.java.DoesNotWorkYet"
                                                                , "-eIKNCHLPQ"
                                                                )
```
