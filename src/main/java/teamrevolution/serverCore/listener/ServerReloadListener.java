package teamrevolution.serverCore.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.enums.Rank;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

public class ServerReloadListener implements Listener {

    public ServerReloadListener(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onReload(ServerLoadEvent event) {
        // TODO see PlayerJoinListener
        Bukkit.getOnlinePlayers().forEach(player -> {
            RevoCore.getInstance().addPlayerToOnlineList(player);

            Rank playerRank = LuckyPermsIntegration.getRankFromPlayer(player);

            if (playerRank == Rank.BUG || playerRank == null) {
                String command = "lp user " + player.getName() + " parent set " + Rank.ERKUNDER.getRankName();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        });
    }

}
