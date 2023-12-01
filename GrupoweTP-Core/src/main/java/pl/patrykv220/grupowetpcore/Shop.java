package pl.patrykv220.grupowetpcore;


import org.bukkit.configuration.file.*;


import java.lang.reflect.*;

public class Shop
{

    public static int CENA_WEDKA;
    public static int CENA_PAJECZYNA;
    public static int CENA_SNIEZKI;
    public static int CENA_OBSYDIAN;
    public static int CENA_LOD;


    
    static {
        Shop.CENA_WEDKA = 200;
        Shop.CENA_PAJECZYNA = 2000;
        Shop.CENA_SNIEZKI = 100;
        Shop.CENA_OBSYDIAN = 600;
        Shop.CENA_LOD = 1000;
    }
    
    public static void loadConfig() {
        try {
            Main.getPlugin().saveDefaultConfig();
            final FileConfiguration c = Main.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Shop.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (c.isSet("shop." + f.getName().toLowerCase().replace("_", "."))) {
                    f.set(null, c.get("shop." + f.getName().toLowerCase().replace("_", ".")));
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
            for (int length = (fields = Shop.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                c.set("shop." + f.getName().toLowerCase().replace("_", "."), f.get(null));
            }
            Main.getPlugin().saveConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void reloadConfig() {
        Main.getPlugin().reloadConfig();
        loadConfig();
        saveConfig();
    }
}
