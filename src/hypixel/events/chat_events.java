package hypixel.events;

import hypixel.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Niels on 02-07-16.
 */

public class chat_events implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player paramPlayer = event.getPlayer();
        String[] words = event.getMessage().split(" ");

        event.setFormat(paramPlayer.getDisplayName() + "§f: " + event.getMessage());
        if(!Main.chatenabled) {
            if(!paramPlayer.hasPermission("staff")) {
                event.setCancelled(true);
                paramPlayer.sendMessage("§cChat is disabled!");
            }
        }
    }

    @EventHandler
    public void onChats(PlayerCommandPreprocessEvent event) {
        Player paramPlayer = event.getPlayer();
        if (event.getMessage().startsWith("tpss")) {
            event.setCancelled(true);
            String[] args = event.getMessage().split(" ");
            if (args.length == 0) {
                paramPlayer.sendMessage("wrong");
                return;
            }
            if (args.length == 1) {
                Player pl2 = Bukkit.getPlayer(args[0]);
                paramPlayer.sendMessage("DONE");
                paramPlayer.teleport(pl2.getLocation());
            }
        }
    }
}
