package org.foop.finalproject.theMessageServer.rounds;

import org.foop.finalproject.theMessageServer.Action;
import org.foop.finalproject.theMessageServer.gamecards.Counteract;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.actions.GameCardAction;
import org.foop.finalproject.theMessageServer.enums.MessageType;

public class CounteractRound extends Round {
    public CounteractRound(Player creator, GameCardRound gameCardRound) {
        super(gameCardRound);
        this.creator = creator;
        currentPlayer = creator;
        currentPlayer = getNextPlayer();
        endPlayer = creator;
        name = "Counteract Round";
    }

    @Override
    public void onRoundStart() {
        System.out.println(name+": onRoundStart start.");
        messageService.broadcastRoundStartMessage(game);
        System.out.println(name+": onRoundStart end.");
        onTurnStart();
    }

    @Override
    public void onTurnStart() {
        System.out.println(name+": onTurnStart start.");
        //messageService.broadcastTurnStartMessage(game, currentPlayer);
        //沒牌打
        if(currentPlayer.getHandcardsNum() == 0){
            System.out.print("沒牌打，進行跳過！！");
            System.out.println(name + ": onTurnStart end.");
            onTurnEnd();
            return;
        }
        else {
            messageService.broadcastPlayerToSelectAction(game, currentPlayer, MessageType.BROADCAST_PLAYER_START_SELECTING_GAMECARD);
            System.out.println(name + ": onTurnStart end.");
        }
    }


    @Override
    public void onTurnProgressing(Action action) {
        System.out.println(name+": onTurnProgressing start.");
        if(action instanceof GameCardAction){
            if(action.getCard() instanceof Counteract){
                game.placeGameCardActionOnBoard((GameCardAction)action);
                setEndPlayer(currentPlayer);
            }
        }
        System.out.println(name+": onTurnProgressing end.");
        onTurnEnd();
    }

    @Override
    public void doWhenLeaveChildRound() {
        System.out.println("In CounteractRound : 識破round不應該有child Round");
    }

    @Override
    public void onTurnEnd()  {
        System.out.println(name+": onTurnEnd.");
        // 判斷是否結束
        if(satisfyRoundEndCondition()){
            onRoundEnd();
            return;
        }
        // 更新currentPlayer
        currentPlayer = getNextPlayer();
        // start new turn
        onTurnStart();
    }

    @Override
    public void onRoundEnd() {
        System.out.println(name+": onRoundEnd start.");
        game.leaveRound();
        System.out.println(name+": onRoundEnd end.");
    }


    @Override
    public boolean satisfyRoundEndCondition() {
        return getEndPlayer() == getNextPlayer();
    }
}
