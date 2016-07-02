package hypixel.commands;

import hypixel.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 03-07-16.
 */

public class shutup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String strig, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used in-game!");
            return true;
        }
        Player paramPlayer = (Player) sender;
        if(paramPlayer.hasPermission("admin")) {
            if(!Main.chatenabled) {
                Main.chatenabled = true;
                paramPlayer.sendMessage("§aChat has been disabled.");
                return true;
            } else if(Main.chatenabled) {
                Main.chatenabled = false;
                paramPlayer.sendMessage("§aChat has been enabled.");
            }
        }
        return true;
    }
}
