package pl.patrykv220.grupowetpcore.listener;


import org.bukkit.command.*;
import org.bukkit.entity.*;

import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

import pl.yspar.core.Config;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.TagManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.tab.TablistManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.TitleAPI;
import pl.yspar.core.utils.Util;

import org.bukkit.*;

public class JoinQuitListener implements Listener
{
    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(final PlayerJoinEvent e) {
        e.setJoinMessage((String)null);
        final Player p = e.getPlayer();
        UserManager.joinPlayer(p);
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&8&m-[&7&m------&a&l GTP &7&m------&8&m]-");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, " &8&l» &7Połączono z&8: &2Grupowy teleport");
        ChatUtil.sendMessage((CommandSender)p, " &8&l» &7Graczy na trybie&8: &2" + Bukkit.getOnlinePlayers().size());
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&8&m-[&7&m------&a&l GTP &7&m------&8&m]-");
        final Guild x = User.get(p).getGuild();
        final User u = User.get(p);
        p.teleport(Config.SPAWN);
        TablistManager.executeCreate(p);
        TablistManager.update(p);
        UserManager.getJoinRank(p);
        UserManager.loadLobby(p);
        TitleAPI.sendTitle(p, 0, 40, 0, "", ChatUtil.fixColor("&8→&7→ &2" + Config.SERWERNAME + " &7←&8←"));
        if (u.isVanish() ==  true) {
            TitleAPI.sendTitle(p, 0, 30, 0, "", Util.fixColor("&6Vanish na tym trybie jest &aWlaczony"));
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (online.hasPermission("core.cmd.vanish")) {
                    continue;
                }
                online.hidePlayer(p);
            }
        }


    }
    
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Location loc = e.getPlayer().getLocation();
        e.setQuitMessage((String)null);
        final Player p = e.getPlayer();
        UserManager.quitPlayer(p);

        TablistManager.executeRemove(p);
        return;
    }
}

