package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.DaMeiNyuMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class DaMeiNyu extends Character {
    public DaMeiNyu() {
        super("大美女", Gender.WOMAN, new DaMeiNyuMission(), new emptySkill(), false);
    }
}
