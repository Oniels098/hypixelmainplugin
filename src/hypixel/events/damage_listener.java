package hypixel.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Niels on 03-07-16.
 */
public class damage_listener implements Listener {

    @EventHandler
    public void onDameg(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
}
