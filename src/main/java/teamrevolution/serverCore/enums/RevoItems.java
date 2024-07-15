package teamrevolution.serverCore.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import teamrevolution.serverCore.gui.GuiUtils;

public enum RevoItems {
    BADGE(ChatColor.AQUA + "Ausweis", "badge", Material.CREEPER_BANNER_PATTERN, 1000004),
    BRONZE("Bronzemünze", "bronzeCoin", Material.GOLD_NUGGET, 1000000),
    SILVER("Silbermünze", "silverCoin", Material.GOLD_NUGGET, 1000001),
    GOLD("Goldmünze", "goldCoin", Material.GOLD_NUGGET, 1000002),
    PURSE("Geldbeutel", "purse", Material.PHANTOM_MEMBRANE, 1000003);;

    private final ItemStack itemStack;

    RevoItems(String name, String keyValue, Material material, int customID) {
        itemStack = GuiUtils.createItem("revo_items", name, material, keyValue, false);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(customID);
        itemStack.setItemMeta(itemMeta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
