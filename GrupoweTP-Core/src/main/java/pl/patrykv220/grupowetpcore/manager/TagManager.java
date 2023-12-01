package pl.patrykv220.grupowetpcore.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardTeam;
import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.RelationType;
import pl.patrykv220.grupowetpcore.utils.RelationUtil;
import pl.patrykv220.grupowetpcore.utils.Util;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TagManager {
	private static Scoreboard scoreboard;

	public static void init() {
		scoreboard = new Scoreboard();
	}

	public static void createBoard(Player p) {
		try {
			ScoreboardTeam team = null;
			if (scoreboard.getPlayerTeam(p.getName()) == null) {
				team = scoreboard.createTeam(p.getName());
			}
			scoreboard.addPlayerToTeam(p.getName(), team.getName());
			team.setPrefix("");
			team.setDisplayName("");
			team.setSuffix("");
			PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(
					team, 0);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			Bukkit.getOnlinePlayers()
					.stream()
					.filter(pp -> pp != p)
					.forEach(
							pp -> ((CraftPlayer) pp).getHandle().playerConnection
									.sendPacket(packet));
			Bukkit.getOnlinePlayers()
					.stream()
					.filter(pp -> pp != p)
					.forEach(
							pp -> {
								ScoreboardTeam t = scoreboard.getTeam(pp
										.getName());
								PacketPlayOutScoreboardTeam packetShow = new PacketPlayOutScoreboardTeam(
										t, 0);
								((CraftPlayer) p).getHandle().playerConnection
										.sendPacket(packetShow);
							});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeBoard(Player p) {
		try {
			if (scoreboard.getPlayerTeam(Bukkit.getPlayer(p.getName())
					.getName()) == null) {
				return;
			}
			ScoreboardTeam team = scoreboard.getPlayerTeam(p.getName());
			scoreboard.removePlayerFromTeam(p.getName(), team);
			PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(
					team, 1);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			Bukkit.getOnlinePlayers()
					.stream()
					.filter(pp -> pp != p)
					.forEach(
							pp -> ((CraftPlayer) pp).getHandle().playerConnection
									.sendPacket(packet));
			Bukkit.getOnlinePlayers()
					.stream()
					.filter(pp -> pp != p)
					.forEach(
							pp -> {
								ScoreboardTeam t = scoreboard.getTeam(pp
										.getName());
								PacketPlayOutScoreboardTeam packetHide = new PacketPlayOutScoreboardTeam(
										t, 1);
								((CraftPlayer) p).getHandle().playerConnection
										.sendPacket(packetHide);
							});
			scoreboard.removeTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateBoard(Player p) {
		if (p != null) {
			ScoreboardTeam team = scoreboard.getPlayerTeam(p.getName());
			User user = User.get(p);
			if (team == null) {
				createBoard(p);
			}
			team.setDisplayName(" ");
			for (Player online : Bukkit.getOnlinePlayers()) {
				team.setPrefix(getValidPrefix(user, User.get(online.getName())));
				PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(
						team, 2);
				((CraftPlayer) online).getHandle().playerConnection
						.sendPacket(packet);
			}
		}	
	}



	public static String getValidPrefix(User get, User send) {
		User user = get;
		String tag = "";
        final Guild x = User.get(get).getGuild();
        if (user.isVanish()) {
			tag = ChatUtil.fixColor("&3VANISH &b");
			return tag;
        }
        if (x != null) {
			tag = ChatUtil.fixColor("&6(" + get.getGuild().getTag()
					+ ") &e" + (user.isIncognito() ? ChatUtil.fixColor("&k") : ChatUtil.fixColor("")));
			return tag;
		}
		return String.valueOf(tag) + (Object) ChatColor.GRAY + (user.isIncognito() ? ChatUtil.fixColor("&k") : ChatUtil.fixColor(""));
	}
}
