name := "test"

version := "1.0-SNAPSHOT"

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  , "-w", "unusual.tests.runner" // Paquete de tests
                                                                //, "-n", "unusual.testTags.java.WIP" // Incluidas
                                                                , "-l", "unusual.testTags.java.DoesNotWorkYet" // Excluidas
                                                                , "-eIKNCHLPQ"
                                                                )


libraryDependencies ++= Seq(
  cache,
  filters,
  "org.seleniumhq.selenium" % "selenium-java" % "2.44.0" % "test",
  "org.scalatestplus" % "play_2.10" % "1.0.0" % "test",
  "com.saucelabs" % "sauce_junit" % "2.1.4" % "test",
  "com.github.detro.ghostdriver" % "phantomjsdriver" % "1.1.0",
  "org.seleniumhq.selenium" % "selenium-safari-driver" % "2.43.1",
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % "2.3.1"
)

resolvers += "saucelabs-repository" at "http://repository-saucelabs.forge.cloudbees.com/release"

play.Project.playScalaSettings
