package org.foop.finalproject.theMessageServer.restapi;

import org.foop.finalproject.theMessageServer.Main;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{userId}/room/{roomId}/game/player/{playerId}")
@CrossOrigin(origins = "*")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/ready")
    public ResponseEntity playerReady(@PathVariable String userId, @PathVariable String roomId, @PathVariable String playerId) {
        try {
            //TODO 可以比對userId跟playerId有沒有吻合
            Player player = Main.getPlayer(roomId, playerId);
            if(player == null){
                throw new Exception("該玩家不在此房間內");
            }
            else if(!player.getUser().getId().equals(userId)){
                throw new Exception("userId和playerId不匹配");
            }
            playerService.readyForPlayer(roomId, playerId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
