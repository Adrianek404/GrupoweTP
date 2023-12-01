package pl.patrykv220.grupowetpcore.task;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.TitleAPI;
import pl.patrykv220.grupowetpcore.utils.Util;



public class ActionTask extends BukkitRunnable {

    public void run() {
  
        for (final Player p : Bukkit.getOnlinePlayers()) {
            User u = User.get(p);
            if (u.isVanish()) {
            	ChatUtil.sendActionBar(p, Util.fixColor("&8-» &7Vanish: &aWlaczony &8«-"));
            	continue;
            }
        }	
    }
    

}