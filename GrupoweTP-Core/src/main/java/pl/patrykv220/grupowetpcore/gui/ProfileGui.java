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

import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.ItemBuilder;
import pl.patrykv220.grupowetpcore.utils.Util;

public class ProfileGui
{
  public static boolean open(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
    	final ItemBuilder zamknij = new ItemBuilder(Material.getMaterial(187), 1, (short)2).setName(ChatUtil.fixColor("&6Zamknij")).addLore(ChatUtil.fixColor("&cZamknij menu."));
    	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(187), 1, (short)1).setName(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE")).addLore(ChatUtil.fixColor("&cKliknij aby powrocic."));
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&6Profil gracza"));
        final ItemBuilder disco = new ItemBuilder(Material.getMaterial(351), (short)9).setName(ChatUtil.fixColor("&6Disco zbroja"));
        final ItemBuilder particle = new ItemBuilder(Material.getMaterial(399)).setName(ChatUtil.fixColor("&6Particlesy"));
        final ItemBuilder jezyk = new ItemBuilder(Material.SIGN).setName(ChatUtil.fixColor("&6Jezyk"));
        final ItemBuilder incognito = new ItemBuilder(Material.ENDER_PEARL).setName(ChatUtil.fixColor("&6Incognito")).addLore(ChatUtil.fixColor("&6Status: " + (u.isIncognito() ? "&aWlaczone" : "&cWylaczone")));

        ItemStack cipa2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta cips2 = (SkullMeta) cipa2.getItemMeta();
        cips2.setDisplayName(p.getName());
        ArrayList<String> xlores22 = new ArrayList<String>();
        xlores22.add(ChatUtil.fixColor(" "));
        xlores22.add(ChatUtil.fixColor("&6Twoja ranga:" + UserManager.getRank(p)));
        xlores22.add(ChatUtil.fixColor("&6Incognito: " + (u.isIncognito() ? "&aWlaczone" : "&cWylaczone")));
        xlores22.add(ChatUtil.fixColor(" "));
        cips2.setDisplayName(ChatUtil.fixColor("&6Twoj nick: &e" + p.getName()));

        cips2.setLore(xlores22);
        cips2.setOwner("" + p.getName());
        cipa2.setItemMeta(cips2);
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
        inv.setItem(inv.getSize() - 34, disco.build());
        inv.setItem(inv.getSize() - 32, particle.build());
        inv.setItem(inv.getSize() - 30, jezyk.build());
        inv.setItem(inv.getSize() - 16, incognito.build());
        inv.setItem(inv.getSize() - 28, s2.build());
        inv.setItem(inv.getSize() - 36, s2.build());
        inv.setItem(inv.getSize() - 37, s2.build());
        inv.setItem(inv.getSize() - 45, s2.build());
        inv.setItem(inv.getSize() - 46, s1.build());
        inv.setItem(inv.getSize() - 47, s2.build());
        inv.setItem(inv.getSize() - 48, s2.build());
        inv.setItem(inv.getSize() - 49, s3.build());
        inv.setItem(inv.getSize() - 50, cipa2);
        inv.setItem(inv.getSize() - 51, s3.build());
        inv.setItem(inv.getSize() - 52, s2.build());
        inv.setItem(inv.getSize() - 53, s2.build());
        inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
        p.openInventory(inv);
		return false;
  }
  
  public static boolean openJezyk(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
    	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(187), 1, (short)1).setName(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE")).addLore(ChatUtil.fixColor("&cKliknij aby powrocic."));
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&6Profil gracza"));
        final ItemBuilder poland = new ItemBuilder(Material.getMaterial(160), 1, (short)14).setName(ChatUtil.fixColor("&6Polski"));
        final ItemBuilder soon = new ItemBuilder(Material.getMaterial(160), 1, (short)8).setName(ChatUtil.fixColor("&6Wkrótce"));
        ItemStack cipa2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta cips2 = (SkullMeta) cipa2.getItemMeta();
        cips2.setDisplayName(p.getName());
        ArrayList<String> xlores22 = new ArrayList<String>();
        xlores22.add(ChatUtil.fixColor(" "));
        xlores22.add(ChatUtil.fixColor("&6Twoja ranga:" + UserManager.getRank(p)));
        xlores22.add(ChatUtil.fixColor("&6Incognito: " + (u.isIncognito() ? "&aWlaczone" : "&cWylaczone")));
        xlores22.add(ChatUtil.fixColor(" "));
        cips2.setDisplayName(ChatUtil.fixColor("&6Twoj nick: &e" + p.getName()));

        cips2.setLore(xlores22);
        cips2.setOwner("" + p.getName());
        cipa2.setItemMeta(cips2);
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
        inv.setItem(inv.getSize() - 30, soon.build());
        inv.setItem(inv.getSize() - 31, soon.build());
        inv.setItem(inv.getSize() - 32, soon.build());
        inv.setItem(inv.getSize() - 33, soon.build());
        inv.setItem(inv.getSize() - 34, poland.build());
        inv.setItem(inv.getSize() - 28, s2.build());
        inv.setItem(inv.getSize() - 36, s2.build());
        inv.setItem(inv.getSize() - 37, s2.build());
        inv.setItem(inv.getSize() - 45, s2.build());
        inv.setItem(inv.getSize() - 46, s1.build());
        inv.setItem(inv.getSize() - 47, s2.build());
        inv.setItem(inv.getSize() - 48, s2.build());
        inv.setItem(inv.getSize() - 49, s3.build());
        inv.setItem(inv.getSize() - 50, cipa2);
        inv.setItem(inv.getSize() - 51, s3.build());
        inv.setItem(inv.getSize() - 52, s2.build());
        inv.setItem(inv.getSize() - 53, s2.build());
        inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
        p.openInventory(inv);
		return false;
  }
  
  public static boolean openDisco(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
  	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(187), 1, (short)1).setName(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE")).addLore(ChatUtil.fixColor("&cKliknij aby powrocic."));
      Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&6Profil gracza"));
      final ItemBuilder szara = new ItemBuilder(Material.getMaterial(159), 1, (short)8).setName(ChatUtil.fixColor("&7SZARA"));
      ItemStack cipa2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
      SkullMeta cips2 = (SkullMeta) cipa2.getItemMeta();
      cips2.setDisplayName(p.getName());
      ArrayList<String> xlores22 = new ArrayList<String>();
      xlores22.add(ChatUtil.fixColor(" "));
      xlores22.add(ChatUtil.fixColor("&6Twoja ranga:" + UserManager.getRank(p)));
      xlores22.add(ChatUtil.fixColor("&6Incognito: " + (u.isIncognito() ? "&aWlaczone" : "&cWylaczone")));
      xlores22.add(ChatUtil.fixColor(" "));
      cips2.setDisplayName(ChatUtil.fixColor("&6Twoj nick: &e" + p.getName()));

      cips2.setLore(xlores22);
      cips2.setOwner("" + p.getName());
      cipa2.setItemMeta(cips2);
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
      inv.setItem(inv.getSize() - 30, szara.build());
      inv.setItem(inv.getSize() - 28, s2.build());
      inv.setItem(inv.getSize() - 36, s2.build());
      inv.setItem(inv.getSize() - 37, s2.build());
      inv.setItem(inv.getSize() - 45, s2.build());
      inv.setItem(inv.getSize() - 46, s1.build());
      inv.setItem(inv.getSize() - 47, s2.build());
      inv.setItem(inv.getSize() - 48, s2.build());
      inv.setItem(inv.getSize() - 49, s3.build());
      inv.setItem(inv.getSize() - 50, cipa2);
      inv.setItem(inv.getSize() - 51, s3.build());
      inv.setItem(inv.getSize() - 52, s2.build());
      inv.setItem(inv.getSize() - 53, s2.build());
      inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
      p.openInventory(inv);
      return true;
  
  }
  
  public static boolean openParticles(Player p)
  {
	    final User u = User.get(p);  
	    Guild g = User.get(p).getGuild();
    	final ItemBuilder powrot = new ItemBuilder(Material.getMaterial(187), 1, (short)1).setName(ChatUtil.fixColor("&6Powrot do: &cMENU GLOWNE")).addLore(ChatUtil.fixColor("&cKliknij aby powrocic."));
        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&6Profil gracza"));
        final ItemBuilder serca = new ItemBuilder(Material.getMaterial(372), 1).setName(ChatUtil.fixColor("&6Serduszka"));
        final ItemBuilder ogien = new ItemBuilder(Material.getMaterial(377), 1).setName(ChatUtil.fixColor("&6Ogien"));
        final ItemBuilder particlesEnable = new ItemBuilder(Material.getMaterial(160), 1, (short)5).setName(ChatUtil.fixColor("&6Efekt particlesow jest &aWlaczony")).addLore(ChatUtil.fixColor(" ")).addLore(ChatUtil.fixColor("&6Kliknij tutaj aby &cWylaczyc")).addLore(ChatUtil.fixColor(" "));
        final ItemBuilder particlesDisable = new ItemBuilder(Material.getMaterial(160), 1, (short)14).setName(ChatUtil.fixColor("&6Efekt particlesow jest &cWylaczony"));
        ItemStack cipa2 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta cips2 = (SkullMeta) cipa2.getItemMeta();
        cips2.setDisplayName(p.getName());
        ArrayList<String> xlores22 = new ArrayList<String>();
        xlores22.add(ChatUtil.fixColor(" "));
        xlores22.add(ChatUtil.fixColor("&6Twoja ranga:" + UserManager.getRank(p)));
        xlores22.add(ChatUtil.fixColor("&6Incognito: " + (u.isIncognito() ? "&aWlaczone" : "&cWylaczone")));
        xlores22.add(ChatUtil.fixColor(" "));
        cips2.setDisplayName(ChatUtil.fixColor("&6Twoj nick: &e" + p.getName()));

        cips2.setLore(xlores22);
        cips2.setOwner("" + p.getName());
        cipa2.setItemMeta(cips2);
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
        inv.setItem(inv.getSize() - 32, serca.build());
        inv.setItem(inv.getSize() - 31, ogien.build());

        inv.setItem(inv.getSize() - 28, s2.build());
        inv.setItem(inv.getSize() - 36, s2.build());
        inv.setItem(inv.getSize() - 37, s2.build());
        inv.setItem(inv.getSize() - 45, s2.build());
        inv.setItem(inv.getSize() - 46, s1.build());
        inv.setItem(inv.getSize() - 47, s2.build());
        inv.setItem(inv.getSize() - 48, s2.build());
        inv.setItem(inv.getSize() - 49, s3.build());
        inv.setItem(inv.getSize() - 50, cipa2);
        inv.setItem(inv.getSize() - 51, s3.build());
        inv.setItem(inv.getSize() - 52, s2.build());
        inv.setItem(inv.getSize() - 53, s2.build());
        inv.setItem(inv.getSize() - 54, s1.build()); //poczatek
        if (u.isParticles()) {
            inv.setItem(inv.getSize() - 14, particlesEnable.build());
        }
        if (!u.isParticles()) {
            inv.setItem(inv.getSize() - 14, particlesDisable.build());
        }
        p.openInventory(inv);
		return false;
  }
  
  
}