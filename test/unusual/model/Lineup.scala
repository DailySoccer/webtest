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
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jefferson"         , SoccerPlayer.POS_GOAL_KEEPER, 7979)
                                     , new SoccerPlayer ("vedran corluka"    , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("miguel layún"      , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("francisco javier ro", SoccerPlayer.POS_DEFENSE   , 7979)
                                     , new SoccerPlayer ("henri bedimo"      , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("paulinho"          , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("marco fabián"      , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("leroy fer"         , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("isaác brizuela"    , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("dirk kuyt"         , SoccerPlayer.POS_FORWARD    , 7979)
                                     , new SoccerPlayer ("fred"              , SoccerPlayer.POS_FORWARD    , 7979)
                                   )
      lineup
    },
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("jung sung-ryong"   , SoccerPlayer.POS_GOAL_KEEPER, 7979)
                                     , new SoccerPlayer ("kim young-gwon"    , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("vladimir granat"   , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("paul aguilar"      , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("kwak tae-hwi"      , SoccerPlayer.POS_DEFENSE    , 7979)
                                     , new SoccerPlayer ("jean makoun"       , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("josé juan vázquez" , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("eyong enoh"        , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("han kook-young"    , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("alexander kokorin" , SoccerPlayer.POS_FORWARD    , 7979)
                                     , new SoccerPlayer ("oribe peralta"     , SoccerPlayer.POS_FORWARD    , 7979)
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
      lineup.soccerPlayerList = Array(  new SoccerPlayer ("julio césar"       , SoccerPlayer.POS_GOAL_KEEPER, 5300)
                                      , new SoccerPlayer ("diego reyes"       , SoccerPlayer.POS_DEFENSE    ,    0)
                                      , new SoccerPlayer ("dany nounkeu"      , SoccerPlayer.POS_DEFENSE    , 4500)
                                      , new SoccerPlayer ("joël veltman"      , SoccerPlayer.POS_DEFENSE    , 5200)
                                      , new SoccerPlayer ("joel matip"        , SoccerPlayer.POS_DEFENSE    , 5700)
                                      , new SoccerPlayer ("alexandre song"    , SoccerPlayer.POS_MIDDLE     , 5900)
                                      , new SoccerPlayer ("marco fabián"      , SoccerPlayer.POS_MIDDLE     , 7979)
                                      , new SoccerPlayer ("leroy fer"         , SoccerPlayer.POS_MIDDLE     , 7979)
                                      , new SoccerPlayer ("isaác brizuela"    , SoccerPlayer.POS_MIDDLE     , 7979)
                                      , new SoccerPlayer ("javier hernández"  , SoccerPlayer.POS_FORWARD    , 5500)
                                      , new SoccerPlayer ("fred"              , SoccerPlayer.POS_FORWARD    , 7979)
      )
      lineup
    },
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("juri lodygin"      , SoccerPlayer.POS_GOAL_KEEPER, 5500)
                                     , new SoccerPlayer ("diego reyes"       , SoccerPlayer.POS_DEFENSE    ,    0)
                                     , new SoccerPlayer ("dany nounkeu"      , SoccerPlayer.POS_DEFENSE    , 4500)
                                     , new SoccerPlayer ("georgy schennikov" , SoccerPlayer.POS_DEFENSE    , 4600)
                                     , new SoccerPlayer ("vassili berezoutski", SoccerPlayer.POS_DEFENSE   , 5100)
                                     , new SoccerPlayer ("jean makoun"       , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("josé juan vázquez" , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("eyong enoh"        , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("han kook-young"    , SoccerPlayer.POS_MIDDLE     , 7979)
                                     , new SoccerPlayer ("ji dong-won"      , SoccerPlayer.POS_FORWARD    ,    0)
                                     , new SoccerPlayer ("oribe peralta"     , SoccerPlayer.POS_FORWARD    , 7979)
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
