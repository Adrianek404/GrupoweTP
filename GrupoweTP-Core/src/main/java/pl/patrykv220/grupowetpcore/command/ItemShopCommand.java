package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.Util;


public class ItemShopCommand extends Command
{
    public ItemShopCommand() {
        super("is", "informacje o gildii", "/incognito", "core.cmd.admin", new String[0]);
    }
    

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
    	 if (args.length < 2) {

             ChatUtil.sendMessage(sender, "   &2&m----&8&m]-&a&l GTP &8&m]-&2&m----"); 
             ChatUtil.sendMessage(sender, " "); 
             ChatUtil.sendMessage(sender, " &aVIP");
             ChatUtil.sendMessage(sender, " &aSVIP");
             ChatUtil.sendMessage(sender, " &aYT &7( Moze nadac tylko admin )");
             ChatUtil.sendMessage(sender, " &aUNBAN");
             ChatUtil.sendMessage(sender, " ");
             ChatUtil.sendMessage(sender, " ");
             ChatUtil.sendMessage(sender, " &aAby nadac komus &7/is <usluga> <nick>");
             ChatUtil.sendMessage(sender, " ");
             ChatUtil.sendMessage(sender, "   &2&m----&8&m]-&a&l GTP &8&m]-&2&m----"); 
            
             return true;
         }
         if (args[0].equalsIgnoreCase("vip")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aGracz &7" + args[1] + " &azakupił usługę: &6VIP"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aDziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &awww.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("pex user " + args[1] + " group set vip"));
             return true;
         }
         if (args[0].equalsIgnoreCase("svip")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aGracz &7" + args[1] + " &adostał range: &6SVIP"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aDziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &awww.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("pex user " + args[1] + " group set svip"));
             return true;
         }

         if (args[0].equalsIgnoreCase("unban")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aGracz &7" + args[1] + " &azakupił usługę: &7Unban"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aDziekujemy za wsparcie naszego serwera!"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &cStrona internetowa: &awww.servercreators.pl"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
          	Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("unban " + args[1]));
             return true;
         }
         if (args[0].equalsIgnoreCase("yt")) {

        	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aGracz &7" + args[1] + " &azostaje nowym &fYou&4Tuberem"));
         	   Bukkit.broadcastMessage(Util.fixColor(" &8» &aDziekujemy za wsparcie naszego serwera!"));
          	 Bukkit.broadcastMessage(Util.fixColor("&8&m-----&a Item&fShop &8&m-----"));
           
          	 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("pex user " + args[1] + " group set yt"));
             return true;
         }
		return false;
    }
            
    

}