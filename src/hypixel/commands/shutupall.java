package hypixel.commands;

import hypixel.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 03-07-16.
 */

public class shutupall implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String strig, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used in-game!");
            return true;
        }
        Player paramPlayer = (Player) sender;
        if(paramPlayer.hasPermission("mod")) {
            if(!Main.chatenabled) {
                Main.chatenabled = true;
                paramPlayer.sendMessage("§aChat has been disabled.");
                return true;
            } else if(Main.chatenabled) {
                Main.chatenabled = false;
                paramPlayer.sendMessage("§aChat has been enabled.");
            }
        } else if(!paramPlayer.hasPermission("mod")) {
            paramPlayer.sendMessage("§cYou are not allowed to do this!");
        }
        return true;
    }
}
