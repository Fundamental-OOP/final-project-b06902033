package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;

public class LiFuMengMianJenMission extends Mission {
    public LiFuMengMianJenMission(){
        description = "一位女性宣告勝利。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        // TODO
        return false;
    }
}
