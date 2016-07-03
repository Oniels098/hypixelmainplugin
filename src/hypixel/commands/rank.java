package hypixel.commands;

import hypixel.mysql.MySQLRanks;
import hypixel.server.ranks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 03-07-16.
 */
public class rank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        try {
            if (commandSender instanceof Player && !commandSender.isOp()) {
                commandSender.sendMessage("§cYou don't have permissions!");
                return false;
            }
            if(args.length < 2) {
                commandSender.sendMessage("§cCorrect usage:");
                commandSender.sendMessage("§c/rank <player> <rank>");
                return true;
            }
            if (args.length == 2) {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                commandSender.sendMessage("§aThe player's rank has been set!");
                ranks.Rank rank = ranks.Rank.valueOf(args[1].toUpperCase());
                ranks.setRank(targetPlayer, rank);
                MySQLRanks.setRank(targetPlayer, rank);
            }
        }catch (Exception e) {
            commandSender.sendMessage("§cSomething went wrong while executing this command!");
        }
        return true;
    }
}
