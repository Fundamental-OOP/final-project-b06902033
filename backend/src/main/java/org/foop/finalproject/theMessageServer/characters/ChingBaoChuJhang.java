package org.foop.finalproject.theMessageServer.characters;

import org.foop.finalproject.theMessageServer.Character;
import org.foop.finalproject.theMessageServer.enums.Gender;
import org.foop.finalproject.theMessageServer.missions.ChingBaoChuJhangMission;
import org.foop.finalproject.theMessageServer.skills.emptySkill;

public class ChingBaoChuJhang extends Character {
    public ChingBaoChuJhang() {
        super("情報處長", Gender.MAN, new ChingBaoChuJhangMission(), new emptySkill(), false);
    }
}
