package hypixel.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 02-07-16.
 */

public class port implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou can only use this command in-game!");
            return true;
        }
        Player paramPlayer = (Player) sender;
        if(paramPlayer.hasPermission("mod")) {
            if(args.length < 1) {
                paramPlayer.sendMessage("§cCorrect usage: /port <username>");
                return true;
            }
            Player paramPlayer2 = Bukkit.getPlayer(args[0]);
            if(args.length > 1) {
                try {
                    paramPlayer.teleport(paramPlayer2.getLocation());
                    paramPlayer.sendMessage("§aYou have been teleported to §2" + paramPlayer2.getName());
                }catch (Exception e) {
                    paramPlayer.sendMessage("§cSomething went wrong while executing this command!");
                }
            } else if(!paramPlayer.hasPermission("mod")) {
                paramPlayer.sendMessage("§cYou are not allowed to do this!");
                return true;
            }
        }
        return true;
    }
}
