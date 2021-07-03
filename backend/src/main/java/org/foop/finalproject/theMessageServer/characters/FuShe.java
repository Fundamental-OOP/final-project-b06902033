package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.FuSheMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class FuShe extends Character {

    public FuShe() {
        super("蝮蛇", Gender.MAN, new FuSheMission(), new emptySkill(), false);
    }
}
