package hypixel;

import hypixel.commands.opme;
import hypixel.commands.port;
import hypixel.commands.shutupall;
import hypixel.commands.testping;
import hypixel.events.chat_events;
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
    }

    private void registerEvents() {
        PluginManager localPluginManager = getServer().getPluginManager();
        localPluginManager.registerEvents(new chat_events(), (this));
    }
}
