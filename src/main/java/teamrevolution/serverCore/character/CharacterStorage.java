package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.enums.Job;
import teamrevolution.serverCore.enums.Race;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class CharacterStorage {

    private static Path STORAGE_PATH = null;

    private static Path getPath(@NotNull UUID uuid) {
        return Paths.get(STORAGE_PATH + "/" + uuid + ".yml");
    }

    public static void init(@NotNull Plugin plugin) {
        STORAGE_PATH = Paths.get(plugin.getDataFolder().getPath(), "/", "chars");
        try {
            if(!STORAGE_PATH.toFile().mkdirs()) {
                plugin.getLogger().warning("Character STORAGE_PATH not created (because it probably exists already)");
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Error while creating character folder: " + e);
        }
    }

    public static boolean store(@NotNull UUID uuid, @NotNull CharacterData characterData) {
        var file = getPath(uuid).toFile();
        var yamlConfig = YamlConfiguration.loadConfiguration(file);

        // TODO this data is unused?
        // yamlConfig.set("generic.mc_name", player.getPlayer().getName());

        yamlConfig.set("generic.rp_name", characterData.getRoleplayName());
        yamlConfig.set("generic.job", characterData.getJob().toString());
        yamlConfig.set("generic.race", characterData.getRace().toString());
        yamlConfig.set("generic.look", characterData.getLookDescription());

        try {
            yamlConfig.save(file);
            return true;
        } catch (IOException e) {
            RevoCore.getInstance().getLogger().severe("Failed to save player data for " + uuid);
            return false;
        }
    }

    public static CharacterData load(@NotNull UUID uuid) {
        var file = getPath(uuid).toFile();
        var yamlConfig = YamlConfiguration.loadConfiguration(file);

        return new CharacterData(
                yamlConfig.getString("generic.rp_name"),
                Job.valueOf(yamlConfig.getString("generic.job")),
                Race.valueOf(yamlConfig.getString("generic.race")),
                yamlConfig.getStringList("generic.look")
        );
    }

    public static boolean delete(@NotNull UUID uuid) {
        try {
            Files.deleteIfExists(getPath(uuid));
            return true;
        } catch (IOException e) {
            RevoCore.getInstance().getLogger().severe("Failed to delete player " + uuid + " with reason: " + e.getMessage());
            return false;
        }
    }

    public static boolean isRegistered(@NotNull UUID uuid) {
        return Files.exists(getPath(uuid));
    }

}
