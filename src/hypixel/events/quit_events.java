package hypixel.events;

import com.avaje.ebean.ExampleExpression;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Niels on 03-07-16.
 */

public class quit_events implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player paramPlayer = event.getPlayer();
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam(paramPlayer.getName());
        try {

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong while removing a team");
        }
    }
}
