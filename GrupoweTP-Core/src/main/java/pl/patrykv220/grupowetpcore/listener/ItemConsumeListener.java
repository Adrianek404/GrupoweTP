package pl.patrykv220.grupowetpcore.listener;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.TitleAPI;

public class ItemConsumeListener implements Listener{
	
	 @EventHandler(priority = EventPriority.LOW)
	public void ItemConsume(final PlayerItemConsumeEvent e) {
		 Player p = e.getPlayer();
         BigDecimal bigDecimal = new BigDecimal(p.getHealth()/2.0);
         bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
         double health = bigDecimal.doubleValue();
		 if (health <= 1) {
			 Bukkit.broadcastMessage(ChatUtil.fixColor("&cGracz " + p.getName() + " &czjadł na 0.5 ❤"));
			 TitleAPI.sendTitle(p, 0, 10, 0, "", ChatUtil.fixColor("&cFart! >.<"));
			 return;
			 
			 
		 }
		
	 }
}
