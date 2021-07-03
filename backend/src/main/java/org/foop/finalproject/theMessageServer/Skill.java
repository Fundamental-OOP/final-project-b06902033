package org.foop.finalproject.theMessageServer;

public abstract class Skill{
    String description = "";
    public abstract void perform();

    public String getDescription() {
        return description;
    }
}
