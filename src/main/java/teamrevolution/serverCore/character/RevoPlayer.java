package teamrevolution.serverCore.character;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.enums.ChatChannel;
import teamrevolution.serverCore.enums.PlayerEditMode;
import teamrevolution.serverCore.exceptions.PlayerCannotBeLoaded;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class RevoPlayer {
    private static Path STORAGE_PATH = null;
    private static Path getPath(@NotNull UUID uuid) {
        return Paths.get(STORAGE_PATH + "/" + uuid + ".yml");
    }

    private final Player player;
    private final OfflinePlayer offlinePlayer;
    private ChatChannel currentChannel;
    private PlayerEditMode playerEditMode;

    private Brain brain;
    private Preferences preferences;
    private RoleplayData roleplayData;

    private AdminData adminData;

    public static void intiStorage() {
        Plugin plugin = RevoCore.getInstance();
        STORAGE_PATH = Paths.get(plugin.getDataFolder().getPath(),
                "/", "revoChars");
        try {
            if(!STORAGE_PATH.toFile().mkdirs()) {
                plugin.getLogger().warning("Character STORAGE_PATH not created (because it probably exists already)");
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Error while creating character folder: " + e);
        }
    }

    public RevoPlayer(Player player) {
        this.player = player;
        this.offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());
        var file = getPath(player.getUniqueId()).toFile();
        this.playerEditMode = null;
        if (file.exists()) {
            // Player is known to the System
            var yamlConfig = YamlConfiguration.loadConfiguration(file);
            this.currentChannel = ChatChannel.valueOf(yamlConfig.getString("MISC.currentChannel"));


            this.brain = new Brain(yamlConfig);
            this.preferences = new Preferences(yamlConfig);
            this.roleplayData = new RoleplayData(yamlConfig);
        } else {
            // Player is new to the System
            player.sendMessage("Hallo");
            this.currentChannel = ChatChannel.OOC;
        }

    }
    public RevoPlayer(UUID uuid) {
        // Wrapper mostly for use in Brain Logic
        this.player = null;
        this.offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        var file = getPath(uuid).toFile();
        if (!file.exists()) {
            var logger = RevoCore.getInstance().getLogger();
            logger.severe("Player with uuid" + uuid + "was not able to be loaded");
            throw new PlayerCannotBeLoaded(uuid);
        }
        var yamlConfig = YamlConfiguration.loadConfiguration(file);

        this.roleplayData = new RoleplayData(yamlConfig);

    }


    public boolean store() {
        Plugin plugin = RevoCore.getInstance();

        File dataFile = getPath(player.getUniqueId()).toFile();
        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(dataFile);


        plugin.getLogger().info("Saving " + player.getName() + "with UUID" + player.getUniqueId());

        brain.updateYamlConfig(yamlConfig);
        plugin.getLogger().info("Brain Appended");

        preferences.updateYamlConfig(yamlConfig);
        plugin.getLogger().info("Preferences Appended");

        roleplayData.updateYamlConfig(yamlConfig);
        plugin.getLogger().info("RoleplayData Appended");

        yamlConfig.set("MISC.currentChannel", currentChannel);

        adminData.updateYamlConfig(yamlConfig);
        plugin.getLogger().info("ADMIN DATA Appended");

        try {
            yamlConfig.save(dataFile);
            return true;
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save player data for " + player.getUniqueId());
            return false;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }
    public Brain getBrain() {
        return brain;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public RoleplayData getRoleplayData() {
        return roleplayData;
    }

    public AdminData getAdminData() {
        return adminData;
    }

    public ChatChannel getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(ChatChannel currentChannel) {
        this.currentChannel = currentChannel;
    }

    public PlayerEditMode getPlayerEditMode() {
        return playerEditMode;
    }

    public void setPlayerEditMode(PlayerEditMode playerEditMode) {
        this.playerEditMode = playerEditMode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RevoPlayer other = (RevoPlayer) obj;
        return offlinePlayer.getUniqueId().equals(other.getOfflinePlayer().getUniqueId());
    }

    @Override
    public int hashCode() {
        return offlinePlayer.getUniqueId().hashCode();
    }
}
