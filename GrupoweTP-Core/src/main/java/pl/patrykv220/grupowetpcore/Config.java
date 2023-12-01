package pl.patrykv220.grupowetpcore;


import org.bukkit.configuration.file.*;



import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

import org.bukkit.*;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;

public class Config
{
    public static String DATABASE_MODE;
    public static String DATABASE_TABLEPREFIX;
    public static String DATABASE_MYSQL_HOST;
    public static int DATABASE_MYSQL_PORT;
    public static int LIMIT_KOX;
    public static int LIMIT_REF;
    public static int LIMIT_PEARL;
    public static String DATABASE_MYSQL_USER;
    public static String DATABASE_MYSQL_PASS;
    public static String DATABASE_MYSQL_NAME;
    public static String DATABASE_SQLITE_NAME;
    public static List<String> SIDEBAR_LIST;
    public static String SIDEBAR_HEADER;
    public static List<String> ANTILOGOUT_BLOCKED_COMMANDS;
    public static String SERWERNAME;
    public static Location SPAWN;
    public static Location SAVEEQ;
    
    static {
        Config.DATABASE_MODE = "mysql";
        Config.DATABASE_TABLEPREFIX = "gtp_";
        Config.DATABASE_MYSQL_HOST = "host";
        Config.DATABASE_MYSQL_PORT = 3306;
        Config.LIMIT_KOX = 1;
        Config.LIMIT_REF = 10;
        Config.LIMIT_PEARL = 3;
        Config.DATABASE_MYSQL_USER = "user";
        Config.DATABASE_MYSQL_PASS = "pass";
        Config.DATABASE_MYSQL_NAME = "name";
        Config.DATABASE_SQLITE_NAME = "minecraft.db";
        Config.ANTILOGOUT_BLOCKED_COMMANDS = Arrays.asList("/spawn","/tpa", "/home","/dom", "/sethome", "ustawdom", "/depozyt", "/schowek", "/tpaccept", "/ec", "/kit", "/repair", "/wb", "/craft");
        Config.SERWERNAME = "&a&lQuerekNet.pl";
        Config.SIDEBAR_HEADER = ChatUtil.fixColor("");
        final int n5 = 15;
        final String[] array5 = new String[n5];
        array5[0] = " ";
        Config.SIDEBAR_LIST = Arrays.asList(array5);
        final World world = Bukkit.getWorlds().get(0);
        final double n7 = 100.0;
        final double n8 = 0.0;
        Config.SPAWN = new Location(world, n8, n7, n8);
        Config.SAVEEQ = new Location(world, n8, n7, n8);
    }
    
    public static void loadConfig() {
        try {
            Main.getPlugin().saveDefaultConfig();
            final FileConfiguration c = Main.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (c.isSet("config." + f.getName().toLowerCase().replace("_", "."))) {
                    f.set(null, c.get("config." + f.getName().toLowerCase().replace("_", ".")));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveConfig() {
        try {
            final FileConfiguration c = Main.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                c.set("config." + f.getName().toLowerCase().replace("_", "."), f.get(null));
            }
            Main.getPlugin().saveConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setSpawn(final Location loc) {
    	Config.SPAWN = null;
    	saveConfig();
    	Config.SPAWN = loc;
        reloadConfig();
        loadConfig();
        saveConfig();
    }
    
    public static void setSaveeq(final Location loc) {
    	Config.SAVEEQ = null;
    	saveConfig();
    	Config.SAVEEQ = loc;
        reloadConfig();
        loadConfig();
        saveConfig();
    }


    
    public static void reloadConfig() {
        Main.getPlugin().reloadConfig();
        loadConfig();
        saveConfig();
    }
}
