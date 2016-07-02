package hypixel.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 02-07-16.
 */
public class testping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used in-game!");
            return true;
        }
        Player paramPlayer = (Player) sender;
        Player paramPlayer2 = Bukkit.getPlayer(args[0]);
        if(paramPlayer.hasPermission("mod")) {
            if(args.length < 1) {
                paramPlayer.sendMessage("§cCorrect usage: /testping <player>");
                return true;
            }
            if(args.length > 1) {
                try {
                    int ping = getPing(paramPlayer2);
                    paramPlayer.sendMessage("§aPing from §6" + paramPlayer2.getName() + " §a: §6" + ping);
                    ping = 0;
                }catch (Exception e) {
                    sender.sendMessage("§cSomething went wrong while executing this command!");
                }
            } else if(!paramPlayer.hasPermission("mod")) {

            }
        }
        return true;
    }

    private int getPing(Player paramPlayer) {
        CraftPlayer player = (CraftPlayer) paramPlayer;
        return player.getHandle().ping;
    }
}
