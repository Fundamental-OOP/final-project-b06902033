package org.foop.finalproject.theMessageServer.rounds;


import org.foop.finalproject.theMessageServer.Action;
import org.foop.finalproject.theMessageServer.gamecards.Prove;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.Round;
import org.foop.finalproject.theMessageServer.actions.ProveAction;
import org.foop.finalproject.theMessageServer.enums.ProveOption;
import org.json.JSONObject;

public class ProveRound extends Round {
    Action action;
    public ProveRound(Player creator, Round parentRound, Action action) {
        super(parentRound);
        currentPlayer = creator;
        this.action = action;
        this.name = "Prove Round";
    }

    @Override
    public void onRoundStart() {
        System.out.print(name + "onRoundStart start");
    onTurnStart();
    }

    @Override
    public void onTurnStart() {
        System.out.print(name + "onTurnStart start");
        JSONObject jsonObject = new JSONObject();
        Prove gameCard = (Prove)this.action.getCard();
        jsonObject.put("performerId", action.getPerformer().getId());
        jsonObject.put("targetId", action.getPlayerTarget().getId());
        jsonObject.put("camp", gameCard.getTargetCamp().name);
        //TODO 有角色可以騙人 要判斷
        String[] possibleOptions = ProveOption.staticFunctions.getPossibleOptions(gameCard.getProveType());
        jsonObject.put("possibleOptions",possibleOptions);


        //if(gameCard.getProveType()){
            //抽兩張 or 我是臥底
            //String[] possibleOptions = {ProveOption.DRAW_TWO_CARDS.proveOption, ProveOption.BAD_GUY.proveOption};
            //jsonObject.put("possibleOptions",possibleOptions);
            /*
            if(action.getPlayerTarget().getCamp() == gameCard.getTargetCamp()){
                jsonObject.put("shouldBeChoosedOption","抽二張牌");
            }
            else{
                jsonObject.put("shouldBeChoosedOption","回答\"其實我是臥底\"");
            }*/
        //}
        //else{
            //丟一張 or 我是好人
        //    String[] possibleOptions = {ProveOption.THROW_ONE_CARD.proveOption, ProveOption.NICE_GUY.proveOption};
        //    jsonObject.put("possibleOptions",possibleOptions);
            /*
            if(action.getPlayerTarget().getCamp() == gameCard.getTargetCamp()){
                jsonObject.put("shouldBeChoosedOption","棄一張手牌");
            }
            else{
                jsonObject.put("shouldBeChoosedOption","回答\"我是一個好人\"");
            }*/
        //}
        System.out.print(name + "onTurnEnd end");
        messageService.informPlayerStartSelectActionForProve(action.getPerformer(), action.getPlayerTarget(), jsonObject);

    }

    @Override
    public void onTurnProgressing(Action action)  {
        System.out.print(name + "onTurnProgressing Start");
        //接到player選擇的指令（試探)
        //currentAction
        //this.action = action;
        ((ProveAction)this.action).setChosenOption(((ProveAction)action).getChosenOptionString());
        if(((ProveAction)this.action).checkIfNeedTarget()){
            System.out.println("需要選擇target 開始targetRound");
            childRound = new TargetSelectRound(this.action.getPlayerTarget(), this, this.action);
            game.setRound(childRound);
            childRound.onRoundStart();
        }
        else{
            onTurnEnd();
        }
        System.out.print(name + "onTurnProgressing end");
    }

    @Override
    public void doWhenLeaveChildRound() {
        onTurnEnd();
    }

    @Override
    public void onTurnEnd()  {
        System.out.print(name + "onTurnEnd start");
        action.execute();
        System.out.println("Prove操作執行完畢");
        System.out.println(name+": onTurnEnd end.");
        onRoundEnd();
    }

    @Override
    public void onRoundEnd() {
        System.out.print(name + "onRoundEnd end");
        game.leaveRound();
    }

    @Override
    public boolean satisfyRoundEndCondition() { return true; }
}
