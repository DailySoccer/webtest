name := "test"

version := "1.0-SNAPSHOT"

//javaOptions += "-l fantasyTags.DbTest"
//testOptions in Test += Tests.Argument("v")
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest  ,"-w", "unusual.tests.enterContestTest"
                                                                //,"-n", "unusual.testTags.java.WIP"
                                                                //,"-l", "unusual.testTags.java.DoesNotWorkYet"
                                                                , "-eIKNCHLPQ"
                                                                )

libraryDependencies ++= Seq(
  cache,
  filters,
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0" % "test",
  "org.scalatestplus" % "play_2.10" % "1.0.0" % "test",
  "com.saucelabs" % "sauce_junit" % "2.0.5" % "test"
)

resolvers += "saucelabs-repository" at "http://repository-saucelabs.forge.cloudbees.com/release"

play.Project.playScalaSettings
