package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.professionalKillerMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class professionalKiller extends Character {
    public professionalKiller() {
        super("職業殺手", Gender.MAN, new professionalKillerMission(), new emptySkill(), false);
    }
}
