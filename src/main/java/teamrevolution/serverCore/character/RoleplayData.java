package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;
import teamrevolution.serverCore.enums.Job;
import teamrevolution.serverCore.enums.Race;

public class RoleplayData implements IProperty{

    private final String PREFIX = "ROLEPLAY.";

    private final String NAME = PREFIX + "NAME";
    private final String JOB = PREFIX + "JOB";
    private final String RACE = PREFIX + "RACE";
    private final String IS_ROLEPLAY = PREFIX + "IS_ROLEPLAY";

    private String rolePlayName;
    private Job job;
    private Race race;
    private boolean isRolePlaying;

    public RoleplayData() {
        this.rolePlayName = "";
        this.job = Job.DEFAULT;
        this.race = Race.DEFAULT;
        this.isRolePlaying = false;
    }
    public RoleplayData(YamlConfiguration yamlConfiguration) {
        this.rolePlayName = yamlConfiguration.getString(NAME);
        this.job = Job.valueOf(yamlConfiguration.getString(JOB));
        this.race = Race.valueOf(yamlConfiguration.getString(RACE));
        this.isRolePlaying = yamlConfiguration.getBoolean(IS_ROLEPLAY);
    }

    @Override
    public String toSQLString(String collumName) {
        return "";
    }

    @Override
    public void updateYamlConfig(YamlConfiguration configuration) {
        configuration.set(NAME, rolePlayName);
        configuration.set(JOB, job.toString());
        configuration.set(RACE, race.toString());
        configuration.set(IS_ROLEPLAY, isRolePlaying);
    }

    public Job getJob() {
        return job;
    }

    public boolean isRolePlaying() {
        return isRolePlaying;
    }

    public Race getRace() {
        return race;
    }

    public String getRolePlayName() {
        return rolePlayName;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setRolePlaying(boolean rolePlaying) {
        isRolePlaying = rolePlaying;
    }

    public void toggleRolePlaying() {
        isRolePlaying = !isRolePlaying;
    }
    public void setRolePlayName(String rolePlayName) {
        this.rolePlayName = rolePlayName;
    }
}
