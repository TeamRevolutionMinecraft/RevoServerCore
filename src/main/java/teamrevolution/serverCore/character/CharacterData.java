package teamrevolution.serverCore.character;

import teamrevolution.serverCore.enums.Job;
import teamrevolution.serverCore.enums.Race;

import java.util.ArrayList;
import java.util.List;

public class CharacterData {

    private String roleplayName;
    private Job job;
    private Race race;
    private List<String> lookDescription;

    public CharacterData() {
        this.roleplayName = "";
        this.job = Job.DEFAULT;
        this.race = Race.DEFAULT;
        this.lookDescription = new ArrayList<>();
    }

    public CharacterData(String roleplayName, Job job, Race race, List<String> lookDescription) {
        this.roleplayName = roleplayName;
        this.job = job;
        this.race = race;
        this.lookDescription = lookDescription;
    }

    public String getRoleplayName() {
        return roleplayName;
    }

    public void setRoleplayName(String roleplayName) {
        this.roleplayName = roleplayName;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public List<String> getLookDescription() {
        return lookDescription;
    }

    public void setLookDescription(List<String> lookDescription) {
        this.lookDescription = lookDescription;
    }
}
