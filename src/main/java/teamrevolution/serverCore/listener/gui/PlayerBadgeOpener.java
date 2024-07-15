package teamrevolution.serverCore.listener.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamrevolution.serverCore.enums.RevoItems;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.character.Character;

public class PlayerBadgeOpener implements Listener {
    private ItemStack badge = RevoItems.BADGE.getItemStack();
    public PlayerBadgeOpener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void playerClick(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player))
            return;
        Player clickingPlayer = event.getPlayer();
        if (!itemIsBadge(clickingPlayer)) return;

        Character characterClicking = RevoCore.getInstance().getRevoPlayer(clickingPlayer.getUniqueId());

        ((Player) event.getRightClicked()).openInventory(characterClicking.getBadge());
    }

    private boolean itemIsBadge(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack == null || !itemStack.hasItemMeta()) return false;
        ItemMeta meta = itemStack.getItemMeta();

        if (!meta.hasCustomModelData()) return false;
        return meta.getCustomModelData() == 1000004;
    }
}
