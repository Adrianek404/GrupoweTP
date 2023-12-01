package pl.patrykv220.grupowetpcore.command;

import org.bukkit.command.*;
import org.bukkit.command.Command;
import org.bukkit.entity.*;

import pl.yspar.core.utils.ChatUtil;

public abstract class PlayerCommand extends Command
{
    public PlayerCommand(final String name, final String desc, final String usage, final String permission, final String... aliases) {
        super(name, desc, usage, permission, aliases);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof Player)) {
            return ChatUtil.sendMessage(sender, "&cTej komendy nie mozesz uzyc z poziomu konsoli!");
        }
        return this.onCommand((Player)sender, args);
    }
    
    public abstract boolean onCommand(final Player p0, final String[] p1);
}
