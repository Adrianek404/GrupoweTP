package pl.patrykv220.grupowetpcore.task;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import pl.yspar.core.Config;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.User;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ItemUtils;
import pl.yspar.core.utils.Util;



public class LimitRunnable extends BukkitRunnable {
	private static LimitRunnable inst;
	private static BukkitTask task;

	public LimitRunnable() {
		LimitRunnable.inst = this;
	}

	public void run() {
		final Iterator<Player> iterator2;
		Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
		while (iterator.hasNext()) {
			final Player player = iterator2.next();
			if (player.getHealth() >= 4) {
				final int kox = ItemUtils.getAmountOfItem(Material.GOLDEN_APPLE, player, (short) 1);
				final int ref = ItemUtils.getAmountOfItem(Material.GOLDEN_APPLE, player, (short) 0);
				final int lod = ItemUtils.getAmountOfItem(Material.PACKED_ICE, player, (short) 0);
				final int perla = ItemUtils.getAmountOfItem(Material.ENDER_PEARL, player, (short) 0);
				final int vip;
				final int maxKox = ((vip = (player.hasPermission("Core.limit.vip") ? 1 : 0)) != 0)
						? Config.LIMIT_KOX : Config.LIMIT_KOX;
				final int maxRef = (vip != 0) ? Config.LIMIT_REF : Config.LIMIT_REF;
				final int maxPerla = (vip != 0) ? Config.LIMIT_PEARL : Config.LIMIT_PEARL;
				User user = null;
				if (kox > maxKox) {
					final int toRemove = kox - maxKox;
					ItemUtils.remove(new ItemStack(Material.GOLDEN_APPLE, toRemove, (short) 1), player, toRemove);
					if (user == null) {
						user = User.get(player);
					}
					user.addSchowekKox(toRemove);
					player.sendMessage(Util.fixColor("&7Nadmiar koxow zostal odlozony do schowka.".replace("{MAX}", Integer.toString(maxKox))
							.replace("{MOVE-SIZE}", Integer.toString(toRemove))));
					ChatUtil.sendActionBar(player, ChatUtil.fixColor("&8&m---&2 Nadmiar przedmiotow odlozony do schowka. &8&m---"));
				}
				if (ref > maxRef) {
					final int toRemove = ref - maxRef;
					ItemUtils.remove(new ItemStack(Material.GOLDEN_APPLE, toRemove), player, toRemove);
					if (user == null) {
						user = User.get(player);
					}
					user.addSchowekRef(toRemove);
					player.sendMessage(Util.fixColor("&7Nadmiar refili zostal odlozony do schowka.".replace("{MAX}", Integer.toString(maxKox))
							.replace("{MOVE-SIZE}", Integer.toString(toRemove))));
					ChatUtil.sendActionBar(player, ChatUtil.fixColor("&8&m---&2 Nadmiar przedmiotow odlozony do schowka. &8&m---"));
				}
				if (lod > 16) {
					final int toRemove = lod - 16;
					ItemUtils.remove(new ItemStack(Material.PACKED_ICE, toRemove), player, toRemove);
					player.sendMessage(Util.fixColor("&7Nadmiar lodu zostal usuniety".replace("{MAX}", Integer.toString(maxKox))
							.replace("{MOVE-SIZE}", Integer.toString(toRemove))));

				}
				if (perla > maxPerla) {
					final int toRemove = perla - maxPerla;
					ItemUtils.remove(new ItemStack(Material.ENDER_PEARL, toRemove), player, toRemove);
					if (user == null) {
						user = User.get(player);
					}
					user.addSchowekPerla(toRemove);
					player.sendMessage(Util.fixColor("&7Nadmiar perel zostal odlozony do schowka.".replace("{MAX}", Integer.toString(maxKox))
							.replace("{MOVE-SIZE}", Integer.toString(toRemove))));
					ChatUtil.sendActionBar(player, ChatUtil.fixColor("&8&m---&2 Nadmiar przedmiotow odlozony do schowka. &8&m---"));
				}
				iterator = iterator2;
			}
		}
	}

	public static LimitRunnable getInst() {
		return LimitRunnable.inst;
	}

	public void start() {
		final BukkitScheduler scheduler = Bukkit.getScheduler();
		final CorePlugin inst = CorePlugin.getPlugin();
		final long n = 160L;
		LimitRunnable.task = scheduler.runTaskTimer((Plugin) inst, (BukkitRunnable) this, n, n);
	}
}
