package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.EMeiFongMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class EMeiFong extends Character {
    public EMeiFong() {
        super("峨眉峰", Gender.MAN, new EMeiFongMission(), new emptySkill(), true);
    }
}
