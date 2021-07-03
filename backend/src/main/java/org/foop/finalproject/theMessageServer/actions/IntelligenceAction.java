package org.foop.finalproject.theMessageServer.actions;

import org.foop.finalproject.theMessageServer.*;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;
import org.json.JSONObject;

public class IntelligenceAction extends Action {
    public IntelligenceAction(Game game, Player performer, GameCard card, Player playerTarget) {
        super(game, performer, card, playerTarget);
    }

    public IntelligenceType getType(){ return this.card.getIntelligenceType(); }

    @Override
    public void execute() {
        System.out.println("This should not occur since an intelligence will not be executed.");
    }
    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerId", performer.getId());
        jsonObject.put("action", "sendIntelligence");
        jsonObject.put("gameCard", card.toJsonObject());
        if(playerTarget != null){
            jsonObject.put("targetId", playerTarget.getId());
        }
        return jsonObject;
    }
    @Override
    public String getGameMessage(){
        System.out.println("This should not occur since an intelligence will not be executed.");
        return "";
    }
}
