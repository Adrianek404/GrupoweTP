package pl.patrykv220.grupowetpcore.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.Shop;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemBuilder;
import pl.yspar.core.utils.Util;

public class ShopGui
{
  public static boolean open(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
    	final ItemBuilder zamknij = new ItemBuilder(Material.getMaterial(187), 1, (short)2).setName(ChatUtil.fixColor("&6Zamknij")).addLore(ChatUtil.fixColor("&cZamknij menu."));
    	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(187), 1, (short)1).setName(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE")).addLore(ChatUtil.fixColor("&cKliknij aby powrocic."));
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&aSklep"));
        final ItemBuilder stale = new ItemBuilder(Material.getMaterial(371)).setName(ChatUtil.fixColor("&6Przedmioty stałe"));


        final ItemBuilder s1 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        final ItemBuilder s2 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        final ItemBuilder s3 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        inv.setItem(inv.getSize() - 1, s1.build()); //koniec
        inv.setItem(inv.getSize() - 2, s2.build());
        inv.setItem(inv.getSize() - 3, s2.build());
        inv.setItem(inv.getSize() - 4, s3.build());
        inv.setItem(inv.getSize() - 5, zamknij.build());
        inv.setItem(inv.getSize() - 6, s3.build());
        inv.setItem(inv.getSize() - 7, s2.build());
        inv.setItem(inv.getSize() - 8, s2.build());
        inv.setItem(inv.getSize() - 9, s1.build());
        inv.setItem(inv.getSize() - 10, s2.build());
        inv.setItem(inv.getSize() - 18, s2.build());
        inv.setItem(inv.getSize() - 19, s2.build());
        inv.setItem(inv.getSize() - 27, s2.build());
        inv.setItem(inv.getSize() - 32, stale.build());
        inv.setItem(inv.getSize() - 28, s2.build());
        inv.setItem(inv.getSize() - 36, s2.build());
        inv.setItem(inv.getSize() - 37, s2.build());
        inv.setItem(inv.getSize() - 45, s2.build());
        inv.setItem(inv.getSize() - 46, s1.build());
        inv.setItem(inv.getSize() - 47, s2.build());
        inv.setItem(inv.getSize() - 48, s2.build());
        inv.setItem(inv.getSize() - 49, s3.build());

        inv.setItem(inv.getSize() - 51, s3.build());
        inv.setItem(inv.getSize() - 52, s2.build());
        inv.setItem(inv.getSize() - 53, s2.build());
        inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
        p.openInventory(inv);
		return false;
  }
  
  public static boolean openStale(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
    	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(187), 1, (short)1).setName(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE")).addLore(ChatUtil.fixColor("&cKliknij aby powrocic."));
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&aSklep: &6Przedmioty stałe"));
        final ItemBuilder wedka = new ItemBuilder(Material.FISHING_ROD).setName(ChatUtil.fixColor("&6Wedka")).addLore("&7Cena: &a" + Shop.CENA_WEDKA).addLore("&7Posiadasz: " + ((u.getShop("wedka") == 1) ? "&aZakupione" : "&4Nie"));
        final ItemBuilder lod = new ItemBuilder(Material.PACKED_ICE, 16).setName(ChatUtil.fixColor("&6Lod")).addLore("&7Cena: &a" + Shop.CENA_LOD).addLore("&7Posiadasz: " + ((u.getShop("lod") == 1) ? "&aZakupione" : "&4Nie"));
        final ItemBuilder sniezki = new ItemBuilder(Material.SNOW_BALL, 8).setName(ChatUtil.fixColor("&6Sniezki")).addLore("&7Cena: &a" + Shop.CENA_SNIEZKI).addLore("&7Posiadasz: " + ((u.getShop("sniezki") == 1) ? "&aZakupione" : "&4Nie"));
        final ItemBuilder obs = new ItemBuilder(Material.OBSIDIAN, 32).setName(ChatUtil.fixColor("&6Obsydian")).addLore("&7Cena: &a" + Shop.CENA_OBSYDIAN).addLore("&7Posiadasz: " + ((u.getShop("obsydian") == 1) ? "&aZakupione" : "&4Nie"));
        final ItemBuilder pajeczyna = new ItemBuilder(Material.WEB, 4).setName(ChatUtil.fixColor("&6Pajeczyna")).addLore("&7Cena: &a" + Shop.CENA_PAJECZYNA).addLore("&7Posiadasz: " + ((u.getShop("pajeczyna") == 1) ? "&aZakupione" : "&4Nie"));



        final ItemBuilder s1 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        final ItemBuilder s2 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        final ItemBuilder s3 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor(""));
        inv.setItem(inv.getSize() - 1, s1.build()); //koniec
        inv.setItem(inv.getSize() - 2, s2.build());
        inv.setItem(inv.getSize() - 3, s2.build());
        inv.setItem(inv.getSize() - 4, s3.build());
        inv.setItem(inv.getSize() - 5, powrot.build());
        inv.setItem(inv.getSize() - 6, s3.build());
        inv.setItem(inv.getSize() - 7, s2.build());
        inv.setItem(inv.getSize() - 8, s2.build());
        inv.setItem(inv.getSize() - 9, s1.build());
        inv.setItem(inv.getSize() - 10, s2.build());
        inv.setItem(inv.getSize() - 18, s2.build());
        inv.setItem(inv.getSize() - 19, s2.build());
        inv.setItem(inv.getSize() - 27, s2.build());
        inv.setItem(inv.getSize() - 44, wedka.build());
        inv.setItem(inv.getSize() - 43, lod.build());
        inv.setItem(inv.getSize() - 42, sniezki.build());
        inv.setItem(inv.getSize() - 41, obs.build());
        inv.setItem(inv.getSize() - 40, pajeczyna.build());
        inv.setItem(inv.getSize() - 28, s2.build());
        inv.setItem(inv.getSize() - 36, s2.build());
        inv.setItem(inv.getSize() - 37, s2.build());
        inv.setItem(inv.getSize() - 45, s2.build());
        inv.setItem(inv.getSize() - 46, s1.build());
        inv.setItem(inv.getSize() - 47, s2.build());
        inv.setItem(inv.getSize() - 48, s2.build());
        inv.setItem(inv.getSize() - 49, s3.build());
        inv.setItem(inv.getSize() - 51, s3.build());
        inv.setItem(inv.getSize() - 52, s2.build());
        inv.setItem(inv.getSize() - 53, s2.build());
        inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
        p.openInventory(inv);
		return false;
  }
  
  
  
}