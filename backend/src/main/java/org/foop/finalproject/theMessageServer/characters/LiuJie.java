package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.LiuJieMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class LiuJie extends Character{
    public LiuJie() {
        super("六姊", Gender.WOMAN, new LiuJieMission(), new emptySkill(), false);
    }
}
