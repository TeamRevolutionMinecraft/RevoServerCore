package teamrevolution.serverCore.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import teamrevolution.serverCore.RevoCore;

public class PlayerLeaveListener implements Listener {
    public PlayerLeaveListener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void PlayerLeaveEvent(PlayerQuitEvent playerQuitEvent) {
        Player player = playerQuitEvent.getPlayer();
        playerQuitEvent.quitMessage(Component.text(ChatColor.RED + player.getName() + " hat die Welt verlassen"));

        var plugin = RevoCore.getInstance();
        plugin.getCharacter(player.getUniqueId()).ifPresent(revoPlayer -> {
            revoPlayer.store();
            plugin.removePlayer(player.getUniqueId());
        });
    }

}
