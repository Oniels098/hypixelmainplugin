package hypixel.mysql;

import hypixel.server.ranks;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

/**
 * Created by Niels on 03-07-16.
 */

public class MySQLRanks {
    private static String table = "ranks";

    public static void checkTable()
    {
        try
        {
            MySQL.update("CREATE TABLE IF NOT EXISTS " + table + " (UUID VARCHAR(64), RANK VARCHAR);");
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }

    public static boolean exists(Player paramPlayer)
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
    public static void create(Player offlinePlayer) {
        try {
            if (!exists(offlinePlayer)) {
                MySQL.update("CREATE TABLE IF NOT EXISTS " + table + " (UUID VARCHAR(64), RANK VARCHAR);");
                MySQL.update("INSERT INTO " + table + "(UUID, RANK) VALUES ('" + offlinePlayer.getUniqueId() + "', '" + ranks.getName(ranks.Rank.DEFAULT) + "');");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static ranks.Rank getRank(Player paramPlayer) {
        try {
            if (exists(paramPlayer)) {
                ResultSet resultSet = MySQL.query("SELECT * FROM " + table + " WHERE UUID='" + paramPlayer.getUniqueId() + "'");
                if (resultSet.next()) {
                    return ranks.Rank.valueOf(resultSet.getString("RANK"));
                }
            } else {
                create(paramPlayer);
            }
        } catch (Exception exception) {
            return ranks.Rank.DEFAULT;
        }
        return ranks.Rank.DEFAULT;
    }

    public static void setRank(Player paramPlayer, ranks.Rank rank) {
        try {
            if (exists(paramPlayer)) {
                MySQL.update("UPDATE " +table + " SET RANK='" + ranks.getName(rank) + "' WHERE UUID='" + paramPlayer.getUniqueId() + "'");
            } else {
                create(paramPlayer);
                setRank(paramPlayer, rank);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
