package org.foop.finalproject.theMessageServer.service;

import org.foop.finalproject.theMessageServer.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    public void readyForPlayer(String roomId, String playerId) throws Exception{
        Room room = Main.getRoom(roomId);
        if(room == null){
            throw new Exception("此房間不存在");
        }
        Game game = room.getGame();
        if(game == null){
            throw new Exception("此房間尚未開始遊戲");
        }
        Player player = game.getPlayerById(playerId);
        if(player == null){
            throw new Exception("這個人不在遊戲裡還想準備好啊？");
        }
        game.addReadyPlayer(player);
    }
}
