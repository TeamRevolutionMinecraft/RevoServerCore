package teamrevolution.serverCore.listener.gui;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import teamrevolution.serverCore.RevoCore;

public class GenericGuiListener implements Listener {
    private final RevoCore plugin;

    public GenericGuiListener(RevoCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void guiClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem != null && clickedItem.hasItemMeta()) {
            PersistentDataContainer container = clickedItem.getItemMeta().getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(plugin, "util");
            if (container.has(key, PersistentDataType.STRING)) {
                event.setCancelled(true);
            }
        }
    }
}
