package org.foop.finalproject.theMessageServer;
import org.json.JSONObject;

import javax.websocket.Session;

import static org.foop.finalproject.theMessageServer.utils.Utility.generateUserId;

public class User {
    private String name;
    private String id;
    private Room currentRoom;
    private Session session;
    public User(String name, Session session) {
        this.name = name;
        this.id = generateUserId();
        System.out.println("name:" + name + " id:" + id);
        this.currentRoom = null;
        this.session = session;
    }

    public String getName() {
        return name;
    }
    public Session getSession(){
        return session;
    }
    public String getId() {
        return id;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    public void leaveRoom() {
        currentRoom = null;
    }

    public JSONObject toJsonObject () {
        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("id", id);
        return userObject;
    }
}
