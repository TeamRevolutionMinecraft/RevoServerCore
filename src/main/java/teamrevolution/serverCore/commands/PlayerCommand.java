package teamrevolution.serverCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrevolution.serverCore.RevoCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class implementing common functionality for commands ran by players.
 */
public abstract class PlayerCommand implements CommandExecutor, TabCompleter {

    @NotNull
    protected final RevoCore plugin;
    @NotNull
    protected final String commandName;
    @NotNull
    protected final List<String> permissions;

    /**
     * Constructs a new PlayerCommand object and registers it.
     * @param plugin the plugin to register this command at
     * @param commandName the name of the command in the plugin.yml file
     * @param permissions the permissions which are needed to run this command; can be null
     */
    public PlayerCommand(@NotNull RevoCore plugin, @NotNull String commandName, @Nullable List<String> permissions) {
        this.plugin = plugin;
        this.commandName = commandName;
        this.permissions = permissions == null ? new ArrayList<>() : permissions;
        Objects.requireNonNull(plugin.getCommand(commandName)).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!permissions.isEmpty() && permissions.stream().noneMatch(sender::hasPermission)) {
            sender.sendMessage(CommandComponents.COMMAND_NO_PERMISSION);
            return false;
        }
        if(sender instanceof Player) {
            var player = (Player) sender;
            try {
                onPlayerCommand(player, command, label, args);
            } catch (Exception e) {
                RevoCore.getInstance().getLogger().warning("Error running command `" + commandName + "`: " + e);
            }
        }
        return false;
    }

    /**
     * This method is called when the {@link CommandSender} is a player and has the required permissions.
     * @param player the player who called this command
     * @param command the command object
     * @param label the label (alias) which this command has been called with
     * @param args the args of this command
     */
    public abstract void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args);

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
