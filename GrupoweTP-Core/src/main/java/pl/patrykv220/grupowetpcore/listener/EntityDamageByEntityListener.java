package pl.patrykv220.grupowetpcore.listener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.manager.DataManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;


public class EntityDamageByEntityListener implements Listener {


	
	    @EventHandler
	    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
	    if (event.getDamager() instanceof Snowball) {
	    	Snowball snowball = (Snowball) event.getDamager();
	    	Entity hitBySnowball = event.getEntity();
	    	LivingEntity shooter = (LivingEntity) snowball.getShooter();
	    	if (hitBySnowball instanceof Player) {
	    		Player player = (Player) hitBySnowball;
	    		BigDecimal bigDecimal = new BigDecimal(player.getHealth()/2.0);
	    		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
	    		double health = bigDecimal.doubleValue();
	    		User u = User.get(player);
	    		ChatUtil.sendMessage(shooter, "&cGracz " + (u.isIncognito() ? "&c&k" : "&c") + player.getName() + "&c " + health + " ❤");
	    		return;
	    
	    		}
	    	}
	    }
	    
	    @EventHandler
	    public void onFoodLeveChange(FoodLevelChangeEvent e) {
	    	
	    	if (e.getEntity().getWorld().getName().equals("gtp")) {
	    		return;
	    	}
	        e.setFoodLevel(((Player) e.getEntity()).getPlayer().getFoodLevel());
	    }
	     
	    
	    @EventHandler
	    public void onDamage(EntityDamageEvent event){
	    	if (event.getEntity().getWorld().getName().equals("gtp")) {
	    		return;
	    	}

	    	if (event.getEntity().getType() != EntityType.PLAYER)
	    		return;

	    	if (event.getCause() == DamageCause.FALL){
	    		event.setCancelled(true);
	    	}

	    }

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity().getWorld().getName().equals("world")) {
	        e.setCancelled(true);
	        return;
		}
		if (!(e.getDamager() instanceof Player)
				|| !(e.getEntity() instanceof Player)) {
			return;
		}
		Player playerUser = (Player) e.getEntity();
		Player damager = (Player) e.getDamager();
		if (damager.equals((Object) (e.getEntity()))) {
			return;
		}
		User damagerUser = User.get(damager);
		User user = User.get(playerUser);
		if (!damagerUser.hasGuild()) {
			return;
		}
		if (!user.hasGuild()) {
			return;
		}
		Guild damagerGuild = damagerUser.getGuild();
		if (damagerGuild.isMember(User.get(playerUser))) {
			if (damagerGuild.isPvp()) {
				return;
			}
			e.setCancelled(true);
			damager.sendMessage(ChatUtil.fixColor("&4[!] &cPvp w gildii jest wylaczone"));
			return;
		}
	}
}
