package teamrevolution.serverCore.gui.equipment;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import teamrevolution.serverCore.gui.GuiUtils;

public class EquipmentGui {
    private static final String keyValue = "equipmentGui";
    private static final ItemStack air = new ItemStack(Material.AIR);
    public static Inventory createEquipmentGui() {
        Inventory gui = GuiUtils.createChestGui(2, keyValue, "Equipment", Material.BLACK_STAINED_GLASS_PANE, false);

        gui.setItem(1, GuiUtils.createItem(keyValue, "Ringe", Material.DIAMOND, "space", true));
        gui.setItem(4, GuiUtils.createItem(keyValue, "Rüstung", Material.CHAINMAIL_CHESTPLATE, "space", true));
        gui.setItem(7, GuiUtils.createItem(keyValue, "Magie", Material.NETHER_STAR, "space", true));

        gui.setItem(1+9, air);
        gui.setItem(4+9, air);
        gui.setItem(7+9, air);


        return gui;

    }
}
