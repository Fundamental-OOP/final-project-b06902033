package org.foop.finalproject.theMessageServer.service;

import org.foop.finalproject.theMessageServer.gamecards.Prove;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.GameCard;
import org.foop.finalproject.theMessageServer.actions.GameCardAction;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Stack;

@Service
public class JsonService {

    public ArrayList<JSONObject> getPlayersInformationObj(ArrayList<Player> players) {
        ArrayList<JSONObject> playersObj = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            JSONObject playerObj = players.get(i).toJsonObject();
            playersObj.add(playerObj);
        }
        return playersObj;
    }

    public ArrayList<JSONObject> getHandCardsObjs(ArrayList<GameCard> handCards){
        ArrayList<JSONObject> handCardsObjs = new ArrayList<>();
        for(GameCard gameCard:handCards){
            if(gameCard instanceof Prove){
                handCardsObjs.add(((Prove)gameCard).toJsonObjectOnHand());
            }else{
                handCardsObjs.add(gameCard.toJsonObject());
            }
        }
        return handCardsObjs;
    }

    public JSONObject getIntelligencesObjs(ArrayList<ArrayList<GameCard>> intelligences){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("black", intelligences.get(GameCardColor.BLACK.type).size());
        jsonObject.put("blue", intelligences.get(GameCardColor.BLUE.type).size());
        jsonObject.put("red", intelligences.get(GameCardColor.RED.type).size());
        return jsonObject;
    }


    public ArrayList<JSONObject> getActionsOnBoardInformationObj(Stack<GameCardAction> ActionsOnBoard) {
        ArrayList<JSONObject> BoardCardsObjs = new ArrayList<>();
        for(GameCardAction action:ActionsOnBoard){
            BoardCardsObjs.add(action.getCard().toJsonObject());
        }
        return BoardCardsObjs;
    }
    /*
    public ArrayList<JSONObject> getBoardInformationObj(ArrayList<GameCard> BoardCards) {
        ArrayList<JSONObject> BoardCardsObjs = new ArrayList<>();
        for(GameCard gameCard:BoardCards){
            BoardCardsObjs.add(gameCard.toJsonObject());
        }
        return BoardCardsObjs;
    }*/
}
