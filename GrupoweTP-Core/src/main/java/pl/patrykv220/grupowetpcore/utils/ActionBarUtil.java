// 
// Decompiled by Procyon v0.5.30
// 

package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class ActionBarUtil extends Event
{
    private static final HandlerList handlers;
    private final Player player;
    private String message;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public ActionBarUtil(final Player player, final String message) {
        this.cancelled = false;
        this.player = player;
        this.message = message;
    }
    
    public HandlerList getHandlers() {
        return ActionBarUtil.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return ActionBarUtil.handlers;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
