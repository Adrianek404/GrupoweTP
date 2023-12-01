package pl.patrykv220.grupowetpcore.comparator;

import java.util.*;

import pl.yspar.core.basic.User;


public class UserPointsComparator implements Comparator<User>
{
    @Override
    public int compare(final User g0, final User g1) {
        final Integer p0 = g0.getPoints();
        final Integer p2 = g1.getPoints();
        return p2.compareTo(p0);
    }
}

