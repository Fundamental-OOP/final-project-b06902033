package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.SiaoBaiMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class SiaoBai extends Character {
    public SiaoBai() {
        super("小白", Gender.BOTH, new SiaoBaiMission(), new emptySkill(), false);
    }
}
