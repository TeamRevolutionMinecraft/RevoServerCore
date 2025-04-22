package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;

public interface IProperty {
    String toSQLString(String collumName);
    void updateYamlConfig(YamlConfiguration configuration);

}
