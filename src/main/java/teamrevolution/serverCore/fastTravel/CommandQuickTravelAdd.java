package teamrevolution.serverCore.fastTravel;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;

import java.util.Arrays;

public class CommandQuickTravelAdd extends PlayerCommand {
    public CommandQuickTravelAdd(@NotNull RevoCore plugin) {
        super(plugin, "travelAdd", Arrays.asList("revo.admin"));
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        RevoCore.getInstance().getLogger().info("COMMAND");
        player.sendMessage("Wird erstell mit dem namen: " + args[0]);

        Location loc = player.getLocation();

        QuickTravelManger manger = plugin.getQuickTravelManger();
        manger.addLocation(loc, args[0]);

    }
}
