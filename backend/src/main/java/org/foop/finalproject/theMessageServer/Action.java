package org.foop.finalproject.theMessageServer;

import org.json.JSONObject;

public abstract class Action {
    protected Game game;
    protected Player performer;
    protected GameCard card;
    protected Player playerTarget;
    protected GameCard gameCardTarget;
    public Action(Game game, Player performer, GameCard card) {
        this.game = game;
        this.performer = performer;
        this.card = card;
    }

    public Action(Game game, Player performer, GameCard card, Player playerTarget) {
        this.game = game;
        this.performer = performer;
        this.card = card;
        this.playerTarget = playerTarget;
    }

    abstract public void execute() ;
    public Game getGame(){
        return game;
    }
    public GameCard getCard() {
        return card;
    }

    public Player getPerformer() {
        return performer;
    }

    public void setPlayerTarget(Player playerTarget) { this.playerTarget = playerTarget; }

    public Player getPlayerTarget(){ return playerTarget; }

    public boolean needToChooseTarget(){
        if(ifNeedTarget() && playerTarget == null)
            return true;
        return false;
    }
    public boolean ifNeedTarget(){
        return this.card.getNeedTarget();
    }
    public abstract JSONObject toJsonObject();

    public abstract String getGameMessage();
    public GameCard getGameCardTarget(){
        return this.gameCardTarget;
    }
    public void setGameCardTarget(GameCard gameCardTarget){
        this.gameCardTarget = gameCardTarget;
    }
    public void setGameCard(GameCard gameCard){
        this.card = gameCard;
    }
}
