package pl.patrykv220.grupowetpcore.utils;

import java.util.logging.*;

import pl.patrykv220.grupowetpcore.Main;

import java.io.*;

public final class Logger
{
    public static void info(final String... logs) {
        for (final String s : logs) {
            log(Level.INFO, s);
        }
    }
    
    public static void warning(final String... logs) {
        for (final String s : logs) {
            log(Level.WARNING, s);
        }
    }
    
    public static void severe(final String... logs) {
        for (final String s : logs) {
            log(Level.SEVERE, s);
        }
    }
    
    public static void log(final Level level, final String log) {
        Main.getPlugin().getLogger().log(level, log);
    }
    
    public static void exception(final Throwable cause) {
        cause.printStackTrace();
    }
    
    public static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
