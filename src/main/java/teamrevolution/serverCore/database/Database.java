package teamrevolution.serverCore.database;

import teamrevolution.serverCore.RevoCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final Connection dataBaseConnection;
    public Database(RevoCore plugin, String databaseName) {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        try {
            this.dataBaseConnection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/" + databaseName + ".db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
