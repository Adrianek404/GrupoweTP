package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.Util;


public class KoszCommand extends PlayerCommand
{
    public KoszCommand() {
        super("kosz", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	if (p.getWorld().getName().equals("gtp")){
    		Inventory inv = Bukkit.createInventory((InventoryHolder) p, 54, Util.fixColor("&6Wyrzuc &eitemki &6:)"));
    		p.openInventory(inv);
        
    		return true;
    	}
		return true;
    }
            
    

}