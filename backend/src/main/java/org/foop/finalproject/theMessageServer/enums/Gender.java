package org.foop.finalproject.theMessageServer.enums;

public enum Gender {
    MAN(true, false, "man"),
    WOMAN(false, true, "woman"),
    BOTH(true, true, "both"),
    NONE(true, true, "none");

    boolean male;
    boolean female;
    public String name;

    Gender(boolean male, boolean female, String name) {
        this.male = male;
        this.female = female;
        this.name = name;
    }

    public boolean isMale(){
        return male;
    }
    public boolean isFemale(){
        return female;
    }
}
