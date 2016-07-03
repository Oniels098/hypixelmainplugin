package hypixel.events;

import hypixel.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

/**
 * Created by Niels on 03-07-16.
 */

public class interact_events implements Listener {

    private static HashMap<Player, Integer> coolDown = new HashMap<>();

    @EventHandler
    public void onPunch(EntityDamageByEntityEvent event) {
        final Player puncher = (Player) event.getDamager();
        event.setCancelled(true);
        if(!(event.getEntity() instanceof Player)) {
            return;
        }
        Player target = (Player) event.getEntity();
        if(!puncher.hasPermission("punch") || !target.hasPermission("staff")) {
            return;
        }
        if(coolDown.containsKey(puncher)) {
            puncher.sendMessage("§cYour punch ability is in cooldown!");
            return;
        }
        coolDown.put(puncher, 30);
        target.setVelocity(new Vector(0, 5, 0));
        Bukkit.broadcastMessage(puncher.getDisplayName() + "§7has punched §9" + target.getName() + "§7into the sky!");
        new BukkitRunnable() {
            public void run() {
                Integer cooldown = coolDown.get(puncher);
                cooldown--;
                if (cooldown > 0) {
                    coolDown.put(puncher, cooldown);
                } else {
                    coolDown.remove(puncher);
                    cancel();
                }
            }
        }.runTaskTimer(Main.pl, 0L, 20L);
    }
}
