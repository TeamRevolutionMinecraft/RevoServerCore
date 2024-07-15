package teamrevolution.serverCore.fastTravel;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;

import java.util.Arrays;

public class CommandQuickTravelTeleport extends PlayerCommand {

    public CommandQuickTravelTeleport(@NotNull RevoCore plugin) {
        super(plugin, "travelTP", Arrays.asList("revo.admin", "revo.debug"));
    }


    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        QuickTravelManger manger = plugin.getQuickTravelManger();

        QuickTravelLocation travelLocation = manger.getLocation(args[0]);
        travelLocation.pullPlayer(player, -1);
    }
}
