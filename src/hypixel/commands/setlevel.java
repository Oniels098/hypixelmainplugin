package hypixel.commands;

import hypixel.mysql.MySQLRanks;
import hypixel.server.playerlevel;
import hypixel.server.ranks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 10-07-16.
 */
public class setlevel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        try {
            if (commandSender instanceof Player && !commandSender.isOp()) {
                commandSender.sendMessage("§cYou don't have permissions!");
                return false;
            }
            if(args.length < 2) {
                commandSender.sendMessage("§cCorrect usage:");
                commandSender.sendMessage("§c/setlevel <player> <level>");
                return true;
            }
            if (args.length == 2) {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                String level = args[1];
                playerlevel.setLevel(targetPlayer, level);
                commandSender.sendMessage("§aThe player's level has been set!");
                targetPlayer.setLevel(Integer.valueOf(level));
            }
        }catch (Exception e) {
            commandSender.sendMessage("§cSomething went wrong while executing this command!");
        }
        return true;
    }
}
