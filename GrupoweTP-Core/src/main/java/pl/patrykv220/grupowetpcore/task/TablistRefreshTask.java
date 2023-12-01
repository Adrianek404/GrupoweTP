package pl.patrykv220.grupowetpcore.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import pl.patrykv220.grupowetpcore.tab.TablistManager;


public class TablistRefreshTask
extends BukkitRunnable {
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p2 -> TablistManager.update(p2));
    }
}
