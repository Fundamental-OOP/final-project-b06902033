package org.foop.finalproject.theMessageServer.restapi;

import org.foop.finalproject.theMessageServer.*;
import org.foop.finalproject.theMessageServer.rounds.ProveRound;
import org.foop.finalproject.theMessageServer.service.GameService;
import org.foop.finalproject.theMessageServer.service.RoomService;
import org.foop.finalproject.theMessageServer.service.MessageService;
import org.foop.finalproject.theMessageServer.actions.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room/{roomId}/game/")
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MessageService messageService;
    @PostMapping("/player/{playerId}/skip")
    public ResponseEntity receivePass(@PathVariable String roomId, @PathVariable String playerId) throws Exception {
        Game game;
        Player player = Main.getPlayer(roomId, playerId);
        try {
            game = Main.getRoom(roomId).getGame();
        } catch (Exception e) {
            throw new Exception("Room not found.");
        }
        System.out.println("接到" + player.getUser().getName() + "pass");
        Action pass = new PassAction(game, player);
        gameService.onReceiveAction(pass);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/player/{playerId}/game-card/{gameCardId}")
    public ResponseEntity receiveGameCard(@PathVariable String roomId,
                                          @PathVariable String playerId,
                                          @PathVariable String gameCardId) throws Exception {
        Game game = Main.getRoom(roomId).getGame();
        Player player = Main.getPlayer(roomId, playerId);
        if (player == null) {
            throw new Exception("Performer player not found");
        }

        GameCard gameCard = player.getCardById(gameCardId);
        if(gameCard == null){
            throw new Exception("該玩家沒有這張手牌");
        }
        player.removeCardFromHandCards(gameCard);
        Action action = new GameCardAction(game, player, gameCard, null);
        System.out.println("接到" + player.getUser().getName() + gameCardId);
        gameService.onReceiveAction(action);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/player/{playerId}/target/{targetPlayerId}")
    public ResponseEntity receiveTarget(@PathVariable String roomId,
                                        @PathVariable String playerId,
                                        @PathVariable String targetPlayerId) throws Exception {
        Game game = Main.getRoom(roomId).getGame();
        Player player = Main.getPlayer(roomId, playerId);
        if (player == null) {
            throw new Exception("Performer player not found");
        }
        Player targetPlayer;
        if (targetPlayerId.equals(null)) {
            targetPlayer = null;
            return ResponseEntity.status(403).body("no this targetPlayerId");
        } else {
            targetPlayer = Main.getPlayer(roomId, targetPlayerId);
            if(targetPlayer == null){
                return ResponseEntity.status(403).body("this targetPlayerId not exist");
            }
        }
        System.out.println("接到" + player.getUser().getName() + "指定" + targetPlayer.getUser().getName());
        Action action = new GameCardAction(game, player, null, targetPlayer);
        gameService.onReceiveAction(action);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start")
    public ResponseEntity startGame(@PathVariable String roomId) {
        try {
            roomService.startGame(roomId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/player/{playerId}/intelligence/{gameCardId}")
    public ResponseEntity receivePlayerSendIntelligence(@PathVariable String roomId,
                                          @PathVariable String playerId,
                                          @PathVariable String gameCardId) throws Exception {
        Game game = Main.getRoom(roomId).getGame();
        Player player = Main.getPlayer(roomId, playerId);
        if (player == null) {
            throw new Exception("Performer player not found");
        }
        //Todo getCardById
        GameCard gameCard = player.getCardById(gameCardId);
        if(gameCard == null){
            throw new Exception("該玩家沒有這張手牌");
        }
        player.removeCardFromHandCards(gameCard);
        Action action = new IntelligenceAction(game, player, gameCard, null);
        gameService.onReceiveAction(action);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/player/{playerId}/intelligence")
    public ResponseEntity playerChooseToReceiveIntelligence(@PathVariable String roomId,
                                              @PathVariable String playerId) throws Exception {
        Game game = Main.getRoom(roomId).getGame();
        Player player = Main.getPlayer(roomId, playerId);
        if (player == null) {
            throw new Exception("Performer player not found");
        }
        GameCard gameCard = game.getPassingCard();
        Action action = new ReceiveAction(game, player);
        JSONObject json = new JSONObject();
        json.put("color", gameCard.getColor().name);
        gameService.onReceiveAction(action);
        return ResponseEntity.ok().body(json.toString());
    }
    @PostMapping("/player/{playerId}/provechoose/{choosedOption}")
    public ResponseEntity playerToChooseForProve(@PathVariable String roomId,
                                                @PathVariable String playerId,
                                                @PathVariable String choosedOption) throws Exception {
        Game game = Main.getRoom(roomId).getGame();
        Player player = Main.getPlayer(roomId, playerId);
        if (player == null) {
            throw new Exception("Performer player not found");
        }
        Action action = new ProveAction(game, null, player, null, choosedOption);
        Round round = game.getRound();
        if(round instanceof ProveRound) {
            gameService.onReceiveAction(action);
        } else{
            System.out.println("Error: proof choice at wrong ");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/player/{playerId}/gameCard/target/{gameCardTargetId}")
    public ResponseEntity receiveGameCardTarget(@PathVariable String roomId,
                                        @PathVariable String playerId,
                                        @PathVariable String gameCardTargetId) throws Exception {
        Game game = Main.getRoom(roomId).getGame();
        Player player = Main.getPlayer(roomId, playerId);
        if (player == null) {
            throw new Exception("Performer player not found");
        }
        GameCard gameCardTarget = player.getCardById(gameCardTargetId);
        System.out.println("接到" + player.getUser().getName() + "指定" + player.getCardById(gameCardTargetId).getId());
        Action action = new ProveAction(game, null, player, gameCardTarget, null);
        gameService.onReceiveAction(action);
        return ResponseEntity.ok().build();
    }
}