package teamrevolution.serverCore.fastTravel;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;


public class CommandTravelFound extends PlayerCommand {
    public CommandTravelFound(@NotNull RevoCore plugin) {
        super(plugin, "travelFound", null);
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

    }
}
