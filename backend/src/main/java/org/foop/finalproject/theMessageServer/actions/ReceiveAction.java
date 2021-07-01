package org.foop.finalproject.theMessageServer.actions;

import org.foop.finalproject.theMessageServer.Action;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Player;
import org.json.JSONObject;

public class ReceiveAction extends Action {
    public ReceiveAction(Game game, Player receiver){
        super(game, null, null, receiver);
    }

    public ReceiveAction(IntelligenceAction intelligenceAction, Player playerTarget){
        super(intelligenceAction.getGame(), intelligenceAction.getPerformer(), intelligenceAction.getCard(), playerTarget);
    }

    @Override
    public void execute() {
        System.out.println("A pass should not be execute, so this should not happen ...");
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receiverId", playerTarget.getId());
        jsonObject.put("senderId", performer.getId());
        jsonObject.put("intelligence", card.toJsonObject());
        jsonObject.put("action", "receive");
        return jsonObject;
    }
    @Override
    public String getGameMessage(){
        System.out.println("This should not occur since an intelligence will not be executed.");
        return "";
    }
}
