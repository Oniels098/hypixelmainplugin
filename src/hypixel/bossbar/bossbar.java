package hypixel.bossbar;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by Niels on 04-07-16.
 */
public class bossbar {

    private static Map<String, FakeDragon> dragonMap = new HashMap<String, FakeDragon>();

    /*
     * set the status of the fake dragon of a player
     *
     * @player: the player you want to set the fake dragon of
     * @text: the text displayed above the bar
     * @percent: the percentage of the bar that is full
     * @reset: reset the fake dragon of the player or not
     */
    public static void setStatus(Player player, String text, int percent, boolean reset) throws SecurityException,
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            NoSuchFieldException {
        FakeDragon fd = null;

        if(dragonMap.containsKey(player.getName()) &! reset) {
            fd = dragonMap.get(player.getName());
        }
        // create a new dragon if needed or if reset equals true
        else {
            // spawn it 400 blocks above the player so you don't see it die at 0 health
            fd = new FakeDragon(text, player.getLocation().add(0, 20, 0), percent);
            Object mobPacket = fd.getSpawnPacket();
            Reflection.sendPacket(player, mobPacket);
            dragonMap.put(player.getName(), fd);
        }

        // set the status of the dragon and send the package to the player
        fd.setName(text);
        fd.setHealth(percent);
        Object metaPacket = fd.getMetaPacket(fd.getWatcher());
        Object teleportPacket = fd.getTeleportPacket(player.getLocation().add(0 , 20 , 0));
        Reflection.sendPacket(player, metaPacket);
        Reflection.sendPacket(player, teleportPacket);
    }

    /*
     * kill the dragon of a player
     *
     * @player: the player you want to kill the fake dragon of
     */
    public static void killDragon(Player player) {
        try {
            Reflection.sendPacket(player, dragonMap.get(player.getName()).getDestroyPacket());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        dragonMap.remove(player.getName());
    }

    /*
     * check if the fake dragon of a player is killed or not
     *
     * @player: the player you want to check the fake dragon of
     * @returns: true if the fake dragon of the player exists, else false
     */
    public static boolean isKilled(Player player) {
        if(dragonMap.containsKey(player.getName())) {
            return true;
        }
        return false;
    }

    // you don't need to use what's in here
    private static class FakeDragon {

        private static final int MAX_HEALTH = 200;
        private int id;
        private int x;
        private int y;
        private int z;
        private float health;
        private String name;
        private Object world;
        private Object dragon;

        public FakeDragon(String name, Location loc, int percent) {
            this.name = name;
            this.x = loc.getBlockX();
            this.y = loc.getBlockY();
            this.z = loc.getBlockZ();
            this.health = percent / 100F * MAX_HEALTH;
            this.world = Reflection.getHandle(loc.getWorld());
        }

        public void setHealth(int percent) {
            this.health = percent / 100F * MAX_HEALTH;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSpawnPacket() throws SecurityException, InstantiationException, IllegalAccessException,
                InvocationTargetException, NoSuchMethodException {
            // make it so we don't need to update this plugin every time a new bukkit version is used
            Class<?> Entity = Reflection.getNMSClass("Entity");
            Class<?> EntityLiving = Reflection.getNMSClass("EntityLiving");
            Class<?> EntityEnderDragon = Reflection.getNMSClass("EntityZombie");
            dragon = EntityEnderDragon.getConstructor(Reflection.getNMSClass("World")).newInstance(world);

            // in Entity
            Method setLocation = Reflection.getMethod(EntityEnderDragon, "setPosition", new Class<?>[]{double.class, double.class, double.class});
            setLocation.invoke(dragon, x, y, z);

            Method setInvisible = Reflection.getMethod(EntityEnderDragon, "setInvisible", new Class<?>[]{boolean.class});
            setInvisible.invoke(dragon, false);

            // in EntityInsentient
            Method setCustomName = Reflection.getMethod(EntityEnderDragon, "setCustomName", new Class<?>[]{String.class});
            setCustomName.invoke(dragon, name);

            // in LivingEntity
            Method setHealth = Reflection.getMethod(EntityEnderDragon, "setHealth", new Class<?>[]{float.class});
            setHealth.invoke(dragon, health);

            // in Entity
            Field motX = Reflection.getField(Entity, "motX");
            motX.set(dragon, 0); // x velocity, double

            // in Entity
            Field motY = Reflection.getField(Entity, "motY");
            motY.set(dragon, 0); // y velocity, double

            // in Entity
            Field motZ = Reflection.getField(Entity, "motZ");
            motZ.set(dragon, 0); // z velocity, double

            // in Entity
            Method getId = Reflection.getMethod(EntityEnderDragon, "getId", new Class<?>[]{});
            this.id = (Integer) getId.invoke(dragon);

            // get the spawn living entity packet class
            Class<?> PacketPlayOutSpawnEntityLiving = Reflection.getNMSClass("PacketPlayOutSpawnEntityLiving");
            // create an instance of this class using dragon entity
            Object packet = PacketPlayOutSpawnEntityLiving.getConstructor(new Class<?>[]{EntityLiving}).newInstance(dragon);
            return packet;
        }

        public Object getDestroyPacket() throws SecurityException, InstantiationException, IllegalAccessException,
                InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
            // get the destroy entity packet class
            Class<?> PacketPlayOutEntityDestroy = Reflection.getNMSClass("PacketPlayOutEntityDestroy");
            // create a new instance of the class
            Object packet = PacketPlayOutEntityDestroy.getConstructor(new Class<?>[]{int[].class}).newInstance(new int[]{id});
            return packet;
        }

        public Object getMetaPacket(Object watcher) throws SecurityException, InstantiationException,
                IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Class<?> DataWatcher = Reflection.getNMSClass("DataWatcher");
            Class<?> PacketPlayOutEntityMetadata = Reflection.getNMSClass("PacketPlayOutEntityMetadata");

            Object packet = PacketPlayOutEntityMetadata.getConstructor(new Class<?>[]{int.class, DataWatcher, boolean.class})
                    .newInstance(id, watcher, true);
            return packet;
        }

        public Object getTeleportPacket(Location loc) throws SecurityException, InstantiationException,
                IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            Class<?> PacketPlayOutEntityTeleport = Reflection.getNMSClass("PacketPlayOutEntityTeleport");
            Object packet = PacketPlayOutEntityTeleport.getConstructor(new Class<?>[]{
                    int.class, int.class, int.class, int.class, byte.class, byte.class})
                    .newInstance(this.id, loc.getBlockX() * 32, loc.getBlockY() * 32, loc.getBlockZ() * 32,
                            (byte) ((int) loc.getYaw() * 256 / 360), (byte) ((int) loc.getPitch() * 256 / 360));
            return packet;
        }

        public Object getWatcher() throws SecurityException, InstantiationException, IllegalAccessException,
                InvocationTargetException, NoSuchMethodException {
            Class<?> Entity = Reflection.getNMSClass("Entity");
            Class<?> DataWatcher = Reflection.getNMSClass("DataWatcher");

            Object watcher = DataWatcher.getConstructor(new Class<?>[]{Entity}).newInstance(dragon);
            Method a = Reflection.getMethod(DataWatcher, "a", new Class<?>[]{int.class, Object.class});

            a.invoke(watcher, 0, (byte) 0x20); // visible, 0 = true, 0x20 = false
            a.invoke(watcher, 6, (Float) health); // health
            a.invoke(watcher, 10, name); // name
            a.invoke(watcher, 11, (Byte) (byte) 1); // show name, 1 = true, 0 = false
            return watcher;
        }
    }
}
