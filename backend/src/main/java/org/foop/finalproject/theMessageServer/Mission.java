package org.foop.finalproject.theMessageServer;

public abstract class Mission{
    protected String description;

    abstract protected boolean isCompleted(Game game, Player player);

    public String getDescription() {
        return description;
    }
}