package pl.patrykv220.grupowetpcore.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import pl.yspar.core.Config;
import pl.yspar.core.basic.User;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;



public class PlayerCommandPreprocessListener implements Listener {
	private static Map<Player, Long> time;

	public static void quit(final Player player) {
		if (!PlayerCommandPreprocessListener.time.containsKey(player)) {
			return;
		}
		PlayerCommandPreprocessListener.time.remove(player);
	}

	@EventHandler
	public void onCommand(final PlayerCommandPreprocessEvent e) {
		final Player player = e.getPlayer();
		final User u = User.get(player);
		final String pcmd = e.getMessage();
		final String[] s2 = e.getMessage().split(" ");
        if (!e.isCancelled()) {

            String cmd = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            if (topic == null) {
                player.sendMessage(ChatUtil.fixColor("&4[!] &cKomenda (" + cmd + ") nie istnieje."));
                e.setCancelled(true);
            }
        }
		if (e.getMessage().isEmpty() || e.getMessage() == null) {
			return;
		}
		if (!player.hasPermission("Core.antilogout.bypass")
				&& PlayerAntyLogoutListener.antiRelog.containsKey(player.getUniqueId())
				&& Config.ANTILOGOUT_BLOCKED_COMMANDS.contains(s2[0].toLowerCase())) {
			final Player player4 = player;
			e.setCancelled(true);
			player4.sendMessage(Util.fixColor("&4[!] &cKomenda jest zablokowana podczas walki."));
			return;
		}
		
	}

	static {
		PlayerCommandPreprocessListener.time = new HashMap<Player, Long>();
	}
}
