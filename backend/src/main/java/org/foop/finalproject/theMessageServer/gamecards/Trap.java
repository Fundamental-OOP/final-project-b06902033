package org.foop.finalproject.theMessageServer.gamecards;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;

import java.util.ArrayList;

public class Trap extends GameCard {
    public Trap(GameCardColor gameCardColor, IntelligenceType intelligenceType, int order) {
        super(gameCardColor, intelligenceType, order);
        /// name = "Trap"; // 調虎離山
        // timingDescription = "Play when message transmitting.";
        // effectDescription = "Another player becomes [Trap Status] (Player in [Lock On Status] can not be affected by this)";
        name = "調虎離山"; // 調虎離山
        timingDescription = "當情報開始傳遞後使用。";
        effectDescription = "指定一位自己以外的玩家不參與本次的情報傳遞。";
        setId(name);
        needTarget = true;
    }

    @Override
    public void perform(Player performer, Player playerTarget, Game game) {
        if(!playerTarget.isAlive()){
            System.out.println("Should not happen ...");
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "無法發揮作用，因為", playerTarget.getId(), "早已不在遊戲中。");
            messageService.broadcastActionPerformed(game, messages);
        } else if(playerTarget.isLockOn()){
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "無法發揮作用，因為", playerTarget.getId(), "已經被鎖定了。");
            messageService.broadcastActionPerformed(game, messages);
        } else if(playerTarget == game.getRound().getParentRound().getEndPlayer()){
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "無法發揮作用，因為", playerTarget.getId(), "是傳遞出情報的人，無法被調虎離山。");
            messageService.broadcastActionPerformed(game, messages);
        } else if(playerTarget.isTrapped()){
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "無法發揮作用，因為", playerTarget.getId(), "早已被調虎離山過了。");
            messageService.broadcastActionPerformed(game, messages);
        }
        else {
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "生效了，", playerTarget.getId(), "在本回合不可以接收情報。");

            messageService.broadcastActionPerformed(game, messages);
            playerTarget.beTrap();
        }
    }

    @Override
    public String getGameMessage(Player performer, Player playerTarget, Game game){
        return "";
    }
    @Override
    public boolean isValid(Round currentRound, Player owner){
        return currentRound.isGameCardRound() && currentRound.parentRoundIsIntelligenceRound();
    }
}