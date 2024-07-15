package teamrevolution.serverCore.enums;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import teamrevolution.serverCore.RevoCore;

@Deprecated
public enum Coins {
    //TODO Move this into Eco Plugin
    BRONZE("Bronzemünze", "bronzeCoin", Material.GOLD_NUGGET, 1000000),
    SILVER("Silbermünze", "silverCoin", Material.GOLD_NUGGET,  1000001),
    GOLD("Goldmünze", "goldCoin", Material.GOLD_NUGGET,1000002),
    PURSE("Geldbeutel", "purse", Material.PHANTOM_MEMBRANE, 1000003);

    private final ItemStack itemStack;
    Coins(String name, String keyValue, Material material, int customID) {
        NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "CustomItem");

        ItemStack coin = new ItemStack(material);
        ItemMeta coinMeta = coin.getItemMeta();
        coinMeta.displayName(Component.text(name));
        coinMeta.addEnchant(Enchantment.MENDING, 1, false);
        coinMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        coinMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, keyValue);
        coinMeta.setCustomModelData(customID);
        coin.setItemMeta(coinMeta);

        itemStack = coin;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }
    public ItemStack getItemStack(int amount) {
        ItemStack newItemStack = itemStack.clone();
        newItemStack.setAmount(amount);
        return newItemStack;
    }
    public ItemStack getItemStackWithoutEnchant() {
        ItemStack newItemStack = itemStack.clone();
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.removeEnchant(Enchantment.MENDING);
        newItemStack.setItemMeta(itemMeta);
        return newItemStack;
    }
    public ItemStack getItemStackWithoutEnchant(int amount) {
        ItemStack newItemStack = getItemStackWithoutEnchant().clone();
        newItemStack.setAmount(amount);
        return newItemStack;
    }
}
