package hypixel.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niels on 04-07-16.
 */

public class itemstackutils {

    public static void setItemStackName(ItemStack itemStack, String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(itemMeta);
    }

    public static void setItemStackLore(ItemStack itemStack, List<String> lore) {
        List<String> lore2 = new ArrayList<String>();
        for (String line : lore) {
            lore2.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore2);
        itemStack.setItemMeta(itemMeta);
    }

    public static ItemStack getItemStack(Material material, int amount, String name, List<String> lore, byte data) {
        ItemStack itemStack = new ItemStack(material, amount, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(name);
        }
        if (lore != null) {
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getItemStack(Material material, int amount, String name, List<String> lore) {
        return getItemStack(material, amount, name, lore, (byte) 0);
    }
}
