package pl.patrykv220.grupowetpcore.manager;


import java.util.*;

import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.comparator.GuildComparator;
import pl.yspar.core.comparator.UserKillsComparator;


public class KsMaxManager
{
    private static List<User> kills;
    private static List<Guild> guildKills;
    
    static {
        KsMaxManager.kills = new LinkedList<User>();
        KsMaxManager.guildKills = new LinkedList<Guild>();
    }
    
    public static List<User> getKills() {
        return KsMaxManager.kills;
    }
    
    public static List<Guild> getGuildKills() {
        return KsMaxManager.guildKills;
    }
    
    public static void addKill(final User kill) {
        KsMaxManager.kills.add(kill);
        sortUserKills();
    }
    
    public static void addKill(final Guild kill) {
        KsMaxManager.guildKills.add(kill);
        sortGuildKills();
    }
    
    public static void removeKill(final User kill) {
        KsMaxManager.kills.remove(kill);
        sortUserKills();
    }
    
    public static void removeKill(final Guild kill) {
        KsMaxManager.guildKills.remove(kill);
        sortGuildKills();
    }
    
    public static void sortUserKills() {
        KsMaxManager.kills.sort(new UserKillsComparator());
    }
    
    public static void sortGuildKills() {
        KsMaxManager.guildKills.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < KsMaxManager.kills.size(); ++num) {
            if (KsMaxManager.kills.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < KsMaxManager.kills.size(); ++num) {
            if (KsMaxManager.guildKills.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}