package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class LiuJieMission extends Mission {
    public LiuJieMission(){
        description = "獲得六張或以上的任意情報。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        int redIntelligenceNum = player.getIntelligences().get(GameCardColor.RED.type).size();
        int blackIntelligenceNum = player.getIntelligences().get(GameCardColor.BLUE.type).size();
        int blueIntelligenceNum = player.getIntelligences().get(GameCardColor.BLACK.type).size();
        return (redIntelligenceNum + blackIntelligenceNum + blueIntelligenceNum) >= 6;
    }
}
