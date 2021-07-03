package org.foop.finalproject.theMessageServer.missions;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.gamecards.Counteract;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Player;

public class GangTieTeGongMission extends Mission {
    public GangTieTeGongMission(){
        description = "手牌集齊四張識破。";
    }
    @Override
    protected boolean isCompleted(Game game, Player player) {
        int counteractNum = 0;
        for(GameCard gameCard: player.getHandCards()){
            if (gameCard instanceof Counteract){
                counteractNum += 1;
            }
        }
        return counteractNum == 4;
    }
}
