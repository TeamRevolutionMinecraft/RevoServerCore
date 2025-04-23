package teamrevolution.serverCore.gui;

import dev.dbassett.skullcreator.SkullCreator;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.character.RevoPlayer;

import java.util.ArrayList;
import java.util.List;

public class GuiUtils {

    /**
     * Creates an Item used mainly in the Char gui
     * @param label name of the Item
     * @param material the base Item
     * @param id CustomID to identify
     * @param glint boolean
     * @param player player who is executing
     * @return the created Item
     */
    public static ItemStack createItemWithPlayerInfo(String keyString, String label, Material material, String id, boolean glint, RevoPlayer player) {
        var key = RevoCore.getInstance().getNamespacedKey(keyString);
        Component name = Component.text(label);
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        ArrayList<Component> loreArray = new ArrayList<>();
        String loreString = updateLoreText(player, id);
        loreArray.add(Component.text((loreString == null) ? " " : loreString));

        meta.lore(loreArray);
        meta.displayName(name);
        if (glint) {
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Used to create a custom head as an Item
     * @param label name of the Item
     * @param url Minecraft texture url of the Head
     * @param id CustomID to identify
     * @param player player who is executing
     * @return the created Item
     */
    public static ItemStack createItemWithPlayerInfo(String keyString, String label, String url, String id, RevoPlayer player) {
        Component name = Component.text(label);
        ArrayList<Component> loreArray = new ArrayList<>();
        String loreString = updateLoreText(player, id);
        loreArray.add(Component.text((loreString == null) ? " " : loreString));

        var key = RevoCore.getInstance().getNamespacedKey(keyString);
        ItemStack item = SkullCreator.itemFromUrl("http://textures.minecraft.net/texture/" + url);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.displayName(name);
        meta.lore(loreArray);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        item.setItemMeta(meta);
        return item;

    }

    /**
     * Creates an Item Without Values attached to player Info
     * @param keyString keyName in Namespace ServerCore
     * @param label Name of the Item
     * @param material material of the Item
     * @param id CustomID to identify
     * @param glint item glows
     * @return created Item
     */
    public static ItemStack createItem(String keyString, String label, Material material, String id, boolean glint) {
        var key = RevoCore.getInstance().getNamespacedKey(keyString);
        Component name = Component.text(label);
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(name);

        if (glint) {
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;

    }

    /**
     * Creates a playerhead without info linked to the Player
     * @param keyString keyName in Namespace ServerCore
     * @param label Name of the Item
     * @param url Minecraft texture url of the Head
     * @param id CustomID to identify
     * @return customhead
     */
    public static ItemStack createItem(String keyString, String label, String url, String id) {
        Component name = Component.text(label);

        var key = RevoCore.getInstance().getNamespacedKey(keyString);
        ItemStack item = SkullCreator.itemFromUrl("http://textures.minecraft.net/texture/" + url);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.displayName(name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createPlayerHeadWithLore(String keyString, String label, Player player, String id, boolean glint, List<Component> loreText) {
        Component name = Component.text(label);
        var key = RevoCore.getInstance().getNamespacedKey(keyString);
        ItemStack itemStack = SkullCreator.itemFromUuid(player.getUniqueId());

        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

        if (glint) {
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.lore(loreText);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        meta.displayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack createItemWithLore(String keyString, String label, Material material, String id, boolean glint, List<Component> loreText) {
        ItemStack itemStack = createItem(keyString, label, material, id, glint);
        ItemMeta meta = itemStack.getItemMeta();

        meta.lore(loreText);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    /**
     * creates an Item with another namespace
     * @param plugin the other plugin
     * @param label the name of the Plugin
     * @param material the Material of the Item
     * @param id CustomID for separation
     * @return the created Item
     */
    public static ItemStack createItemFromOtherPlugin(Plugin plugin, String keyString, String label, Material material, String id) {
        Component name = Component.text(label);

        NamespacedKey key = new NamespacedKey(plugin, keyString);

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Creates an inventory with a filler material
     * @deprecated
     * @param height the height of the Inventory
     * @param nameOfInventory title of the Interface
     * @param material material of the filler item
     * @return chestGui
     */
    public static Inventory createChestGui(int height, String nameSpace, String nameOfInventory, Material material, boolean glint) {
        Inventory inv = Bukkit.createInventory(null, 9*height, Component.text(nameOfInventory));
        ItemStack placeholder = nonAirPlaceholder(material, nameSpace, glint);
        for (int i = 0; i < height * 9; i++) {
            inv.setItem(i, placeholder);
        }
        return inv;
    }
    public static Inventory createChestGui(int height, String nameSpace, String nameOfInventory, Material material, boolean glint, boolean check) {

        Inventory inv;
        if (check) {
            inv = Bukkit.createInventory(new DummyHolder(), 9 * height, Component.text(nameOfInventory));
        } else {
            inv = Bukkit.createInventory(null, 9 * height, Component.text(nameOfInventory));
        }
        ItemStack placeholder = nonAirPlaceholder(material, nameSpace, glint);
        for (int i = 0; i < height * 9; i++) {
            inv.setItem(i, placeholder);
        }
        return inv;
    }

    /**
     * Creates an Info item with an "infoUrl" attached
     * @param nameSpace
     * @param label
     * @param url
     * @param material
     * @return item
     */
    public static ItemStack createInfoItem(String nameSpace, String label, String url, Material material) {
        Component name = Component.text(label);

        var key = RevoCore.getInstance().getNamespacedKey(nameSpace);
        var urlKey = RevoCore.getInstance().getNamespacedKey("infoUrl");
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "url");
        meta.getPersistentDataContainer().set(urlKey, PersistentDataType.STRING, url);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Updates the Lore of the Player based on the values known
     * @param player player who is executing
     * @param id CustomId for seperation
     * @return loreString
     */
    private static String updateLoreText(RevoPlayer player, String id) {
        return switch (id) {
            case "raceSelect" -> player.getRoleplayData().getRace().toString();
            case "jobSelect" -> player.getRoleplayData().getJob().toString();
            case "nameSelect" -> player.getRoleplayData().getRolePlayName();
            default -> " ";
        };
    }

    /**
     * Creates a Placeholder Item that is just there to block a slot
     * @param material the Item material that is used
     * @return the modified Item
     */
    private static ItemStack nonAirPlaceholder(Material material, String nameSpace, boolean glint ) {
        var key = RevoCore.getInstance().getNamespacedKey(nameSpace);
        ItemStack itemStack = new ItemStack(material);

        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text(" "));
        if (glint) {
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "placeHolder");
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
