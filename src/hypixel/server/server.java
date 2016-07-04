package hypixel.server;

import hypixel.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 04-07-16.
 */

public class server {

    public static Player[] getPlayers()
    {
        return (Player[]) getServer().getOnlinePlayers().toArray(new Player[0]);
    }

    public static Server getServer()
    {
        return Bukkit.getServer();
    }
}
