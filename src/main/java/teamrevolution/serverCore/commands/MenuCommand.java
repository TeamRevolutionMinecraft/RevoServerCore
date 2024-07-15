package teamrevolution.serverCore.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrevolution.serverCore.RevoCore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Extension of {@link PlayerCommand} which simplifies the process of creating sub commands
 */
public abstract class MenuCommand extends PlayerCommand {

    /**
     * Represents a subcommand action
     */
    @FunctionalInterface
    public interface Action {
        /**
         * This method is called when a valid subcommand is provided. Thrown exception are logged.
         * @param player the player which executes this command
         * @param command the object representing the whole command
         * @param label the label which the main command was called with TODO is this even useful here?
         * @param args the args of the original command invoke but stripped of the first element
         */
        void onCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args);
    }

    @NotNull
    protected Map<String, Action> actions;
    protected Action defaultAction = (player, command, label, args) -> {
        var subcommands = this.actions.keySet().stream().reduce("", (s, s2) -> s + " " + s2);
        player.sendMessage(Component.translatable("commands.possible_subcommands", Component.text(subcommands)));
    };

    /**
     * Constructs a new menu command object and registers it. <br>
     * To specify sub commands, call `this.actions.put(name, Action#onCommand)` in the constructor for each subcommand
     * @param plugin the plugin to register this command at
     * @param commandName the name of the base command
     * @param permissions the permission needed for the base command
     */
    public MenuCommand(@NotNull RevoCore plugin, @NotNull String commandName, @Nullable List<String> permissions) {
        super(plugin, commandName, permissions);
        // TODO alias for actions?
        this.actions = new HashMap<>();
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length >= 1) {
            var subcommand = args[0].toLowerCase();
            var action = actions.get(subcommand);
            if(action != null) {
                try {
                    action.onCommand(player, command, label, Arrays.copyOfRange(args, 1, args.length));
                } catch (Exception e) {
                    RevoCore.getInstance().getLogger().warning("Error running command " + commandName + " " + subcommand + " with exception: " + e);
                }
                return;
            }
        }
        this.defaultAction.onCommand(player, command, label, new String[0]);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1) {
            return this.actions.keySet().stream().filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }
        return super.onTabComplete(sender, command, label, args);
    }
}
