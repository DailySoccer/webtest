package unusual.tests.integrityTest.runner

import org.scalatest.Sequential
import unusual.model.Resolution
import unusual.tests.integrityTest.IntegrityTest
import unusual.tests.simulatorController.{InitializerLeaguesTest, TimeShiftTest}

class IntegritySequentialTestRunner extends Sequential (
  new InitializerLeaguesTest(Resolution.ANY)

  , new TimeShiftTest(Resolution.ANY, 25, 8, 2014, 0, 0, 0, "2014/08/25 00:00:00 UTC", description = "go to Jornada 1")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 1, 9, 2014, 0, 0, 0, "2014/09/01 00:00:00 UTC", description = "go to Jornada 2")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 15, 9, 2014, 0, 0, 0, "2014/09/15 00:00:00 UTC", description = "go to Jornada 3")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 22, 9, 2014, 0, 0, 0, "2014/09/22 00:00:00 UTC", description = "go to Jornada 4")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 26, 9, 2014, 0, 0, 0, "2014/09/26 00:00:00 UTC", description = "go to Jornada 5")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 29, 9, 2014, 0, 0, 0, "2014/09/29 00:00:00 UTC", description = "go to Jornada 6")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 6, 10, 2014, 0, 0, 0, "2014/10/06 00:00:00 UTC", description = "go to Jornada 7")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 20, 10, 2014, 0, 0, 0, "2014/10/20 00:00:00 UTC", description = "go to Jornada 8")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 27, 10, 2014, 0, 0, 0, "2014/10/27 00:00:00 UTC", description = "go to Jornada 9")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 3, 11, 2014, 0, 0, 0, "2014/11/03 00:00:00 UTC", description = "go to Jornada 10")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 10, 11, 2014, 0, 0, 0, "2014/11/10 00:00:00 UTC", description = "go to Jornada 11")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 24, 11, 2014, 0, 0, 0, "2014/11/24 00:00:00 UTC", description = "go to Jornada 12")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 1, 12, 2014, 0, 0, 0, "2014/12/01 00:00:00 UTC", description = "go to Jornada 13")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 8, 12, 2014, 0, 0, 0, "2014/12/08 00:00:00 UTC", description = "go to Jornada 14")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 15, 12, 2014, 0, 0, 0, "2014/12/15 00:00:00 UTC", description = "go to Jornada 15")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 22, 12, 2014, 0, 0, 0, "2014/12/22 00:00:00 UTC", description = "go to Jornada 16")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 5, 1, 2015, 0, 0, 0, "2015/01/01 00:00:00 UTC", description = "go to Jornada 17")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 12, 1, 2015, 0, 0, 0, "2015/01/12 00:00:00 UTC", description = "go to Jornada 18")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 19, 1, 2015, 0, 0, 0, "2015/01/19 00:00:00 UTC", description = "go to Jornada 19")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 26, 1, 2015, 0, 0, 0, "2015/01/26 00:00:00 UTC", description = "go to Jornada 20")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 2, 2, 2015, 0, 0, 0, "2015/02/02 00:00:00 UTC", description = "go to Jornada 21")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 9, 2, 2015, 0, 0, 0, "2015/02/09 00:00:00 UTC", description = "go to Jornada 22")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 16, 2, 2015, 0, 0, 0, "2015/02/16 00:00:00 UTC", description = "go to Jornada 23")
  , new IntegrityTest
/*
  , new TimeShiftTest(Resolution.ANY, 23, 2, 2015, 0, 0, 0, "2015/02/23 00:00:00 UTC", description = "go to Jornada 24")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 2, 3, 2015, 0, 0, 0, "2015/03/02 00:00:00 UTC", description = "go to Jornada 25")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 9, 3, 2015, 0, 0, 0, "2015/03/09 00:00:00 UTC", description = "go to Jornada 26")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 16, 3, 2015, 0, 0, 0, "2015/03/16 00:00:00 UTC", description = "go to Jornada 27")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 23, 3, 2015, 0, 0, 0, "2015/03/23 00:00:00 UTC", description = "go to Jornada 28")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 6, 4, 2015, 0, 0, 0, "2015/04/06 00:00:00 UTC", description = "go to Jornada 29")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 10, 4, 2015, 0, 0, 0, "2015/04/10 00:00:00 UTC", description = "go to Jornada 30")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 13, 4, 2015, 0, 0, 0, "2015/04/13 00:00:00 UTC", description = "go to Jornada 31")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 20, 4, 2015, 0, 0, 0, "2015/04/20 00:00:00 UTC", description = "go to Jornada 32")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 27, 4, 2015, 0, 0, 0, "2015/04/27 00:00:00 UTC", description = "go to Jornada 33")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 1, 5, 2015, 0, 0, 0, "2015/05/01 00:00:00 UTC", description = "go to Jornada 34")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 4, 5, 2015, 0, 0, 0, "2015/05/04 00:00:00 UTC", description = "go to Jornada 35")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 11, 5, 2015, 0, 0, 0, "2015/05/11 00:00:00 UTC", description = "go to Jornada 36")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 18, 5, 2015, 0, 0, 0, "2015/05/18 00:00:00 UTC", description = "go to Jornada 37")
  , new IntegrityTest
  , new TimeShiftTest(Resolution.ANY, 25, 5, 2015, 0, 0, 0, "2015/05/25 00:00:00 UTC", description = "go to Jornada 38")
  , new IntegrityTest
 */
)
