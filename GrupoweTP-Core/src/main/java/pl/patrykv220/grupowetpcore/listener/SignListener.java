package pl.patrykv220.grupowetpcore.listener;


import org.bukkit.command.*;
import org.bukkit.entity.*;

import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

import pl.yspar.core.Config;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.TagManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.tab.TablistManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.TitleAPI;
import pl.yspar.core.utils.Util;

import java.io.IOException;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class SignListener implements Listener
{

	
    @EventHandler
    public void interacSign(PlayerInteractEvent event) {
    	Player p = event.getPlayer();
    	User u = User.get(p);
    	if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
    		Block b = event.getClickedBlock();
    		if (b.getType() == Material.WALL_SIGN) {
    			Sign sign = (Sign) b.getState();
    			if (sign.getLine(1).equalsIgnoreCase(ChatUtil.fixColor("Aby zapisać EQ"))){

    				  p.teleport(Config.SPAWN);
    				  u.setSaveEQ(false);
    				  UserManager.loadLobby(p);;
    	          	  ChatUtil.sendTitleMessage(p, "", "&aZapisano ewkipunek.", 30, 70, 40);
						try {
							User.save(p);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	          	  return;
    				
    			}
    			if (sign.getLine(1).equalsIgnoreCase(ChatUtil.fixColor("Przywróc"))){
    				UserManager.loadItems(p);
  	          	  ChatUtil.sendTitleMessage(p, "", "&aWczytano standardowe przedmioty.", 30, 70, 40);
  	          	  return;
    				
    			}
    				
    		}
    	}
    }
}

