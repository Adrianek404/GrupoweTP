package pl.patrykv220.grupowetpcore.manager;

import java.util.*;

import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.comparator.GuildComparator;
import pl.patrykv220.grupowetpcore.comparator.UserPointsComparator;

public class RankingManager
{
    private static List<User> rankings;
    private static List<Guild> guildRankings;
    
    static {
        RankingManager.rankings = new LinkedList<User>();
        RankingManager.guildRankings = new LinkedList<Guild>();
    }
    
    public static List<User> getRankings() {
        return RankingManager.rankings;
    }
    
    public static List<Guild> getGuildRankings() {
        return RankingManager.guildRankings;
    }
    
    public static void addRanking(final User ranking) {
        RankingManager.rankings.add(ranking);
        sortUserRankings();
    }
    
    public static void addRanking(final Guild ranking) {
        RankingManager.guildRankings.add(ranking);
        sortGuildRankings();
    }
    
    public static void removeRanking(final User ranking) {
        RankingManager.rankings.remove(ranking);
        sortUserRankings();
    }
    
    public static void removeRanking(final Guild ranking) {
        RankingManager.guildRankings.remove(ranking);
        sortGuildRankings();
    }
    
    public static void sortUserRankings() {
        RankingManager.rankings.sort(new UserPointsComparator());
    }
    
    public static void sortGuildRankings() {
        RankingManager.guildRankings.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < RankingManager.rankings.size(); ++num) {
            if (RankingManager.rankings.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < RankingManager.rankings.size(); ++num) {
            if (RankingManager.guildRankings.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}

