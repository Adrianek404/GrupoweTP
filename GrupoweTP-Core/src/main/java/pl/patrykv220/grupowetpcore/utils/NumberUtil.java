package pl.patrykv220.grupowetpcore.utils;

public class NumberUtil
{
   
    public static boolean isInt(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isIntItem(final String s) {
        if (s.contains(":")) {
            final String[] i;
            return isInt((i = s.split(":"))[0]) && isInt(i[1]);
        }
        return isInt(s);
    }
}
