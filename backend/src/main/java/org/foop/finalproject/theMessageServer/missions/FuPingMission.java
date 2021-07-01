package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.PlayerStatus;

public class FuPingMission extends Mission {
    public FuPingMission(){
        description = "你成為第二位或以後死亡的玩家。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        //TODO Future work: simplified
        boolean hasFirsyPlayer = false;
        for(Player otherPlayer: game.getPlayers()){
            if(otherPlayer.getStatus() == PlayerStatus.Dead){
                hasFirsyPlayer = true;
            }
        }
        return player.getStatus() == PlayerStatus.Dead && hasFirsyPlayer;
    }
}
