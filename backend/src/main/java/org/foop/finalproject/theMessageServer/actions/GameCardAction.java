package org.foop.finalproject.theMessageServer.actions;

import org.foop.finalproject.theMessageServer.*;
import org.json.JSONObject;


public class GameCardAction extends Action {

    public GameCardAction(Game game, Player performer, GameCard card, Player playerTarget) {
        super(game, performer, card, playerTarget);
        this.card = card;
        this.playerTarget = playerTarget;
    }

    @Override
    public void execute() {
        try {
            card.perform(performer, playerTarget, game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getGameMessage(){
        return card.getGameMessage(performer, playerTarget, game);
    }
    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerId", performer.getId());
        jsonObject.put("action", "playGameCard");
        jsonObject.put("gameCard", card.toJsonObject());
        if(playerTarget != null){
            jsonObject.put("targetId", playerTarget.getId());
        }
        return jsonObject;
    }

}
