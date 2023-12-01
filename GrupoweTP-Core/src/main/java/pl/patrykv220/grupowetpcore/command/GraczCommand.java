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

public class GraczCommand extends PlayerCommand
{
    public GraczCommand() {
        super("gracz", "informacje o gildii", "/gildia <tag>", "core.cmd.user", new String[] { "player", "ranking" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
		if (args.length == 0) {
			return ChatUtil.sendMessage(p, "&4Błąd: &cBrakujace argumenty (/gracz <nick>).");
		}
			User u;
		if ((u = User.get(args[0])) == null) {
				return ChatUtil.sendMessage(p, "&4Błąd: &cBrak gracza.");
		}
		if (args.length >= 0) {
            final Guild guild = u.getGuild();
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&a&m------&2&l " + u.getName() + " &a&m------&8&m]-");
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&7Ranking: &a" + u.getPoints());
            ChatUtil.sendMessage((CommandSender)p, "&7Zabojstw: &a" + u.getKills());
            ChatUtil.sendMessage((CommandSender)p, "&7KillStreak: &a" + u.getKs());
            ChatUtil.sendMessage((CommandSender)p, "&7KillStreak rekord: &a" + u.getKsMax());
            ChatUtil.sendMessage((CommandSender)p, "&7Zgonow &a" + u.getDeaths());
            ChatUtil.sendMessage((CommandSender)p, "&7Asyst: &a" + u.getAsists());
            ChatUtil.sendMessage((CommandSender)p, "&7Prestiż: &a" + u.getPrestiz());
            ChatUtil.sendMessage((CommandSender)p, "&7K/D ratio: &a" + u.getKd());
            ChatUtil.sendMessage((CommandSender)p, "&7Gildia: &a" + ((guild == null) ? "Brak gildii" : guild.getTag()));
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&a&m------&2&l " + u.getName() + " &a&m------&8&m]-");
            return false;
		}
		return false;

    }
}
