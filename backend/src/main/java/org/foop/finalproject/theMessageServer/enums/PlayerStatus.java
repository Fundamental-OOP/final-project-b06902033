package org.foop.finalproject.theMessageServer.enums;

public enum PlayerStatus {
    // TODO: refresh when intelligence turn end
    Normal("NORMAL"),
    LockOn("LOCKEDON"),
    Trap("TRAPPED"),
    Dead("DEAD"),
    Lose("LOSE"),
    Win("WIN");


    public String status;
    PlayerStatus(String status) {
            this.status = status;
    }

}
