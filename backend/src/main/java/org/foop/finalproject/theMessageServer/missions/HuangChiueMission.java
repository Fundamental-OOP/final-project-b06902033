package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.Camp;

public class HuangChiueMission extends Mission {
    public HuangChiueMission(){
        description = "一位潛伏玩家和一位軍情玩家死亡";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        boolean aBlueDie = false;
        boolean aRedDie = false;
        for(Player deadPlayer: game.getDeadPlayers()){
            switch (deadPlayer.getCamp()){
                case BLUE:
                    aBlueDie = true;
                    break;
                case RED:
                    aRedDie = true;
                    break;
            }
        }
        return aBlueDie && aRedDie;
        /*
        boolean aBlueDie = false;
        boolean aRedDie = false;

        for(Player otherPlayer: game.getPlayers()){
            if(otherPlayer.getCamp() == Camp.BLUE && otherPlayer.isDead()){
                aBlueDie = true;
            }
            if(otherPlayer.getCamp() == Camp.RED && otherPlayer.isDead()){
                aRedDie = true;
            }
        }
        return aBlueDie && aRedDie;
        */
    }
}
