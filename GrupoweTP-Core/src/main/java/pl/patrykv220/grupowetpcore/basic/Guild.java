package pl.patrykv220.grupowetpcore.basic;

import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;


import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.manager.GuildManager;
import pl.patrykv220.grupowetpcore.manager.RankingManager;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.store.MySQLEntry;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.RelationType;
import pl.patrykv220.grupowetpcore.utils.StringUtil;

import java.sql.*;
import org.bukkit.command.*;
import java.util.*;
import java.util.Date;

import org.bukkit.*;

public class Guild extends MySQLEntry {
    private String tag;
    private String name;
	private List<User> members;
    private User owner;
    private boolean pvp;
    private int points;
    private int kills;
    private int deaths;
	private Map<String, BukkitTask> invitedGuilds;
	private Map<String, BukkitTask> invited;
    
    public Guild(final String tag, final String name, final User owner) {
        this.tag = tag;
        this.name = name;
        this.members = new ArrayList<User>();
        this.points = 1000;
        this.deaths = 0;
        this.kills = 0;
		this.addMember(this.owner = owner);
		GuildManager.add(this);
		this.changes();
		RankingManager.addRanking(this);
		this.addMember(this.owner = owner);
		GuildManager.add(this);

		this.changes();


    }
	public List<User> getMembers() {
		return this.members;
	}
	
	public void sendMembers(final String text) {
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = this.members.iterator();
		while (iterator.hasNext()) {
			final Player player;
			if ((player = iterator2.next().getPlayer()) != null) {
				player.sendMessage(text);
			}
			iterator = iterator2;
		}
	}
    

	public Guild(ResultSet rs) {
		this.members = new ArrayList<User>();
		this.invited = new HashMap<String, BukkitTask>();
		try {
			this.tag = rs.getString("TAG");
			this.name = rs.getString("NAME");
			this.owner = User.get(rs.getString("OWNER"));
			this.members = UserManager.getUsers(StringUtil.fromString(rs.getString("MEMBERS")));
			this.points = rs.getInt("POINTS");
			this.deaths = rs.getInt("DEATHS");
			this.kills = rs.getInt("KILLS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (User us : this.members) {
			us.setGuild(this);
		}
		GuildManager.add(this);
		RankingManager.addRanking(this);
	}

    
    

	public String getSQL() {
		StringBuilder sb;
		(sb = new StringBuilder()).append("INSERT INTO `SC_GUILDS` VALUES(");
		sb.append("'%tag%',");
		sb.append("'%name%',");
		sb.append("'%owner%',");
		sb.append("'%members%',");
		sb.append("%points%,");
		sb.append("%deaths%,");
		sb.append("%kills%");
		sb.append(") ON DUPLICATE KEY UPDATE ");
		sb.append("NAME='%name%',");
		sb.append("OWNER='%owner%',");
		sb.append("MEMBERS='%members%',");
		sb.append("POINTS=%points%,");
		sb.append("DEATHS=%deaths%,");
		sb.append("KILLS=%kills%;");
		String s = StringUtil
				.replace(
						s = StringUtil.replace(s = StringUtil.replace(
								s = StringUtil.replace(

																		s = StringUtil.replace(s = sb.toString(),
																				"%tag%", this.tag),
																		"%name%", this.name),
																"%owner%", this.owner.getName()),
														"%members%",

														StringUtil.toString(UserManager.getNames(this.members))),

						"%points%", this.points);
		s = StringUtil.replace(s, "%deaths%", this.deaths);
		return s = StringUtil.replace(s, "%kills%", "'" + this.kills + "'");

	}
	
	public boolean isMember(final String name) {
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = this.members.iterator();
		while (iterator.hasNext()) {
			if (iterator2.next().getName().equalsIgnoreCase(name)) {
				return true;
			}
			iterator = iterator2;
		}
		return false;
	}
	
	public Map<String, BukkitTask> getInvitedGuilds() {
		return this.invitedGuilds;
	}
	
	public void removeInvited(final String player) {
		final String playerLower = player.toLowerCase();
		if (!this.invited.containsKey(playerLower)) {
			return;
		}
		this.invited.get(playerLower).cancel();
		this.invited.remove(playerLower);
	}
	
	public void setInvitedGuilds(final Map<String, BukkitTask> invitedGuilds) {
		this.invitedGuilds = invitedGuilds;
	}
	
	public void sendInfo(final CommandSender sender) {
		final Iterator<String> iterator2;
		Iterator<String> iterator = iterator2 = Arrays.asList(ChatUtil.fixColor("&6Czlonkowie: &7{MEMBERS}")).iterator();
		while (iterator.hasNext()) {
			String s = iterator2.next();
			final StringBuilder members = new StringBuilder();
			int i = 0;
			final Iterator<User> iterator4;
			Iterator<User> iterator3 = iterator4 = this.members.iterator();
			while (iterator3.hasNext()) {
				final User member = iterator4.next();
				if (i == 0) {
					members.append(member.isOnline() ? (ChatColor.GREEN + (member.isIncognito() ? ChatUtil.fixColor("&k") : "") + member.getName()) : member.getName());
				} else {
					members.append(String.valueOf(String.valueOf(ChatColor.getLastColors(s))) + ","
							+ (member.isOnline() ? (ChatColor.GREEN + (member.isIncognito() ? ChatUtil.fixColor("&k") : "") + member.getName()) : member.getName()));
				}
				++i;
				iterator3 = iterator4;
			}

			s = s.replace("{TAG}", this.tag)
					.replace("{MEMBERS}", members.toString());
			iterator = iterator2;
			sender.sendMessage(s);

		}
	}
	
	public boolean isMember(final User user) {
		return this.members.contains(user);
	}
    

    public String getTag() {
        return this.tag;
    }
    
    public String getName() {
        return this.name;
    }
    
	public boolean isInvited(final String player) {
		final String playerLower = player.toLowerCase();
		return this.invited.containsKey(playerLower);
	}
    
    
	public void addMember(final User user) {
		if (!this.members.contains(user)) {
			this.members.add(user);
		}
		this.changes();
	}
	
	public void addInvited(final String player) {
		final String playerLower = player.toLowerCase();
		if (this.invited.containsKey(playerLower)) {
			return;
		}
		this.invited.put(playerLower,
				Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin) CorePlugin.getPlugin(), (Runnable) new Runnable() {
					@Override
					public void run() {
						Guild.this.invited.get(playerLower).cancel();
						Guild.this.invited.remove(playerLower);
					}
				}, 600L));
	}
	
	public void removeMember(final User user) {
		if (this.members.contains(user)) {
			this.members.remove(user);
		}
		this.changes();
	}
    
	public static Guild get(final String tag) {
		final Iterator<Guild> iterator2;
		Iterator<Guild> iterator = iterator2 = GuildManager.getGuilds().iterator();
		while (iterator.hasNext()) {
			final Guild guild;
			if ((guild = iterator2.next()).getTag().equalsIgnoreCase(tag)) {
				return guild;
			}
			iterator = iterator2;
		}
		return null;
	}
	

	public static Guild get(final ResultSet rs) {
		final Iterator<Guild> iterator2;
		Iterator<Guild> iterator = iterator2 = GuildManager.getGuilds().iterator();
		while (iterator.hasNext()) {
			final Guild guild;

			try {
				if ((guild = iterator2.next()).getTag().equalsIgnoreCase(rs.getString("TAG"))) {
					return guild;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			iterator = iterator2;
		}
		return null;
	}


    

	
    
	public User getOwner() {
		return this.owner;
	}
    
    public void setOwner(final User owner) {
        this.owner = owner;
       
    }
    
    
    public boolean isPvp() {
        return this.pvp;
    }
    
    public void setPvp(final boolean pvp) {
        this.pvp = pvp;
        
    }
    
    public RelationType getRelationGuild(final Guild g) {
        if (g.equals(this)) {
            return RelationType.TEAM;
        }
        return RelationType.ENEMY;
    }
    

    
	public boolean isOwner(final User user) {
		return user.equals(this.owner);
	}

    

    
    public int getPoints() {
        return this.points;
    }

    
    public void setPoints(final int points) {
        this.points = points;
        
    }
    
    public int getKills() {
        return this.kills;
    }
    
    public void setKills(final int kills) {
        this.kills = kills;
        
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
        
    }
    
    public void addPoints(final int index) {
        this.setPoints(this.getPoints() + index);
    }
    
    public void removePoints(final int index) {
        this.setPoints(this.getPoints() - index);
    }
    
    public void addKills(final int index) {
        this.setKills(this.getKills() + index);
    }
    
    public void removeKills(final int index) {
        this.setKills(this.getKills() - index);
    }
    
    public void addDeaths(final int index) {
        this.setDeaths(this.getDeaths() + index);
    }
    
    public void removeDeaths(final int index) {
        this.setDeaths(this.getDeaths() + index);
    }
    
    
    public double getKd() {
        if (this.getKills() == 0 && this.getDeaths() == 0) {
            return 0.0;
        }
        if (this.getKills() > 0 && this.getDeaths() == 0) {
            return this.getKills();
        }
        if (this.getDeaths() > 0 && this.getKills() == 0) {
            return -this.getDeaths();
        }
        return ChatUtil.round(this.getKills() / this.getDeaths(), 2);
    }
    

    
    
   
}
