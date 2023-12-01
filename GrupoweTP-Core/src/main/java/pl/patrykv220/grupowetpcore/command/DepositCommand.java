package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.yspar.core.Config;
import pl.yspar.core.basic.User;
import pl.yspar.core.gui.PrestizGui;
import pl.yspar.core.gui.ProfileGui;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemBuilder;
import pl.yspar.core.utils.Util;


public class DepositCommand extends PlayerCommand
{
    public DepositCommand() {
        super("schowek", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	if (p.getWorld().getName().equals("gtp")) {
    		openDeposit(p);
    		return false;
    	}
    	return false;
    }
    
	public static void openDeposit(final Player player) {
		final User user = User.get(player);
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)27, ChatUtil.fixColor("&7Depozyt"));

		final ItemBuilder kox;
		(kox = new ItemBuilder(Material.GOLDEN_APPLE, (short) 1)).setName(" &a&lKOXY");
		kox.addLore(" ");
		kox.addLore(" &8* &aPosiadsz w schowku: &7" + Integer.toString(user.getSchowekKox()));
		kox.addLore(" &8* &aLimit przedmiotow w eq&8: &7" + Config.LIMIT_KOX);
		kox.addLore(" ");
		kox.addLore("  &8* &2Klikajac Tutaj mozesz wyplacic przedmioty.");
		inv.setItem(12, kox.build());
		final ItemBuilder perla;
		(perla = new ItemBuilder(Material.ENDER_PEARL)).setName(" &5&lPERLY");
		perla.addLore(" ");
		perla.addLore(" &8* &aPosiadsz w schowku: &7" + Integer.toString(user.getSchowekPerla()));
		perla.addLore(" &8* &aLimit przedmiotow w eq&8: &7" +Config.LIMIT_PEARL);
		perla.addLore(" ");
		perla.addLore("  &8* &2Klikajac Tutaj mozesz wyplacic przedmioty.");
		inv.setItem(13, perla.build());
		final ItemBuilder ref;
		(ref = new ItemBuilder(Material.GOLDEN_APPLE)).setName(" &7&lREFILE");
		ref.addLore(" ");
		ref.addLore(" &8* &aPosiadsz w schowku: &7" + Integer.toString(user.getSchowekRefil()));
		ref.addLore(" &8* &aLimit przedmiotow w eq&8: &7" + Config.LIMIT_REF);
		ref.addLore(" ");
		ref.addLore("  &8* &2Klikajac Tutaj mozesz wyplacic przedmioty.");
		inv.setItem(14, ref.build());
		final ItemBuilder all;
		(all = new ItemBuilder(Material.HOPPER)).setName(" &7Dobierz caly limit");
		all.addLore(" ");
		all.addLore("  &8* &aKlikajac Tutaj mozesz wyplacic wszystko.");
		all.addLore(" ");
		inv.setItem(22, all.build());
		Util.guisetGlass(inv);
		player.openInventory(inv);
	}
            
    

}