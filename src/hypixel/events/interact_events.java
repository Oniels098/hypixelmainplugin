package hypixel.events;

import hypixel.Main;
import hypixel.mysql.MySQLRanks;
import hypixel.particleeffect.ParticleEffect;
import hypixel.server.ranks;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
        target.getWorld().playSound(target.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
        ParticleEffect.EXPLOSION_LARGE.display(3.0F, 3.0F, 3.0F, 0.5F, 25, target.getLocation(), 30);
        Bukkit.broadcastMessage(puncher.getDisplayName() + " §7has punched " + ranks.getChatColor(MySQLRanks.getRank(target)) + target.getName() + " §7into the sky!");
        target.setVelocity(new Vector(0, 5, 0));
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
