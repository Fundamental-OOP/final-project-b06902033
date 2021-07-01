package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class DaiLiMission extends Mission {
    public DaiLiMission(){
        description = "獲得三張或以上的藍情報。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        return player.getIntelligences().get(GameCardColor.BLUE.type).size() >= 3;
    }
}
