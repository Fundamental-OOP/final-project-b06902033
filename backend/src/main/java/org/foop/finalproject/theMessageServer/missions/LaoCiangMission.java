package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class LaoCiangMission extends Mission {
    public LaoCiangMission(){
        description = "獲得三張或以上的紅色情報。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        return player.getIntelligences().get(GameCardColor.RED.type).size() >= 3;
    }
}
