package pl.patrykv220.grupowetpcore.manager;


import java.util.*;

import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.comparator.UserPrestizComparator;

public class PrestizManager
{
    private static List<User> prestiz;
    
    static {
        PrestizManager.prestiz = new LinkedList<User>();
    }
    
    public static List<User> getprestiz() {
        return PrestizManager.prestiz;
    }
    
    public static void addDeath(final User death) {
        PrestizManager.prestiz.add(death);
        sortUserprestiz();
    }
    
    public static void removeDeath(final User death) {
        PrestizManager.prestiz.remove(death);
        sortUserprestiz();
    }
    
    public static void sortUserprestiz() {
        PrestizManager.prestiz.sort(new UserPrestizComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < PrestizManager.prestiz.size(); ++num) {
            if (PrestizManager.prestiz.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
}
