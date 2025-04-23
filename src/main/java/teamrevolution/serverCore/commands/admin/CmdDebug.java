package teamrevolution.serverCore.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;
import teamrevolution.serverCore.enums.Rank;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

import java.util.Arrays;

public class CmdDebug extends PlayerCommand {

    public CmdDebug(@NotNull RevoCore plugin) {
        super(plugin, "debug", Arrays.asList("revo.admin", "revo.debug"));
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(player.isOp() && LuckyPermsIntegration.getRankFromPlayer(player).ordinal() == Rank.TEAM.ordinal())) {
            return;
        }
        var character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
        player.sendMessage(character.toString());
    }
}
