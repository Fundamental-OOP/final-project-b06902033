package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.LaoCiangMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class LaoCiang extends Character {
    public LaoCiang() {
        super("老槍", Gender.MAN, new LaoCiangMission(), new emptySkill(), true);
    }
}
