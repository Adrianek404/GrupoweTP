package pl.patrykv220.grupowetpcore.listener;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.DataUtil;
import pl.patrykv220.grupowetpcore.utils.RelationType;
import pl.patrykv220.grupowetpcore.utils.RelationUtil;
import pl.patrykv220.grupowetpcore.utils.StringUtil;
import pl.patrykv220.grupowetpcore.utils.TimeUtil;
import pl.patrykv220.grupowetpcore.utils.Util;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;





public class AsyncPlayerChatListener implements Listener
{
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player player;
        final User user1 = User.get(player = e.getPlayer());
        if (user1.hasGuild()) {

            if (e.getMessage().startsWith("!")) {
                e.setCancelled(true);
                user1.getGuild().sendMembers(StringUtil.replace(StringUtil.replace(Util.fixColor("&2[DO GILDII] &6{PLAYER}&8: &7{MESSAGE}"), "{PLAYER}", player.getName()), "{MESSAGE}", e.getMessage().replaceFirst("!", "")));
               
                return;
            }
        }
        if (user1.hasGuild()) {
            if (e.getMessage().startsWith("@")) {
                e.setCancelled(true);
                String msg = ChatUtil.fixColor("&2GRACZ Z TWOJEJ GILDII LUB SOJUSZNICZEJ POTRZEBUJE POMOCY, NICK GRACZA: &a" + player.getName() + " &8(&fKoordynaty: &7X: " + player.getLocation().getBlockX() + " " + " Z:" + player.getLocation().getBlockZ() + "&8)");
                final User user5 = user1;
                user5.getGuild().sendMembers(msg);
                return;
            }
        }
        if (e.getMessage().equals("&") || e.getMessage().contains("\n") ) {

                	ChatUtil.sendTitleMessage(player, "", ChatUtil.fixColor("&cWykryto nie dozwolone znaki w wiadomosci."), 0, 20, 0);
                	return;

        }
	    if ((!player.hasPermission("ignorecooldown")) && (!user1.isChat()))
	    {
	      ChatUtil.sendMessage(player, "&cNa czacie bedziesz mogl pisac dopiero za &7" + DataUtil.secondsToString(user1.getLastChat()) + "&c!");
	      e.setCancelled(true);
	      return;
	    }

		    user1.setLastChat(System.currentTimeMillis() + TimeUtil.SECOND.getTime(5));
            final String name = player.getName();
     
            String globalformat = "";
            String prefix = "";
            String suffix = "";
            final PermissionUser user = PermissionsEx.getUser(player);
            final String[] grups = user.getGroupNames();
            if (grups[0].toLowerCase().equals("root")) {
	            prefix = ChatUtil.fixColor("&4Root ");
	        }
            if (grups[0].toLowerCase().equals("admin")) {
	            prefix = ChatUtil.fixColor("&cAdmin ");
	        }
            if (grups[0].toLowerCase().equals("mod")) {
	            prefix = ChatUtil.fixColor("&2Mod ");
	        }
            if (grups[0].toLowerCase().equals("helper")) {
	            prefix = ChatUtil.fixColor("&3helper ");
	        }
            if (grups[0].toLowerCase().equals("vip")) {
	           prefix = ChatUtil.fixColor("&eVIP ");
	        }
            if (grups[0].toLowerCase().equals("yt")) {
	            prefix = ChatUtil.fixColor("&fY&CT ");
	        }
            if (grups[0].toLowerCase().equals("svip")) {
	            prefix = ChatUtil.fixColor("&6SVIP ");
	        }
	        else if (player.hasPermission("Core.chat.color")) {
                e.setMessage(Util.fixColor(e.getMessage()));
            }
            
		    String a1 = e.getMessage().replace("<3", ChatUtil.fixColor("&d❤&f"));
		    e.setMessage(a1);
            User u = User.get(player);
	        for (Player online : Bukkit.getOnlinePlayers()) {
	        	User onlineuser = User.get(online);
	        	RelationType type = RelationUtil.getRelation(u, onlineuser);
	        
            final String tag = u.hasGuild() ? (Object) type.getColor() + u.getGuild().getTag() + " " : "";
		    String globalFormat = Util.fixColor("{PRESTIZ}{POINTS}{TAG}{PREFIX}&7{PLAYER}&8: &f{MESSAGE}");
		    globalFormat = globalFormat.replace("{PREFIX}", prefix);
		    globalFormat = globalFormat.replace("{POINTS}", Util.fixColor("&7" + u.getPoints() + " "));
		    globalFormat = globalFormat.replace("{PRESTIZ}", Util.fixColor("&8(&f" + u.getPrestiz() + "&b✰&8) "));
		    globalFormat = globalFormat.replace("{PLAYER}", player.getName());
		    globalFormat = globalFormat.replace("{TAG}", tag);
		    globalFormat = globalFormat.replace("{MESSAGE}", e.getMessage());

		    
		    String adminFormat = Util.fixColor("{PRESTIZ}{POINTS}{TAG}{PREFIX}&7{PLAYER}&8: &f{MESSAGE}");
		    adminFormat = adminFormat.replace("{PREFIX}", prefix);
		    adminFormat = adminFormat.replace("{POINTS}", Util.fixColor("&7" + u.getPoints() + " "));
		    adminFormat = adminFormat.replace("{PRESTIZ}", Util.fixColor("&8(&f" + u.getPrestiz() + "&b✰&8) "));
		    adminFormat = adminFormat.replace("{PLAYER}", player.getName());
		    adminFormat = adminFormat.replace("{TAG}", tag);
		    adminFormat = adminFormat.replace("{MESSAGE}", e.getMessage());

        	if (player.hasPermission("chat.admin")) {
                e.setFormat(globalFormat);
                return;
        	}

        	e.setFormat(globalFormat);
            return;
	        
	        }

    }
    
    
    
    
  

    
}
