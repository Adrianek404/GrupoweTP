package pl.patrykv220.grupowetpcore.command.guild;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class InfoCommand extends PlayerCommand
{
    public InfoCommand() {
        super("gildia", "informacje o gildii", "/gildia <tag>", "core.cmd.user", new String[] { "info", "ginfo" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            ChatUtil.sendMessage((CommandSender)p, "&cPoprawne uzycie: /info <tag>");
            return false;
        }
        final Guild g = Guild.get(args[0]);
        if (g == null && args.length == 0) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cNie posiadasz gildii!");
        }
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cGildia o takim tagu nie istnieje!");
        }

        final User u = User.get(p);
        if (args[0].equalsIgnoreCase(new StringBuilder().append(u.getGuild().getTag()).toString())) {
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l " + g.getTag() + " &2Twoja gildia &e&m------&8&m]-");
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&6Lider gildii: &7" + g.getOwner().getName());
            ChatUtil.sendMessage((CommandSender)p, "&6Pozycja: &7" + RankingManager.getPlaceGuild(g) + " &8| &6Ranking: &7" + g.getPoints() + " &8| &6Zabojstwa: &7" + g.getKills() + " &8| &6Zgony: &7" + g.getDeaths());
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendHoverMessage((CommandSender)p, "&6Ulepszenia gildii: &7(Kliknij tutaj)", "&6Aktualnie brak");
            ChatUtil.sendMessage((CommandSender)p, " ");
            g.sendInfo(p);
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&6Zdobyty respekt: &7Brak");
            ChatUtil.sendMessage((CommandSender)p, " ");
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l " + g.getTag() + " &2Twoja gildia &e&m------&8&m]-");
            return false;
        }
        ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l " + g.getTag() + " &cWroga gildia &e&m------&8&m]-");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&6Lider gildii: &7" + g.getOwner().getName());
        ChatUtil.sendMessage((CommandSender)p, "&6Pozycja: &7" + RankingManager.getPlaceGuild(g) + " &8| &6Ranking: &7" + g.getPoints() + " &8| &6Zabojstwa: &7" + g.getKills() + " &8| &6Zgony: &7" + g.getDeaths());
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendHoverMessage((CommandSender)p, "&6Ulepszenia gildii: &7(Kliknij tutaj)", "&6Aktualnie brak");
        ChatUtil.sendMessage((CommandSender)p, " ");
        g.sendInfo(p);
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&6Zdobyty respekt: &7Brak");
        ChatUtil.sendMessage((CommandSender)p, " ");
        ChatUtil.sendMessage((CommandSender)p, "&8&m-[&e&m------&6&l " + g.getTag() + " &cWroga gildia &e&m------&8&m]-");
        return false;
    }
    
    public static String[] getMemberList(final Set<String> members) {
        final String[] s = new String[members.size()];
        int i = 0;
        for (final String u : members) {
            final OfflinePlayer op = Bukkit.getOfflinePlayer(u);
            s[i] = "&7" + (op.isOnline() ? "&a" : "&7") + op.getName();
            ++i;
        }
        return s;
    }
}

