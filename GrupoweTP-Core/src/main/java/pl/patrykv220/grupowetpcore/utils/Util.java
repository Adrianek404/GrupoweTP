package pl.patrykv220.grupowetpcore.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import pl.yspar.core.basic.User;


public class Util
{
    public static ItemStack parseItem(final String string) {
        final Integer amount = Integer.parseInt(string.substring(0, string.indexOf(32)));
        String type;
        final Material material;
        ItemStack itemstack;
        ItemStack itemStack;
        if ((material = Material.getMaterial(type = (type = (type = string.substring(string.indexOf(32) + 1)).toUpperCase()).replaceAll(" ", "_"))) == null) {
            if (!type.equalsIgnoreCase("Enchanted_Golden_Apple")) {
                return new ItemStack(Material.AIR);
            }
            itemStack = (itemstack = new ItemStack(322, 1));
        }
        else {
            itemStack = (itemstack = new ItemStack(material));
        }
        itemStack.setAmount((int)amount);
        return itemstack;
    }
    public static boolean sendMsg(CommandSender p, String msg){
    	p.sendMessage(fixColor(msg));
    	return true;
    }
    
    public static boolean isInteger(final String string) {
        return string.matches("-?[0-9]+");
    }
    
    public static boolean isInt(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public static void guisetGlass(final Inventory inv) {
        final ItemStack glass2 = new ItemBuilder(Material.STAINED_GLASS_PANE,1, (short)15).setName(fixColor("&8&l###")).build();
        int i = 0;
        while (i < inv.getSize()) {
            final ItemStack is;
            if ((is = inv.getItem(i)) == null || is.getType().equals(Material.AIR)) {
                inv.setItem(i, glass2);
            }
            i += 1;
        }
    }
    
    public static boolean broadcast(final String message, final String permission) {
        final Iterator<Player> iterator2;
        Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
        while (iterator.hasNext()) {
            final Player player;
            if ((player = iterator2.next()).hasPermission(permission)) {
                player.sendMessage(fixColor(message));
            }
            iterator = iterator2;
        }
        System.out.println(message);
        return true;
    }
    
    public static void giveItems(final Player p, final ItemStack... items) {
        for(ItemStack m : items){
        	if(p.getInventory().firstEmpty() != -1){
        	
        	p.getInventory().addItem(m);
            return;
        	}
        	p.getWorld().dropItemNaturally(p.getLocation(), m);
        }
        }
    
    public static String locationLongToString(Location loc) {
        if (loc == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(loc.getWorld().getName());
        sb.append("," + Double.toString(loc.getX()));
        sb.append("," + Double.toString(loc.getY()));        
        sb.append("," + Double.toString(loc.getZ()));
        sb.append("," + Float.toString(loc.getYaw()));
        sb.append("," + Float.toString(loc.getPitch()));
        return sb.toString();
    }

    
    public static List<Location> locationFromString(List<String> locs) {
        Iterator<String> iterator;
        ArrayList<Location> s = new ArrayList<Location>();
        Iterator<String> iterator2 = iterator = locs.iterator();
        while (iterator2.hasNext()) {
            String l = iterator.next();
            iterator2 = iterator;
            s.add(Util.locationFromString(l));
        }
        return s;
    }

    

    public static boolean sendMessage(CommandSender sender, List<String> message) {
        Iterator<String> iterator;
        Iterator<String> iterator2 = iterator = message.iterator();
        while (iterator2.hasNext()) {
            String text = iterator.next();
            iterator2 = iterator;
            sender.sendMessage(fixColor(text));
        }
        return true;
    }
    
    


    
    
    public static String replace(String text, String searchString, String replacement) {
        int start;
            if (text == null || text.isEmpty() || searchString.isEmpty() || replacement == null) {
                return text;
            }
            start = 0;
            int max = -1;
            int end = text.indexOf(searchString, start);
            if (end == -1) {
                return text;
            }
            int replacedLength = searchString.length();
            int increase = replacement.length() - replacedLength;
            int n = increase = increase < 0 ? 0 : increase;
            StringBuilder sb = new StringBuilder(text.length() + (increase *= max < 0 ? 16 : (max > 64 ? 64 : max)));
            int n2 = end;
            while (n2 != -1) {
                StringBuilder stringBuilder2 = sb.append(text.substring(start, end)).append(replacement);
                start = end + replacedLength;
                if (--max == 0) {
                    break;
                }
                n2 = text.indexOf(searchString, start);
            }
        
        sb.append(text.substring(start));
        return sb.toString();
    }

    
    public static boolean sendMessage(final CommandSender sender, final String message) {
        final boolean b = true;
        sender.sendMessage(fixColor(message));
        return b;
    }
    
    public static List<Damage> sort(Map<String, Double> damage) {
        Iterator<Entry<String, Double>> iterator;
        if (damage.size() == 0) {
            return null;
        }
        ArrayList<Damage> map = new ArrayList<Damage>();
        Iterator<Entry<String, Double>> iterator2 = iterator = damage.entrySet().iterator();
        while (iterator2.hasNext()) {
            Entry<String, Double> e = iterator.next();
            map.add(new Damage((String)e.getKey(), (Double)e.getValue()));
            iterator2 = iterator;
        }
        ArrayList<Damage> arrayList = map;
        Collections.sort(arrayList);
        return arrayList;
    }
    
    public static void playerGiveItems(final Player player, final List<ItemStack> items, final Location location) {
        final Iterator iterator2;
        Iterator<Entry> iterator = (Iterator<Entry>)(iterator2 = player.getInventory().addItem((ItemStack[])items.toArray(new ItemStack[items.size()])).entrySet().iterator());
        while (iterator.hasNext()) {
            final Entry en = (Entry) iterator2.next();
            location.getWorld().dropItemNaturally(location, (ItemStack)en.getValue());
            iterator = (Iterator<Entry>)iterator2;
        }
    }
    
    public static boolean locationEquals(final Location loc, final Location loc1) {
        return loc.getX() == loc1.getX() && loc.getY() == loc1.getY() && loc.getZ() == loc1.getZ();
    }
    
    public static void sendError(final String message) {
        System.out.println(message);
    }
    

    
    public static List<String> fixColor(final List<String> list) {
       List<String> mm = new ArrayList<String>();
       for(String s : list){
    	   mm.add(fixColor(s));
       }
       return mm;
    }
    
    public static boolean locationEqualsBlock(final Location loc, final Location loc1) {
        return loc.getBlockX() == loc1.getBlockX() && loc.getBlockY() == loc1.getBlockY() && loc.getBlockZ() == loc1.getBlockZ();
    }
    
    public static void sendInfo(final String message) {
        System.out.println(message);
    }
    
    
    
    public static Location locationFromString(final String msg) {
        if (msg == null || msg.equalsIgnoreCase("") || !msg.contains(",")) {
            final World world2 = Bukkit.getWorlds().get(0);
            final double n = 0.0;
            return new Location(world2, n, n, n);
        }
        final String[] m;
        final String[] array = m = msg.split(",");
        final World world = Bukkit.getWorld(array[0]);
        final double x = Double.parseDouble(array[1]);
        final double y = Double.parseDouble(array[2]);
        final double z = Double.parseDouble(array[3]);
        if (array.length <= 4) {
            return new Location(world, x, y, z);
        }
        final String[] array2 = m;
        final float yaw = Float.parseFloat(array2[4]);
        final float pitch = Float.parseFloat(array2[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }
    
    public static String locationToString(Location loc) {
        if (loc == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(loc.getWorld().getName());
        sb.append("," + Double.toString(loc.getX()));
        sb.append("," + Double.toString(loc.getY()));
        sb.append("," + Double.toString(loc.getZ()));
    
        return sb.toString();
    }
    

    
    public static void brodcastAllPlayer(final String message) {
        final Iterator<Player> iterator2;
        Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
        while (iterator.hasNext()) {
            iterator2.next().sendMessage(fixColor(message));
            iterator = iterator2;
        }
    }
    
    public static void send(final String message) {
        System.out.println(message);
    }
    

    public static List<String> locationToString(List<Location> locs) {
        Iterator<Location> iterator;
        ArrayList<String> s = new ArrayList<String>();
        Iterator<Location> iterator2 = iterator = locs.iterator();
        while (iterator2.hasNext()) {
            Location l = iterator.next();
            iterator2 = iterator;
            s.add(Util.locationToString(l));
        }
        return s;
    }
    
    public static ItemStack parseItemStack(String itemStack) {
        int i;
        ItemStack is = new ItemStack(Material.AIR);
        String[] strings = itemStack.split(" ");
        String[] item = strings[0].split(":");
        Material m;
        if (item.length > 1) {
            Material ms = Material.getMaterial((int)Integer.parseInt(item[0]));
            ItemStack itemStack2 = is;
            itemStack2.setType(ms);
            itemStack2.setDurability(Short.parseShort(item[1]));
        } else if (Util.isInt(item[0])) {
            m = Material.getMaterial((int)Integer.parseInt(item[0]));
            is.setType(m);
        } else {
            m = Material.getMaterial((String)item[0]);
            is.setType(m);
        }
        int amount = 1;
        if (Util.isInt(strings[1])) {
            amount = Integer.parseInt(strings[1]);
        }
        is.setAmount(amount);
        int n = i = 2;
        while (n < strings.length) {
            String[] trim = strings[i].split(":");
            if (trim.length >= 1) {
                if (trim[0].equalsIgnoreCase("name")) {
                    ItemStack itemStack3 = is;
                    ItemMeta im = itemStack3.getItemMeta();
                    String name = Util.fixColor(Util.replace(trim[1], "_", ""));
                    im.setDisplayName(name);
                    itemStack3.setItemMeta(im);
                } else if (trim[0].equalsIgnoreCase("lore")) {
                    int n2;
                    ItemMeta im = is.getItemMeta();
                    String[] arrstring = trim;
                    arrstring[1] = Util.replace(trim[1], "_", "");
                    String[] lorestring = arrstring[1].split("&nl");
                    ArrayList<String> lore = new ArrayList<String>();
                    String[] arrstring2 = lorestring;
                    int n3 = arrstring2.length;
                    int n4 = n2 = 0;
                    while (n4 < n3) {
                        String s2 = arrstring2[n2];
                        lore.add(Util.fixColor(s2));
                        n4 = ++n2;
                    }
                    ItemMeta itemMeta = im;
                    itemMeta.setLore(lore);
                    is.setItemMeta(itemMeta);
                } else if (trim[0].equalsIgnoreCase("owner")) {
                    ItemStack itemStack4 = is;
                    itemStack4.setType(Material.SKULL);
                    SkullMeta im2 = (SkullMeta)itemStack4.getItemMeta();
                    im2.setOwner(Util.fixColor(trim[1]));
                } else {



                }
            }
            n = ++i;
        }
        return is;
    }

    
    public static String fixColor(final String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace("%>", "�").replace("<%", "�"));
    }
    
    public static String getAsist(final List<Damage> damage, final String killer) {
        if (damage == null || damage.isEmpty()) {
            return null;
        }
        if (!damage.get(0).getUUID().equals(killer) && damage.get(0).getDamage() > 4.0) {
            return damage.get(0).getUUID();
        }
        if (damage.size() <= 1) {
            return null;
        }
        if (!damage.get(1).getUUID().equals(killer) && damage.get(1).getDamage() > 4.0) {
            return damage.get(1).getUUID();
        }
        return null;
    }
    
    public static class Damage implements Comparable<Damage>
    {
        private double damage;
        private String uuid;
        
        public Damage(final String uuid, final double damage) {
            this.uuid = uuid;
            this.damage = damage;
        }
        
        @Override
        public int compareTo(final Damage dmg) {
            int i;
            if ((i = Double.compare(dmg.getDamage(), this.damage)) == 0) {
                if (this.uuid == null) {
                    return -1;
                }
                if (dmg.getUUID() == null) {
                    return 1;
                }
                i = this.uuid.compareTo(dmg.getUUID());
            }
            return i;
        }
        
        public double getDamage() {
            return this.damage;
        }
        
        public String getUUID() {
            return this.uuid;
        }
    }
}
