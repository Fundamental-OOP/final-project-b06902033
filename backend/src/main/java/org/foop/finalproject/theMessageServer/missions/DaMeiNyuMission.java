package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;

public class DaMeiNyuMission extends Mission {
    public DaMeiNyuMission(){
        description = "當一位男性宣告勝利時，你獲得的黑情報少於等於一張。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        // TODO
        return false;
    }
}
