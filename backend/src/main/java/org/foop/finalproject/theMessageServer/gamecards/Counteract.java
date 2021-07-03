package org.foop.finalproject.theMessageServer.gamecards;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.actions.GameCardAction;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;

import java.util.ArrayList;

public class Counteract extends GameCard {
    public Counteract(GameCardColor gameCardColor, IntelligenceType intelligenceType, int order){
        super(gameCardColor, intelligenceType, order);
        // name = "COUNTERACT"; // 識破
        // timingDescription = "Play when effect card is used.";
        // effectDescription = "Target effect ineffective and put to discard pile.";
        name = "識破"; // 識破
        timingDescription = "當有功能牌被打出之後可以使用此牌。";
        effectDescription = "使該被打出的牌無效化，並放入棄牌堆。";
        setId(name);
    }
    @Override
    public void perform(Player performer, Player playerTarget, Game game) {
        // TODO: perform message
        try{
            GameCardAction action = game.getCurrentActionsOnBoard().pop();
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(), "", this.name,
                    "了", action.getPerformer().getId(), "的", action.getCard().getName(), "。");
            messageService.broadcastActionPerformed(game, messages);
        } catch(Exception error){
            // Should don't enter here.
            System.out.println("Exception:"+error);
            System.out.println(performer + "的識破沒有生效，因為場面上沒有其他action了。");
            ArrayList<String> messages = messageService.getActionMessages(performer.getId(), "的", this.name,
                    "沒有生效，因為場面上沒有其他功能牌了。");
            messageService.broadcastActionPerformed(game, messages);
        }

    }

    @Override
    public String getGameMessage(Player performer, Player playerTarget, Game game) {
        return performer + "識破了" + playerTarget + "打的牌";
    }

    @Override
    public boolean isValid(Round currentRound, Player owner) {
        return currentRound.isCounteractRound();
    }
}