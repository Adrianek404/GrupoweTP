package pl.patrykv220.grupowetpcore.manager;


import java.util.*;

import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.comparator.GuildComparator;
import pl.patrykv220.grupowetpcore.comparator.UserKillsComparator;


public class KsManager
{
    private static List<User> kills;
    private static List<Guild> guildKills;
    
    static {
        KsManager.kills = new LinkedList<User>();
        KsManager.guildKills = new LinkedList<Guild>();
    }
    
    public static List<User> getKills() {
        return KsManager.kills;
    }
    
    public static List<Guild> getGuildKills() {
        return KsManager.guildKills;
    }
    
    public static void addKill(final User kill) {
        KsManager.kills.add(kill);
        sortUserKills();
    }
    
    public static void addKill(final Guild kill) {
        KsManager.guildKills.add(kill);
        sortGuildKills();
    }
    
    public static void removeKill(final User kill) {
        KsManager.kills.remove(kill);
        sortUserKills();
    }
    
    public static void removeKill(final Guild kill) {
        KsManager.guildKills.remove(kill);
        sortGuildKills();
    }
    
    public static void sortUserKills() {
        KsManager.kills.sort(new UserKillsComparator());
    }
    
    public static void sortGuildKills() {
        KsManager.guildKills.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < KsManager.kills.size(); ++num) {
            if (KsManager.kills.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < KsManager.kills.size(); ++num) {
            if (KsManager.guildKills.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}