package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;


public class IncognitoCommand extends PlayerCommand
{
    public IncognitoCommand() {
        super("incognito", "informacje o gildii", "/incognito", "core.cmd.user", new String[] { "ic" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = User.get(p);
            if (u == null) {
                return true;
            }
            setIncognito(p, u);
            return true;
        }
        return true;
    }
    
    public static void setIncognito(final Player p, final User u) {
        if (u.isIncognito() == false) {
            		u.setIncognito(true);
            		ChatUtil.sendMessage(p, "&7Tryb incognito zostal &aWlaczony&7.");
                    for (Player online : Bukkit.getOnlinePlayers()) {
                    	if (online.hasPermission("adminsend")) {
                    		ChatUtil.sendMessage(online, "&4[!] &a" + p.getName() + " &eprzeszedł do trybu incognito.");
                    		return;
                    	}
                    }
                    return;

        }
        else {
    		ChatUtil.sendMessage(p, "&7Tryb incognito zostal &cWylaczony&7.");
    		u.setIncognito(false);
            return;
        }
            
    }

}