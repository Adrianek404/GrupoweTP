package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.PrestizGui;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;


public class PrestizCommand extends PlayerCommand
{
    public PrestizCommand() {
        super("prestiz", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = User.get(p);
            if (u == null) {
                return true;
            }
            PrestizGui.open(p);
        }
        return true;
    }
            
    

}