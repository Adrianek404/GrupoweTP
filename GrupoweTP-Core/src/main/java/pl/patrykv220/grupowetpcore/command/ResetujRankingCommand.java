package pl.patrykv220.grupowetpcore.command;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import org.apache.commons.lang.*;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.RankingManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;

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

