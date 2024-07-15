package teamrevolution.serverCore.chat;

import org.bukkit.configuration.file.YamlConfiguration;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.chat.CmdDiscordConnection;
import teamrevolution.serverCore.commands.chat.CmdSelectChannel;
import teamrevolution.serverCore.commands.chat.CmdChannelQuickSelect;
import teamrevolution.serverCore.enums.ChatChannel;
import teamrevolution.serverCore.listener.chat.ChatListener;
import teamrevolution.serverCore.listener.chat.DiscordListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RevoChat {
    private static HashMap<String, String> config;

    private final RevoCore plugin;
    public RevoChat(RevoCore plugin) {
        this.plugin = plugin;
        this.enable();
    }
    public void enable() {
        config = new HashMap<>();

        loadConfig("config.yml");

        new CmdSelectChannel(plugin);
        new ChatListener(plugin);

        enableQuickCommands();

        enableDiscordListener();
    }

    public void enableQuickCommands() {
        new CmdChannelQuickSelect(plugin, "flüstern", ChatChannel.FLUESTERN);
        new CmdChannelQuickSelect(plugin, "global", ChatChannel.OOC);
        new CmdChannelQuickSelect(plugin, "reden", ChatChannel.REDEN);
        new CmdChannelQuickSelect(plugin, "rufen", ChatChannel.RUFEN);
        new CmdChannelQuickSelect(plugin, "schreien", ChatChannel.SCHREIEN);
        new CmdChannelQuickSelect(plugin, "support", ChatChannel.SUPPORT);
    }

    public void enableDiscordListener() {
        DiscordListener listener = new DiscordListener(plugin, config.get("url"), Integer.parseInt(config.get("port")));
        listener.listenToDiscordTask();
        new CmdDiscordConnection(plugin, listener);
    }

    public void loadConfig(String pathToConfigFile) {
        // TODO why is the config file ambiguously named AND located in the root config folder?
        File f = new File(plugin.getDataFolder() + "/" + pathToConfigFile);
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);


        if (!f.exists()) {
            try {
                yaml.set("api.port", 5010);
                yaml.set("api.url", "localhost");
                yaml.set("api.discordRoom", "1009849023991136268");
                yaml.save(f);
            } catch (IOException e) {
                // throw new RuntimeException(e);
                plugin.getLogger().severe("Failed to save chat config: " + e);
            }
        }

        config.put("port", String.valueOf(yaml.getInt("api.port")));
        config.put("url", yaml.getString("api.url"));
        config.put("discordRoom", yaml.getString("api.discordRoom"));

    }

    public static HashMap<String, String> getChatConfig() {
        return config;
    }

}
