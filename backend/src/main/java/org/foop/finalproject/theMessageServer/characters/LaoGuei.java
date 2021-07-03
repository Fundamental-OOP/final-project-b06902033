package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.LauGueiMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class LaoGuei extends Character {
    public LaoGuei(){
        super(  "老鬼", Gender.WOMAN, new LauGueiMission(), new emptySkill(), true);
    }

}
