package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class CiBaiMission extends Mission {
    public CiBaiMission(){
        description = "獲得五張或以上的紅藍情報。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        int redIntelligenceNum = player.getIntelligences().get(GameCardColor.RED.type).size();
        int blueIntelligenceNum = player.getIntelligences().get(GameCardColor.BLUE.type).size();
        return (redIntelligenceNum + blueIntelligenceNum) >= 5;
    }
}
