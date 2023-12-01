package pl.patrykv220.grupowetpcore.basic;

import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import pl.yspar.core.CorePlugin;
import pl.yspar.core.helper.ParticleType;
import pl.yspar.core.manager.AssistManager;
import pl.yspar.core.manager.DeathManager;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.KillManager;
import pl.yspar.core.manager.PrestizManager;
import pl.yspar.core.manager.RankingManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.sidebar.ScoreboardStack;
import pl.yspar.core.sidebar.Sidebar;
import pl.yspar.core.store.MySQLEntry;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.ReflectionUtil;
import pl.yspar.core.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.*;

public class User extends MySQLEntry {
    private String name;

    private int points;
    private int prestiz;
    private int kills;
    private int ks;
    private int ksMax;
    private int deaths;
    private int asyst;
	private int schowekKox;
	private String muteReason;
	private int schowekPerla;
	private int schowekRefil;
	private Sidebar sidebar;
	private String firstIp;
	private String ip;
    private Guild guild;
    private int coins;
    private boolean incognito;
    private boolean saveeq;
    private Map<String, Integer> shop;
	private long lastDamage;
	  private String lastPosition;
    private boolean particles;
    private boolean vanish;
    private long lastChat;
    private UUID lastKill;
	private Map<String, Double> damage;
    private long lastKillTime;
    private long lastCreate;
	private Scoreboard scoreboard;
	private int asists;
    private ParticleType particleType;
    
    public User(final Player p) {
        this.name = p.getName();

        this.coins = 0;
        this.points = 1000;
        this.kills = 0;
        this.prestiz = 0;
        this.coins = 0;
		this.firstIp = ip;
        this.shop = new HashMap<String, Integer>();
        this.ks = 0;
        this.ksMax = 0;
	    this.lastPosition = ChatUtil.locToString(0.0D, 0.0D, 0.0D);
		this.damage = new HashMap<String, Double>();
        this.deaths = 0;
        this.asyst = 0;
        this.lastKillTime = System.currentTimeMillis();
        this.lastCreate = 0;
        this.incognito = false;
        this.particles = false;
		this.changes();
		UserManager.add(this);
        RankingManager.addRanking(this);
        AssistManager.addAssist(this);
        DeathManager.addDeath(this);
        KillManager.addKill(this);
        PrestizManager.addDeath(this);
        
    }
    
    public User(final String p) {
        this.name = p;
        this.points = 1000;
        this.prestiz = 0;
        this.ks = 0;
        this.shop = new HashMap<String, Integer>();
	    this.lastPosition = ChatUtil.locToString(0.0D, 0.0D, 0.0D);
        this.ksMax = 0;
        this.coins = 0;
		this.firstIp = ip;
        this.particles = false;
		this.damage = new HashMap<String, Double>();
        this.kills = 0;
        this.deaths = 0;
        this.lastKillTime = System.currentTimeMillis();
        this.lastCreate = 0;
        this.asyst = 0;
        this.incognito = false;
		this.changes();
		UserManager.add(this);
        RankingManager.addRanking(this);
        AssistManager.addAssist(this);
        DeathManager.addDeath(this);
        KillManager.addKill(this);
        PrestizManager.addDeath(this);
    }
    
    public User(final String p, final String ip) {
        this.name = p;
        this.points = 1000;
		this.firstIp = ip;
        this.prestiz = 0;
	    this.lastPosition = ChatUtil.locToString(0.0D, 0.0D, 0.0D);
        this.particles = false;
        this.ks = 0;
        this.shop = new HashMap<String, Integer>();
        this.ksMax = 0;
		this.damage = new HashMap<String, Double>();
        this.kills = 0;
        this.deaths = 0;
        this.lastKillTime = System.currentTimeMillis();
        this.lastCreate = 0;
        this.asyst = 0;
        this.incognito = false;
		this.changes();
		UserManager.add(this);
        RankingManager.addRanking(this);
        AssistManager.addAssist(this);
        DeathManager.addDeath(this);
        KillManager.addKill(this);
        PrestizManager.addDeath(this);

    }
    
    public User(final ResultSet rs){

        try {
        	
			this.name = rs.getString("name");
	        this.points = rs.getInt("points");
	        this.kills = rs.getInt("kills");
	        this.deaths = rs.getInt("deaths");
	        this.asyst = rs.getInt("asyst");
	        this.shop = StringUtil.fromStringMap(rs.getString("SHOP"));
	        this.coins = rs.getInt("coins");
	        this.prestiz = rs.getInt("prestiz");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.changes();
		UserManager.add(this);
        RankingManager.addRanking(this);
        AssistManager.addAssist(this);
        DeathManager.addDeath(this);
        KillManager.addKill(this);
        PrestizManager.addDeath(this);

    }
    
	public void setShop(final Map<String, Integer> shop) {
		this.shop = shop;
	}
	
	public int getShop(final String name) {
		if (!this.shop.containsKey(name)) {
			return 0;
		}
		return this.shop.get(name);
	}
	
	public void addShop(final String name, final int length) {
		this.shop.put(name, this.getShop(name) + length);
		this.changes();
	}
	
	
	  
	public void setFirstIp(final String firstIp) {
		this.firstIp = firstIp;
	}
	
	
	public String getFirstIp() {
		return this.firstIp;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	
    
	public void addSchowekPerla(final int i) {
		this.schowekPerla += i;
		this.changes();
	}
	
	public void addSchowekRef(final int i) {
		this.schowekRefil += i;
		this.changes();
	}
	
	public void removeSchowekRef(final int i) {
		this.schowekRefil -= i;
		this.changes();
	}
	
	public void removeSchowekPerla(final int i) {
		this.schowekPerla -= i;
		this.changes();
	}
	public void setSchowekRefil(final int schowekRefil) {
		this.schowekRefil = schowekRefil;
	}
	
    public static void save(Player p) throws IOException {
        YamlConfiguration c = new YamlConfiguration();
        c.set("inventory.armor", p.getInventory().getArmorContents());
        c.set("inventory.content", p.getInventory().getContents());
        c.save(new File("plugins/SC-gtp/data/" + p.getName()+".yml"));
    }
    
    @SuppressWarnings("unchecked")
    public static void restore(Player p) throws IOException {
        YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/SC-gtp/data/" + p.getName()+".yml"));
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
        ChatUtil.sendMessage(p, "&2[!] &aWczytano &7(&fTwoje&7) &aułożenie ekwipunku.");
    }
	
	public int getSchowekKox() {
		return this.schowekKox;
	}
	
	public void setSchowekPerla(final int schowekPerla) {
		this.schowekPerla = schowekPerla;
	}
	
	public void setSchowekKox(final int schowekKox) {
		this.schowekKox = schowekKox;
	}
	
	public void addSchowekKox(final int i) {
		this.schowekKox += i;
		this.changes();
	}

	public int getSchowekPerla() {
		return this.schowekPerla;
	}


	public int getSchowekRefil() {
		return this.schowekRefil;
	}
	
	public void removeSchowekKox(final int i) {
		this.schowekKox -= i;
		this.changes();
	}
	

	  public long getLastChat()
	  {
	    return this.lastChat;
	  }

    
	public static User get(final ResultSet rs) {
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = UserManager.getUsers().iterator();
		while (iterator.hasNext()) {
			final User user;
			try {
				if ((user = iterator2.next()).getName().equalsIgnoreCase(rs.getString("NAME"))) {
					return user;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			iterator = iterator2;
		}
		@SuppressWarnings("unused")
		User m = new User(rs);
		return null;
	}
	
    public void setParticleType(final ParticleType particleType) {
        this.particleType = particleType;
    }
	
    public ParticleType getParticleType() {
        return this.particleType;
    }
	  public String getlastPosition()
	  {
	    return this.lastPosition;
	  }
	  
	  public Location getlastPositionLocation()
	  {
	    return ChatUtil.locFromString(getlastPosition());
	  }
	  
	  public void setlastPosition(Location lastPosition)
	  {
	    this.lastPosition = ChatUtil.locToString(lastPosition);
	  }

    
	    @Override
		public String getSQL() {
			final StringBuilder sb;
			(sb = new StringBuilder()).append("INSERT INTO `SC_USERS` VALUES(");
			sb.append("'%name%',");
			sb.append("%points%,");
			sb.append("%guild%,");
			sb.append("%kills%,");
			sb.append("%deaths%,");
			sb.append("%asyst%,");
			sb.append("%coins%,");
			sb.append("'%shop%',");
			sb.append("%prestiz%");
			sb.append(") ON DUPLICATE KEY UPDATE ");
			sb.append("POINTS=%points%,");
			sb.append("GUILD=%guild%,");
			sb.append("KILLS=%kills%,");
			sb.append("DEATHS=%deaths%,");
			sb.append("ASYST=%asyst%,");
			sb.append("COINS=%coins%,");
			sb.append("SHOP='%shop%',");
			sb.append("PRESTIZ=%prestiz%;");
			String s = StringUtil.replace(s = StringUtil
					.replace(s = StringUtil.replace(s = sb.toString(), "%name%", this.name), "%points%", this.points),
					"%kills%", this.kills);

							return s = StringUtil.replace(
									s = StringUtil
											.replace(
													s = StringUtil
													.replace(
															s = StringUtil
															.replace(
															s = StringUtil
															.replace(

																																																			
																																																			StringUtil
																																																					.replace(
																																																							s,
																																																							"%guild%",
																																																							((this.guild != null)
																																																									? ("'" + this.guild
																																																											.getTag()
																																																											+ "'")
																																																									: "NULL")),
																																																			"%asyst%",
																																																			this.asists),
															"%shop%", StringUtil.toString(this.shop)),
											"%coins%",
											this.coins),
											"%prestiz%",
											this.prestiz),

																																											"%deaths%",
																																											this.deaths);

		}
    
    


	public void setLastKiller(final UUID lastKiller) {
		this.lastKill = lastKiller;
	}
	
	public UUID getLastKiller() {
		return this.lastKill;
	}
	
	public static User getNew(final String name, final String ip) {
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = UserManager.getUsers().iterator();
		while (iterator.hasNext()) {
			final User user;
			if ((user = iterator2.next()).getName().equalsIgnoreCase(name)) {
				return user;
			}
			iterator = iterator2;
		}
		return new User(name, ip);
	}

    
	public void damage(final String uuid, final double damage) {
		if (this.lastDamage <= System.currentTimeMillis()) {
			this.damage.clear();
		}
		if (this.damage.containsKey(uuid)) {
			double i = (i = this.damage.get(uuid)) + damage;
			this.damage.put(uuid, i);
			this.lastDamage = System.currentTimeMillis() + 10000L;
			return;
		}
		this.damage.put(uuid, damage);
		this.lastDamage = System.currentTimeMillis() + 10000L;
	}
	
	public void setLastDamage(final long lastDamage) {
		this.lastDamage = lastDamage;
	}
	
	public long getLastDamage() {
		return this.lastDamage;
	}
	
	public void setGuild(final Guild guild) {
		this.guild = guild;
		this.changes();
	}
    
    
	public void setDamage(final Map<String, Double> damage) {
		this.damage = damage;
	}
	public Map<String, Double> getDamage() {
		return this.damage;
	}
	
    public long getLastKillTime() {
        return this.lastKillTime;
    }
    
    public boolean isLastCreate() {
        return System.currentTimeMillis() > this.lastCreate;
    }
    

    public void setLastCreate(final long lastCreate) {
        this.lastCreate = lastCreate;
    }
    
    
	public long getLastCreate() {
		return this.lastCreate;
	}
    
	
	@SuppressWarnings("unchecked")
	public static User[] getUsers(final String ip) {
		@SuppressWarnings("rawtypes")
		final List users = new ArrayList();
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = UserManager.getUsers().iterator();
		while (iterator.hasNext()) {
			final User user;
			if ((user = iterator2.next()).getIp().equals(ip)) {
				users.add(user);
			}
			iterator = iterator2;
		}
		return (User[]) users.toArray();
	}


    
    
    public void setLastKillTime(final long lastKillTime) {
        this.lastKillTime = lastKillTime;
    }
    
	public void setSidebar(final Sidebar sidebar) {
		this.sidebar = sidebar;
	}

	public Sidebar getSidebar() {
		if (this.sidebar == null) {
			new Sidebar(this);
		}
		return this.sidebar;
	}
	
	public void setAsists(final int asists) {
		this.asists = asists;
	}
	public void addAsist() {
		++this.asists;
	}
	
	public int getAsists() {
		return this.asists;
	}
    
	public Scoreboard getScoreboard() {
		if (this.scoreboard == null) {
			this.scoreboard = ScoreboardStack.pull();
		}
		return this.scoreboard;
	}

	public void setScoreboard(final Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}
	
	

    
    public boolean isIncognito() {
        return this.incognito;
    }
    
    public void setIncognito(final boolean incognito) {
        this.incognito = incognito;
    }
    
    
    
    public boolean isSaveEQ() {
        return this.saveeq;
    }
    
    public void setSaveEQ(final boolean saveeq) {
        this.saveeq = saveeq;
    }
    
    
    
    public boolean isVanish() {
        return this.vanish;
    }
    
    public void setVanish(final boolean vanish) {
        this.vanish = vanish;
    }
    
    public boolean isParticles() {
        return this.particles;
    }
    
    public void setParticles(final boolean particles) {
        this.particles = particles;
    }
    
    
    public boolean isChat() {
        return System.currentTimeMillis() > this.lastChat;
    }
    
    public void setLastChat(final long lastChat) {
        this.lastChat = lastChat;
    }
    
    public int getCoins() {
        return this.coins;
    }
    
    public void addCoins(final int i) {
        this.coins += i;
    }
    
    public void removeCoins(final int i) {
        this.coins -= i;
    }
    
    public int getPrestiz() {
        return this.prestiz;
    }
    
    public void addPrestiz(final int i) {
        this.prestiz += i;
    }
    
    public boolean hasGuild() {
        return this.guild != null;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
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
    
    public int getAsyst() {
        return this.asyst;
    }
    
    public void setAsyst(final int asyst) {
        this.asyst = asyst;
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(this.getName());
    }
    
    public boolean isOnline() {
        return this.getPlayer() != null;
    }
    
    public void addPoints(final int index) {
        this.points += index;
    }
    
    public void addKills(final int index) {
        this.kills += index;
    }
    
    public void addDeaths(final int index) {
        this.deaths += index;
    }
    
    public void addAsyst(final int index) {
        this.asyst += index;
    }
    
    public int getKs() {
        return this.ks;
    }
    
    public void addKs(final int i) {
        this.ks += i;
    }
    
    public void setKs (final int i) {
    	this.ks = i;
    }
    
    
	@SuppressWarnings("unchecked")
	public int getPing() {
		int ping = 0;
		final Player p;
		if ((p = this.getPlayer()) == null) {
			return ping;
		}
		try {
			@SuppressWarnings({ "rawtypes", "unused" })
			final Class c;
			@SuppressWarnings("rawtypes")
			final Class clazz = c = ReflectionUtil.getBukkitEntityClass("CraftPlayer");
			final Object cp = clazz.cast(p);
			final Object handle;
			ping = (int) (handle = clazz.getMethod("getHandle", (Class<?>[]) new Class[0]).invoke(cp, new Object[0]))
					.getClass().getField("ping").get(handle);
			return ping;
		} catch (Exception c) {
			return ping;
		}
	}
    
    public int getKsMax() {
        return this.ksMax;
    }
    
    public void addKsMax(final int i) {
        this.ksMax += i;
    }
    
    public void setKsMax(final int i) {
    	this.ksMax = i;
    }
    
    public void removePoints(final int index) {
        this.points -= index;
    }
    
    public void removeKills(final int index) {
        this.kills -= index;
    }
    
    public void removeDeaths(final int index) {
        this.deaths -= index;
    }
    
    public void removeAsyst(final int index) {
        this.asyst -= index;
    }
    
	public Guild getGuild() {
		return this.guild;
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
    
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.getName());
    }
    

    
	public static User get(final User d) {
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = UserManager.getUsers().iterator();
		while (iterator.hasNext()) {
			final User user;

			if ((user = iterator2.next()).getName().equalsIgnoreCase(d.getName())) {
				return user;
			}
			iterator = iterator2;
		}
		@SuppressWarnings("unused")
		User m = new User(d.getName());
		return null;
	}
	
	public static User get(final String name) {
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = UserManager.getUsers().iterator();
		while (iterator.hasNext()) {
			final User user;
			if ((user = iterator2.next()).getName().equalsIgnoreCase(name)) {
				return user;
			}
			iterator = iterator2;
		}
		@SuppressWarnings("unused")
		User m = new User(name);
		return null;
	}
	
	public static User get(final Player player) {
		final String name = player.getName();
		final Iterator<User> iterator2;
		Iterator<User> iterator = iterator2 = UserManager.getUsers().iterator();
		while (iterator.hasNext()) {
			final User user;
			if ((user = iterator2.next()).getName().equalsIgnoreCase(name)) {
				return user;
			}
			iterator = iterator2;
		}
		return new User(player.getName(), player.getAddress().getAddress().getHostAddress());
	}

}