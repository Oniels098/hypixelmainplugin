package hypixel.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 02-07-16.
 */

public class opme implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou can only use this command in-game!");
            return true;
        }
        Player paramPlayer = (Player) sender;
        paramPlayer.sendMessage("§cYou are no longer OP.");
        return true;
    }
}
