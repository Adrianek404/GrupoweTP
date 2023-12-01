// 
// Decompiled by Procyon v0.5.36
// 

package pl.patrykv220.grupowetpcore.command;


import org.bukkit.Bukkit;

import org.bukkit.command.CommandSender;

import pl.yspar.core.manager.ChatManager;
import pl.yspar.core.utils.ChatUtil;


public class CcCommand extends Command
{
    public CcCommand() {
        super("chat", "Ustawienia chatu", "/chat <cc|on|off|lvl|slow>", "core.helper", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, "za malo argumentow");
        }
        final String s;
        switch ((s = args[0]).hashCode()) {
            case 3168: {
                if (!s.equals("cc")) {
                    return ChatUtil.sendMessage(sender, "&4Blad: &cChat jest wylaczony!");
                }
                for (int i = 0; i < 100; ++i) {
                    Bukkit.broadcastMessage("");
                }
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(" §8» §7Chat zostal §fwyczyszczony!");
                Bukkit.broadcastMessage(" §8» §7Administrator: §f" + sender.getName());
                Bukkit.broadcastMessage("");
                return ChatUtil.sendMessage(sender, " &8» &7Chat zostal wyczyszczony");
            }
            case 3551: {
                if (!s.equals("on")) {
                    return ChatUtil.sendMessage(sender, "&4Blad: &cChat jest wylaczony!");
                }
                if (ChatManager.enable) {
                    return ChatUtil.sendMessage(sender, "&4Blad: &cChat jest juz wlaczony!");
                }
                ChatManager.enable = true;
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(" §8» §7Chat zostal §awlaczony!");
                Bukkit.broadcastMessage(" §8» §7Administrator: §f" + sender.getName());
                Bukkit.broadcastMessage("");
                return ChatUtil.sendMessage(sender, " &8» &7Chat zostal wlaczony");
            }
            case 107554: {
                if (!s.equals("lvl")) {
                    return ChatUtil.sendMessage(sender, "&4Blad: &cChat jest wylaczony!");
                }
                break;
            }
            case 109935: {
                if (!s.equals("off")) {
                    return ChatUtil.sendMessage(sender, ChatUtil.fixColor("Błędne argumenty"));
                }
                if (!ChatManager.enable) {
                    return ChatUtil.sendMessage(sender, "&4Blad: &cChat jest wylaczony!");
                }
                ChatManager.enable = false;
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(" §8» §7Chat zostal §cwylaczony!");
                Bukkit.broadcastMessage(" §8» §7Administrator: §f" + sender.getName());
                Bukkit.broadcastMessage("");
                return ChatUtil.sendMessage(sender, " &8» &7Chat zostal wylaczony");
            }


        }
		return false;
    }
}
