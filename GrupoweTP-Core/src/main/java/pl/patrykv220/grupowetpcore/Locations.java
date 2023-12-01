// 
// Decompiled by Procyon v0.5.36
// 

package pl.patrykv220.grupowetpcore;

import java.lang.reflect.Field;
import java.io.InputStream;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.yspar.core.utils.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class Locations
{


    public static int RANDOMTP_OD;
    public static int RANDOMTP_DO;

    private static File file;
    private static FileConfiguration c;
    
    static {
        Locations.file = new File(Main.getPlugin().getDataFolder(), "locations.yml");
        Locations.c = null;


        Locations.RANDOMTP_OD = -1500;
        Locations.RANDOMTP_DO = 1500;

    }
    
    public static void loadLocations() {
        try {
            if (!Locations.file.exists()) {
                Locations.file.getParentFile().mkdirs();
                final InputStream is = Main.getPlugin().getResource(Locations.file.getName());
                if (is != null) {
                    Logger.copy(is, Locations.file);
                }
            }
            Locations.c = (FileConfiguration)YamlConfiguration.loadConfiguration(Locations.file);
            Field[] fields;
            for (int length = (fields = Locations.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (Locations.c.isSet("locations." + f.getName().toLowerCase().replaceFirst("_", ",").replace(",", "."))) {
                    f.set(null, Locations.c.get("locations." + f.getName().toLowerCase().replaceFirst("_", ",").replace(",", ".")));
                }
            }
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }
    
    public static void saveLocations() {
        try {
            Field[] fields;
            for (int length = (fields = Locations.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                Locations.c.set("locations." + f.getName().toLowerCase().replaceFirst("_", ",").replace(",", "."), f.get(null));
            }
            Locations.c.save(Locations.file);
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }
    
    public static void reloadLocations() {
        loadLocations();
        saveLocations();
    }
}
