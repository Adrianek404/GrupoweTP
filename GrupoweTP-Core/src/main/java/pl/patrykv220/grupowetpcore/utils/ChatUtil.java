package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import org.bukkit.event.*;
import java.lang.reflect.*;
import org.bukkit.scheduler.*;


import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.io.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import java.util.regex.*;
import net.minecraft.server.v1_8_R3.*;
import pl.yspar.core.CorePlugin;

public final class ChatUtil
{
    public static String iiIi;
    public static String nmsver;
    
    static {
        ChatUtil.nmsver = Bukkit.getServer().getClass().getPackage().getName();
        ChatUtil.nmsver = ChatUtil.nmsver.substring(ChatUtil.nmsver.lastIndexOf(".") + 1);
    }
    
    public static Location locFromString(final String str) {
        final String[] str2loc = str.split(":");
        final Location loc = new Location((World)Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0, 0.0f, 0.0f);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        loc.setYaw(Float.parseFloat(str2loc[3]));
        loc.setPitch(Float.parseFloat(str2loc[4]));
        return loc;
    }
    
    public static void sendActionBar(final Player player, final String message) {
        final ActionBarUtil actionBarMessageEvent = new ActionBarUtil(player, message);
        Bukkit.getPluginManager().callEvent((Event)actionBarMessageEvent);
        if (actionBarMessageEvent.isCancelled()) {
            return;
        }
        try {
            final Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + ChatUtil.nmsver + ".entity.CraftPlayer");
            final Object p = c1.cast(player);
            final Class<?> c2 = Class.forName("net.minecraft.server." + ChatUtil.nmsver + ".PacketPlayOutChat");
            final Class<?> c3 = Class.forName("net.minecraft.server." + ChatUtil.nmsver + ".Packet");
            final Class<?> c4 = Class.forName("net.minecraft.server." + ChatUtil.nmsver + ".ChatComponentText");
            final Class<?> c5 = Class.forName("net.minecraft.server." + ChatUtil.nmsver + ".IChatBaseComponent");
            final Object o = c4.getConstructor(String.class).newInstance(message);
            final Object ppoc = c2.getConstructor(c5, Byte.TYPE).newInstance(o, (byte)2);
            final Method m1 = c1.getDeclaredMethod("getHandle", (Class<?>[])new Class[0]);
            final Object h = m1.invoke(p, new Object[0]);
            final Field f1 = h.getClass().getDeclaredField("playerConnection");
            final Object pc = f1.get(h);
            final Method m2 = pc.getClass().getDeclaredMethod("sendPacket", c3);
            m2.invoke(pc, ppoc);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);
        if (duration >= 0) {
            new BukkitRunnable() {
                public void run() {
                    ChatUtil.sendActionBar(player, "");
                }
            }.runTaskLater((Plugin)CorePlugin.getPlugin(), (long)(duration + 1));
        }
        while (duration > 60) {
            duration -= 60;
            final int sched = duration % 60;
            new BukkitRunnable() {
                public void run() {
                    ChatUtil.sendActionBar(player, message);
                }
            }.runTaskLater((Plugin)CorePlugin.getPlugin(), (long)sched);
        }
    }
    
    public static ItemStack getItemStackFromString(final String itemstack) {
        final String[] splits = itemstack.split("@");
        final String type = splits[0];
        final String data = (splits.length == 2) ? splits[1] : null;
        if (data == null) {
            return new ItemStack(Material.getMaterial(type), 1);
        }
        return new ItemStack(Material.getMaterial(type), 1, (short)Integer.parseInt(data));
    }
    
    public static String locToString(final double x, final double y, final double z) {
        return String.valueOf(x) + ":" + y + ":" + z + ":" + 0.0f + ":" + 0.0f;
    }
    
    public static String locToString(final Location loc) {
        return String.valueOf(loc.getX()) + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
    }
    
    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static Collection<String> fixColor(final Collection<String> collection) {
        final Collection<String> local = new ArrayList<String>();
        for (final String s : collection) {
            local.add(fixColor(s));
        }
        return local;
    }
    
    public static int floor(final double value) {
        final int i = (int)value;
        return (value < i) ? (i - 1) : i;
    }
    
    public static double round(final double value, final int decimals) {
        final double p = Math.pow(10.0, decimals);
        return Math.round(value * p) / p;
    }
    
    public static String[] fixColor(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = fixColor(array[i]);
        }
        return array;
    }
    
    public static boolean sendMessage(final CommandSender sender, final String message, final String permission) {
        if (sender instanceof ConsoleCommandSender) {
            sendMessage(sender, message);
        }
        return permission != null && permission != "" && sender.hasPermission(permission) && sendMessage(sender, message);
    }
    
    public static boolean sendMessage(final CommandSender sender, final String message) {
        if (sender instanceof Player) {
            if (message != null || message != "") {
                sender.sendMessage(fixColor(message));
            }
        }
        else {
            sender.sendMessage(ChatColor.stripColor(fixColor(message)));
        }
        return true;
    }
    
    public static void sendTitleMessage(final Player player, String title, String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        if (title == null) {
            title = "";
        }
        if (subtitle == null) {
            subtitle = "";
        }
        title = title.replace("&", "§");
        subtitle = subtitle.replace("&", "§");
        final CraftPlayer craftPlayer = (CraftPlayer)player;
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        final PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)packetTitle);
        final IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        final PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)packetSubtitle);
    }
    
 
    
    
    public static void removeItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.removeItem(items);
        for (Map.Entry<Integer, ItemStack> entry : notStored.entrySet()) {}
    }
    
    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message);
        }
        return true;
    }
    
    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message, final String permission) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message, permission);
        }
        return true;
    }
    
    public static boolean containsIgnoreCase(final String[] array, final String element) {
        for (final String s : array) {
            if (s.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return false;
    }
    
    public static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Material getMaterial(final String materialName) {
        Material returnMaterial = null;
        if (isInteger(materialName)) {
            final int id = Integer.parseInt(materialName);
            returnMaterial = Material.getMaterial(id);
        }
        else {
            returnMaterial = Material.matchMaterial(materialName);
        }
        return returnMaterial;
    }
    
    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }
    
    
    public static boolean isAlphaNumeric(final String s) {
        return s.matches("^[a-zA-Z0-9_]*$");
    }
    
    public static boolean isFloat(final String string) {
        return Pattern.matches("([0-9]*)\\.([0-9]*)", string);
    }
    
    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }
    
    public static void sendHoverMessageCommand(final Player p, final String s1, final String s2, final String cmd) {
        final IChatBaseComponent msg = IChatBaseComponent.ChatSerializer.a(fixColor("{\"text\":\"" + s1 + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + s2 + "\"}]}},\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + cmd + "\"}}"));
        final PacketPlayOutChat hover = new PacketPlayOutChat(msg);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)hover);
    }
    
    public static void sendHoverMessage(final CommandSender p, final String s1, final String s2) {
        final IChatBaseComponent msg = IChatBaseComponent.ChatSerializer.a(fixColor("{\"text\":\"" + s1 + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + s2 + "\"}]}},\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"\"}}"));
        final PacketPlayOutChat hover = new PacketPlayOutChat(msg);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)hover);
    }
}
