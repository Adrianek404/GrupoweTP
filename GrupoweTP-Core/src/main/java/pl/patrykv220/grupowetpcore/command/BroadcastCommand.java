package pl.patrykv220.grupowetpcore.command;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;


import pl.yspar.core.basic.User;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;

public class BroadcastCommand extends Command
{
    public BroadcastCommand() {
        super("$bcproxy", "informacje o gildii", "/incognito", "core.cmd.user", new String[0] );
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
    	User user = User.get(args[0]);
            final boolean b = false;
            Util.sendMessage(user.getPlayer(), ChatUtil.fixColor("{MESSAGE}".replace("{MESSAGE}", StringUtils.join((Object[])args, " ").replaceFirst(user.getName(), ""))));
            return b;

    }
    


}