package pl.patrykv220.grupowetpcore.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import pl.yspar.core.tab.TablistManager;


public class TablistRefreshTask
extends BukkitRunnable {
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p2 -> TablistManager.update(p2));
    }
}
