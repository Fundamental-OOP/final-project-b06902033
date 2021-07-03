package org.foop.finalproject.theMessageServer.enums;

public enum GameCardColor {
    BLACK(2, "Black"), RED(0, "Red"), BLUE(1, "Blue");
    public int type;
    public String name;
    GameCardColor(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
