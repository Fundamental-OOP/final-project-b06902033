package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import java.util.ArrayList;

public class HeiMeiGuiMission extends Mission {
    public HeiMeiGuiMission(){
        description = "親手讓另一位獲得了三張或以上紅藍情報的玩家死亡。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        // TODO
        ArrayList<Player> killPeople = player.getKillPeople();
        for(Player diePlayer:killPeople){
            if(diePlayer.getIntelligences().get(GameCardColor.BLUE.type).size() +
                    diePlayer.getIntelligences().get(GameCardColor.RED.type).size() >=3){
                return true;
            }
        }
        return false;
    }
}
