package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Skill;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.JhihMingSiangShueiMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class JhihMingSiangShuei extends Character {
    public JhihMingSiangShuei() {
        super("致命香水", Gender.WOMAN, new JhihMingSiangShueiMission(), new emptySkill(), false);
    }
}
