package pl.patrykv220.grupowetpcore.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;


import pl.yspar.core.Config;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.Util;
import pl.yspar.core.basic.User;




public class MySQL {
    public static Connection conn;
    private static List<String> guildsToRemove;

    private static MySQL inst;
    private static boolean loaded = false;


    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void save() {
            this.openConnection();
            if (MySQL.conn == null) {
                return;
            }
            Statement stmt;
  			try {
  				stmt = MySQL.conn.createStatement();
				  for(User u : UserManager.getUsers()){
			            
		                if (!u.isChanges()) {
		                    continue;
		                }
		                stmt.executeUpdate(u.getSQL());
		            }
				  for(Guild guild : GuildManager.getGuilds()){
  		                if (!guild.isChanges()) {
  		                    continue;
  		                }
  		                stmt.executeUpdate(guild.getSQL());
  		}
  		            List<String> guilds = new ArrayList<String>(MySQL.guildsToRemove);
  		            MySQL.guildsToRemove.clear();
  		           for(String s : guilds){
  		                stmt.executeUpdate("DELETE FROM `SC_GUILDS` WHERE TAG='" + (String)s + "'");
  		            }
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}
	            this.closeConnection();
          }

    private  void openConnection() {
        block3 : {
        try {
			if (conn == null || conn.isClosed()) break block3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return;
    }
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://" + Config.DATABASE_MYSQL_HOST + ":" + Config.DATABASE_MYSQL_PORT + "/" + Config.DATABASE_MYSQL_NAME + "?user=" + Config.DATABASE_MYSQL_NAME + "&password=" + Config.DATABASE_MYSQL_PASS);
                return;
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor("&7[&4SC-core&7] &cNie mozna polaczyc z baza mysql."));
                return;
            }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void load() {
        this.openConnection();
        if (MySQL.conn == null) {
            return;
        }
        try {
            Statement stmt = MySQL.conn.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS `SC_GUILDS` (");
            sb.append("TAG varchar(20) not null, ");
            sb.append("NAME text not null, ");
            sb.append("OWNER varchar(20) not null, ");
            sb.append("MEMBERS text not null, ");
            sb.append("POINTS int not null, ");
            sb.append("DEATHS int not null, ");
            sb.append("KILLS int not null, ");
            sb.append("primary key(TAG));");
            stmt.executeUpdate(sb.toString());
            StringBuilder sb1 = new StringBuilder();
            sb1.append("CREATE TABLE IF NOT EXISTS `SC_USERS` (");
            sb1.append("NAME varchar(20) not null, ");
            sb1.append("POINTS int not null, ");
           sb1.append("GUILD varchar(20), ");
           sb1.append("KILLS int not null, ");
            sb1.append("DEATHS int not null, ");
            sb1.append("ASYST int not null, ");
            sb1.append("COINS int not null, ");
            sb1.append("SHOP varchar(255), ");
            sb1.append("PRESTIZ int not null, ");
            sb1.append("primary key(NAME));");
            
          
            stmt.executeUpdate(sb1.toString());
            int usersize = 0;
            int guildsize = 0;
            if(!loaded){ 
            ResultSet rs = stmt.executeQuery("SELECT * FROM `SC_USERS`");
           
           
            while(rs.next()){
            ++usersize;
            new User((ResultSet)rs);
        }
        Bukkit.getConsoleSender().sendMessage(Util.fixColor("&7[&4SC-core&7] &aZaladowano &c " + usersize + " graczy"));
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM `SC_GUILDS`");
        while (rs1.next()) {
            ++guildsize;
            new Guild((ResultSet)rs1);
        }
        Bukkit.getConsoleSender().sendMessage(Util.fixColor("&7[&4SC-core&7] &aZaladowano &c " + guildsize + " gildii"));
    loaded = true;
            }
            
        }
        catch (SQLException e) {
      
            e.printStackTrace();
          
        }   
        this.closeConnection();
}


    private  void closeConnection() {
            try {
                if (conn != null && (conn == null || !conn.isClosed())) {
                return;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        conn = null;
    }

    public void removeGuild(String tag) {
        guildsToRemove.add(tag);
    }

    public MySQL() {
        MySQL mySQL = this;
        inst = mySQL;
    }

    public static MySQL getInst() {
        if (inst == null) {
            new MySQL();
        }
        return inst;
    }



    static {
        guildsToRemove = new ArrayList<String>();
    }
}

