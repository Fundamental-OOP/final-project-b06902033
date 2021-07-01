package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.DaiLiMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class DaiLi extends Character {
    public DaiLi() {
        super("戴笠", Gender.MAN, new DaiLiMission(), new emptySkill(), false);
    }
}
