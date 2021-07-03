package org.foop.finalproject.theMessageServer.gamecards;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;
import org.foop.finalproject.theMessageServer.rounds.IntelligenceRound;

import java.util.ArrayList;

public class Decode extends GameCard {
    public Decode(GameCardColor gameCardColor, IntelligenceType intelligenceType, int order){
        super(gameCardColor, intelligenceType, order);
        // name = "Decode"; // 破譯
        // timingDescription = "Play when message comes to you.";
        // effectDescription = "Check the message.";
        name = "破譯"; // 破譯
        timingDescription = "當情報傳遞到你的時候可以使用";
        effectDescription = "檢視該情報。";
        setId(name);
    }

    @Override
    public void perform(Player performer, Player playerTarget, Game game) throws Exception {
        System.out.print(performer.getUser().getName() + "使用破譯！！");
        IntelligenceRound intelligenceRound =  (IntelligenceRound) game.getRound().getParentRound();
        GameCard intelligenceCard = intelligenceRound.getIntelligence().getCard();
        ArrayList<String> messages = messageService.getActionMessages(performer.getId(), "", this.name,
                "了傳遞中的情報。");
        messageService.broadcastActionPerformed(game, messages);
        messageService.sendIntelligenceInformationToPlayer(intelligenceCard, performer);

    }

    @Override
    public String getGameMessage(Player performer, Player playerTarget, Game game) {
        return "";
    }

    @Override
    public boolean isValid(Round currentRound, Player owner){
        return currentRound.isGameCardRound() && currentRound.parentRoundIsIntelligenceRound()  && currentRound.playerIsCurrentPlayerOfParentRound(owner);
    }
}