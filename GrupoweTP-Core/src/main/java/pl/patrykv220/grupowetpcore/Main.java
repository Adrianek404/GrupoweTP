package pl.patrykv220.grupowetpcore;


import org.bukkit.command.Command;
import org.bukkit.plugin.java.*;


import pl.patrykv220.grupowetpcore.command.*;
import pl.patrykv220.grupowetpcore.command.guild.*;
import pl.patrykv220.grupowetpcore.listener.*;
import pl.patrykv220.grupowetpcore.manager.TagManager;

import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.entity.Player;

import org.bukkit.event.*;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.sidebar.ScoreboardStack;
import pl.patrykv220.grupowetpcore.sidebar.SidebarRunnable;
import pl.patrykv220.grupowetpcore.store.MySQL;
import pl.patrykv220.grupowetpcore.tab.TablistManager;
import pl.patrykv220.grupowetpcore.task.*;


public class Main extends JavaPlugin {
    private static Main plugin;
    private static PluginManager pluginManager;
    
    public void onLoad() {
        Main.plugin = this;
    }
    
    
    public void onEnable() {
		TagManager.init();
	      Config.reloadConfig();
	      Shop.reloadConfig();
		MySQL.getInst().save();
		MySQL.getInst().load();
        this.registerListener();
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new ParticleRunnable(), 11L, 11L);
		new ActionTask().runTaskTimerAsynchronously((Plugin)this, 40L, 20L);
        new TablistRefreshTask().runTaskTimerAsynchronously((Plugin)this, 30L, 300L);
		new RefreshThread().start();
		new LimitRunnable().start();
		AntiLogoutRunnable.start();
		new ScoreboardStack().start();
		new TagRunnable().start();
		new SidebarRunnable().start();
        registerCommand();

		Bukkit.getScheduler().runTaskLater((Plugin) this,
		(Runnable) new Runnable() {
			@Override
			public void run() {
				UserManager.registerPlayers();
			}
		}, 60L);

    }



    
    public void onDisable() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
        	TablistManager.executeRemove(p);
        }
        Bukkit.getScheduler().cancelTasks((Plugin)this);
        Bukkit.savePlayers();
        for (final World w : Bukkit.getWorlds()) {
            w.save();
        }
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException e2) {
            e2.printStackTrace();
        }
		UserManager.unregisterPlayers();
		MySQL.getInst().save();
    }
    
    public static Main getPlugin() {
        return Main.plugin;
    }
    

    public static void registerCommand(final Command command) {
        CommandManager.register(command);
    }
    
    public static void registerListener(final Plugin plugin, final Listener... listeners) {
        if (Main.pluginManager == null) {
            Main.pluginManager = Bukkit.getPluginManager();
        }
        for (final Listener listener : listeners) {
            Main.pluginManager.registerEvents(listener, plugin);
        }
    }
    
    public static void registerCommand() {
        registerCommand(new IncognitoCommand());
        registerCommand(new sBCommand());
        registerCommand(new CreateCommand());
        registerCommand(new DeleteCommand());
        registerCommand(new GuildHelpCommand());
        registerCommand(new InfoCommand());
        registerCommand(new InviteCommand());
        registerCommand(new JoinCommand());
        registerCommand(new CcCommand());
        registerCommand(new KickCommand());
        registerCommand(new KoszCommand());
        registerCommand(new GraczCommand());
        registerCommand(new LeaveCommand());
        registerCommand(new VanishCommand());
        registerCommand(new ItemShopCommand());
        registerCommand(new SetSpawnCommand());
        registerCommand(new SpawnCommand());
        registerCommand(new DepositCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new PrestizCommand());
        registerCommand(new SetSaveeqCommand());
        registerCommand(new SaveEqCommand());
        registerCommand(new GamemodeCommand());
        registerCommand(new CoinsCommand());
        registerCommand(new ProfileCommand());
        registerCommand(new ResetujRankingCommand());
    }
   
    
    
    public void registerListener() {
    	
        registerListener((Plugin)this, (Listener)new PlayerCommandPreprocessListener());
        registerListener((Plugin)this, (Listener)new JoinQuitListener());
        registerListener((Plugin)this, (Listener)new SignListener());
        registerListener((Plugin)this, (Listener)new GtpListener());
        registerListener((Plugin)this, (Listener)new AsyncPlayerChatListener());
        registerListener((Plugin)this, (Listener)new PlayerInteractListener());
        registerListener((Plugin)this, (Listener)new ItemConsumeListener());
        registerListener((Plugin)this, (Listener)new InventoryClickListener());
        registerListener((Plugin)this, (Listener)new PlayerRespawnListener());
        registerListener((Plugin)this, (Listener)new PlayerDeathListener());
        registerListener((Plugin)this, (Listener)new EntityDamageByEntityListener());
        registerListener((Plugin)this, (Listener)new PlayerAntyLogoutListener());
    }
}

