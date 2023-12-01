package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.Config;
import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;


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