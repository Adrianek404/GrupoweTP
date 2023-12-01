package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.Config;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.PrestizGui;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.ItemBuilder;
import pl.patrykv220.grupowetpcore.utils.Util;


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