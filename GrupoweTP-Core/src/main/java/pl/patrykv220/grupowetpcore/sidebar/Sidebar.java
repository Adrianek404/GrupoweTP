package pl.patrykv220.grupowetpcore.sidebar;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import pl.yspar.core.Config;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.OfflineUser;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.StringUtil;



public class Sidebar
{
	  private String[] prefix;
	    private List<String> message;
	    private boolean disabled;
	    private String[] suffix;
	    private String[] ss;
	    private User user;
	    

	    public void update() {
	        int i;
	        Scoreboard sb = this.user.getScoreboard();
	        if (sb == null) {
	            return;
	        }
	        Objective ob = sb.getObjective("sc_SIDEBAR");

	        if (this.disabled) {
	            if (ob != null) {
	                ob.unregister();
	            }
	            return;
	        }
	        if (ob == null) {
	            this.create();
	        }
	        Sidebar sidebar = this;
	        sidebar.replace();
	        sidebar.split();
	        String[] scheme = Scheme.getInst().getSchemeSidebar();
	        int n = i = 0;
	        while (n < this.ss.length) {
	            Team team = sb.getTeam(scheme[i]);
	            if (team == null) {
	                team = sb.registerNewTeam(scheme[i]);
	                team.addPlayer((OfflinePlayer)new OfflineUser(scheme[i]));
	            }
	            team.setPrefix(this.prefix[i] == null ? "" : ChatUtil.fixColor(this.prefix[i]));
	            team.setSuffix(this.suffix[i] == null ? "" : ChatUtil.fixColor(this.suffix[i]));
	            n = ++i;
	        }
	    }

	    public boolean change() {
	        if (this.disabled) {
	            this.disabled = false;
	            this.update();
	            return true;
	        }
	        this.disabled = true;
	        this.update();
	        return false;
	    }


		private void split() {
			int i;
			int n = i = 0;
			while (n < this.ss.length) {
				Sidebar sidebar;
				String s = this.ss[i];
				String prefix = "";
				String suffix = "";
				if (s.length() <= 16) {
					Sidebar sidebar2 = this;
					sidebar = sidebar2;
					sidebar2.prefix[i] = s;
				} else {
					String color;
					prefix = s.substring(0, 16);
					int l = 16;
					if (prefix.charAt(15) == '\u00a7') {
						prefix = s.substring(0, 15);
						l = 15;
					}
					if ((color = ChatColor.getLastColors((String) ChatUtil
							.fixColor(prefix))) == null || color.isEmpty()) {
						color = "&f";
					}
					String string = l == 16 ? (suffix = String.valueOf(String
							.valueOf(color)) + s.substring(16)) : (suffix = String
							.valueOf(String.valueOf(color)) + s.substring(15));
					if (string.length() > 16) {
						suffix = ChatUtil.fixColor(suffix.substring(0, 16));
					}
					Sidebar sidebar3 = this;
					sidebar = sidebar3;
					sidebar3.prefix[i] = ChatUtil.fixColor(prefix);
				}
				sidebar.suffix[i++] = ChatUtil.fixColor(suffix);
				n = i;
			}
		}

	    public void restart() {
	        Scoreboard sb = this.user.getScoreboard();
	        if (sb == null) {
	            return;
	        }
	        Objective ob = sb.getObjective("sc_SIDEBAR");
	        if (ob != null) {
	            ob.unregister();
	        }
	        Sidebar sidebar = this;
	        sidebar.create();
	        sidebar.update();
	    }

	    public void setMessage(List<String> msg) {
	        this.message = msg;
	        this.restart();
	    }

	    public Sidebar(User user) {
	        Sidebar sidebar = this;
	        Sidebar sidebar2 = this;
	        sidebar2.disabled = false;
	        sidebar.user = user;
	        int i = Config.SIDEBAR_LIST.size();
	        sidebar2.message = Config.SIDEBAR_LIST;
	        sidebar.prefix = new String[i];
	        this.suffix = new String[i];
	        user.setSidebar(this);
	    }
	    
	

	    private void replace() {
			int i;
			this.ss = new String[this.message.size()];
			int n = i = 0;
			while (n < this.message.size()) {
				Calendar calendar;
				int n2;
				Sidebar sidebar;
				String msg = this.message.get(i);
				Calendar calendar2 = calendar = Calendar.getInstance();
				int secondi = calendar2.get(13);
				int minutei = calendar2.get(12);
				msg = StringUtil.replace(msg, "{ONLINE}", Bukkit.getOnlinePlayers()
						.size());
				msg = StringUtil.replace(msg, "{PLAYER}", this.user.getName());
				msg = StringUtil.replace(msg, "{POINTS}",
						Integer.toString(this.user.getPoints()));
				msg = StringUtil.replace(msg, "{KILLS}",
						Integer.toString(this.user.getKills()));
				msg = StringUtil.replace(msg, "{PRESTIZ}",
						Integer.toString(this.user.getPrestiz()));
				msg = StringUtil.replace(msg, "{KS}",
						Integer.toString(this.user.getKs()));
				msg = StringUtil.replace(msg, "{KSMAX}",
						Integer.toString(this.user.getKsMax()));
				msg = StringUtil.replace(msg, "{DEATHS}",
						Integer.toString(this.user.getDeaths()));
				msg = StringUtil.replace(msg, "{ASISTS}",
						Integer.toString(this.user.getAsyst()));
				msg = StringUtil.replace(msg, "{KD}",
						Double.toString(this.user.getKd()));
				msg = StringUtil.replace(msg, "{COINS}",
						Double.toString(this.user.getCoins()));
				msg = StringUtil.replace(msg, "{INCOGNITO}",
						(this.user.isIncognito() ? "&aWlaczone" : "&cWylaczone"));
				if (secondi < 10) {
					msg = StringUtil.replace(msg, "{SECOND}",
							"0" + Integer.toString(secondi));
					n2 = minutei;
				} else {
					msg = StringUtil.replace(msg, "{SECOND}",
							Integer.toString(secondi));
					n2 = minutei;
				}
				msg = StringUtil.replace(
						n2 < 10 ? (msg = StringUtil.replace(msg, "{MINUTE}", "0"
								+ Integer.toString(minutei)))
								: (msg = StringUtil.replace(msg, "{MINUTE}",
										Integer.toString(minutei))), "{HOUR}",
						Integer.toString(calendar.get(11)));
				if (this.user.hasGuild()) {
					Sidebar sidebar2 = this;
					sidebar = sidebar2;
					Guild g = sidebar2.user.getGuild();
					msg = StringUtil.replace(msg, "{GUILD}", g.getTag());
					msg = StringUtil.replace(msg, "{GPOINTS}",
							Integer.toString(g.getPoints()));
					msg = StringUtil.replace(msg, "{GDEATHS}",
							Integer.toString(g.getDeaths()));
					msg = StringUtil.replace(msg, "{GKILLS}",
							Integer.toString(g.getKills()));
					msg = StringUtil.replace(msg, "{GKD}",
							Double.toString(g.getKd()));
				} else {
					msg = StringUtil.replace(msg, "{GUILD}", "Brak");
					msg = StringUtil.replace(msg, "{GPOINTS}", "Brak");
					msg = StringUtil.replace(msg, "{GDEATHS}", "Brak");
					msg = StringUtil.replace(msg, "{GKILLS}", "Brak");
					msg = StringUtil.replace(msg, "{LIVES}", "Brak");
					msg = StringUtil.replace(msg, "{GKD}", "Brak");
					sidebar = this;
				}
				sidebar.ss[i++] = ChatUtil.fixColor(msg);
				n = i;
			}
		}

	    public static void globalUpdate() {
	        Iterator<User> iterator;
	        Iterator<User> iterator2 = iterator = UserManager.getOnline().iterator();
	        while (iterator2.hasNext()) {
	            iterator.next().getSidebar().update();
	            iterator2 = iterator;
	        }
	    }

	    public void create() {
	        int i;

	        this.prefix = new String[this.message.size()];
	        this.suffix = new String[this.message.size()];
	        Scoreboard sb = this.user.getScoreboard();
	        if (sb == null) {
	            return;
	        }
	    	Objective ob = sb.getObjective("sc_SIDEBAR");
			if (ob == null) {
				ob = sb.registerNewObjective("sc_SIDEBAR", "dummy");
	            ob.setDisplaySlot(DisplaySlot.SIDEBAR);
	        }
	        ob.setDisplayName(ChatUtil.fixColor(Config.SIDEBAR_HEADER));
	        String[] scheme = Scheme.getInst().getSchemeSidebar();
	        int ii = 0;
	        int n = i = 0;
	        while (n < this.message.size()) {
	            OfflineUser offlineUser = new OfflineUser(scheme[i]);
	            ob.getScore((OfflinePlayer)offlineUser).setScore(--ii);
	            n = ++i;
	        }
	        this.update();
	    }
	}

