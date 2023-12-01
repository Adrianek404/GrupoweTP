package pl.patrykv220.grupowetpcore.command;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import pl.yspar.core.Config;
import pl.yspar.core.basic.User;
import pl.yspar.core.gui.SaveEqGui;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;

import org.apache.commons.lang.*;


public class SaveEqCommand extends PlayerCommand
{
    public SaveEqCommand() {
        super("saveeq", "zapisywanie eq gracza", "/saveeq", "saveeq.use", new String[] { "zapiszeq"});
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	User u = User.get(p);
    	if (u.isSaveEQ() == false) {
    		if (p.getWorld().getName().equals("world")) {
    			u.setSaveEQ(true);
    			p.updateInventory();
    			ChatUtil.sendMessage(p, "&aUkładasz eq, jesli juz ułożysz kliknij na odpowiednią tabliczke.");
    			UserManager.loadItems(p);
    			p.teleport(Config.SAVEEQ);
    			return false;
    		}
    	}

		ChatUtil.sendMessage(p, "&4[!] &cAktualnie układasz EQ.");

		return false;
    }
}
            



