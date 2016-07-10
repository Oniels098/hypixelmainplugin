package hypixel.server;

import hypixel.mysql.MySQLData;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 10-07-16.
 */
public class playerlevel {

    public static String getLevel(Player paramPlayer) {
        return MySQLData.getLevel(paramPlayer);
    }

    public static void setLevel(Player paramPlayer, String level) {
        MySQLData.setLevel(paramPlayer, level);
    }
}
