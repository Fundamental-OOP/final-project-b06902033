package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.Camp;
import org.foop.finalproject.theMessageServer.enums.PlayerStatus;

public class SiaoBaiMission extends Mission {
    public SiaoBaiMission(){
        description = "你第一個死亡，或潛伏與軍情雙方的其中一方全滅。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        boolean firstDieFlag = game.getDeadPlayers().size() == 1 && game.getDeadPlayers().contains(player);
        boolean blueCampAllDieFlag = true;
        boolean redCampAllDieFlag = true;
        // Check blue camp 全滅
        for(Player otherPlayer: game.getPlayers()){
            switch(otherPlayer.getCamp()){
                case RED:
                    if(otherPlayer.isAlive()){
                        redCampAllDieFlag = false;
                    }
                    break;
                case BLUE:
                    if(otherPlayer.isAlive()){
                        blueCampAllDieFlag = false;
                    }
                    break;
                default:
                    break;
            }
        }
        return firstDieFlag || blueCampAllDieFlag || redCampAllDieFlag;
    }
}
