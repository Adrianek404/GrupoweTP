package pl.patrykv220.grupowetpcore.listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pl.yspar.core.basic.Damage;



public class PlayerAntyLogoutListener implements Listener {
	public static HashMap<UUID, Damage> antiRelog;

	static {
		PlayerAntyLogoutListener.antiRelog = new HashMap<UUID, Damage>();
	}

	public static Player getDamager(final EntityDamageByEntityEvent e) {
		final Entity damager;
		if ((damager = e.getDamager()) instanceof Player) {
			return (Player) damager;
		}
		final Projectile p;
		if (damager instanceof Projectile && (p = (Projectile) damager).getShooter() instanceof Player) {
			return (Player) p.getShooter();
		}
		return null;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public static void entityDamageByEntityListener(final EntityDamageByEntityEvent e) {
		if (!e.isCancelled() && e.getDamage() > 0.0 && e.getEntity() instanceof Player && getDamager(e) != null) {
			final Player p = (Player) e.getEntity();
			final Player d = getDamager(e);
			if (!p.equals(d)) {
				PlayerAntyLogoutListener.antiRelog.put(d.getUniqueId(), new Damage((Entity) p, 30L));
				PlayerAntyLogoutListener.antiRelog.put(p.getUniqueId(), new Damage((Entity) d, 30L));
			}
		}
	}
}
