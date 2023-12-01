package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.User;
import pl.yspar.core.gui.ProfileGui;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;


public class SetSpawnCommand extends PlayerCommand
{
    public SetSpawnCommand() {
        super("setspawn", "informacje o gildii", "/incognito", "core.cmd.setspawn", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Location l = p.getLocation();
        p.getWorld().setSpawnLocation(l.getBlockX(), l.getBlockY(), l.getBlockZ());
        Config.setSpawn(l);
        ChatUtil.sendMessage(p, "&aUstawiono spawn");
        return true;
    }
          
    


}