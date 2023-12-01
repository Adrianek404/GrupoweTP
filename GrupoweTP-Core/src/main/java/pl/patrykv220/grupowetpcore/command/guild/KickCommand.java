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


public class KickCommand extends PlayerCommand
{
    public KickCommand() {
        super("wyrzuc", "", "", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
		final User user;
		final Guild guild;

        if (args.length < 1) {
            ChatUtil.sendMessage((CommandSender)p, "&cZa malo argumentow");
            return false;
        }

		if ((guild = (user = User.get(p)).getGuild()) == null) {
			final boolean b2 = false;
			p.sendMessage(ChatUtil.fixColor("&cGracz nie ma gildii"));
			return b2;
		}
		if (!guild.isOwner(user)) {
			final boolean b3 = false;
			p.sendMessage(ChatUtil.fixColor("&cNie jestes liderem"));
			return b3;
		}
		final User kickUser;
		if ((kickUser = User.get(args[1])) == null || !guild.isMember(kickUser)) {
			p.sendMessage(ChatUtil.fixColor("&cGracz nie jest w twojej gildii"));
			return false;
		}
		if (guild.isOwner(kickUser)) {
			final boolean b4 = false;
			p.sendMessage(ChatUtil.fixColor("&cNie mozesz wyrzucic lidera"));
			return b4;
		}
        
        User u = User.get(p);
		user.setGuild(null);

		guild.removeMember(user);
        Bukkit.broadcastMessage(ChatUtil.fixColor("&aGildie &8★ &7Gracz &f" + user.getName() + " &7zostal wyrzucony z &8[&2" + guild.getTag() + "&8] &a" + guild.getName() + "&7!"));
        return false;
    }
}

