package pl.patrykv220.grupowetpcore.command.guild;



import org.bukkit.entity.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.io.*;

import org.bukkit.plugin.*;


import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.command.PlayerCommand;
import pl.patrykv220.grupowetpcore.manager.GuildManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;


public class LeaveCommand extends PlayerCommand
{
    public LeaveCommand() {
        super("opusc", "dolacza do gildii", "/dolacz <tag>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Player player;
        final User user;
        final Guild guild;
        if ((guild = (user = User.get(player = (Player)p)).getGuild()) == null) {
            final boolean b = false;
            player.sendMessage(ChatUtil.fixColor("&4Blad: &cNie posiadasz gildii"));
            return b;
        }
        if (guild.isOwner(user)) {
            final boolean b2 = false;
            player.sendMessage(ChatUtil.fixColor("&4Blad: &cLider nie moze opuscic gildii"));
            return b2;
        }

        guild.removeMember(user);
        final Player player2 = player;
        user.setGuild(null);
        Bukkit.broadcastMessage(ChatUtil.fixColor("&aGildie &8★ &7Gracz &f" + p.getName() + " &7opuscil gildie &8[&2" + guild.getTag() + "&8] &a" + guild.getName() + "&7!"));
		return false;
    }
}

