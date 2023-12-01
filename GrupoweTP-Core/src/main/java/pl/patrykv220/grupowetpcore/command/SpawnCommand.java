package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;


import pl.patrykv220.grupowetpcore.Config;
import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;


public class SpawnCommand extends PlayerCommand
{
    public SpawnCommand() {
        super("spawn", "informacje o gildii", "/incognito", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        p.teleport(Config.SPAWN);
        ChatUtil.sendMessage(p, "&aPomyślnie przeteleportowano.");
        UserManager.loadLobby(p);
		return false;
    }
          
    


}