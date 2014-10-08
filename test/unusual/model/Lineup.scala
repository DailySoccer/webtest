package unusual.model

class Lineup {

  var soccerPlayerList = Array[SoccerPlayer]()

}


object Lineup {

  val TIME_0_EXPENSIVE_LIST = Array(
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("guillermo ochoa"   , SoccerPlayer.POS_GOAL_KEEPER, 10100)
                                     , new SoccerPlayer ("miguel layún"      , SoccerPlayer.POS_DEFENSE    , 9000)
                                     , new SoccerPlayer ("maxwell"           , SoccerPlayer.POS_DEFENSE    , 7800)
                                     , new SoccerPlayer ("henrique"          , SoccerPlayer.POS_DEFENSE    , 6200)
                                     , new SoccerPlayer ("thiago silva"      , SoccerPlayer.POS_DEFENSE    , 14300)
                                     , new SoccerPlayer ("paulinho"          , SoccerPlayer.POS_MIDDLE     , 12800)
                                     , new SoccerPlayer ("sammir"            , SoccerPlayer.POS_MIDDLE     , 6100)
                                     , new SoccerPlayer ("willian"           , SoccerPlayer.POS_MIDDLE     , 10100)
                                     , new SoccerPlayer ("oscar"             , SoccerPlayer.POS_MIDDLE     , 14400)
                                     , new SoccerPlayer ("robin van persie"  , SoccerPlayer.POS_FORWARD    , 13700)
                                     , new SoccerPlayer ("ante rebic"        , SoccerPlayer.POS_FORWARD    , 5200)
                                   )
      lineup
    },
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("mattia perin"      , SoccerPlayer.POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("juanfran"          , SoccerPlayer.POS_DEFENSE    , 7200)
                                     , new SoccerPlayer ("ron vlaar"         , SoccerPlayer.POS_DEFENSE    , 14200)
                                     , new SoccerPlayer ("stefan de vrij"    , SoccerPlayer.POS_DEFENSE    , 14600)
                                     , new SoccerPlayer ("terence kongolo"   , SoccerPlayer.POS_DEFENSE    , 6900)
                                     , new SoccerPlayer ("thiago motta"      , SoccerPlayer.POS_MIDDLE     , 5800)
                                     , new SoccerPlayer ("koke"              , SoccerPlayer.POS_MIDDLE     , 8000)
                                     , new SoccerPlayer ("juan mata"         , SoccerPlayer.POS_MIDDLE     , 6000)
                                     , new SoccerPlayer ("francesc fábregas" , SoccerPlayer.POS_MIDDLE     , 5800)
                                     , new SoccerPlayer ("danny welbeck"     , SoccerPlayer.POS_FORWARD    , 6300)
                                     , new SoccerPlayer ("ciro immobile"     , SoccerPlayer.POS_FORWARD    , 5200)
                                   )
      lineup
    }, {
      val lineup = new Lineup
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


  val TIME_0_EXPEND_ALL_MONEY_LIST = Array(
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("guillermo ochoa"   , SoccerPlayer.POS_GOAL_KEEPER, 10100)
                                     , new SoccerPlayer ("jordi alba"        , SoccerPlayer.POS_DEFENSE    , 8200)
                                     , new SoccerPlayer ("maxwell"           , SoccerPlayer.POS_DEFENSE    , 7800)
                                     , new SoccerPlayer ("henrique"          , SoccerPlayer.POS_DEFENSE    , 6200)
                                     , new SoccerPlayer ("gordon schildenfeld", SoccerPlayer.POS_DEFENSE   , 4500)
                                     , new SoccerPlayer ("paulinho"          , SoccerPlayer.POS_MIDDLE     , 12800)
                                     , new SoccerPlayer ("sammir"            , SoccerPlayer.POS_MIDDLE     , 6100)
                                     , new SoccerPlayer ("willian"           , SoccerPlayer.POS_MIDDLE     , 10100)
                                     , new SoccerPlayer ("Ognjen vukojevic"  , SoccerPlayer.POS_MIDDLE     , 4500)
                                     , new SoccerPlayer ("fabrice"           , SoccerPlayer.POS_FORWARD    , 4500)
                                     , new SoccerPlayer ("ante rebic"        , SoccerPlayer.POS_FORWARD    , 5200)
      )
      lineup
    },
    { val lineup = new Lineup
      lineup.soccerPlayerList = Array( new SoccerPlayer ("mattia perin"      , SoccerPlayer.POS_GOAL_KEEPER, 4500)
                                     , new SoccerPlayer ("juanfran"          , SoccerPlayer.POS_DEFENSE    , 7200)
                                     , new SoccerPlayer ("raúl albiol"       , SoccerPlayer.POS_DEFENSE    , 7100)
                                     , new SoccerPlayer ("joël veltman"      , SoccerPlayer.POS_DEFENSE    , 7200)
                                     , new SoccerPlayer ("terence kongolo"   , SoccerPlayer.POS_DEFENSE    , 6900)
                                     , new SoccerPlayer ("thiago motta"      , SoccerPlayer.POS_MIDDLE     , 5800)
                                     , new SoccerPlayer ("koke"              , SoccerPlayer.POS_MIDDLE     , 8000)
                                     , new SoccerPlayer ("juan mata"         , SoccerPlayer.POS_MIDDLE     , 6000)
                                     , new SoccerPlayer ("francesc fábregas" , SoccerPlayer.POS_MIDDLE     , 5800)
                                     , new SoccerPlayer ("danny welbeck"     , SoccerPlayer.POS_FORWARD    , 6300)
                                     , new SoccerPlayer ("ciro immobile"     , SoccerPlayer.POS_FORWARD    , 5200)
      )
      lineup
    }, {
      val lineup = new Lineup
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
