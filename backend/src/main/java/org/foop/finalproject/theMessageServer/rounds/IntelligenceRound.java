package org.foop.finalproject.theMessageServer.rounds;

import org.foop.finalproject.theMessageServer.Action;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.actions.IntelligenceAction;
import org.foop.finalproject.theMessageServer.actions.PassAction;
import org.foop.finalproject.theMessageServer.actions.ReceiveAction;
import org.foop.finalproject.theMessageServer.enums.IntelligenceType;
import org.foop.finalproject.theMessageServer.enums.MessageType;
import org.foop.finalproject.theMessageServer.enums.PlayerStatus;

import java.util.ArrayList;

public class IntelligenceRound extends Round {
    IntelligenceAction intelligence;
    public IntelligenceRound(Player creator, Round parentRound, IntelligenceAction intelligence) {
        super(parentRound);
        this.creator = creator;
        currentPlayer = creator;
        this.intelligence = intelligence;
        currentPlayer = getNextPlayer();
        endPlayer = creator;
        this.name = "Intelligence Round";
    }

    public IntelligenceAction getIntelligence() {
        return intelligence;
    }

    public void changeDirection(){ direction *= -1; }

    @Override
    public Player getNextPlayer(){
        //直達
        if(intelligence.getType().equals(IntelligenceType.DIRECT_MSG) ){
            if(currentPlayer == intelligence.getPlayerTarget()){
                return intelligence.getPerformer();
            }
            else{
                return intelligence.getPlayerTarget();
            }
        }//密電 文本
        else{
            int playerId = -1;
            ArrayList<Player> players = game.getPlayers();
            for(int i = 0; i < players.size(); i++){
                if(players.get(i) == currentPlayer){
                    playerId = i;
                    break;
                }
            }
            int nextPlayerId = (playerId + direction + players.size()) % players.size();
            while(!players.get(nextPlayerId).isAlive() || players.get(nextPlayerId).getStatus() == PlayerStatus.Trap){
                nextPlayerId = (nextPlayerId + direction + players.size()) % players.size();
            }
            return players.get(nextPlayerId);
        }
    }

    @Override
    public void onRoundStart() {
        //this.endPlayer.beLockOn();
        //messageService.broadcastRoundStartMessage(game);
        //廣播誰打出情報
        onTurnStart();
    }

    @Override
    public void onTurnStart()  {
        System.out.println("IntelligenceRound: onTurnStart start.");
        messageService.broadcastPlayerOnIntelligenceInFront(game, currentPlayer);
        childRound = new GameCardRound(currentPlayer, this);
        game.setRound(childRound);
        childRound.onRoundStart();
        System.out.println("IntelligenceRound: onTurnStart end.");
    }

    @Override
    public void onTurnProgressing(Action action) {
        System.out.println("IntelligenceRound: onTurnProgressing start.");
        if(action instanceof ReceiveAction){
            //接收情報
            ReceiveAction receiveAction = new ReceiveAction(intelligence, currentPlayer);
            currentPlayer.receiveIntelligence(receiveAction);
            onRoundEnd();
        }
        else if(action instanceof PassAction){
            //Pass情報
            messageService.broadcastActionBeenPlayedMessage(game, action);
            onTurnEnd();
        }
        else{
            System.out.println("Error in IntelligenceRound: get neither ReceiveAction nor PassAction!");
        }
        System.out.println("IntelligenceRound: onTurnProgressing end.");
    }

    @Override
    public void doWhenLeaveChildRound() {
        // 詢問接收
        System.out.println("IntelligenceRound: doWhenLeaveChildRound start.");

        // TODO 調虎離山 and 退回
        if(currentPlayer.getStatus() == PlayerStatus.Trap){
            System.out.println(currentPlayer + " 不能接收情報。");
            System.out.println("IntelligenceRound: doWhenLeaveChildRound end.");
            onTurnEnd();
        }
        else {
            messageService.broadcastPlayerToSelectAction(game, currentPlayer, MessageType.BROADCAST_PLAYER_START_SELECTING_RECEIVE);
            System.out.println("IntelligenceRound: doWhenLeaveChildRound end.");
        }
    }


    @Override
    public void onTurnEnd() {

        System.out.println("IntelligenceRound: onTurnEnd start.");
        this.currentPlayer = getNextPlayer();

        System.out.println("IntelligenceRound: onTurnEnd end.");
        onTurnStart();
    }

    @Override
    public void onRoundEnd() {
        System.out.println("IntelligenceRound: onRoundEnd start.");
        refreshAllPlayerStatus();
        System.out.println("IntelligenceRound: onRoundEnd end.");
        game.leaveRound();
    }
    private void refreshAllPlayerStatus(){
        for(Player player: game.getPlayers()){
            if(player.isAlive()) {
                player.setStatus(PlayerStatus.Normal);
            }
        }
    }
    @Override
    public boolean satisfyRoundEndCondition() {
        return false;
    }
}
