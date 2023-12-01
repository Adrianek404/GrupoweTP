package pl.patrykv220.grupowetpcore.manager;


import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import pl.yspar.core.helper.ArmorType;

public class DataManager
{
    private static HashMap<String, ArmorType> disco;
    private static HashMap<String, Color> lastColor;
    private static HashMap<String, ItemStack[]> shiftArmor;
    
    static {
        DataManager.disco = new HashMap<String, ArmorType>();
        DataManager.lastColor = new HashMap<String, Color>();
        DataManager.shiftArmor = new HashMap<String, ItemStack[]>();
    }
    
    public static HashMap<String, ArmorType> getDisco() {
        return DataManager.disco;
    }
    
    public static HashMap<String, Color> getLastColor() {
        return DataManager.lastColor;
    }
    
    public static HashMap<String, ItemStack[]> getShiftArmor() {
        return DataManager.shiftArmor;
    }
}

