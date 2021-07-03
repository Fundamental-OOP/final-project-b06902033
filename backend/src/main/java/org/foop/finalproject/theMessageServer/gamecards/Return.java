package org.foop.finalproject.theMessageServer.gamecards;

import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;
import org.foop.finalproject.theMessageServer.rounds.IntelligenceRound;

import java.util.ArrayList;

public class Return extends GameCard {
    public Return(GameCardColor gameCardColor, IntelligenceType intelligenceType, int order){
        super(gameCardColor, intelligenceType, order);
        // name = "RETURN"; // 退回
        // timingDescription = "Play when message comes to you in other players' turn. Player in [Lock On Status] will not affect by this card";
        // effectDescription = "The intelligence will be transmitted in another direction.";
        name = "退回"; // 退回
        timingDescription = "當情報傳遞到你的時候使用。(若你已經被鎖定了則此牌無法發揮作用)";
        effectDescription = "該情報以反方向傳遞。";
        setId(name);
    }
    @Override
    public void perform(Player performer, Player playerTarget, Game game) {
        if(performer.isLockOn()){
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "發動了，但因為他處於被鎖定的狀態，退回不會產生功效。");
            messageService.broadcastActionPerformed(game, messages);
            return;
        }else {

            /*
            if (performer.getStatus() == PlayerStatus.Normal) {
                performer.beTrap();
            }*/
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(),
                    "的", this.name, "生效了，情報將會反方向傳遞回去。");
            messageService.broadcastActionPerformed(game, messages);
            ((IntelligenceRound) game.getRound().getParentRound()).changeDirection();
        }
    }

    @Override
    public String getGameMessage(Player performer, Player playerTarget, Game game) {
        return "";
    }

    @Override
    public boolean isValid(Round currentRound, Player owner) {
        return currentRound.isGameCardRound() && currentRound.parentRoundIsIntelligenceRound() && currentRound.playerIsCurrentPlayerOfParentRound(owner);
    }
}
