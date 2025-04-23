package teamrevolution.serverCore.commands.support;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;

import java.util.Arrays;

public class CmdActivateSupportMode extends PlayerCommand {
    public CmdActivateSupportMode(@NotNull RevoCore plugin) {
        super(plugin, "supporting", Arrays.asList("revo.admin", "revo.support"));
    }


    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        boolean playerAuthorized = false;
        for (String perm : permissions) {
            if (player.hasPermission(perm)) {
                playerAuthorized = true;
            }
        }
        if (!(playerAuthorized || player.isOp())) {
            //TODO add errormessage
            return;
        }
        var revoPlayer = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();

        if (revoPlayer.getAdminData().isCurrently_supporting()) {
            RevoCore.getInstance().getLogger().info(" [SUPPORT] " + player.getName() + " is no longer supporting");
        } else {
            RevoCore.getInstance().getLogger().info(" [SUPPORT] " + player.getName() + " is now supporting");
        }
        revoPlayer.getAdminData().setCurrently_supporting(!revoPlayer.getAdminData().isCurrently_supporting());
    }
}
