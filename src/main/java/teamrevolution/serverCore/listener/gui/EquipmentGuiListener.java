package teamrevolution.serverCore.listener.gui;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import teamrevolution.serverCore.RevoCore;

public class EquipmentGuiListener implements Listener {
    public EquipmentGuiListener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void charGuiOnClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null && clickedItem.hasItemMeta()) {
            PersistentDataContainer container = clickedItem.getItemMeta().getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "equipmentGui");

            if (container.has(key, PersistentDataType.STRING)) {
                event.setCancelled(true);
            }

        }
    }
}
