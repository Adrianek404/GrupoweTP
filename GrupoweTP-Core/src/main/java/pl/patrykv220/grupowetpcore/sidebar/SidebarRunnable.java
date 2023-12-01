package pl.patrykv220.grupowetpcore.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import pl.patrykv220.grupowetpcore.Main;


public class SidebarRunnable implements Runnable {
	private static BukkitTask task;
	private static SidebarRunnable inst;

	@Override
	public void run() {
		Sidebar.globalUpdate();
	}


	public void start() {

		SidebarRunnable.task = Bukkit.getScheduler()
				.runTaskTimerAsynchronously((Plugin) Main.getPlugin(),
						(Runnable) this, 120L, 20L);
	}
}
