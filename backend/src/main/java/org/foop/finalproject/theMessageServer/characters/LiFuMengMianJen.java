package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.LiFuMengMianJenMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class LiFuMengMianJen extends Character {
    public LiFuMengMianJen() {
        super("禮服蒙面人", Gender.MAN, new LiFuMengMianJenMission(), new emptySkill(), false);
    }
}
