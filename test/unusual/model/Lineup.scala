package unusual.model

class Lineup {

  var soccerPlayerList = Array[SoccerPlayer]()

  def price: Int = {
    var money = 0
    for (soccer <- soccerPlayerList) { money += soccer.salary }
    money
  }

}


object Lineup {

  val TIME_0_EXPENSIVE_LIST = Array(
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jefferson"         , SoccerPlayer.POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("vedran corluka"    , SoccerPlayer.POS_DEFENSE    , 7000)
                                     , new SoccerPlayer ("miguel layún"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("francisco javier ro", SoccerPlayer.POS_DEFENSE   , 13000)
                                     , new SoccerPlayer ("henri bedimo"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("paulinho"          , SoccerPlayer.POS_MIDDLE     , 4000)
                                     , new SoccerPlayer ("marco fabián"      , SoccerPlayer.POS_MIDDLE     , 6000)
                                     , new SoccerPlayer ("leroy fer"         , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("isaác brizuela"    , SoccerPlayer.POS_MIDDLE     , 7000)
                                     , new SoccerPlayer ("dirk kuyt"         , SoccerPlayer.POS_FORWARD    , 4500)
                                     , new SoccerPlayer ("eric maxim choupo-m", SoccerPlayer.POS_FORWARD   , 12000)
                                   )
      lineup
    },
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jung sung-ryong"   , SoccerPlayer.POS_GOAL_KEEPER, 7500)
                                     , new SoccerPlayer ("francisco javier ro", SoccerPlayer.POS_DEFENSE   , 13000)
                                     , new SoccerPlayer ("vladimir granat"   , SoccerPlayer.POS_DEFENSE    , 7500)
                                     , new SoccerPlayer ("paul aguilar"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("kwak tae-hwi"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("jean makoun"       , SoccerPlayer.POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("josé juan vázquez" , SoccerPlayer.POS_MIDDLE     , 8500)
                                     , new SoccerPlayer ("eyong enoh"        , SoccerPlayer.POS_MIDDLE     , 5000)
                                     , new SoccerPlayer ("han kook-young"    , SoccerPlayer.POS_MIDDLE     , 7000)
                                     , new SoccerPlayer ("alexander kokorin" , SoccerPlayer.POS_FORWARD    , 8500)
                                     , new SoccerPlayer ("oribe peralta"     , SoccerPlayer.POS_FORWARD    , 6500)
                                   )
      lineup
    }, {
      val lineup = new Lineup
    // ESTA ALINEACION NO ESTA CONFIGURADA
      lineup.soccerPlayerList = Array( new SoccerPlayer ("sergio romero"     , SoccerPlayer.POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("dante"             , SoccerPlayer.POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("maxwell"           , SoccerPlayer.POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("marcos rojo"       , SoccerPlayer.POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("maicon"            , SoccerPlayer.POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("koo ja-cheol"      , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("luka modric"       , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("javad nekounam"    , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("han kook-young"    , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("lionel messi"      , SoccerPlayer.POS_FORWARD    , 4500)
                                     , new SoccerPlayer ("kevin mirallas"    , SoccerPlayer.POS_FORWARD    , 4500)
      )
      lineup
    }
  )


  val TIME_0_AFFORDABLE_LIST = Array(
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jefferson"         , SoccerPlayer.POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("dante"             , SoccerPlayer.POS_DEFENSE    , 2500)
                                     , new SoccerPlayer ("miguel layun"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("nyom"              , SoccerPlayer.POS_DEFENSE    , 2000)
                                     , new SoccerPlayer ("henri bedimo"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("paulinho"          , SoccerPlayer.POS_MIDDLE     , 4000)
                                     , new SoccerPlayer ("koke"              , SoccerPlayer.POS_MIDDLE     , 2000)
                                     , new SoccerPlayer ("leroy fer"         , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("xavi"              , SoccerPlayer.POS_MIDDLE     , 2000)
                                     , new SoccerPlayer ("hulk"              , SoccerPlayer.POS_FORWARD    , 2000)
                                     , new SoccerPlayer ("fred"              , SoccerPlayer.POS_FORWARD    , 2000)
      )
      lineup
    }, {
      val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jung sung-ryong"   , SoccerPlayer.POS_GOAL_KEEPER, 7500)
                                     , new SoccerPlayer ("nyom"              , SoccerPlayer.POS_DEFENSE    , 2000)
                                     , new SoccerPlayer ("vladimir granat"   , SoccerPlayer.POS_DEFENSE    , 7500)
                                     , new SoccerPlayer ("paul aguilar"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("kwak tae-hwi"      , SoccerPlayer.POS_DEFENSE    , 6000)
                                     , new SoccerPlayer ("jean makoun"       , SoccerPlayer.POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("carlos peña"       , SoccerPlayer.POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("eyong enoh"        , SoccerPlayer.POS_MIDDLE     , 5000)
                                     , new SoccerPlayer ("ha dae-sung"       , SoccerPlayer.POS_MIDDLE     , 5500)
                                     , new SoccerPlayer ("fabrice"           , SoccerPlayer.POS_FORWARD    , 3500)
                                     , new SoccerPlayer ("alan pulido"       , SoccerPlayer.POS_FORWARD    , 5500)
      )
      lineup
    }, {
      val lineup = new Lineup
    // ESTA ALINEACION NO ESTA CONFIGURADA
      lineup.soccerPlayerList = Array( new SoccerPlayer ("sergio romero"     , SoccerPlayer.POS_GOAL_KEEPER, 13800)
                                     , new SoccerPlayer ("dante"             , SoccerPlayer.POS_DEFENSE    , 6200)
                                     , new SoccerPlayer ("maxwell"           , SoccerPlayer.POS_DEFENSE    , 7800)
                                     , new SoccerPlayer ("henrique"          , SoccerPlayer.POS_DEFENSE    , 6200)
                                     , new SoccerPlayer ("maicon"            , SoccerPlayer.POS_DEFENSE    , 9600)
                                     , new SoccerPlayer ("koo ja-cheol"      , SoccerPlayer.POS_MIDDLE     , 8300)
                                     , new SoccerPlayer ("luka modric"       , SoccerPlayer.POS_MIDDLE     , 8000)
                                     , new SoccerPlayer ("javad nekounam"    , SoccerPlayer.POS_MIDDLE     , 8000)
                                     , new SoccerPlayer ("han kook-young"    , SoccerPlayer.POS_MIDDLE     , 7200)
                                     , new SoccerPlayer ("alireza jahanbakhsh", SoccerPlayer.POS_FORWARD   , 5600)
                                     , new SoccerPlayer ("kevin mirallas"    , SoccerPlayer.POS_FORWARD    , 9300)
                                  )
      lineup
    }
  )

}
