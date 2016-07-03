package hypixel.events;

import hypixel.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Niels on 02-07-16.
 */

public class chat_events implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player paramPlayer = event.getPlayer();
        event.setFormat(paramPlayer.getDisplayName() + "§f: " + event.getMessage());
        if(!Main.chatenabled) {
            if(!paramPlayer.hasPermission("staff")) {
                event.setCancelled(true);
                paramPlayer.sendMessage("§cChat is disabled!");
            }
        }
    }
}
