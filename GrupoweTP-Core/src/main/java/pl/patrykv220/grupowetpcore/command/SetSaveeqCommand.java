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


public class SetSaveeqCommand extends PlayerCommand
{
    public SetSaveeqCommand() {
        super("setsaveeq", "", "", "core.cmd.setspawn", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Location l = p.getLocation();
        p.getWorld().setSpawnLocation(l.getBlockX(), l.getBlockY(), l.getBlockZ());
        Config.setSaveeq(l);
        ChatUtil.sendMessage(p, "&aUstawiono spawn saveeq");
        return true;
    }
          
    


}