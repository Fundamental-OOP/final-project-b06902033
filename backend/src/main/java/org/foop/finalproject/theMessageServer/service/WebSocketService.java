package org.foop.finalproject.theMessageServer.service;

import org.foop.finalproject.theMessageServer.Main;
import org.foop.finalproject.theMessageServer.Room;
import org.foop.finalproject.theMessageServer.User;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/websocket")    //宣告這是一個Socket服務 ws://localhost:8080/websocket
public class WebSocketService {
    @Autowired
    private JsonService jsonService;

    /**
     * 連線建立成功呼叫的方法
     *
     * @param session 可選的引數
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session) {
        // Main.openSession(session);
        System.out.println("有人連線websocket");
    }

    /**
     * 連線關閉呼叫的方法
     *
     * @throws Exception
     */
    @OnClose
    public void onClose(Session session, CloseReason closedReason) {
        // Main.closeSessions();

        System.out.println("有人結束連線 : " + session.getId() + " " + closedReason.getReasonPhrase());
        /*
        if(Main.sessionIdMapToUser.containsKey(session.getId())){
            User user = Main.sessionIdMapToUser.get(session.getId());
            Room room = user.getCurrentRoom();
            //在房間裡
            if(room != null){
                RoomService roomService = new RoomService();
                if(room.getIsPlaying()) {
                    Game game = room.getGame();
                    if(user == game.getRound().getCurrentPlayer().getUser()){

                    }
                    else{
                        ArrayList<Player> players = game.getPlayers();
                        for(Player player:players){
                            if(player.getUser() == user){
                                player.setStatus(PlayerStatus.Lose);
                            }
                        }
                    }
                    System.out.println(user.getName() + "從遊戲中離");
                }
                else{
                    roomService.leaveRoom(room.getId(), user.getId());
                    System.out.println(user.getName() + "斷線，從房間中移除。");
                }
            }
            Main.sessionIdMapToUser.remove(session.getId());
        }

        */

    }

    /**
     * 發生錯誤時呼叫
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 收到訊息後呼叫的方法
     *
     * @param message 客戶端傳送過來的訊息
     * @param session 可選的引數
     * @throws Exception
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        MessageService messageService = new MessageService();
        System.out.println(message);
        if (message != null) {
            String[] messages = message.split("-", 2);
            switch (messages[0]) {
                case "start": //Todo 創建user start-{username}
                    User user = new User(messages[1], session);
                    Main.addUser(user);
                    Main.sessionIdMapToUser.put(session.getId(), user);
                    // Todo 看之後怎麼接
                    messageService.informUserId(user);
                    System.out.println("receive start and sent userId!!");
                    break;
                default:
                    break;
            }
        }
    }
}