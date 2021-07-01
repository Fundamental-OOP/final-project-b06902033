package org.foop.finalproject.theMessageServer;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;
import org.foop.finalproject.theMessageServer.service.MessageService;
import org.json.JSONObject;

import java.util.Locale;

public abstract class GameCard {
    protected GameCardColor color;
    protected String id;
    protected int order;
    protected boolean targetSelf;
    protected String name;
    protected String timingDescription;
    protected String effectDescription;
    protected IntelligenceType intelligenceType;
    protected boolean needTarget;
    protected MessageService messageService;

    protected GameCard(GameCardColor gameCardColor, IntelligenceType intelligenceType, int order){
        this.color = gameCardColor;
        this.intelligenceType = intelligenceType;
        this.order = order;
        this.needTarget = false;
        this.targetSelf = false;
        this.messageService = new MessageService();
    }

    public abstract boolean isValid(Round currentRound, Player owner);
    public abstract void perform(Player performer, Player playerTarget, Game game) throws Exception;
    public abstract String getGameMessage(Player performer, Player playerTarget, Game game);

    // public String getTimingDescription(){
    //     return timingDescription;
    // }
    // public String getEffectDescription(){
    //     return effectDescription;
    // }

    public boolean canTargetPerformer() {return targetSelf;};
    public boolean getNeedTarget(){return needTarget;}
    public GameCardColor getColor(){
        return color;
    }
    public IntelligenceType getIntelligenceType() {
        return intelligenceType;
    }

    public JSONObject toJsonObject(){
        JSONObject handCardObj = new JSONObject();
        handCardObj.put("name", name);
        handCardObj.put("timingDescription", timingDescription);
        handCardObj.put("effectDescription", effectDescription);
        handCardObj.put("id",id);
        handCardObj.put("color", color.name.toLowerCase());
        handCardObj.put("type", intelligenceType.name);
        handCardObj.put("needTarget", needTarget);
        return handCardObj;
    }

    public void setId(String name){
        this.id = this.color.name.toLowerCase() + "-" + name + "-" + this.intelligenceType.name + "-" + order;
    }
    public String getId() {
        return id;
    }
    public boolean isDirectMessage(){ return this.intelligenceType == IntelligenceType.DIRECT_MSG;}

    public String getName() { return name; }
}