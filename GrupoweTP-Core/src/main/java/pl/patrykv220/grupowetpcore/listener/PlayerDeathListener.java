package pl.patrykv220.grupowetpcore.listener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.StringUtil;
import pl.yspar.core.utils.TitleAPI;
import pl.yspar.core.utils.Util;



public class PlayerDeathListener implements Listener
{
	
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event1){
        Player player = event1.getPlayer();
        Entity entity = event1.getRightClicked();
        Player t = (Player)entity;
        final User user = User.get(player);
        final User usere = User.get(t);
    	int add = (int)(300.0 + (user.getPoints() - ((usere != null) ? usere.getPoints() : 0)) * -0.25);
        if (add <= 0) {
            add = 0;
        }
        final int remove = add / 4 * 3;
        if(player.isSneaking()) {
            if(entity instanceof Player == true){
            		ChatUtil.sendActionBar(player, ChatUtil.fixColor("&7Za gracza &a" + (usere.isIncognito() ? "&a&k" : "&a") + t.getDisplayName() + "&7 dostaniesz: &2+&7" + add +"&8, &7Stracisz: &c-&7" + remove));
            		return;

            	}
            }
    }
   
    
	  @EventHandler
	    public void onDeath(PlayerRespawnEvent e) {
		  final Player p = e.getPlayer();
		  User u = User.get(p);
		  u.setKs(0);
		  return;
	  }
    
	  @EventHandler
	    public void onDeath(PlayerDeathEvent e) {
	        PlayerDeathEvent playerDeathEvent = e;
	        playerDeathEvent.setDeathMessage(null);
	        final Player player = playerDeathEvent.getEntity();
	        World w = player.getWorld();
	        w.strikeLightningEffect(player.getLocation());
	        Player killer = player.getKiller();
	        if (PlayerAntyLogoutListener.antiRelog.containsKey(player.getUniqueId())) {
	            PlayerAntyLogoutListener.antiRelog.remove(player.getUniqueId());
	        }

	        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CorePlugin.getPlugin(), new Runnable(){

	            @Override
	            public void run() {
            User userKiller = User.get(killer.getName());
            User userPlayer = User.get(player.getName());
            if (userPlayer.getLastDamage() < System.currentTimeMillis()) {
            	userPlayer.getDamage().clear();
            }
            List<Util.Damage> damage = Util.sort(userPlayer.getDamage());
            if (killer == null || !(killer instanceof Player)) {
                if (damage == null || damage.isEmpty()) {
                    return;
                }
            }

            if (player.equals((Object)killer)) {
                return;
            }
            if (player.getAddress().getHostString().equals(killer.getAddress().getHostString())) {
    			  userKiller.removePoints(50);
    			  userPlayer.removePoints(50);
                String message2 = ChatUtil.fixColor("{KTAG}&6{KINCOGNITO}&6{KILLER} &8(&c-&750&8) &czabił gracza &6Mulciaka &8(&c-&750&8)") ;
                message2 = StringUtil.replace(message2, "{PLAYER}", userPlayer.getName());
                message2 = StringUtil.replace(message2, "{KILLER}", userKiller.getName());
                message2 = StringUtil.replace(message2, "{PINCOGNITO}", (userPlayer.isIncognito() ? ChatUtil.fixColor("&c&k") : ChatUtil.fixColor("&c")));
                message2 = StringUtil.replace(message2, "{KINCOGNITO}", (userKiller.isIncognito() ? ChatUtil.fixColor("&c&k") : ChatUtil.fixColor("&c")));
                message2 = StringUtil.replace(message2, "{PTAG}", userPlayer.hasGuild() ? StringUtil.replace(ChatUtil.fixColor(" &8[&c{TAG}&8] "), "{TAG}", userPlayer.getGuild().getTag()) : "");
                message2 = StringUtil.replace(message2, "{KTAG}", userKiller.hasGuild() ? StringUtil.replace(ChatUtil.fixColor(" &8[&c{TAG}&8] "), "{TAG}", userKiller.getGuild().getTag()) : "");
    			Bukkit.broadcastMessage(message2);

                TitleAPI.sendTitle(killer, 0, 50, 0, ChatUtil.fixColor("&cZabójstwo błąd"), ChatUtil.fixColor("&cZabiłeś swojego &6mulciaka &8(&c-&750&8)"));
                return;
            }
            UUID lastKill = userKiller.getLastKiller();
            if (player.getUniqueId().equals(lastKill)) {
                String message2 = ChatUtil.fixColor("{KTAG}&6{KINCOGNITO}{KILLER} &8(&a+&750&8) &czabił gracza &6{PINCOGNITO}{PLAYER} &8(&c-&750&8)") ;
                message2 = StringUtil.replace(message2, "{PLAYER}", userPlayer.getName());
                message2 = StringUtil.replace(message2, "{KILLER}", userKiller.getName());
                message2 = StringUtil.replace(message2, "{PINCOGNITO}", (userPlayer.isIncognito() ? ChatUtil.fixColor("&c&k") : ChatUtil.fixColor("&c")));
                message2 = StringUtil.replace(message2, "{KINCOGNITO}", ( userKiller.isIncognito() ? ChatUtil.fixColor("&c&k") : ChatUtil.fixColor("&c")));
                message2 = StringUtil.replace(message2, "{PTAG}", userPlayer.hasGuild() ? StringUtil.replace(ChatUtil.fixColor(" &8[&c{TAG}&8] "), "{TAG}", userPlayer.getGuild().getTag()) : "");
                message2 = StringUtil.replace(message2, "{KTAG}",  userKiller.hasGuild() ? StringUtil.replace(ChatUtil.fixColor(" &8[&c{TAG}&8] "), "{TAG}",  userKiller.getGuild().getTag()) : "");
    			Bukkit.broadcastMessage(message2);
    			userKiller.addKills(1);
    			userKiller.addPoints(50);
    			userPlayer.removePoints(50);
    			userPlayer.addDeaths(1);
            	TitleAPI.sendTitle(killer, 0, 50, 0, ChatUtil.fixColor("&cZabójstwo błąd"), ChatUtil.fixColor("&cZabiles ostatnio tego gracza, odczekaj chwile."));
                return;
            }
            if (player.equals((Object)killer)) {
                return;
            }
	                Player playerx = killer;
	                Guild guild1 = User.get(player).getGuild();
	                Guild guild2 = User.get(playerx).getGuild();

	                int add = (int)(300.0 + ( userKiller.getPoints() - ((userPlayer != null) ? userPlayer.getPoints() : 0)) * -0.25);
	                String asist = Util.getAsist(damage, killer.getName());
	                Player asistPlayer = asist == null ? null : Bukkit.getPlayer((String)asist);
	                userKiller.addKills(1);
	                userKiller.addPoints(add);
	                userKiller.addCoins(100);
	                userKiller.addKs(1);
	                userKiller.setLastKiller(player.getUniqueId());
	                userPlayer.addDeaths(1);
	                userPlayer.removePoints(add);
	      		  if (userKiller.getKs() > userKiller.getKsMax()) {
	      			userKiller.setKsMax(userKiller.getKs());
	    		  }
	    		  if (userKiller.getKs() == 5 || userKiller.getKs() == 10 || userKiller.getKs() == 15 || userKiller.getKs() == 20 || userKiller.getKs() == 25|| userKiller.getKs() == 30) {
	    			  Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l→ &6Gracz &c" + userKiller.getName() + " &6Zdobył &cKillStreak x" + userKiller.getKs()));

	    		  }
	    		  if (userKiller.getPoints() >= 2000) {
						ChatUtil.sendTitleMessage(killer.getPlayer(), "", ChatUtil.fixColor("&6Wymieniono ranking na prestiz &eGratulacje&6!"), 30, 70, 40);
						Bukkit.broadcastMessage(ChatUtil.fixColor("&b&lGWIAZDKA &8&l→ &6Gracz &7" + killer.getName() + " &6wymienił ranking na gwiazdke &7Gratulacje&6!"));
						userKiller.addPrestiz(1);
						userKiller.setPoints(1000);
						return;
	    		  }
	            
	                if (asistPlayer != null) {
	                	
	                    User asistUser = User.get((Player)asistPlayer);
	                   
	                    asistUser.addAsist();
	                    int asistPoints = add / 3;
	                    asistUser.addPoints(asistPoints);
	                    int killerPoints = add - asistPoints;
	                    userKiller.addPoints(killerPoints);
	                    userKiller.addKs(1);
	                    TitleAPI.sendTitle( userKiller.getPlayer(), 0, 50, 0, "&aZabójstwo", ChatUtil.fixColor("&a{PLAYER} &8(&a+&7{PKT}&8)".replace("{PLAYER}", player.getName()).replace("{PKT}", Integer.toString(killerPoints))));
	                    TitleAPI.sendTitle(asistUser.getPlayer(), 0, 50, 0, "&6Asysta", ChatUtil.fixColor("&e{PLAYER} &8(&f+&2{PKT}&8)".replace("{PLAYER}", player.getName()).replace("{PKT}", Integer.toString(asistPoints))));
	                    String message = ChatUtil.fixColor("&eAsystował&8: {ATAG} &6{ASIST} &8(&a+&7{A}&8)");
	                    message = StringUtil.replace(message, "{PLAYER}", userPlayer.getName());
	                    message = StringUtil.replace(message, "{KILLER}",  userKiller.getName());
	                    message = StringUtil.replace(message, "{ASIST}", asistUser.getName());
	                    message = StringUtil.replace(message, "{D}", Integer.toString(add));
	                    message = StringUtil.replace(message, "{K}", Integer.toString(killerPoints));
	                    message = StringUtil.replace(message, "{A}", Integer.toString(asistPoints));
	                    message = StringUtil.replace(message, "{PTAG}", userPlayer.hasGuild() ? StringUtil.replace("&8[&c{TAG}&8]", "{TAG}", userPlayer.getGuild().getTag()) : "");
	                    message = StringUtil.replace(message, "{KTAG}",  userKiller.hasGuild() ? StringUtil.replace("&8[&c{TAG}&8]", "{TAG}", userKiller.getGuild().getTag()) : "");
	                    message = StringUtil.replace(message, "{ATAG}", asistUser.hasGuild() ? StringUtil.replace("&8[&c{TAG}&8]", "{TAG}", asistUser.getGuild().getTag()) : "");
	        			Bukkit.broadcastMessage(message);
	                } else {
	                	
	                    BigDecimal bigDecimal = new BigDecimal(killer.getHealth()/2.0);
	                    bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
	                    double health = bigDecimal.doubleValue();
	        			String ks = "";
	        			if (userKiller.getKs() ==2){
	        				ks = "&8(&7Double kill&8) ";
	        			}
	        			if (userKiller.getKs() ==3){
	        				ks = "&8(&7Triple kill&8) ";
	        			}
	        			if (userKiller.getKs() ==4){
	        				ks = "&8(&7Quadra kill&8) ";
	        			}
	        			if (userKiller.getKs() ==5){
	        				ks = "&8(&7Penta kill&8) ";
	        			}
	        			if (userKiller.getKs() ==6){
	        				ks = "&8(&7Hexa kill&8) ";
	        			}
	        			if (userKiller.getKs() ==7){
	        				ks = "&8(&7Hepta kill&8) ";
	        			}
	                    TitleAPI.sendTitle(userKiller.getPlayer(), 40, 40, 40, ChatUtil.fixColor("&6Zabójstwo"), ChatUtil.fixColor("&c{PLAYER} &8(&a+&7{PKT}&8)").replace("{PLAYER}", player.getName()).replace("{PKT}", Integer.toString(add)));
	                    String message2 = ChatUtil.fixColor(ks + "{KTAG}&6{KINCOGNITO}&6{KILLER} &8(&a+&7{POINTS}&8) &cZabil gracza {PTAG}&6{PINCOGNITO}&6{PLAYER} &8(&c-&7{POINTS}&8) &8(&c" + health + "❤&8)") ;
	                    message2 = StringUtil.replace(message2, "{PLAYER}", userPlayer.getName());
	                    message2 = StringUtil.replace(message2, "{KILLER}", userKiller.getName());
	                    message2 = StringUtil.replace(message2, "{PINCOGNITO}", (userPlayer.isIncognito() ? ChatUtil.fixColor("&c&k") : ChatUtil.fixColor("&c")));
	                    message2 = StringUtil.replace(message2, "{KINCOGNITO}", (userKiller.isIncognito() ? ChatUtil.fixColor("&c&k") : ChatUtil.fixColor("&c")));
	                    message2 = StringUtil.replace(message2, "{POINTS}", Integer.toString(add));
	                    message2 = StringUtil.replace(message2, "{PTAG}", userPlayer.hasGuild() ? StringUtil.replace(ChatUtil.fixColor(" &8[&c{TAG}&8] "), "{TAG}", userPlayer.getGuild().getTag()) : "");
	                    message2 = StringUtil.replace(message2, "{KTAG}", userKiller.hasGuild() ? StringUtil.replace(ChatUtil.fixColor(" &8[&c{TAG}&8] "), "{TAG}", userKiller.getGuild().getTag()) : "");
	        			Bukkit.broadcastMessage(message2);
	                }


	                userPlayer.getDamage().clear();
	            }
	        });
	        Bukkit.getScheduler().runTaskLater((Plugin)CorePlugin.getPlugin(), () -> {
	            if (player.isOnline() && player.isDead()) {
	                return;
	            }
	        }
	        , 1);
	    }

	
	  
	  
	  
}

