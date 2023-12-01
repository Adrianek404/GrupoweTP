package pl.patrykv220.grupowetpcore.listener;

import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;


import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;


public class PlayerRespawnListener implements Listener {

	
	
    @EventHandler
    public void onEntityDamage(final PlayerRespawnEvent e) {
            final Player p = (Player)e.getPlayer();
                p.setHealth(20.0);
                p.setFoodLevel(20);
                p.setSaturation(20.0f);
                p.setFireTicks(0);
                UserManager.loadLobby(p);
  	        	ChatUtil.sendTitleMessage(p, Util.fixColor(p.getName()), Util.fixColor("&7Nie poddawaj się!"), 0, 20, 0);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CorePlugin.getPlugin(), new Runnable(){
                    public void run(){
                    	p.teleport(Config.SPAWN);
                    }
                }, 5L);

    }
	
}
