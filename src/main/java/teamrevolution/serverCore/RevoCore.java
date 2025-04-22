package teamrevolution.serverCore;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import teamrevolution.serverCore.character.RevoPlayer;
import teamrevolution.serverCore.chat.RevoChat;
import teamrevolution.serverCore.commands.*;
import teamrevolution.serverCore.commands.admin.CmdCustomItem;
import teamrevolution.serverCore.commands.admin.CmdDebug;
import teamrevolution.serverCore.commands.player.CmdCharacter;
import teamrevolution.serverCore.commands.player.CmdRoleplayNameToggle;
import teamrevolution.serverCore.commands.support.CmdActivateSupportMode;
import teamrevolution.serverCore.economy.EconomyMain;
import teamrevolution.serverCore.fastTravel.CommandQuickTravelAdd;
import teamrevolution.serverCore.fastTravel.CommandQuickTravelTeleport;
import teamrevolution.serverCore.fastTravel.QuickTravelManger;
import teamrevolution.serverCore.listener.*;
import teamrevolution.serverCore.listener.gui.*;
import teamrevolution.serverCore.tasks.TablistTask;

import java.util.*;

public class RevoCore extends JavaPlugin {

    private static RevoCore instance;

    private HashMap<UUID, RevoPlayer> onlinePlayers;

    private QuickTravelManger quickTravelManger;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        initTranslations();
        initDebug();
        initCore();
        initChars();
//        initEconomy();
        initEquipment();
        initQuickTravel();

//        startTasks();
    }
    @Override
    public void onDisable() {
        stopEconomy();
    }

    private void initTranslations() {
        var translationRegistry = TranslationRegistry.create(Key.key("namespace:value"));

        var deBundle = ResourceBundle.getBundle("revocore.Bundle_de_DE");
        translationRegistry.registerAll(Locale.GERMAN, deBundle, true);
        translationRegistry.defaultLocale(Locale.GERMAN);

        // var enBundle = ResourceBundle.getBundle("revocore.Bundle_en_US");
        // translationRegistry.registerAll(Locale.US, enBundle, true);

        GlobalTranslator.translator().addSource(translationRegistry);
        getLogger().info("Translations loaded");
    }

    private void initDebug() {
        new CmdDebug(this);
        new CmdCustomItem(this);
        getLogger().info("Debugging features loaded");
    }

    private void initCore() {
        onlinePlayers = new HashMap<>();
        new PlayerJoinListener(this);
        new PlayerLeaveListener(this);
        new GenericGuiListener(this);
        new RevoChat(this);
        new ServerReloadListener(this);
        getLogger().info("Core features loaded");
    }

    private void initEconomy() {
        EconomyMain.initEconomy();
        EconomyMain.initEconomyCrafting();
        getLogger().info("Economy initialized");
    }

    private void stopEconomy() {
        EconomyMain.disableEconomy();
        getLogger().info("Economy stopped");
    }

    private void initChars() {
        new CmdCharacter(this);
        new CmdActivateSupportMode(this);
        new CharacterGuiListener(this);
        new CmdRoleplayNameToggle(this);
        new PlayerBadgeOpener(this);

        RevoPlayer.intiStorage();
        getLogger().info("Characters loaded");
    }

    private void initEquipment() {
        new CmdEquipmentMenu(this);
        new OpenEquipmentInventoryListener(this);
        new EquipmentGuiListener(this);
        getLogger().info("Equipment loaded");
    }

    private void initQuickTravel() {
        quickTravelManger = new QuickTravelManger();

        new CommandQuickTravelAdd(this);
        new CommandQuickTravelTeleport(this);
    }

    private void startTasks() {
        new TablistTask(this);
    }

    public void removePlayer(UUID uuid) {
        onlinePlayers.remove(uuid);
    }

    public Optional<RevoPlayer> getCharacter(UUID uuid) {
        return Optional.ofNullable(onlinePlayers.get(uuid));
    }

    public void addPlayerToOnlineList(Player player) {
        var revoPlayer = new RevoPlayer(player);
        onlinePlayers.put(player.getUniqueId(), revoPlayer);
        getLogger().info("Added player " + revoPlayer);
    }

    public HashMap<UUID, RevoPlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

    public NamespacedKey getNamespacedKey(String key) {
        return new NamespacedKey(this, key);
    }

    public static RevoCore getInstance() {
        return instance;
    }

    public QuickTravelManger getQuickTravelManger() {
        return quickTravelManger;
    }
}
