package hypixel.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Niels on 03-07-16.
 */

public class teamutils {

    public static void setNameTagColor(Player p, String prefix) {
        prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        if (prefix.length() > 16) {
            prefix = prefix.substring(0, 16);
        }
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = board.getTeam(p.getName());
        if (t == null) {
            t = board.registerNewTeam(p.getName());
            t.setPrefix(prefix);
            t.addPlayer(p);
        } else {
            t = board.getTeam(p.getName());
            t.setPrefix(prefix);
            t.addPlayer(p);
        }
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.setScoreboard(board);
        }
    }
}
