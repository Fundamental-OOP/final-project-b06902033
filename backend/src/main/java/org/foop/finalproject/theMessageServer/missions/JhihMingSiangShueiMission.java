package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;

public class JhihMingSiangShueiMission extends Mission {
    public JhihMingSiangShueiMission(){
        description = "No mission";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        return false;
    }
}
