package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.ShanLingMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class ShanLing extends Character {
    public ShanLing() {
        super("閃靈", Gender.WOMAN, new ShanLingMission(), new emptySkill(), true);
    }
}
