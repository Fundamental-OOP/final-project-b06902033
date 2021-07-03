package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class GuaiDaoJiouJiouMission extends Mission {
    public GuaiDaoJiouJiouMission(){
        description = "獲得十張或以上的手牌。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        return player.getHandcardsNum() >= 10;
    }
}
