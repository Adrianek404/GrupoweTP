package pl.patrykv220.grupowetpcore.listener;

import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;


import pl.patrykv220.grupowetpcore.Config;
import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.Util;


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
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable(){
                    public void run(){
                    	p.teleport(Config.SPAWN);
                    }
                }, 5L);

    }
	
}
