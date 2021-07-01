package org.foop.finalproject.theMessageServer;

import org.foop.finalproject.theMessageServer.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;

public class Main {
    static public ArrayList<Room> rooms = new ArrayList<>();
    static public ArrayList<User> users = new ArrayList<>();
    static public Map<String, User> sessionIdMapToUser = new HashMap<>();
    public Main() throws Exception {
        /*
        rooms.add(new Room("123456", new User("EG", null)));
        joinRoom("123456", (new User("Sam", null)));
        joinRoom("123456", (new User("Momo", null)));
        */
    }

    static public void addUser(User user){
        users.add(user);
    }

    static public String createRoom(User user) {
        String roomId;
        do {
            roomId = Utility.generateRoomId();
        } while (checkRoomIdExist(roomId));
        rooms.add(new Room(roomId, user));
        return roomId;
    }

    static public void joinRoom(String roomId, User user) throws Exception {
        Room roomToJoin = Utility.findInArrayList(rooms, room -> room.getId().equals(roomId));
        if (roomToJoin == null) {
            throw new Exception("房間不存在");
        }
        else if(roomToJoin.isFull()){
            throw new Exception("房間滿了");
        }
        roomToJoin.joinRoom(user);
    }

    static public Room getRoom(String roomId)  {
        return Utility.findInArrayList(rooms, room -> room.getId().equals(roomId));
    }

    static public User getUser(String userId)  {
        return Utility.findInArrayList(users, user -> user.getId().equals(userId));
    }

    static public Player getPlayer(String roomId, String playerId) throws Exception {
        Room room;
        room = Utility.findInArrayList(rooms, r -> r.getId().equals(roomId));
        return room.getPlayer(playerId);
    }

    static private boolean checkRoomIdExist(String roomId) {
        for (Room room : rooms) {
            if (room.getId().equals(roomId))
                return true;
        }
        return false;
    }

    public static void removeRoom(Room room) {
        rooms.remove(room);
    }
}

