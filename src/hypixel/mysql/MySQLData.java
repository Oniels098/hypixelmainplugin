package hypixel.mysql;

import hypixel.server.ranks;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

/**
 * Created by Niels on 09-07-16.
 */

public class MySQLData {

    private static String table = "playerdata";

    public static void checkTable()
    {
        try
        {
            MySQL.update("CREATE TABLE IF NOT EXISTS " + table + " (UUID VARCHAR(64), NAME VARCHAR(64), LEVEL VARCHAR(64));");
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }

    public static boolean exists(OfflinePlayer paramPlayer)
    {
        try
        {
            ResultSet localResultSet = MySQL.query("SELECT * FROM " + table + " WHERE UUID='" + paramPlayer.getUniqueId() + "';");
            if (localResultSet.next()) {
                return localResultSet.getString("UUID") != null;
            }
        }
        catch (Exception localException)
        {
            return false;
        }
        return false;
    }

    public static void create(OfflinePlayer offlinePlayer) {
        try {
            if (!exists(offlinePlayer)) {
                MySQL.update("CREATE TABLE IF NOT EXISTS " + table + " (UUID VARCHAR(64), NAME VARCHAR(64), LEVEL VARCHAR(64));");
                MySQL.update("INSERT INTO " + table + "(UUID, NAME, LEVEL) VALUES ('" + offlinePlayer.getUniqueId() + "', '" + offlinePlayer.getName() + "', '1');");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getLevel(Player paramPlayer) {
        try {
            if (exists(paramPlayer)) {
                ResultSet resultSet = MySQL.query("SELECT * FROM " + table + " WHERE UUID='" + paramPlayer.getUniqueId() + "'");
                if (resultSet.next()) {
                    return resultSet.getString("LEVEL");
                }
            } else {
                create(paramPlayer);
            }
        } catch (Exception exception) {
            return "1";
        }
        return "1";
    }

    public static void setLevel(Player paramPlayer, String level) {
        try {
            if (exists(paramPlayer)) {
                MySQL.update("UPDATE " +table + " SET LEVEL='" + level + "' WHERE UUID='" + paramPlayer.getUniqueId() + "'");
            } else {
                create(paramPlayer);
                setLevel(paramPlayer, level);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
