package teamrevolution.serverCore.economy;

import teamrevolution.serverCore.RevoCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EconomyIO {
    /**
     * Creates a new Database
     * Only use if the server is fresh
     * @param fileName name of Database file
     */
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + RevoCore.getInstance().getDataFolder() + "//" + fileName;
        String sqlBank = "CREATE TABLE IF NOT EXISTS Bank (\n"
                + " id integer PRIMARY KEY,\n"
                + " uuid name NOT NULL,\n"
                + " amount integer\n"
                + ");";

        //TODO was mit dem GEldbeutel überlegen
        String sqlPort = "CREATE TABLE IF NOT EXISTS Geldbeutel (\n"
                + " id integer PRIMARY KEY,\n"
                + " uuid name NOT NULL,\n"
                + " amount integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute(sqlBank);
                stmt.execute(sqlPort);

                conn.close();
                RevoCore.getInstance().getLogger().info("New Database created");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
