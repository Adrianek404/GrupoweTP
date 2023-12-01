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


public class CoinsCommand extends PlayerCommand
{
    public CoinsCommand() {
        super("coins", "", "/coins", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	User u = User.get(p);
    	if (args.length < 1) {
    		ChatUtil.sendMessage(p, "&2[!] &aStan twoich coinsow wynosi: &6" + u.getCoins());
    		return false;
    	}
    	if (args[0].equals("add") && p.hasPermission("coinsadd")) {
    		if(args.length > 1) {
    			int i = Integer.valueOf(args[1]);
    			u.addCoins(i);
    			ChatUtil.sendMessage(p, "&2[!] &aDodano do twojego konta: &6" + args[1]);
    			return false;
    		}
			ChatUtil.sendMessage(p, "&4[!] &cPodaj ilosc coins");
			return false;
    	}
    	return false;
    }
    

            
    

}