package org.foop.finalproject.theMessageServer.enums;

public enum Camp {
    RED(0, "潛伏戰線"), BLUE(1, "軍情局"), GREEN(2, "打醬油的");

    public int type;
    public String name;

    Camp(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
