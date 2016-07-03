package hypixel.utils;

import hypixel.mysql.MySQLRanks;
import hypixel.server.ranks;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 03-07-16.
 */
public class stringutils {

    public static String getColorname(Player paramPlayer) {
        return ranks.getChatColor(MySQLRanks.getRank(paramPlayer)).toString();
    }
}
