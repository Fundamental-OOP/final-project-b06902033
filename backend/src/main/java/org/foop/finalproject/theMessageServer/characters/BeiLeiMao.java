package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.BeiLeiMaoMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class BeiLeiMao extends Character {
    public BeiLeiMao() {
        super("貝雷帽", Gender.MAN, new BeiLeiMaoMission(), new emptySkill(), false);
    }
}
