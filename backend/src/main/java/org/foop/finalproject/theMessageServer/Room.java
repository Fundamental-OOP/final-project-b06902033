package org.foop.finalproject.theMessageServer;

import org.foop.finalproject.theMessageServer.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Room {
    private String id;
    private ArrayList<User> users;
    private int minPlayerNum = 3;
    private int maxPlayerNum = 9;
    private Game game;
    private boolean isPlaying = false;


    public Room(String id, User creator) {
        this.id = id;
        this.users = new ArrayList<>();
        this.users.add(creator);
        creator.setCurrentRoom(this);
    }

    public void joinRoom(User user) throws Exception {
        if (isPlaying) {
            throw new Exception("The room is playing a game.");
        }
        if (users.size() == maxPlayerNum) {
            throw new Exception(String.format("This room (id: %s) is full", id));
        } else {
            users.add(user);
            user.setCurrentRoom(this);
        }
    }
    public boolean getIsPlaying(){
        return isPlaying;
    }
    public void startGame() throws Exception {
        if (isPlaying) {
            throw new Exception("遊戲早就開始了");
        }
        if (users.size() < minPlayerNum) {
            throw new Exception("房間人數不足（需要大於等於三人才可以開始）");
        } else {
            isPlaying = true;
            game = new Game(this);
            game.initializeStage();
            game.start();
            // Thread gameThread = new Thread(game);
            // gameThread.start();
        }
    }

    public String getId() {
        return id;
    }

    public Player getPlayer(String playerId) throws Exception {
        if (!isPlaying) {
            throw new Exception("This room is not playing game.");
        }
        try {
            return Utility.findInArrayList(game.players, player -> player.id.equals(playerId));
        }
        catch (Exception e) {
            throw new Exception("Player not found");
        }
    }
    public boolean isFull(){
        // return true;
        return (users.size() >= maxPlayerNum)? true:false;
    }
    public Game getGame(){
        return game;
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public void onGameOver() { isPlaying = false; }
}
