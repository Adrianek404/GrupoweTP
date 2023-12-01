package pl.patrykv220.grupowetpcore.basic;

import org.bukkit.entity.Entity;

public class Damage
{
    public Entity damager;
    public long logoutBaseTime;
    public long time;
    
    public Damage(final Entity damager, final long logoutBaseTime) {
        this.time = System.currentTimeMillis();
        this.damager = damager;
        this.logoutBaseTime = logoutBaseTime;
    }
}
