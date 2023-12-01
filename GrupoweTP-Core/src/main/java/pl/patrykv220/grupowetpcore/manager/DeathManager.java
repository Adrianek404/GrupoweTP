package pl.patrykv220.grupowetpcore.manager;


import java.util.*;

import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.comparator.GuildComparator;
import pl.yspar.core.comparator.UserDeathsComparator;

public class DeathManager
{
    private static List<User> deaths;
    private static List<Guild> guildDeaths;
    
    static {
        DeathManager.deaths = new LinkedList<User>();
        DeathManager.guildDeaths = new LinkedList<Guild>();
    }
    
    public static List<User> getDeaths() {
        return DeathManager.deaths;
    }
    
    public static List<Guild> getGuildDeaths() {
        return DeathManager.guildDeaths;
    }
    
    public static void addDeath(final User death) {
        DeathManager.deaths.add(death);
        sortUserDeaths();
    }
    
    public static void addDeath(final Guild death) {
        DeathManager.guildDeaths.add(death);
        sortGuildDeaths();
    }
    
    public static void removeDeath(final User death) {
        DeathManager.deaths.remove(death);
        sortUserDeaths();
    }
    
    public static void removeDeath(final Guild death) {
        DeathManager.guildDeaths.remove(death);
        sortGuildDeaths();
    }
    
    public static void sortUserDeaths() {
        DeathManager.deaths.sort(new UserDeathsComparator());
    }
    
    public static void sortGuildDeaths() {
        DeathManager.guildDeaths.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < DeathManager.deaths.size(); ++num) {
            if (DeathManager.deaths.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < DeathManager.deaths.size(); ++num) {
            if (DeathManager.guildDeaths.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}
