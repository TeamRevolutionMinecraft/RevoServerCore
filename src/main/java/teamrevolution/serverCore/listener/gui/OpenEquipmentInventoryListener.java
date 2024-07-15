package teamrevolution.serverCore.listener.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import teamrevolution.serverCore.equipmentMenu.EquipmentMain;
import teamrevolution.serverCore.RevoCore;

public class OpenEquipmentInventoryListener implements Listener {
    public OpenEquipmentInventoryListener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void pressHotkey(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        EquipmentMain.openGui(event.getPlayer());
    }
}
