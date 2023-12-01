package pl.patrykv220.grupowetpcore.command.guild;



import org.bukkit.entity.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.io.*;

import org.bukkit.plugin.*;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.utils.ChatUtil;


public class JoinCommand extends PlayerCommand
{
    public JoinCommand() {
        super("dolacz", "dolacza do gildii", "/dolacz <tag>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild guild = User.get(p).getGuild();
        if (guild != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cAktualnie jestes juz w gildii!");
        }
        if (args.length < 1) {
            ChatUtil.sendMessage((CommandSender)p, "&cZa malo argumentow");
            return false;
        }
        final Guild g = User.get(args[0]).getGuild();
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cGildia o takim tagu nie istnieje!");
        }
        if (!guild.isInvited(p.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cNie posiadasz zaproszenia do gildii " + g.getTag() + "!");
        }
        User u = User.get(p);
        g.addMember(u);
        g.removeInvited(p.getName());
        Bukkit.broadcastMessage(ChatUtil.fixColor("&aGildie &8★ &7Gracz &f" + p.getName() + " &7dolaczyl do gildii &8[&2" + g.getTag() + "&8] &a" + g.getName() + "&7!"));
        return false;
    }
}

