package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;


public class sBCommand extends PlayerCommand
{
    public sBCommand() {
        super("sb", "informacje o gildii", "/incognito", "core.cmd.user", new String[] { "sidebar", "scoreboard" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = User.get(p);
            if (u == null) {
                return true;
            }
            return ChatUtil.sendMessage(p, ChatUtil.fixColor("&7Sidebar zostal " + (u.getSidebar().change() ? "&awlaczony" : "&cwylaczony") + "&6."));
        }
        return true;
    }
            
    

}