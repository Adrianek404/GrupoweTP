package pl.patrykv220.grupowetpcore.listener;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.patrykv220.grupowetpcore.Config;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.gui.ProfileGui;
import pl.patrykv220.grupowetpcore.gui.ShopGui;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.Util;




public class PlayerInteractListener implements Listener {
	

	public void onBlockInteract(PlayerInteractEvent event) {

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.DRAGON_EGG && event.getClickedBlock().getType() == Material.WORKBENCH && event.getClickedBlock().getType() == Material.CHEST && event.getClickedBlock().getType() == Material.ENDER_CHEST) {
				event.setCancelled(true);
			}
		}
		
	}
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void wyrzucanie(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
           if (player.getWorld().getName().equals("world")) {
        	   event.setCancelled(true);
           	
           }
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void niszczenie(BlockBreakEvent event) {
        Player player = event.getPlayer();
           if (player.getWorld().getName().equals("world") && !player.hasPermission("budowanie")) {
        	   event.setCancelled(true);
           	
           }
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void stawianie(BlockPlaceEvent event) {
        Player player = event.getPlayer();
           if (player.getWorld().getName().equals("world") && !player.hasPermission("budowanie")) {
        	   event.setCancelled(true);
           	
           }
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType().equals((Object) Material.EMERALD) && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Util.fixColor("&aSklep"))) {
			ShopGui.open(p);
			return;
			
		}
		if (p.getItemInHand().getType().equals((Object) Material.BOOK) && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Util.fixColor("&aZapisz EQ"))) {

    		ChatUtil.sendMessage(p, "&4[!] &cAby zapisac ekwipunek uzyj &6/zapiszeq");


			return;
			
		}
		if (p.getItemInHand().getType().equals((Object) Material.DIAMOND) && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Util.fixColor("&aProfil gracza"))) {
			ProfileGui.open(p);
			return;
			
		}

	}
}