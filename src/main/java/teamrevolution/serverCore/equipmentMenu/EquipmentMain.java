package teamrevolution.serverCore.equipmentMenu;

import org.bukkit.entity.Player;
import teamrevolution.serverCore.gui.equipment.EquipmentGui;

public class EquipmentMain {
    public static void openGui(Player player) {
        player.openInventory(EquipmentGui.createEquipmentGui());
    }
}
