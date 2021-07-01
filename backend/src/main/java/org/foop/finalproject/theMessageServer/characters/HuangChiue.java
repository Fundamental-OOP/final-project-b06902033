package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.HuangChiueMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class HuangChiue extends Character {
    public HuangChiue() {
        super("黃雀", Gender.WOMAN, new HuangChiueMission(), new emptySkill(), true);
    }
}
