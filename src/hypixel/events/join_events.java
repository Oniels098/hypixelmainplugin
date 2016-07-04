package hypixel.events;

import hypixel.bossbar.bossbar;
import hypixel.mysql.MySQLRanks;
import hypixel.server.ranks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Niels on 03-07-16.
 */

public class join_events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player paramPlayer = event.getPlayer();
        ranks.updaterank(paramPlayer, MySQLRanks.getRank(paramPlayer));
    }
}
