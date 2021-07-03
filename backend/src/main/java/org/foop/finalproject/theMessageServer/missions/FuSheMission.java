package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;

public class FuSheMission extends Mission {
    public FuSheMission(){
        description = "當一位玩家宣告勝利時，沒有玩家死亡。你的勝利會導致他的失敗。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        // TODO
        //當Main Round離開讓game.round變成null代表有人獲勝

        if(game.getRound() == null && game.getDeadPlayers().size() == 0)
            return true;
        return false;
    }
}
