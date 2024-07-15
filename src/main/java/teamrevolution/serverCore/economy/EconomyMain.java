package teamrevolution.serverCore.economy;

import org.bukkit.Bukkit;
import teamrevolution.serverCore.RevoCore;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EconomyMain {
    public static Connection databaseConnection;

    public static void initEconomy() {
        String databaseName = "economy.db";
        File databaseFile = new File(RevoCore.getInstance().getDataFolder() + "//" + databaseName);
        if (!databaseFile.exists()) {
            EconomyIO.createNewDatabase(databaseName);
        }
        setDatabaseConnection(databaseName);
    }

    public static void initEconomyCrafting() {
        Bukkit.addRecipe(EconomyCrafting.recipePurse());
        Bukkit.addRecipe(EconomyCrafting.bronzeToSilver());
        Bukkit.addRecipe(EconomyCrafting.silverToBronze());
        Bukkit.addRecipe(EconomyCrafting.silverToGold());
        Bukkit.addRecipe(EconomyCrafting.goldToSilver());
    }

    /**
     * Closes connection to database and disables the ecomony
     *
     */
    public static void disableEconomy() {
        try {
            getConnection().close();
        } catch (Exception e) {
            RevoCore.getInstance().getLogger().warning("Database not cleanly closed: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return databaseConnection;
    }
    public static void setDatabaseConnection(String filename) {
        String url = "jdbc:sqlite:" + RevoCore.getInstance().getDataFolder() + "//" + filename;
        try {
            databaseConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            RevoCore.getInstance().getLogger().warning("SQL CONNECTION ERROR");
            RevoCore.getInstance().getLogger().warning(e.toString());
        }
    }
}
