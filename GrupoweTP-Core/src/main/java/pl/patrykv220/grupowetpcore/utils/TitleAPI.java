package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import pl.yspar.core.basic.User;

public class TitleAPI {
	public static void clearTitle(Player player) {
		TitleAPI.sendTitle(player, 0, 0, 0, "", "");
	}

	@SuppressWarnings("rawtypes")
	public static void sendTitle(Player player, Integer fadeIn, Integer stay,
			Integer fadeOut, String title, String subtitle) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(
				EnumTitleAction.TIMES, null, fadeIn.intValue(),
				stay.intValue(), fadeOut.intValue());
		connection.sendPacket((Packet) packetPlayOutTitle);
		if (subtitle != null) {
			IChatBaseComponent titleSub = ChatSerializer
					.a((String) ("{\"text\": \"" + subtitle + "\"}"));
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(
					EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket((Packet) packetPlayOutSubTitle);
		}
		if (title != null) {
			IChatBaseComponent titleMain = ChatSerializer
					.a((String) ("{\"text\": \"" + title + "\"}"));
			PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(
					EnumTitleAction.TITLE, titleMain);
			connection.sendPacket((Packet) packetPlayOutTitle2);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void sendTitle(User killerUser, Integer fadeIn, Integer stay,
			Integer fadeOut, String title, String subtitle) {
		Player p = (Player) killerUser;
		PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(
				EnumTitleAction.TIMES, null, fadeIn.intValue(),
				stay.intValue(), fadeOut.intValue());
		connection.sendPacket((Packet) packetPlayOutTitle);
		if (subtitle != null) {
			IChatBaseComponent titleSub = ChatSerializer
					.a((String) ("{\"text\": \"" + subtitle + "\"}"));
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(
					EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket((Packet) packetPlayOutSubTitle);
		}
		if (title != null) {
			IChatBaseComponent titleMain = ChatSerializer
					.a((String) ("{\"text\": \"" + title + "\"}"));
			PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(
					EnumTitleAction.TITLE, titleMain);
			connection.sendPacket((Packet) packetPlayOutTitle2);
		}
	}
}
