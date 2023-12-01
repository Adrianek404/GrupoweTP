package pl.patrykv220.grupowetpcore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;


public class StringUtil {
	public static String replace(final String text, final String searchString, final int replacement) {
		return replace(text, searchString, Integer.toString(replacement));
	}

	public static String replace(final String text, final String searchString, final long replacement) {
		return replace(text, searchString, Long.toString(replacement));
	}

	public static String replace(final String text, final String searchString, final boolean replacement) {
		return replace(text, searchString, Boolean.toString(replacement));
	}

	public static String replace(final String text, final String searchString, String replacement) {
		if (text == null || text.isEmpty() || searchString.isEmpty()) {
			return text;
		}
		if (replacement == null) {
			replacement = "";
		}
		int start = 0;
		int max = -1;
		int end = text.indexOf(searchString, start);
		if (end == -1) {
			return text;
		}
		final int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = ((increase < 0) ? 0 : increase);
		increase *= ((max > 64) ? 64 : ((max < 0) ? 16 : max));
		final StringBuilder sb = new StringBuilder(text.length() + increase);
		while (end != -1) {
			sb.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = text.indexOf(searchString, start);
		}
		sb.append(text.substring(start));
		return sb.toString();
	}

	public static String toString(final Map<String, Integer> map) {
		if (map == null || map.isEmpty()) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		for (final Map.Entry<String, Integer> e : map.entrySet()) {
			sb.append(String.valueOf(e.getKey()) + ":" + e.getValue() + ",");
		}
		return sb.toString();
	}


	public static List<String> locationToString(final List<Location> locs) {
		final List<String> s = new ArrayList<String>();
		for (final Location l : locs) {
			s.add(locationToString(l));
		}
		return s;
	}

	public static List<Location> locationFromString(final List<String> locs) {
		final List<Location> s = new ArrayList<Location>();
		for (final String l : locs) {
			s.add(locationFromString(l));
		}
		return s;
	}

	public static String toStringSpace(final List<String> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); ++i) {
			if (i == 0) {
				sb.append(list.get(i));
			} else {
				sb.append(", " + list.get(i));
			}
		}
		return sb.toString();
	}

	public static String toString(final List<String> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); ++i) {
			if (i == 0) {
				sb.append(list.get(i));
			} else {
				sb.append("," + list.get(i));
			}
		}
		return sb.toString();
	}

	public static List<String> fromString(final String s) {
		final List<String> m = new ArrayList<String>();
		if (s == null || s.equalsIgnoreCase("")) {
			return m;
		}
		final String[] ss = s.split(",");
		String[] array;
		for (int length = (array = ss).length, i = 0; i < length; ++i) {
			final String sss = array[i];
			m.add(sss);
		}
		return m;
	}

	public static String replaceItems(String message, final List<ItemStack> items) {
		if (!message.contains("{ITEMS}")) {
			return message;
		}
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final ItemStack item : items) {
			if (i == 0) {
				sb.append(String.valueOf(Integer.toString(item.getAmount())) + " " + item.getType().toString());
			} else {
				sb.append(", " + Integer.toString(item.getAmount()) + " " + item.getType().toString());
			}
			++i;
		}
		message = replace(message, "{ITEMS}", sb.toString());
		return message;
	}

	public static Location locationFromString(final String msg) {
		if (msg == null || msg.equalsIgnoreCase("") || !msg.contains(",")) {
			return new Location((World) Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0);
		}
		final String[] m = msg.split(",");
		final World world = Bukkit.getWorld(m[0]);
		final double x = Double.parseDouble(m[1]);
		final double y = Double.parseDouble(m[2]);
		final double z = Double.parseDouble(m[3]);
		if (m.length <= 4) {
			return new Location(world, x, y, z);
		}
		final float yaw = Float.parseFloat(m[4]);
		final float pitch = Float.parseFloat(m[5]);
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static String locationLongToString(final Location loc) {
		if (loc == null) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(loc.getWorld().getName());
		sb.append("," + Double.toString(loc.getX()));
		sb.append("," + Double.toString(loc.getY()));
		sb.append("," + Double.toString(loc.getZ()));
		sb.append("," + Float.toString(loc.getYaw()));
		sb.append("," + Float.toString(loc.getPitch()));
		return sb.toString();
	}

	public static String locationToString(final Location loc) {
		if (loc == null) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(loc.getWorld().getName());
		sb.append("," + Double.toString(loc.getX()));
		sb.append("," + Double.toString(loc.getY()));
		sb.append("," + Double.toString(loc.getZ()));
		return sb.toString();
	}

	public static String kitsToString(final Map<String, Long> kits) {
		if (kits == null || kits.isEmpty()) {
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final Map.Entry<String, Long> e : kits.entrySet()) {
			if (i == 0) {
				sb.append(String.valueOf(e.getKey()) + ":" + e.getValue());
			} else {
				sb.append("," + e.getKey() + ":" + e.getValue());
			}
			++i;
		}
		return sb.toString();
	}

	public static Map<String, Long> stringToKits(final String string) {
		final Map<String, Long> kits = new HashMap<String, Long>();
		if (string == null || string.isEmpty()) {
			return kits;
		}
		final String[] ss = string.split(",");
		String[] array;
		for (int length = (array = ss).length, i = 0; i < length; ++i) {
			final String s = array[i];
			final String[] sss = s.split(":");
			final long delay = Long.parseLong(sss[1]);
			kits.put(sss[0], delay);
		}
		return kits;
	}

	public static String homesToString(final Map<String, Location> homes) {
		if (homes == null || homes.isEmpty()) {
			return "";
		}
		int i = 0;
		final StringBuilder sb = new StringBuilder();
		for (final Map.Entry<String, Location> e : homes.entrySet()) {
			if (i == 0) {
				sb.append(String.valueOf(e.getKey()) + ":" + locationLongToString(e.getValue()));
			} else {
				sb.append(";" + e.getKey() + ":" + locationLongToString(e.getValue()));
			}
			++i;
		}
		return sb.toString();
	}

	public static Map<String, Location> stringToHomes(final String string) {
		final Map<String, Location> homes = new HashMap<String, Location>();
		if (string == null || string.isEmpty()) {
			return homes;
		}
		final String[] sss = string.split(";");
		String[] array;
		for (int length = (array = sss).length, i = 0; i < length; ++i) {
			final String ss = array[i];
			final String[] s = ss.split(":");
			homes.put(s[0], locationFromString(s[1]));
		}
		return homes;
	}
	public static Map<String, Integer> fromStringMap(final String s) {
		final Map<String, Integer> map = new HashMap<String, Integer>();
		if (s == null || s.isEmpty()) {
			return map;
		}
		final String[] a = s.split(",");
		String[] array;
		for (int length = (array = a).length, i = 0; i < length; ++i) {
			final String d = array[i];
			final String[] w = d.split(":");
			if (NumberUtil.isInt(w[1])) {
				map.put(w[0], Integer.parseInt(w[1]));
			}
		}
		return map;
	}


}
