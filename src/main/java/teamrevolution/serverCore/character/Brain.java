package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;

public class Brain implements IProperty{

    public Brain(){

    }
    public Brain(YamlConfiguration configuration){

    }

    @Override
    public String toSQLString(String collumName) {
        return "";
    }

    @Override
    public void updateYamlConfig(YamlConfiguration configuration) {

    }
}
