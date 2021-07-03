package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class LauGueiMission extends Mission {
    public LauGueiMission(){
        description = "當你死亡時，展示你的手牌，裡面有三張或以上的紅色卡牌。";

    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        int redGameCardNum = 0;
        for(GameCard gameCard: player.getHandCards()){
            if(gameCard.getColor() == GameCardColor.RED){
                redGameCardNum += 1;
            }
        }
        return redGameCardNum >= 3 && player.isDead();
    }
}
