package hypixel;

import hypixel.commands.*;
import hypixel.events.*;
import hypixel.mysql.MySQL;
import hypixel.utils.actionbar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Niels on 02-07-16.
 */

public class Main extends JavaPlugin {

    public static Plugin pl;
    public static ArrayList<Player> online_players = new ArrayList<>();
    public static boolean chatenabled;

    @Override
    public void onEnable() {
        setup();
        chatenabled = false;
    }

    @Deprecated
    private void setup() {
        pl = this;
        actionbar.getNmsVersion();
        MySQL.connect();
        System.out.println("MySQL has been connected");
        registerCommands();
        System.out.println("Commands have been loaded.");
        registerEvents();
        System.out.println("Events have been registered.");
    }

    private void registerCommands() {
        getCommand("port").setExecutor(new port());
        getCommand("testping").setExecutor(new testping());
        getCommand("shutupall").setExecutor(new shutupall());
        getCommand("opme").setExecutor(new opme());
        getCommand("rank").setExecutor(new rank());
    }

    private void registerEvents() {
        PluginManager localPluginManager = getServer().getPluginManager();
        localPluginManager.registerEvents(new chat_events(), (this));
        localPluginManager.registerEvents(new join_events(), (this));
        localPluginManager.registerEvents(new quit_events()
                , (this));
        localPluginManager.registerEvents(new interact_events(), (this));
        localPluginManager.registerEvents(new damage_listener(), (this));
    }
}
