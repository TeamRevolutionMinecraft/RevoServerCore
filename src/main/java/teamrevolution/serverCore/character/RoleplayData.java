package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;
import teamrevolution.serverCore.enums.Job;
import teamrevolution.serverCore.enums.Race;

public class RoleplayData implements IProperty{

    private String rolePlayName;
    private Job job;
    private Race race;

    public RoleplayData() {
        this.rolePlayName = "";
        this.job = Job.DEFAULT;
        this.race = Race.DEFAULT;
    }
    public RoleplayData(YamlConfiguration yamlConfiguration) {
        this.rolePlayName = yamlConfiguration.getString("ROLEPLAY.name");
        this.job = Job.valueOf(yamlConfiguration.getString("ROLEPLAY.job"));
        this.race = Race.valueOf(yamlConfiguration.getString("ROLEPLAY.race"));
    }

    @Override
    public String toSQLString(String collumName) {
        return "";
    }

    @Override
    public void updateYamlConfig(YamlConfiguration configuration) {
        configuration.set("ROLEPLAY.name", rolePlayName);
        configuration.set("ROLEPLAY.job", job.toString());
        configuration.set("ROLEPLAY.race", race.toString());
    }
}
