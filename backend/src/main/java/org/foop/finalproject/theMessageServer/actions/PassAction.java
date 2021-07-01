package org.foop.finalproject.theMessageServer.actions;
import org.foop.finalproject.theMessageServer.Action;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Player;
import org.json.JSONObject;



public class PassAction extends Action{
    public PassAction(Game game, Player performer){
        super(game, performer, null, null);
    }

    @Override
    public void execute() {
        System.out.println("A pass should not be execute, so this should not happen ...");
    }
    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerId", performer.getId());
        jsonObject.put("action", "pass");
        return jsonObject;
    }
    @Override
    public String getGameMessage(){
        System.out.println("This should not occur since an intelligence will not be executed.");
        return "";
    }
}
