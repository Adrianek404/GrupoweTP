package pl.patrykv220.grupowetpcore.command.guild;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.patrykv220.grupowetpcore.command.PlayerCommand;
import pl.patrykv220.grupowetpcore.manager.GuildManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;

public class GuildHelpCommand extends PlayerCommand
{
    public GuildHelpCommand() {
        super("gildie", "info o gildiach", "/gildie", "core.cmd.user", new String[] { "gildiepomoc", "g", "gildia" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
            ChatUtil.sendMessage((CommandSender)p, "&8&m-[&a&m------&e&l &2&lGildie &a&m------&8&m]-");
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/zaloz <tag> <nazwa> &8- &fZaklada nowa gildie");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/dolacz <tag/nazwa> &8- &fdolaczasz do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/opusc &8- &fopuszczasz gildie");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/wyrzuc <nick> &8- &fwyrzuca gracza z gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/zapros <nick> &8- &fzaprasza gracza do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/zastepca <nick> &8- &fzmienia zastepce gildii");
            ChatUtil.sendMessage((CommandSender)p, "&r &8» &7/ginfo <gildia> &8- &finformacje o gildii");
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, " &8&l»  &7Liczba gildii (tryb)&8: &f" + GuildManager.getGuilds().size());
            ChatUtil.sendMessage((CommandSender)p, "");
            return ChatUtil.sendMessage((CommandSender)p, "&8&m-[&a&m------&e&l &2&lGildie &a&m------&8&m]-");
        }
}
