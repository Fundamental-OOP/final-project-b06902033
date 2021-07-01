package org.foop.finalproject.theMessageServer;

import org.foop.finalproject.theMessageServer.actions.ReceiveAction;
import org.foop.finalproject.theMessageServer.characters.DaMeiNyu;
import org.foop.finalproject.theMessageServer.characters.LiFuMengMianJen;
import org.foop.finalproject.theMessageServer.enums.Camp;
import org.foop.finalproject.theMessageServer.enums.GameCardColor;
import org.foop.finalproject.theMessageServer.enums.PlayerStatus;
import org.foop.finalproject.theMessageServer.service.JsonService;
import org.foop.finalproject.theMessageServer.service.MessageService;
import org.foop.finalproject.theMessageServer.utils.Utility;
import org.json.JSONObject;

import java.util.ArrayList;

public class Player {
    private final Game game;
    protected String id;
    protected Character character;
    protected Camp camp;
    protected boolean die;
    protected boolean lose;
    protected MessageService messageService;
    protected JsonService jsonService = new JsonService();
    protected PlayerStatus status;
    protected ArrayList<GameCard> handCards;
    protected ArrayList<ArrayList<GameCard>> intelligences; // 0 -> RED, 1 -> BLUE, 2 -> BLACK
    protected User user;
    protected ArrayList<Player> killPeople = new ArrayList<>();
    protected Player killedBy;
    public Player(Game game, Camp camp, Character character, User user) {
        this.game = game;
        this.id = Utility.generatePlayerId();
        this.character = character;
        this.camp = camp;
        this.status = PlayerStatus.Normal;
        this.handCards = new ArrayList<>();
        this.user = user;
        this.intelligences = new ArrayList<>();
        for(int i = 0; i < 3; i ++){
            intelligences.add(new ArrayList<>());
        }
        die = false;
        lose = false;
        messageService = new MessageService();
    }

    public User getUser(){ return user; }

    public String getId(){ return id; }

    public ArrayList<GameCard> getHandCards(){ return handCards; }

    public Camp getCamp() {return camp;}

    public int getHandcardsNum(){ return handCards.size();}

    public ArrayList<ArrayList<GameCard>> getIntelligences(){
        return intelligences;
    }

    public boolean isDirectWin() {
        if(isLose()) return false;
        boolean onlyThisPlayerAlive = isAlive();
        for(Player player: game.getPlayers()){
            if(player == this) {
                continue;
            }
            if(player.isAlive()){
                onlyThisPlayerAlive = false;
                break;
            }
        }
        if(onlyThisPlayerAlive){
            return true;
        }
        switch (camp){
            case RED:
            case BLUE:
                return intelligences.get(camp.type).size() >= 3;
            case GREEN:
                return character.missionComplete(game, this);
            default:
                System.out.println("Which camp is this: " + camp.type + " ...");
                return false;
        }
    }
    public boolean isIndirectWin(ArrayList<Player> directWinners){
        if(directWinners.contains(this)){
            return false;
        }
        boolean redWin = false, blueWin = false;
        for(Player directWinner: directWinners) {
            switch (directWinner.getCamp()) {
                case BLUE:
                    blueWin = true;
                    break;
                case RED:
                    redWin = true;
                    break;
                default:
                    break;
            }
        }
        switch (this.getCamp()) {
            case RED:
                return redWin;
            case BLUE:
                return blueWin;
            case GREEN:
                if (this.character instanceof DaMeiNyu) {
                    if (this.getIntelligences().get(GameCardColor.BLACK.type).size() <= 1) {
                        for (Player directWinner : directWinners) {
                            if (directWinner.character.gender.isMale()) {
                                return true;
                            }
                        }
                    }
                } else if (this.character instanceof LiFuMengMianJen) {
                    for (Player directWinner : directWinners) {
                        if (directWinner.character.gender.isFemale()) {
                            return true;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return false;
    }

    public boolean isDead() { return die; }

    public boolean isLose() { return lose; }

    public boolean isAlive() {
        return status == PlayerStatus.Normal
                || status == PlayerStatus.LockOn
                || status == PlayerStatus.Trap;
    }

    public void drawInitialCards() { handCards.addAll(game.drawCards(3)); }

    public void drawCards(int num) {
        drawCards_wo_broadcast(num);
        messageService.broadcastGameInformation(game);
    }
    public void drawCards_wo_broadcast(int num) {
        System.out.println("Player draw cards start");
        handCards.addAll(game.drawCards(num));
        System.out.println("Player draw cards end");
    }
    public void removeCardFromHandCards(GameCard card) {
        handCards.remove(card);
    }

    public void receiveIntelligence(ReceiveAction receiveAction) {
        GameCard card = receiveAction.getCard();
        intelligences.get(card.color.type).add(card);
        messageService.broadcastActionBeenPlayedMessage(game, receiveAction);
        if(intelligences.get(GameCardColor.BLACK.type).size() >= 3){
            die();
            killedBy = receiveAction.getPerformer(); //殺人的人
            killedBy.addKillPerson(this); //把被殺的加入殺手的得手名單
            //receiveAction.getPlayerTarget().addKillPerson(this);
        }
    }

    private void addKillPerson(Player player) {
        killPeople.add(player);
    }

    public void setStatus(PlayerStatus newStatus){
        this.status = newStatus;
    }

    public JSONObject toJsonObject(){
        JSONObject playerObj = new JSONObject();
        playerObj.put("userId", user.getId());
        playerObj.put("playerId", getId());
        playerObj.put("status", getStatus().status);
        playerObj.put("name", user.getName());
        playerObj.put("handcardsNum", handCards.size());
        playerObj.put("handcards", jsonService.getHandCardsObjs(handCards));
        playerObj.put("intelligences", jsonService.getIntelligencesObjs(intelligences));
        playerObj.put("camp", camp.name);
        playerObj.put("character", character.toJsonObject());
        return playerObj;
    }

    public void beLockOn() {
        this.status = PlayerStatus.LockOn;
    }
    public boolean isLockOn(){
        return this.status == PlayerStatus.LockOn;
    }
    public void beTrap() { this.status = PlayerStatus.Trap; }
    public boolean isTrapped(){
        return this.status == PlayerStatus.Trap;
    }

    public boolean hasNoHandcards() {
        return handCards.isEmpty();
    }

    public void die() { // Todo
        status = PlayerStatus.Dead;
        die = true;
        if(character.isHidden()) {
            character.uncover();
        }
        // messageService.broadcastPlayerStateChangeMessage(game, this);
        game.onPlayerDie(this);
    }

    public void loseTheGame() {
        die();
        status = PlayerStatus.Lose;
        lose = true;
        if(character.isHidden()) {
            character.uncover();
        }
        game.onPlayerLose(this);
        // messageService.broadcastPlayerStateChangeMessage(game, this);
    }
    public PlayerStatus getStatus(){
        return this.status;
    }

    // 會進來只會是 GameCard Round 或 Counteract Round or Intelligence Round?
    // 要看parentRound 才知道現在傳情報了沒以及現在的情報是誰傳的
    public ArrayList<String> getValidCardIndices(Game game){
        ArrayList<String> validCardIndices = new ArrayList<>();
        for(GameCard currentCard: handCards){
            if(currentCard.isValid(game.getRound(), this)){
                validCardIndices.add(currentCard.getId());
            }
        }
        return validCardIndices;
    }

    public GameCard getCardById(String gameCardId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.user.getName() + "的目前手牌:");
        for(GameCard gameCard:handCards){
            stringBuilder.append(gameCard.getId() + ",");
        }
        System.out.println(stringBuilder);
        for(GameCard gameCard:handCards){
            if(gameCard.getId().equals(gameCardId)){
                return gameCard;
            }
        }
        return null;
    }

    public ArrayList<String> getValidIntelligence() {
        ArrayList<String> validCardIndices = new ArrayList<>();
        for(GameCard currentCard: handCards){
            validCardIndices.add(currentCard.getId());
        }
        return validCardIndices;
    }

    @Override
    public String toString(){
        return getUser().getName();
    }

    public ArrayList<Player> getKillPeople() {
        return this.killPeople;
    }

    public boolean hasIntelligenceMoreThanEqualTo(GameCardColor color, int num) {
        return this.intelligences.get(color.type).size() < num;
    }
}
