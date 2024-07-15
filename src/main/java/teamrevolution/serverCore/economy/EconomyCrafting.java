package teamrevolution.serverCore.economy;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import teamrevolution.serverCore.enums.Coins;
import teamrevolution.serverCore.RevoCore;

public class EconomyCrafting {
    private static final int goldToSilverValue = 5;
    private static final int bronzeToSilverValue = 5;
    public static ShapedRecipe recipePurse() {
        NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "purse");

        ShapedRecipe recipe = new ShapedRecipe(key, Coins.PURSE.getItemStackWithoutEnchant());
        recipe.shape("AIA", "LLL", "ALA");
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('L', Material.LEATHER);

        return recipe;
    }
    /**
     * Crafting to convert bronze to silver Coins
     */
    public static ShapelessRecipe bronzeToSilver() {
        NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "b2s");

        ShapelessRecipe sr = new ShapelessRecipe(key, Coins.SILVER.getItemStackWithoutEnchant());
        sr.addIngredient(bronzeToSilverValue, Coins.BRONZE.getItemStackWithoutEnchant());
        return sr;
    }
    /**
     * Crafting to convert silver to bronze Coins
     */
    public static ShapelessRecipe silverToBronze() {
        NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "s2b");

        ShapelessRecipe sr = new ShapelessRecipe(key, Coins.BRONZE.getItemStackWithoutEnchant(bronzeToSilverValue));
        sr.addIngredient(1, Coins.SILVER.getItemStackWithoutEnchant());
        return sr;
    }
    /**
     * Crafting to convert silver to gold Coins
     */
    public static ShapelessRecipe silverToGold() {
        NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "s2g");

        ShapelessRecipe sr = new ShapelessRecipe(key, Coins.GOLD.getItemStackWithoutEnchant());
        sr.addIngredient(goldToSilverValue, Coins.SILVER.getItemStackWithoutEnchant());
        return sr;
    }

    /**
     * Crafting to convert gold to silver Coins
     */
    public static ShapelessRecipe goldToSilver() {
        NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "g2s");
        ShapelessRecipe sr = new ShapelessRecipe(key, Coins.SILVER.getItemStackWithoutEnchant(goldToSilverValue));
        sr.addIngredient(1, Coins.GOLD.getItemStackWithoutEnchant());
        return sr;
    }
}
