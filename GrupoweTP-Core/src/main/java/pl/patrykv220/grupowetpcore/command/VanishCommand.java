package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.TitleAPI;
import pl.patrykv220.grupowetpcore.utils.Util;


public class VanishCommand extends PlayerCommand
{
    public VanishCommand() {
        super("vanish", "informacje o gildii", "/incognito", "core.cmd.vanish", new String[] { "v" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = User.get(p);
            if (u == null) {
                return true;
            }
            setVanish (p, u);
            return false;
        }
        return true;
    }
    
    public static void setVanish(final Player p, final User u) {
        if (u.isVanish() == false) {
            		u.setVanish(true);
            	    p.playSound(p.getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
            	    p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 10);
                    TitleAPI.sendTitle(p, 0, 30, 0, "", Util.fixColor("&7Vanish &aWlaczony"));
                    for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                        if (online.hasPermission("core.cmd.vanish")) {
                        	ChatUtil.sendMessage(online, ChatUtil.fixColor("&4[!] &aAdministrator &6" + p.getName() + " &aWlaczyl &6vanisha."));
                            continue;
                        }
                        online.hidePlayer(p);
                    }
                    return;

        }
        else {
    		u.setVanish(false);
            TitleAPI.sendTitle(p, 0, 30, 0, "", Util.fixColor("&6Vanish &cWylaczony"));
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (online.hasPermission("core.cmd.vanish")) {
                	ChatUtil.sendMessage(online, ChatUtil.fixColor("&4[!] &aAdministrator &6" + p.getName() + " &cWylaczyl &6vanisha."));
                    continue;
                }
                online.showPlayer(p);
            }
            return;
        }
            
    }
    

}