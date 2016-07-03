package hypixel.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Niels on 03-07-16.
 */

public class actionbar {

    private static Class<?> craftPlayer;
    private static Class<?> packet;
    private static Class<?> packetPlayOutChat;
    private static Class<?> iChatBaseComponent;
    private static Class<?> chatComponentText;
    private static String nmsVersion;

    public static void getNmsVersion() {
        try {
            nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
            nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);
            craftPlayer = Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + nmsVersion + "." + className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendActionbar(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        packet = getNMSClass("Packet");
        packetPlayOutChat = getNMSClass("PacketPlayOutChat");
        iChatBaseComponent = getNMSClass("IChatBaseComponent");
        chatComponentText = getNMSClass("ChatComponentText");

        if (packet != null && packetPlayOutChat != null && iChatBaseComponent != null && chatComponentText != null) {
            try {
                Object nmsPlayer = craftPlayer.cast(player);

                Object instanceChatComponentText = chatComponentText.getConstructor(new Class<?>[]{String.class})
                        .newInstance(message);
                Object instancePacketPlayOutChat = packetPlayOutChat.getConstructor(new Class<?>[]{iChatBaseComponent, byte.class})
                        .newInstance(instanceChatComponentText, (byte) 2);
                Method craftPlayerGetHandle = craftPlayer.getDeclaredMethod("getHandle");
                Object handle = craftPlayerGetHandle.invoke(nmsPlayer);
                Field playerConnection = handle.getClass().getDeclaredField("playerConnection");
                Object instancePlayerConnection = playerConnection.get(handle);
                Method sendPacket = instancePlayerConnection.getClass().getDeclaredMethod("sendPacket", packet);
                sendPacket.invoke(instancePlayerConnection, instancePacketPlayOutChat);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Could not send action bar packet. NMS Class not found.");
        }
    }
}
