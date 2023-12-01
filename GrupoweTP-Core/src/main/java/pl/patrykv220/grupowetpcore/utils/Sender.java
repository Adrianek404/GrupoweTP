package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.*;
import org.bukkit.entity.*;
import java.lang.reflect.*;

public class Sender
{
    private static final String packageName;
    private static final String version;
    
    static {
        packageName = Bukkit.getServer().getClass().getPackage().getName();
        version = Sender.packageName.substring(Sender.packageName.lastIndexOf(".") + 1);
    }
    
    public static void sendPacket(final Player player, final Object... os) {
        sendPacket(new Player[] { player }, os);
    }
    
    public static void sendPacket(final Player[] players, final Object... os) {
        try {
            final Class<?> packetClass = Class.forName("net.minecraft.server." + Sender.version + ".Packet");
            final Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + Sender.version + ".entity.CraftPlayer");
            for (final Player p : players) {
                final Object cp = craftPlayer.cast(p);
                final Object handle = craftPlayer.getMethod("getHandle", (Class<?>[])new Class[0]).invoke(cp, new Object[0]);
                final Object con = handle.getClass().getField("playerConnection").get(handle);
                final Method method = con.getClass().getMethod("sendPacket", packetClass);
                for (final Object o : os) {
                    if (o != null) {
                        method.invoke(con, o);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
