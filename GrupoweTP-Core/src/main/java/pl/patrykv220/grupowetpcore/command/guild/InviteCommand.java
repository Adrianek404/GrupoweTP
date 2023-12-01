package pl.patrykv220.grupowetpcore.command.guild;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.command.PlayerCommand;
import pl.patrykv220.grupowetpcore.manager.GuildManager;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;

public class InviteCommand extends PlayerCommand
{
    public InviteCommand() {
        super("zapros", "zaprasza gracza do gildii", "/zapros <gracz>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = User.get(p).getGuild();
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cNie posiadasz gildii!");
        }
        if (args.length < 1) {
            ChatUtil.sendMessage((CommandSender)p, "&cZa malo argumentow");
            return false;
        }
        final User o = User.get(args[0]);
		final Player invited =  Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cGracz nie istnieje!");
        }
        if (o.getPlayer() == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cGracz jest offline!");
        }
        final Guild go = User.get(o.getPlayer()).getGuild();
        if (go != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cGracz posiada juz gildie!");
        }
		if (g.isInvited(invited.getName())) {
			g.removeInvited(invited.getName());
            ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&6Zaproszenie do gildii &c" + g.getTag() + " &6zostalo cofniete przez &c" + p.getName() + "&6!");
            return ChatUtil.sendMessage((CommandSender)p, "&6Cofnieto zaproszenie do gildii dla gracza &c" + o.getName() + "&6!");
        }
        g.addInvited(invited.getName());
        ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&6Zostales zaproszony do gildii &c" + g.getTag() + " &6przez &c" + p.getName() + "&6!");
        ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&6Wpisz &c/dolacz " + g.getTag() + "&6, aby dolaczyc do gildii.");
        return ChatUtil.sendMessage((CommandSender)p, "&aWyslales zaproszenie do gildii dla gracza &6" + o.getName() + "&a!");
    }
}

