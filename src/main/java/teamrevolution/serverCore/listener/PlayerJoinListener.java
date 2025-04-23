package teamrevolution.serverCore.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import teamrevolution.serverCore.enums.Rank;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

public class PlayerJoinListener implements Listener {
    public PlayerJoinListener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        // TODO move to RevoCore, as this is also needed on a server reload
        Player player = playerJoinEvent.getPlayer();

        RevoCore.getInstance().addPlayerToOnlineList(player);

        Rank playerRank = LuckyPermsIntegration.getRankFromPlayer(player);

        if (playerRank == Rank.BUG || playerRank == null) {
            String command = "lp user " + player.getName() + " parent set " + Rank.ERKUNDER.getRankName();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }

        playerJoinEvent.joinMessage(Component.text(ChatColor.GOLD + "> " + player.getName() + " hat die Welt betreten"));
    }
}
