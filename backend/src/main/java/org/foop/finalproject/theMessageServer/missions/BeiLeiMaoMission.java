package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;

public class BeiLeiMaoMission extends Mission {
    public BeiLeiMaoMission(){
        description = "手牌集齊三張紅色卡牌和三張藍色卡牌。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        int redGameCardNum = 0;
        int blueGameCardNum = 0;
        for(GameCard gameCard: player.getHandCards()){
            if(gameCard.getColor() == GameCardColor.RED){
                redGameCardNum += 1;
            }
            else if(gameCard.getColor() == GameCardColor.BLUE){
                blueGameCardNum += 1;
            }
        }
        return redGameCardNum == 3 && blueGameCardNum == 3;
    }
}
