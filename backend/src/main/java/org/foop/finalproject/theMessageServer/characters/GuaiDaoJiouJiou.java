package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.Mission;
import org.foop.finalproject.theMessageServer.Skill;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.GuaiDaoJiouJiouMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class GuaiDaoJiouJiou extends Character {

    public GuaiDaoJiouJiou() {
        super("怪盜九九", Gender.MAN, new GuaiDaoJiouJiouMission(), new emptySkill(), false);
    }
}
