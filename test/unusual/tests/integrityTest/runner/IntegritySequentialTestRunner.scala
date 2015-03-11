package unusual.tests.integrityTest.runner

import org.scalatest.Sequential
import unusual.model.Resolution
import unusual.tests.integrityTest.IntegrityTest
import unusual.tests.runner.simulatorController.{InitializerLeaguesTest, FinisherLeaguesTest, TimeShiftTest}

class IntegritySequentialTestRunner extends Sequential (
  new InitializerLeaguesTest(Resolution.ANY)

  , new TimeShiftTest(Resolution.ANY, 26, 8, 2014, 0, 0, 0, "2014/08/26 00:00:00 UTC", description = "go to Jornada 1")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 1, 9, 2014, 0, 0, 0, "2014/09/01 00:00:00 UTC", description = "go to Jornada 2")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 16, 9, 2014, 0, 0, 0, "2014/09/16 00:00:00 UTC", description = "go to Jornada 3")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 23, 9, 2014, 0, 0, 0, "2014/09/23 00:00:00 UTC", description = "go to Jornada 4")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 26, 9, 2014, 0, 0, 0, "2014/09/26 00:00:00 UTC", description = "go to Jornada 5")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 29, 9, 2014, 0, 0, 0, "2014/09/29 00:00:00 UTC", description = "go to Jornada 6")
  , new IntegrityTest
  /*
  , new TimeShiftTest(Resolution.ANY, 6, 10, 2014, 0, 0, 0, "2014/10/06 00:00:00 UTC", description = "go to Jornada 7")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 21, 10, 2014, 0, 0, 0, "2014/10/21 00:00:00 UTC", description = "go to Jornada 8")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 27, 10, 2014, 0, 0, 0, "2014/10/27 00:00:00 UTC", description = "go to Jornada 9")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 4, 11, 2014, 0, 0, 0, "2014/11/04 00:00:00 UTC", description = "go to Jornada 10")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 10, 11, 2014, 0, 0, 0, "2014/11/10 00:00:00 UTC", description = "go to Jornada 11")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 25, 11, 2014, 0, 0, 0, "2014/11/25 00:00:00 UTC", description = "go to Jornada 12")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 2, 12, 2014, 0, 0, 0, "2014/12/02 00:00:00 UTC", description = "go to Jornada 13")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 9, 12, 2014, 0, 0, 0, "2014/12/09 00:00:00 UTC", description = "go to Jornada 14")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 16, 12, 2014, 0, 0, 0, "2014/12/16 00:00:00 UTC", description = "go to Jornada 15")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 22, 12, 2014, 0, 0, 0, "2014/12/22 00:00:00 UTC", description = "go to Jornada 16")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 6, 1, 2015, 0, 0, 0, "2015/01/06 00:00:00 UTC", description = "go to Jornada 17")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 13, 1, 2015, 0, 0, 0, "2015/01/13 00:00:00 UTC", description = "go to Jornada 18")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 19, 1, 2015, 0, 0, 0, "2015/01/19 00:00:00 UTC", description = "go to Jornada 19")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 27, 1, 2015, 0, 0, 0, "2015/01/27 00:00:00 UTC", description = "go to Jornada 20")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 3, 2, 2015, 0, 0, 0, "2015/02/03 00:00:00 UTC", description = "go to Jornada 21")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 10, 2, 2015, 0, 0, 0, "2015/02/10 00:00:00 UTC", description = "go to Jornada 22")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 17, 2, 2015, 0, 0, 0, "2015/02/17 00:00:00 UTC", description = "go to Jornada 23")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 24, 2, 2015, 0, 0, 0, "2015/02/24 00:00:00 UTC", description = "go to Jornada 24")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 3, 3, 2015, 0, 0, 0, "2015/03/03 00:00:00 UTC", description = "go to Jornada 25")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 10, 3, 2015, 0, 0, 0, "2015/03/10 00:00:00 UTC", description = "go to Jornada 26")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 17, 3, 2015, 0, 0, 0, "2015/03/17 00:00:00 UTC", description = "go to Jornada 27")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 24, 3, 2015, 0, 0, 0, "2015/03/24 00:00:00 UTC", description = "go to Jornada 28")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 7, 4, 2015, 0, 0, 0, "2015/04/07 00:00:00 UTC", description = "go to Jornada 29")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 10, 4, 2015, 0, 0, 0, "2015/04/10 00:00:00 UTC", description = "go to Jornada 30")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 14, 4, 2015, 0, 0, 0, "2015/04/14 00:00:00 UTC", description = "go to Jornada 31")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 21, 4, 2015, 0, 0, 0, "2015/04/21 00:00:00 UTC", description = "go to Jornada 32")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 28, 4, 2015, 0, 0, 0, "2015/04/28 00:00:00 UTC", description = "go to Jornada 33")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 1, 5, 2015, 0, 0, 0, "2015/05/01 00:00:00 UTC", description = "go to Jornada 34")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 5, 5, 2015, 0, 0, 0, "2015/05/05 00:00:00 UTC", description = "go to Jornada 35")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 12, 5, 2015, 0, 0, 0, "2015/05/12 00:00:00 UTC", description = "go to Jornada 36")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 19, 5, 2015, 0, 0, 0, "2015/05/19 00:00:00 UTC", description = "go to Jornada 37")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 26, 5, 2015, 0, 0, 0, "2015/05/26 00:00:00 UTC", description = "go to Jornada 38")
  , new IntegrityTest
 */

  , new FinisherLeaguesTest(Resolution.ANY)
)
