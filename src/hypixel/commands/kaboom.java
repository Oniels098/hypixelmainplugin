package hypixel.commands;

import hypixel.server.server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by Niels on 04-07-16.
 */

public class kaboom implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be in-game to use this command!");
            return true;
        }
        Player paramPlayer = (Player) sender;
        if(paramPlayer.hasPermission("admin")) {
            kaboom(paramPlayer);
            return true;
        } else if(!paramPlayer.hasPermission("admin")) {
            paramPlayer.sendMessage("§You're noy of the rank to use this command! You must be of the admin rank or higher!");
            return true;
        }
        return true;
    }

    private static void kaboom(Player sender) {
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = server.getPlayers()).length;
        for (int i = 0; i < j; i++) {
            Player cur = arrayOfPlayer[i];
            cur.setVelocity(new Vector(0, 5, 0));
            cur.getWorld().strikeLightningEffect(cur.getLocation());
            sender.sendMessage("§aLaunched " + cur.getName() + "!");
        }
    }
}
