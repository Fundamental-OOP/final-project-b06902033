package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.YiDianYuanMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class YiDianYuan extends Character {
    public YiDianYuan() {
        super("譯電員", Gender.WOMAN, new YiDianYuanMission(), new emptySkill(), true);
    }
}
