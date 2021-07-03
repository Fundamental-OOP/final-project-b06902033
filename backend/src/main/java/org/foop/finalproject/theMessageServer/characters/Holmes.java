package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.HolmesMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class Holmes extends Character {
    public Holmes() {
        super("福爾摩斯", Gender.MAN, new HolmesMission(), new emptySkill(), false);
    }
}
