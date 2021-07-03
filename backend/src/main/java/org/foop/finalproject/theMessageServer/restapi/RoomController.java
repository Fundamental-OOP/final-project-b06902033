package org.foop.finalproject.theMessageServer.restapi;

import org.apache.coyote.Response;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Main;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.User;
import org.foop.finalproject.theMessageServer.Room;
import org.foop.finalproject.theMessageServer.service.RoomService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{userId}/room")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public ResponseEntity createRoom(@PathVariable String userId) {
        try {
            String roomId = roomService.createRoom(userId);
            // Todo response maybe need to transform to json
            JSONObject json = new JSONObject();
            json.put("roomId", roomId);
            return ResponseEntity.ok().body(json.toString());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/{roomId}")
    public ResponseEntity joinRoom(@PathVariable String userId, @PathVariable String roomId)  {
        try {
            roomService.joinRoom(roomId, userId);
            JSONObject json = new JSONObject();
            json.put("roomId", roomId);
            return ResponseEntity.ok().body(json.toString());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity leaveRoom(@PathVariable String userId, @PathVariable String roomId)  {
        User user = Main.getUser(userId);
        roomService.leaveRoom(roomId, userId);
        System.out.println(user.getName() + " leave room "+ roomId +" successfully.");
        return ResponseEntity.ok().build();
    }

}