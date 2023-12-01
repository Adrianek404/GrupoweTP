package pl.patrykv220.grupowetpcore.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemBuilder;
import pl.yspar.core.utils.Util;

public class PrestizGui
{
    public static void open(final CommandSender sender) {
        User u = User.get((Player)sender);
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatUtil.fixColor("&6Prestiż"));
        final ItemBuilder szklo = new ItemBuilder(Material.getMaterial(160), (short)7).setName(Util.fixColor("&8#"));
        final ItemBuilder info = new ItemBuilder(Material.getMaterial(351), (short)14).setName(Util.fixColor("&6&lCo to jest prestiż?"))
        		.addLore(Util.fixColor(" "))
        		.addLore(Util.fixColor("&eJesli posiadasz duzo rankingu, mozesz go wymienic na prestiz"))
        		.addLore(Util.fixColor("&ePosiadajac ^2000 rankingu mozesz wymienic go na 1 prestiz"))
        		.addLore(Util.fixColor("&ePrestiże zostały zainspirowane serwerem &bCraf&fCore&7.pl"))
        		.addLore(Util.fixColor(" "));
        final ItemBuilder tak = new ItemBuilder(Material.getMaterial(160), (short)13).setName(Util.fixColor("&2Spełniasz wymagania"))
        		.addLore(Util.fixColor(" "))
        		.addLore(Util.fixColor("&6Klikknij aby &eWymienic &7ranking &6na &7prestiz&6!"))
        		.addLore(Util.fixColor(" "));
        final ItemBuilder nie  = new ItemBuilder(Material.getMaterial(160), (short)14).setName(Util.fixColor("&4Posiadasz zbyt malo rankingu"))
        		.addLore(Util.fixColor(" "))
        		.addLore(Util.fixColor("&6Aktualnie nie mozesz wymienic rankingu na prestiz."))
        		.addLore(Util.fixColor(" "));

        inv.setItem(0, info.build());
        inv.setItem(1, szklo.build());
        if (u.getPoints() < 1999) {
            inv.setItem(2, nie.build());
          }
        if (u.getPoints() > 1999) {
            inv.setItem(2, tak.build());
          }
        inv.setItem(3, szklo.build());
        inv.setItem(4, szklo.build());
        ((HumanEntity) sender).openInventory(inv);
    }
  
  
}