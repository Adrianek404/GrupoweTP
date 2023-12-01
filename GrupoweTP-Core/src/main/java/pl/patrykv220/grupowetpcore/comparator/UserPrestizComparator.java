package pl.patrykv220.grupowetpcore.comparator;

import java.util.*;

import pl.yspar.core.basic.User;


public class UserPrestizComparator implements Comparator<User>
{
    @Override
    public int compare(final User g0, final User g1) {
        final Integer p0 = g0.getPrestiz();
        final Integer p2 = g1.getPrestiz();
        return p2.compareTo(p0);
    }
}

