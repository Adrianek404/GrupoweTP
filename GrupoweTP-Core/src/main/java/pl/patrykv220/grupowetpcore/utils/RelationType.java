package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.ChatColor;

public enum RelationType {
    TEAM(ChatColor.GREEN),
    ALLY(ChatColor.GOLD),
    ENEMY(ChatColor.RED),
    WAR(ChatColor.DARK_RED);
    
    private final ChatColor color;

    public ChatColor getColor() {
        return this.color;
    }
    private RelationType(ChatColor color){
    	this.color = color;
    }

}