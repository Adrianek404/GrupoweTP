package pl.patrykv220.grupowetpcore.command;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import org.apache.commons.lang.*;
import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.command.PlayerCommand;
import pl.patrykv220.grupowetpcore.manager.GuildManager;
import pl.patrykv220.grupowetpcore.manager.RankingManager;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;

public class ResetujRankingCommand extends PlayerCommand
{
    public ResetujRankingCommand() {
        super("rs", "informacje o gildii", "/gildia <tag>", "core.cmd.user", new String[] { "resetujranking", "resetrank" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
		User u = User.get(p);
		u.setPoints(1000);
		u.setDeaths(0);
		u.setKills(0);
		ChatUtil.sendMessage(p, "&aTwój ranking oraz statystyki zostały zrestartowane.");
		return false;
    }
    
}

