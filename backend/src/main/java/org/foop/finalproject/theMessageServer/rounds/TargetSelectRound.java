package org.foop.finalproject.theMessageServer.rounds;

import org.foop.finalproject.theMessageServer.*;

public class TargetSelectRound extends Round {
    Action action;
    public TargetSelectRound(Player creator, Round parentRound, Action action) {
        super(parentRound);
        currentPlayer = creator;
        this.action = action;
        this.name = "Target Select Round";
    }

    @Override
    public void onRoundStart() {
        onTurnStart();
    }

    @Override
    public void onTurnStart() {
        if(parentRound instanceof ProveRound){
            messageService.informPlayerStartSelectGameCardTarget(game, currentPlayer);
        }
        else {
            messageService.informPlayerStartSelectTarget(game, currentPlayer, action);
        }
    }

    @Override
    public void onTurnProgressing(Action action)  {
        if(parentRound instanceof ProveRound){
            GameCard gameCardTarget = action.getGameCardTarget();
            this.action.setGameCardTarget(gameCardTarget);
        }
        else {
            Player playerTarget = action.getPlayerTarget();
            this.action.setPlayerTarget(playerTarget);
        }
        onTurnEnd();
    }

    @Override
    public void doWhenLeaveChildRound() { }

    @Override
    public void onTurnEnd()  { onRoundEnd(); }

    @Override
    public void onRoundEnd() {
        game.leaveRound();

    }

    @Override
    public boolean satisfyRoundEndCondition() { return true; }
}
