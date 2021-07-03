package org.foop.finalproject.theMessageServer;

import org.foop.finalproject.theMessageServer.rounds.MainRound;
import org.foop.finalproject.theMessageServer.rounds.GameCardRound;
import org.foop.finalproject.theMessageServer.rounds.IntelligenceRound;
import org.foop.finalproject.theMessageServer.rounds.CounteractRound;
import org.foop.finalproject.theMessageServer.service.GameService;
import org.foop.finalproject.theMessageServer.service.MessageService;

import java.util.ArrayList;

public abstract class Round {
    protected Player endPlayer;
    protected Player creator;
    protected Player currentPlayer;
    protected Round parentRound;
    protected Round childRound;
    protected int direction;
    protected Game game;
    protected String name;
    protected MessageService messageService;
    protected GameService gameService;
    public Round(Game game){
        this.game = game;
        this.parentRound = null;
        this.direction = 1;
        this.messageService = new MessageService();
        this.gameService = new GameService();
    }

    public Round(Round parentRound){
        this.game = parentRound.getGame();
        this.parentRound = parentRound;
        this.direction = 1;
        this.messageService = new MessageService();
        this.gameService = new GameService();
    }

    public void setEndPlayer(Player endPlayer) {
        this.endPlayer = endPlayer;
    }

    public Player getEndPlayer(){
        return endPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }

    public Player getCurrentPlayer() { return currentPlayer; }

    public Game getGame() { return game;}

    public Round getParentRound(){return parentRound;}

    public void setChildRound(Round childRound){ this.childRound = childRound; }

    public Player getNextPlayer(){
        ArrayList<Player> players = game.getPlayers();
        int currentPlayerId = -1;
        for(int i = 0; i < players.size(); i++){
            if(players.get(i) == currentPlayer){
                currentPlayerId = i;
                break;
            }
        }
        int nextPlayerId = (currentPlayerId + direction + players.size()) % players.size();
        Player nextPlayer = players.get(nextPlayerId);
        while( !nextPlayer.isAlive() ){
            nextPlayerId = (nextPlayerId + direction + players.size()) % players.size();
            nextPlayer = players.get(nextPlayerId);
        }
        return nextPlayer;
    }

    abstract public void onRoundStart();

    abstract public void onTurnStart();

    abstract public void onTurnProgressing(Action action);

    abstract public void doWhenLeaveChildRound();

    abstract public void onTurnEnd();

    abstract public void onRoundEnd();

    abstract public boolean satisfyRoundEndCondition();

    public String getName(){ return name; }

    // public boolean isMainRound(){ return this instanceof MainRound; }
    public boolean isGameCardRound(){ return this instanceof GameCardRound; }
    // public boolean isIntelligenceRound(){ return this instanceof IntelligenceRound; }
    public boolean isCounteractRound(){ return this instanceof CounteractRound; }

    public boolean parentRoundIsMainRound(){ return parentRound instanceof MainRound; }
    public boolean parentRoundIsIntelligenceRound(){ return parentRound instanceof IntelligenceRound; }

    // public boolean playerIsOwnerOfCurrentRound(Player player){ return player == creator;}
    public boolean playerIsOwnerOfParentRound(Player player){ return player == parentRound.creator; }
    public boolean playerIsCurrentPlayerOfParentRound(Player player) { return player == parentRound.currentPlayer; }

}
