package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.Game;
import org.foop.finalproject.theMessageServer.Player;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.emptyMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;


public class fakeCharacter extends Character {
    public fakeCharacter(){
        super(  "fake guy", Gender.NONE, new emptyMission(), new emptySkill(), false);
    }

    @Override
    public boolean missionComplete(Game game, Player player) {
        return false;
    }
}
