package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.DaoFongMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class DaoFong extends Character {
    public DaoFong() {
        super("刀鋒", Gender.MAN, new DaoFongMission(), new emptySkill(), false);
    }
}
