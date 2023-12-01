package pl.patrykv220.grupowetpcore.manager;

import java.util.concurrent.*;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.User;
import pl.yspar.core.listener.PlayerAntyLogoutListener;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemBuilder;
import pl.yspar.core.utils.Logger;
import pl.yspar.core.utils.Util;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class UserManager
{
    private static List<User> users;
    private static List<User> online;
    
    static {
    	
        UserManager.users = new ArrayList<User>();
        UserManager.online = new ArrayList<User>();
    }
    
    public static List<User> getOnline() {
        return new ArrayList<User>(UserManager.online);
    }
    
    public static List<User> getUsers() {
        return new ArrayList<User>(UserManager.users);
    }
    


	public static void unregisterPlayers() {
		@SuppressWarnings("rawtypes")
		Iterator iterator;
		@SuppressWarnings("rawtypes")
		Iterator iterator2 = iterator = Bukkit.getOnlinePlayers().iterator();
		while (iterator2.hasNext()) {
			UserManager.quitPlayer((Player) iterator.next());
			iterator2 = iterator;
		}
	}
	
    public static void remove(final User user) {
        if (UserManager.users.contains(user)) {
            UserManager.users.remove(user);
        }
    }
    
    
    
	


	public static void registerPlayers() {
		@SuppressWarnings("rawtypes")
		Iterator iterator;
		@SuppressWarnings("rawtypes")
		Iterator iterator2 = iterator = Bukkit.getOnlinePlayers().iterator();
		while (iterator2.hasNext()) {
			UserManager.joinPlayer((Player) iterator.next());
			iterator2 = iterator;
		}
	}
    


	
	public static void loadLobby(final Player player) {
		ItemStack shop = new ItemStack(Material.EMERALD, 1);
		 ItemMeta shops = shop.getItemMeta();
		 shops.setDisplayName(ChatColor.GREEN + "Sklep");
		 shops.setLore(Arrays.asList(ChatColor.GRAY + "Kliknij aby otworzyc"));
		 shop.setItemMeta(shops);
		 
			ItemStack saveeq = new ItemStack(Material.BOOK, 1);
			 ItemMeta saveeqs = saveeq.getItemMeta();
			 saveeqs.setDisplayName(ChatColor.GREEN + "Zapisz EQ");
			 saveeqs.setLore(Arrays.asList(ChatColor.GRAY + "Kliknij aby otworzyc"));
			 saveeq.setItemMeta(saveeqs);
			 
				ItemStack profil = new ItemStack(Material.DIAMOND, 1);
				 ItemMeta profils = saveeq.getItemMeta();
				 profils.setDisplayName(ChatColor.GREEN + "Profil gracza");
				 profils.setLore(Arrays.asList(ChatColor.GRAY + "Kliknij aby otworzyc"));
				 profil.setItemMeta(profils);

		 player.getInventory().clear();
		 player.getPlayer().getInventory().setItem(0, shop);
		 player.getPlayer().getInventory().setItem(4, saveeq);
		 player.getPlayer().getInventory().setItem(8, profil);
		 player.getInventory().setHelmet((ItemStack)null);
		 player.getInventory().setChestplate((ItemStack)null);
		 player.getInventory().setLeggings((ItemStack)null);
		 player.getInventory().setBoots((ItemStack)null);
	}
	
	public static void loadItems(final Player player) {
        player.getInventory().clear();
        User u = User.get(player);
        YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/core/data/" + player.getName()+".yml"));
        if (c.get("inventory.content") == null){
	        ItemStack sword = new ItemStack(Material.IRON_SWORD); 
	        sword.addEnchantment(Enchantment.DAMAGE_ALL, 4); 
	        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
	        ItemStack sword2 = new ItemStack(Material.IRON_SWORD); 
	        sword2.addEnchantment(Enchantment.KNOCKBACK, 2); 
	        ItemStack kox = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1); 
	        ItemStack ref = new ItemStack(Material.GOLDEN_APPLE, 10); 
	        ItemStack mieso = new ItemStack(Material.COOKED_BEEF, 64); 
	        ItemStack woda = new ItemStack(Material.WATER_BUCKET, 1); 
	        ItemStack slime = new ItemStack(Material.SLIME_BLOCK, 3); 
	        ItemStack cobbel = new ItemStack(Material.COBBLESTONE, 64); 
	        ItemStack perly = new ItemStack(Material.ENDER_PEARL, 2); 
	        ItemStack bow = new ItemStack(Material.BOW, 1); 
	        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3); 
	        bow.addEnchantment(Enchantment.ARROW_FIRE,1);
	        ItemStack arrow = new ItemStack(Material.ARROW, 16); 
	        player.getInventory().addItem(sword);
	        player.getInventory().addItem(sword2);
	        player.getInventory().addItem(kox);
	        player.getInventory().addItem(ref);
	        player.getInventory().addItem(woda);
	        player.getInventory().addItem(mieso);
	        player.getInventory().addItem(slime);
	        player.getInventory().addItem(cobbel);
	        player.getInventory().addItem(perly);
	        player.getInventory().addItem(bow);
	        player.getInventory().addItem(arrow);
	        ItemStack h1 = new ItemStack(Material.IRON_HELMET); 
	        h1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
	        h1.addEnchantment(Enchantment.DURABILITY, 3); 
	        
	        ItemStack h2 = new ItemStack(Material.IRON_CHESTPLATE); 
	        h2.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
	        h2.addEnchantment(Enchantment.DURABILITY, 3); 
	        
	        ItemStack h3 = new ItemStack(Material.IRON_LEGGINGS); 
	        h3.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
	        h3.addEnchantment(Enchantment.DURABILITY, 3); 
	        
	        ItemStack h4 = new ItemStack(Material.IRON_BOOTS); 
	        h4.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); 
	        h4.addEnchantment(Enchantment.DURABILITY, 3); 
	        player.getInventory().setHelmet(h1);
	        player.getInventory().setChestplate(h2);
	        player.getInventory().setLeggings(h3);
	        player.getInventory().setBoots(h4);
	        if (u.getShop("sniezki") == 1) {
		        ItemStack sniezka = new ItemStack(Material.SNOW_BALL, 8); 
		        player.getInventory().addItem(sniezka);
		        return;
	        }
	        if (u.getShop("wedka") == 1) {
		        ItemStack wedka = new ItemStack(Material.FISHING_ROD, 1); 
		        player.getInventory().addItem(wedka);
		        return;
	        }
	        if (u.getShop("lod") == 1) {
		        ItemStack lod = new ItemStack(Material.PACKED_ICE, 16); 
		        player.getInventory().addItem(lod);
		        return;
	        }
	        if (u.getShop("obsydian") == 1) {
		        ItemStack obs = new ItemStack(Material.OBSIDIAN, 32); 
		        player.getInventory().addItem(obs);
		        return;
	        }
	        if (u.getShop("pajeczyna") == 1) {
		        ItemStack pajeczyna = new ItemStack(Material.WEB, 4); 
		        player.getInventory().addItem(pajeczyna);
		        return;
	        }
            return;
        }
        if (c.get("inventory.content") != null){
    		try {
    			User.restore(player);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
        return;
	}
    
    public static void joinPlayer(final Player player) {
    	final User user = User.getNew(player.getName(), player.getAddress().getAddress().getHostAddress());

        final Player player2 = player;
        player2.setScoreboard(user.getScoreboard());
        TagManager.createBoard(player2);
        UserManager.online.add(user);

    }
    
    
    
    public static void quitPlayer(final Player player) {
        final Player player2 = player;
        final User user = User.get(player2);
        TagManager.removeBoard(player2);
        UserManager.online.remove(user);
        if (user.isSaveEQ() == true) {
        	user.setSaveEQ(false);
        }
        if (PlayerAntyLogoutListener.antiRelog.containsKey(player.getUniqueId())) {
            player.damage(1337.0);
            PlayerAntyLogoutListener.antiRelog.remove(player.getUniqueId());
            if (user.getPoints() > 50) {
                user.removePoints(50);
            }
        }
    }

    
    public static void RefreshTag(Player p) {
		final Iterator<Player> iterator2;
		Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit
				.getOnlinePlayers().iterator();
		while (iterator.hasNext()) {
			TagManager.updateBoard(iterator2.next());
			iterator = iterator2;
		}
    }
    
    public static void add(final User user) {
        if (!UserManager.users.contains(user)) {
            UserManager.users.add(user);
        }
    }
    
    public static List<String> getNames(final List<User> userList) {
        final ArrayList<String> names = new ArrayList<String>();
        Iterator<User> iterator2;
        final Iterator<User> iterator = iterator2 = userList.iterator();
        while (iterator2.hasNext()) {
            final User user = iterator.next();
            iterator2 = iterator;
            names.add(user.getName());
        }
        return names;
    }
    
    public static String getRank(final Player get) {
    	String prefix = "&7 Gracz";

        final PermissionUser user = PermissionsEx.getUser(get);
        final String[] grups = user.getGroupNames();
        if (grups[0].toLowerCase().equals("root")) {
            prefix = ChatUtil.fixColor("&4Root ");
        }
        if (grups[0].toLowerCase().equals("admin")) {
            prefix = ChatUtil.fixColor("&cAdmin ");
        }
        if (grups[0].toLowerCase().equals("mod")) {
            prefix = ChatUtil.fixColor("&2Mod ");
        }
        if (grups[0].toLowerCase().equals("helper")) {
            prefix = ChatUtil.fixColor("&3helper ");
        }
        if (grups[0].toLowerCase().equals("vip")) {
           prefix = ChatUtil.fixColor("&eVIP ");
        }
        if (grups[0].toLowerCase().equals("yt")) {
            prefix = ChatUtil.fixColor("&fY&CT ");
        }
        if (grups[0].toLowerCase().equals("svip")) {
            prefix = ChatUtil.fixColor("&6SVIP ");
        }
		return prefix;
    }
    
    public static String getJoinRank(final Player get) {
    	ChatUtil.sendActionBar(get, ChatUtil.fixColor("&8→&7→ &6Twoja ranga&8:" + getRank(get) + " &7←&8←"));
		if (get.getPlayer().hasPermission("join")) {
			Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ " + getRank(get) + " &7" + get.getName() + " &fdołączył na tryb."));
			return null;
		}
		return null;
    }

    
    public static void deleteUser(final User u) {
        UserManager.users.remove(u.getName());
        RankingManager.removeRanking(u);
    }
    
    public static List<User> getUsers(final List<String> names) {
        final ArrayList<User> userList = new ArrayList<User>();
        Iterator<String> iterator2;
        final Iterator<String> iterator = iterator2 = names.iterator();
        while (iterator2.hasNext()) {
            final String name = iterator.next();
            iterator2 = iterator;
            userList.add(User.get(name));
        }
        return userList;
    }
    
    
}
