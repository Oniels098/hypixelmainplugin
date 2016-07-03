package hypixel.server;

import hypixel.Main;
import hypixel.utils.teamutils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 03-07-16.
 */
public class ranks {

    public enum Rank {
        DEFAULT("§7Default", ChatColor.GRAY, ChatColor.GRAY),
        VIP("§aVIP", ChatColor.GREEN, ChatColor.GRAY),
        VIP_PLUS("§aVIP§6+", ChatColor.GREEN, ChatColor.GRAY),
        MVP("§bMVP", ChatColor.AQUA, ChatColor.GRAY),
        MVP_PLUS("§bMVP",ChatColor.AQUA, ChatColor.AQUA),
        YOUTUBER("§6YT", ChatColor.GOLD, ChatColor.GRAY),
        HELPER("§9HELPER", ChatColor.BLUE, ChatColor.GRAY),
        MODERATOR("§2MOD", ChatColor.DARK_GREEN, ChatColor.GRAY),
        ADMIN("§cADMIN", ChatColor.RED, ChatColor.GRAY),
        OWNER("§cOWNER", ChatColor.RED, ChatColor.GRAY);

        private String prefix;
        private ChatColor chatColor;

        Rank(String prefix, ChatColor chatColor, ChatColor rankcolor) {
            this.prefix = prefix;
            this.chatColor = chatColor;
        }
    }

    public static String getName(Rank rank) {
        return rank.name();
    }

    public static String getPrefix(Rank rank) {
        return rank.prefix;
    }

    public static ChatColor getChatColor(Rank rank) {
        return rank.chatColor;
    }

    public static void setRank(Player paramPlayer, Rank rank) {
        switch (rank) {
            case DEFAULT :
                paramPlayer.setPlayerListName(ChatColor.GRAY + paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now DEFAULT");
                paramPlayer.setDisplayName("§7" + paramPlayer.getName());
                break;
            case VIP :
                paramPlayer.setPlayerListName("§a[VIP] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§a[VIP] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now VIP");
                break;
            case VIP_PLUS :
                paramPlayer.setPlayerListName("§a[VIP§6+§a] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§a[VIP§6+§a] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now VIP_PLUS");
                break;
            case MVP :paramPlayer.setPlayerListName("§b[MVP] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§b[MVP] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now MVP");
                break;
            case MVP_PLUS :paramPlayer.setPlayerListName("§b[MVP§c+§b] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§b[MVP§c+§b] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now MVP_PLUS");
                break;
            case YOUTUBER : paramPlayer.setPlayerListName("§6[YT] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§6[YT] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now YOUTUBER");
                break;
            case ADMIN :
                paramPlayer.setPlayerListName("§c[ADMIN] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§c[ADMIN] " +  paramPlayer.getName());
                paramPlayer.setOp(true);
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now ADMIN");
                break;
            case HELPER :
                paramPlayer.setPlayerListName("§9[HELPER] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§9[HELPER] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now HELPER");
                break;
            case MODERATOR :
                paramPlayer.setPlayerListName("§2[MOD] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§2[MOD] " +  paramPlayer.getName());
                paramPlayer.addAttachment(Main.pl, "mod", true);
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now MODERATOR");
                break;
            case OWNER :
                paramPlayer.setOp(true);
                paramPlayer.setPlayerListName("§c[OWNER] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§c[OWNER] " +  paramPlayer.getName());
                paramPlayer.sendMessage(ChatColor.GREEN + "You are now OWNER");
                break;
        }
    }

    public static void updaterank(Player paramPlayer, Rank rank) {
        switch (rank) {
            case DEFAULT :
                paramPlayer.setPlayerListName(ChatColor.GRAY + paramPlayer.getName());
                paramPlayer.setDisplayName("§7" + paramPlayer.getName());
                teamutils.setNameTagColor(paramPlayer, "§7");
                break;
            case VIP :
                paramPlayer.setPlayerListName("§a[VIP] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§a[VIP] " +  paramPlayer.getName());
                teamutils.setNameTagColor(paramPlayer, "§a[VIP] ");
                break;
            case VIP_PLUS :
                paramPlayer.setPlayerListName("§a[VIP§6+§a] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§a[VIP§6+§a] " +  paramPlayer.getName());
                teamutils.setNameTagColor(paramPlayer, "§a[VIP§6+§a] ");
                break;
            case MVP :
                paramPlayer.setPlayerListName("§b[MVP] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§b[MVP] " +  paramPlayer.getName());
                teamutils.setNameTagColor(paramPlayer, "§b[MVP] ");
                break;
            case MVP_PLUS :
                paramPlayer.setPlayerListName("§b[MVP§c+§b] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§b[MVP§c+§b] " +  paramPlayer.getName());
                break;
            case YOUTUBER : paramPlayer.setPlayerListName("§6[YT] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§6[YT] " +  paramPlayer.getName());
                teamutils.setNameTagColor(paramPlayer, "§6[YT] ");
                break;
            case ADMIN :
                paramPlayer.setPlayerListName("§c[ADMIN] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§c[ADMIN] " +  paramPlayer.getName());
                paramPlayer.setOp(true);
                teamutils.setNameTagColor(paramPlayer, "§c[ADMIN] ");
                break;
            case HELPER :
                paramPlayer.setPlayerListName("§9[HELPER] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§9[HELPER] " +  paramPlayer.getName());
                teamutils.setNameTagColor(paramPlayer, "§9[HELPER] ");
                break;
            case MODERATOR :
                paramPlayer.setPlayerListName("§2[MOD] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§2[MOD] " +  paramPlayer.getName());
                paramPlayer.addAttachment(Main.pl, "mod", true);
                teamutils.setNameTagColor(paramPlayer, "§2[MOD] ");
                break;
            case OWNER :
                paramPlayer.setPlayerListName("§c[OWNER] " +  paramPlayer.getName());
                paramPlayer.setDisplayName("§c[OWNER] " +  paramPlayer.getName());
                paramPlayer.setOp(true);
                teamutils.setNameTagColor(paramPlayer, "§c[OWNER] ");
                break;
        }
    }
}
