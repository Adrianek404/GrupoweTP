package pl.patrykv220.grupowetpcore.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;

@SerializableAs("Player")
public class OfflineUser implements OfflinePlayer, ConfigurationSerializable {
	private static int type;
	private UUID uuid;
	private String name;
	private GameProfile profile;

	public Player getPlayer() {
		if (this.getName() == null) {
			return null;
		}
		return Bukkit.getPlayer(this.getName());
	}

	public String getUUID() {
		return this.uuid.toString();
	}

	public void setOp(final boolean b) {
		this.getReal().setOp(b);
	}

	public static OfflinePlayer deserialize(final Map<String, Object> args) {
		if (args.get("name") == null) {
			return null;
		}
		return (OfflinePlayer) new OfflineUser((String) args.get("name"));
	}

	public void setWhitelisted(final boolean value) {
		Bukkit.getWhitelistedPlayers().add(this);
	}

	@SuppressWarnings("deprecation")
	public OfflinePlayer getReal() {
		return Bukkit.getOfflinePlayer(this.getName());
	}

	private /* synthetic */ void init() {
		if (OfflineUser.type != 0) {
			return;
		}
		@SuppressWarnings("rawtypes")
		final Constructor[] constructors;
		final int length = (constructors = GameProfile.class.getConstructors()).length;
		int i;
		int j = i = 0;
		while (j < length) {
			@SuppressWarnings("rawtypes")
			final Constructor c;
			if (Arrays.equals((c = constructors[i]).getParameterTypes(), new Class[] { String.class, String.class })) {
				OfflineUser.type = 1;
			} else if (Arrays.equals(c.getParameterTypes(), new Class[] { UUID.class, String.class })) {
				OfflineUser.type = 2;
			} else {
				// System.out.println(TopBlocks.K("T5~1C&|2z8vtp;}'g&f7g;at};gtu;f:wu"));
			}
			j = ++i;
		}
	}

	public Server getServer() {
		return Bukkit.getServer();
	}

	public GameProfile getProfile() {
		return this.profile;
	}

	public OfflineUser(final String name) {
		this.name = name;
		this.uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
		this.init();
		try {
			if (OfflineUser.type == 1) {
				this.profile = GameProfile.class.getConstructor(String.class, String.class)
						.newInstance(this.uuid.toString(), name);
				return;
			}
			if (OfflineUser.type == 2) {
				this.profile = GameProfile.class.getConstructor(UUID.class, String.class).newInstance(this.uuid, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(final Object obj) {
		return obj instanceof ConfigurationSerializable
				&& ((Executable) obj).getName().equalsIgnoreCase(this.getName());
	}

	public boolean isBanned() {
		return this.getName() != null && Bukkit.getServer().getBannedPlayers().contains(this);
	}

	@SuppressWarnings("deprecation")
	public void setBanned(final boolean value) {
		this.getReal().setBanned(value);
	}

	public boolean isWhitelisted() {
		return Bukkit.getWhitelistedPlayers().contains(this);
	}

	public boolean isOnline() {
		return this.getPlayer() != null;
	}

	public boolean isOp() {
		return this.getReal().isOp();
	}

	@Override
	public String toString() {
		// return
		// String.valueOf(String.valueOf(this.getClass().getSimpleName())) +
		// TopBlocks.K("H\u0001F\u001dWi") + this.profile.getId() +
		// NumberUtil.K("\u001a");
		return "null";
	}

	public UUID getUniqueId() {
		return this.uuid;
	}

	public Location getBedSpawnLocation() {
		return this.getReal().getBedSpawnLocation();
	}

	@Override
	public int hashCode() {
		int hash = 5;
		return hash = 97 * hash + ((this.getUniqueId() != null) ? this.getUniqueId().hashCode() : 0);
	}

	public String getName() {
		return this.name;
	}

	public long getFirstPlayed() {
		final Player player;
		if ((player = this.getPlayer()) != null) {
			return player.getFirstPlayed();
		}
		return 0L;
	}

	public long getLastPlayed() {
		final Player player;
		if ((player = this.getPlayer()) != null) {
			return player.getLastPlayed();
		}
		return 0L;
	}

	public boolean hasPlayedBefore() {
		return this.getLastPlayed() != 0L;
	}

	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}
}
