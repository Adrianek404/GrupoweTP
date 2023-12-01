package pl.patrykv220.grupowetpcore.sidebar;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.yspar.core.CorePlugin;


public class Scheme {
	private static Scheme inst;
	public String[] schemeScoreboard;

	public String[] getSchemeSidebar() {
		return this.schemeScoreboard;
	}

	public Scheme() {
		(Scheme.inst = this).load();
	}

	public static Scheme getInst() {
		if (Scheme.inst == null) {
			new Scheme();
		}
		return Scheme.inst;
	}

	public void load() {
		int j;
		@SuppressWarnings("deprecation")
		List<String> lll = YamlConfiguration.loadConfiguration(
				CorePlugin.getPlugin().getResource("scheme.yml")).getStringList("scheme-scoreboard");
		this.schemeScoreboard = new String[lll.size()];
		int n = j = 0;
		while (n < lll.size()) {
			int n2 = j++;
			this.schemeScoreboard[n2] = ChatColor.translateAlternateColorCodes(
					(char) '.', (String) ((String) lll.get(n2)));
			n = j;
		}
	}
}
