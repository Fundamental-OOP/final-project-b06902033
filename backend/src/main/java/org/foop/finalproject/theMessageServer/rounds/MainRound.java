package org.foop.finalproject.theMessageServer.rounds;

import org.foop.finalproject.theMessageServer.*;
import org.foop.finalproject.theMessageServer.actions.IntelligenceAction;
import org.foop.finalproject.theMessageServer.enums.MessageType;

public class MainRound extends Round {
    boolean intelligenceHasSent;
    Action currentAction;
    public MainRound(Player creator, Game game) {
        super(game);
        this.creator = creator;
        currentPlayer = creator;
        endPlayer = null;
        name = "Main Round";
        intelligenceHasSent = false;
    }

    @Override
    public void onRoundStart() {
        System.out.println("--------遊戲開始--------");
        onTurnStart();

    }

    @Override
    public void onTurnStart() {
        System.out.println("MainRound: onTurnStart start");
        // TODO: 廣播誰負責派情報，並且廣播開始功能牌階段
        intelligenceHasSent = false;
        currentPlayer.drawCards(2);
        //要判怪盜99之類集手牌的角色獲勝
        if(satisfyRoundEndCondition()){
            onRoundEnd();
            return;
        }
        messageService.broadcastTurnStartMessage(game, currentPlayer);
        childRound = new GameCardRound(currentPlayer, this);
        game.setRound(childRound);
        System.out.println("MainRound: onTurnStart end");
        childRound.onRoundStart();
    }

    @Override
    public void onTurnProgressing(Action action) {
        System.out.println("MainRound: onTurnProgressing");
        // TODO: 派情報
        // action為收到的情報
        game.setPassingCard(action.getCard());

        if(action.getCard().isDirectMessage()){
            ChooseDirectMessageTarget(action);
        } else{
            SendIntelligence(action);
        }
    }
    public void ChooseDirectMessageTarget(Action action){
        currentAction = action;
        childRound = new TargetSelectRound(currentPlayer, this, action);
        game.setRound(childRound);
        childRound.onRoundStart();
    }
    public void SendIntelligence(Action action){
        messageService.broadcastActionBeenPlayedMessage(game, action);
        childRound = new IntelligenceRound(currentPlayer, this, (IntelligenceAction)action);
        game.setRound(childRound);
        childRound.onRoundStart();
    }

    @Override
    public void doWhenLeaveChildRound() {
        System.out.println("MainRound: doWhenLeaveChildRound");
        if(childRound instanceof GameCardRound){
            //還有沒有手牌能夠打出當情報
            if(game.winnerAppears()){
                onRoundEnd();
                return;
            }
            if(currentPlayer.hasNoHandcards()){
                currentPlayer.loseTheGame();
                onTurnEnd();
            }
            else {
                //Todo
                messageService.broadcastPlayerToSelectAction(game, currentPlayer, MessageType.BROADCAST_PLAYER_START_SELECTING_INTELLIGENCE);

            }
        } else if(childRound instanceof TargetSelectRound){
            SendIntelligence(currentAction);

        } else {
            // end Round

            onTurnEnd();
        }
    }

    @Override
    public void onTurnEnd() {
        System.out.println("MainRound: onTurnEnd");
        if(satisfyRoundEndCondition()){
            onRoundEnd();
        }
        else {
            currentPlayer = getNextPlayer();
            onTurnStart();
        }
    }

    @Override
    public void onRoundEnd() {
        System.out.println("MainRound: onRoundEnd");
        // TODO: Game over
        game.leaveRound();
        // inform a player to play a card
        // GameService -> wake up round.onTurnProgressing

        // gameCard round over: round.onTurnProgressing()
        // Game.over();
    }

    @Override
    public boolean satisfyRoundEndCondition() {
        System.out.println("MainRound: SatisfyRoundEndCondition");
        return game.winnerAppears();
    }
}
