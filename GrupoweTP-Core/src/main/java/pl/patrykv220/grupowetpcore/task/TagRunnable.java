package pl.patrykv220.grupowetpcore.task;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;


import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.manager.AssistManager;
import pl.patrykv220.grupowetpcore.manager.DeathManager;
import pl.patrykv220.grupowetpcore.manager.KillManager;
import pl.patrykv220.grupowetpcore.manager.KsManager;
import pl.patrykv220.grupowetpcore.manager.RankingManager;
import pl.patrykv220.grupowetpcore.manager.TagManager;


public class TagRunnable implements Runnable {
	@Override
	public void run() {
		final Iterator<Player> iterator2;
		Iterator<Player> iterator = iterator2 = (Iterator<Player>) Bukkit
				.getOnlinePlayers().iterator();
		while (iterator.hasNext()) {
			TagManager.updateBoard(iterator2.next());
			RankingManager.sortUserRankings();
			RankingManager.sortGuildRankings();
			KsManager.sortGuildKills();
			AssistManager.sortGuildAssists();
			DeathManager.sortGuildDeaths();
			KillManager.sortGuildKills();
			iterator = iterator2;
		}
	}

	public void start() {
		final BukkitScheduler scheduler = Bukkit.getScheduler();

		final long n = 220L;
		final BukkitTask task = scheduler.runTaskTimerAsynchronously(
				(Plugin) CorePlugin.getPlugin(), (Runnable) this, n, n);
	}
}
