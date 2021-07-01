package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.CiBaiMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class CiBai extends Character {

    public CiBai() {
        super("柒佰", Gender.MAN, new CiBaiMission(), new emptySkill(), false);
    }
}
