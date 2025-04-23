package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;
import teamrevolution.serverCore.enums.Job;
import teamrevolution.serverCore.enums.Race;

public class AdminData implements IProperty {
    private final String PREFIX = "ADMIN_DATA.";

    private final String IS_SUPPORTING = PREFIX + "is_supporting";

    private boolean currently_supporting;
    public AdminData() {
        this.currently_supporting = false;
    }
    public AdminData(YamlConfiguration yamlConfiguration) {
        this.currently_supporting = yamlConfiguration.getBoolean(IS_SUPPORTING);
    }


    @Override
    public String toSQLString(String collumName) {
        return "";
    }

    @Override
    public void updateYamlConfig(YamlConfiguration configuration) {
        configuration.set(IS_SUPPORTING, currently_supporting);
    }

    public boolean isCurrently_supporting() {
        return currently_supporting;
    }

    public void setCurrently_supporting(boolean currently_supporting) {
        this.currently_supporting = currently_supporting;
    }
}
