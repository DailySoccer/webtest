package unusual.model

import unusual.model.FieldPos._

class Lineup {

  var soccerPlayerList = Array[SoccerPlayer]()

  def price: Int = {
    soccerPlayerList.foldLeft(0) { (acc, curr) => acc + curr.salary }
  }

}


object Lineup {

  val TIME_0_EXPENSIVE_LIST = Array(
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jefferson"         , POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("vedran corluka"    , POS_DEFENSE    , 7000)
                                     , new SoccerPlayer ("miguel layún"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("francisco javier ro", POS_DEFENSE   , 13000)
                                     , new SoccerPlayer ("henri bedimo"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("paulinho"          , POS_MIDDLE     , 4000)
                                     , new SoccerPlayer ("marco fabián"      , POS_MIDDLE     , 6000)
                                     , new SoccerPlayer ("leroy fer"         , POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("isaác brizuela"    , POS_MIDDLE     , 7000)
                                     , new SoccerPlayer ("dirk kuyt"         , POS_FORWARD    , 4500)
                                     , new SoccerPlayer ("eric maxim choupo-m", POS_FORWARD   , 12000)
                                   )
      lineup
    },
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jung sung-ryong"   , POS_GOAL_KEEPER, 7500)
                                     , new SoccerPlayer ("francisco javier ro", POS_DEFENSE   , 13000)
                                     , new SoccerPlayer ("vladimir granat"   , POS_DEFENSE    , 7500)
                                     , new SoccerPlayer ("paul aguilar"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("kwak tae-hwi"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("jean makoun"       , POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("josé juan vázquez" , POS_MIDDLE     , 8500)
                                     , new SoccerPlayer ("eyong enoh"        , POS_MIDDLE     , 5000)
                                     , new SoccerPlayer ("han kook-young"    , POS_MIDDLE     , 7000)
                                     , new SoccerPlayer ("alexander kokorin" , POS_FORWARD    , 8500)
                                     , new SoccerPlayer ("oribe peralta"     , POS_FORWARD    , 6500)
                                   )
      lineup
    }, {
      val lineup = new Lineup
      // ESTA ALINEACION NO ESTA CONFIGURADA
      lineup.soccerPlayerList = Array( new SoccerPlayer ("sergio romero"     , POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("dante"             , POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("maxwell"           , POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("marcos rojo"       , POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("maicon"            , POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("koo ja-cheol"      , POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("luka modric"       , POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("javad nekounam"    , POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("han kook-young"    , POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("lionel messi"      , POS_FORWARD    , 4500)
                                     , new SoccerPlayer ("kevin mirallas"    , POS_FORWARD    , 4500)
      )
      lineup
    }
  )


  val TIME_0_AFFORDABLE_LIST = Array(
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jefferson"         , POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("dante"             , POS_DEFENSE    , 2500)
                                     , new SoccerPlayer ("miguel layún"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("nyom"              , POS_DEFENSE    , 2000)
                                     , new SoccerPlayer ("henri bedimo"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("paulinho"          , POS_MIDDLE     , 4000)
                                     , new SoccerPlayer ("koke"              , POS_MIDDLE     , 2000)
                                     , new SoccerPlayer ("leroy fer"         , POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("xavi"              , POS_MIDDLE     , 2000)
                                     , new SoccerPlayer ("hulk"              , POS_FORWARD    , 2000)
                                     , new SoccerPlayer ("fred"              , POS_FORWARD    , 2000)
      )
      lineup
    }, {
      val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jung sung-ryong"   , POS_GOAL_KEEPER, 7500)
                                     , new SoccerPlayer ("nyom"              , POS_DEFENSE    , 2000)
                                     , new SoccerPlayer ("vladimir granat"   , POS_DEFENSE    , 7500)
                                     , new SoccerPlayer ("paul aguilar"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("kwak tae-hwi"      , POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("jean makoun"       , POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("carlos peña"       , POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("eyong enoh"        , POS_MIDDLE     , 5000)
                                     , new SoccerPlayer ("ha dae-sung"       , POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("fabrice"           , POS_FORWARD    , 3500)
                                     , new SoccerPlayer ("alan pulido"       , POS_FORWARD    , 5500)
      )
      lineup
    }, {
      val lineup = new Lineup
      // ESTA ALINEACION NO ESTA CONFIGURADA
      lineup.soccerPlayerList = Array( new SoccerPlayer ("sergio romero"     , POS_GOAL_KEEPER, 13800)
                                     , new SoccerPlayer ("dante"             , POS_DEFENSE    , 6200)
                                     , new SoccerPlayer ("maxwell"           , POS_DEFENSE    , 7800)
                                     , new SoccerPlayer ("henrique"          , POS_DEFENSE    , 6200)
                                     , new SoccerPlayer ("maicon"            , POS_DEFENSE    , 9600)
                                     , new SoccerPlayer ("koo ja-cheol"      , POS_MIDDLE     , 8300)
                                     , new SoccerPlayer ("luka modric"       , POS_MIDDLE     , 8000)
                                     , new SoccerPlayer ("javad nekounam"    , POS_MIDDLE     , 8000)
                                     , new SoccerPlayer ("han kook-young"    , POS_MIDDLE     , 7200)
                                     , new SoccerPlayer ("alireza jahanbakhsh", POS_FORWARD   , 5600)
                                     , new SoccerPlayer ("kevin mirallas"    , POS_FORWARD    , 9300)
                                  )
      lineup
    }
  )

}
