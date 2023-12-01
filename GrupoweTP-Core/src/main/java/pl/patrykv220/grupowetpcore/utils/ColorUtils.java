package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.*;
import java.util.*;

public class ColorUtils
{
    public static Color randomColor() {
        final Random random = new Random();
        return Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
    
    public static Color nextColor(final Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == 255 && g < 255 && b == 0) {
            g += 15;
        }
        if (g == 255 && r > 0 && b == 0) {
            r -= 15;
        }
        if (g == 255 && b < 255 && r == 0) {
            b += 15;
        }
        if (b == 255 && g > 0 && r == 0) {
            g -= 15;
        }
        if (b == 255 && r < 255 && g == 0) {
            r += 15;
        }
        if (r == 255 && b > 0 && g == 0) {
            b -= 15;
        }
        color.setRed(r);
        color.setGreen(g);
        color.setBlue(b);
        return Color.fromRGB(r, g, b);
    }
    
    public static Color nextGrayColor(final Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == 255 || g == 255 || b == 255) {
            r = 0;
            g = 0;
            b = 0;
            color.setRed(r);
            color.setGreen(g);
            color.setBlue(b);
            return Color.fromRGB(r, g, b);
        }
        r += 15;
        g += 15;
        b += 15;
        color.setRed(r);
        color.setGreen(g);
        color.setBlue(b);
        return Color.fromRGB(r, g, b);
    }
}
