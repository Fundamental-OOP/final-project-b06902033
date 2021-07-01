package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.GangTieTeGongMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class GangTieTeGong extends Character {

    public GangTieTeGong() {
        super("鋼鐵特工", Gender.MAN, new GangTieTeGongMission(), new emptySkill(), true);
    }
}
