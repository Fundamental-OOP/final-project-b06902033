package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.FuPingMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class FuPing extends Character {
    public FuPing() {
        super("浮萍", Gender.WOMAN, new FuPingMission(), new emptySkill(), true);
    }
}
