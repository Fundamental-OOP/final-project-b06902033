package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.SiaoMaGeMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class SiaoMaGe extends Character {
    public SiaoMaGe() {
        super("小馬哥", Gender.MAN, new SiaoMaGeMission(), new emptySkill(), false);
    }
}
