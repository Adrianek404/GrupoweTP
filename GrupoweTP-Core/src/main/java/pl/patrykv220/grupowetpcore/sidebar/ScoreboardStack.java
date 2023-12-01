package pl.patrykv220.grupowetpcore.sidebar;

import java.util.Stack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import pl.yspar.core.CorePlugin;
import pl.yspar.core.utils.ChatUtil;


public class ScoreboardStack implements Runnable {
	private static Stack<Scoreboard> stack;
	private static ScoreboardStack instance;

	@Override
	public void run() {
		this.fill();
	}

	private/* synthetic */void fill() {
		final int required = Bukkit.getMaxPlayers() * 2;
		if (ScoreboardStack.stack.size() < required) {
			final ScoreboardManager sm;
			if ((sm = Bukkit.getScoreboardManager()) == null) {
				return;
			}
			final int loop = Bukkit.getMaxPlayers() * 2
					- ScoreboardStack.stack.size();
			int i;
			int j = i = 0;
			while (j < loop) {
				final Stack<Scoreboard> stack = ScoreboardStack.stack;
				++i;
				stack.push(sm.getNewScoreboard());
				j = i;
			}
		}
	}

	public static int size() {
		return ScoreboardStack.stack.size();
	}

	public static ScoreboardStack getInstance() {
		if (ScoreboardStack.instance == null) {
			new ScoreboardStack();
		}
		return ScoreboardStack.instance;
	}

	public ScoreboardStack() {
		ScoreboardStack.instance = this;
		ScoreboardStack.stack = new Stack<Scoreboard>();
		this.fill();
	}

	static {
		ScoreboardStack.stack = new Stack<Scoreboard>();
	}

	public static Scoreboard pull() {
		return ScoreboardStack.stack.pop();
	}

	public void start() {
		Bukkit.getScheduler().runTaskTimer((Plugin) CorePlugin.getPlugin(),
				(Runnable) this, 0L, 20L);
	}
}
