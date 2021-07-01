package org.foop.finalproject.theMessageServer;

import org.foop.finalproject.theMessageServer.enums.Gender;
import org.json.JSONObject;


public abstract class Character {
    protected String name;
    protected Gender gender;
    protected Mission mission;
    protected Skill skill;
    protected boolean hidden;

    public Character(String name, Gender gender, Mission mission, Skill skill, boolean hidden) {
        this.name = name;
        this.gender = gender;
        this.mission = mission;
        this.skill = skill;
        this.hidden = hidden;
    }

    /*
    public String getMissionDescription(){
        if(this.hidden)
            throw new RuntimeException("The information is hidden.");
        return this.mission.getDescription();
    }
    public String getSkillDescription(){
        if(this.hidden)
            throw new RuntimeException("The information is hidden.");
        return this.skill.getDescription();
    }
    public boolean isMale(){return this.gender.isMale();}
    public boolean isFemale(){return this.gender.isFemale();}
    */

    protected void uncover(){
        if(!this.hidden){
            throw new RuntimeException("This card is not covered.");
        }
        this.hidden = false;
    }

    public boolean isHidden(){
        return hidden;
    }

    public void cover(){
        if(hidden){
            throw new RuntimeException("This card is not revealed");
        }
        this.hidden = true;
    }


    public boolean missionComplete(Game game, Player player){
        return mission.isCompleted(game, player);
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("gender", gender.name);
        jsonObject.put("skill", skill.getDescription());
        jsonObject.put("mission", mission.getDescription());
        jsonObject.put("hidden", hidden);
        return jsonObject;
    }
}



