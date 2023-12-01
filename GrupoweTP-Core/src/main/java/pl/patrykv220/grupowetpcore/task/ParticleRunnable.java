package pl.patrykv220.grupowetpcore.task;

import org.bukkit.*;

import org.bukkit.entity.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.helper.ParticleHelper;

public class ParticleRunnable implements Runnable
{
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(online -> {
           User u = User.get(online.getPlayer());
            if (u.isParticles() && online.getPlayer().hasPermission("medpvp.particles")) {
                ParticleHelper.spawnParticle(online.getPlayer(), u.getParticleType().getParticle(), online.getPlayer().getLocation());
            }
        });
    }
}
