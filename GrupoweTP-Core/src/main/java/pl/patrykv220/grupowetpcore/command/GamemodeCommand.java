package pl.patrykv220.grupowetpcore.command;


import org.bukkit.entity.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;

import org.bukkit.inventory.*;


import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.manager.UserManager;
import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.Util;


public class GamemodeCommand extends PlayerCommand
{
    public GamemodeCommand() {
        super("gamemode", "informacje o gildii", "/incognito", "core.cmd.admin", new String[] { "gm" });
    }
    
    private static int[] $SWITCH_TABLE$org$bukkit$GameMode;
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (!(p instanceof Player)) {
            if (args.length == 0) {
                final boolean b = false;
                p.sendMessage(Util.fixColor("&cBrakujacy argument"));
                return b;
            }
            if (args.length > 1) {
                final Player player;
                if ((player = Bukkit.getPlayer(args[0])) != null) {
                    final int i;
                    ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                    player.setGameMode(((i = Integer.parseInt(args[1])) < 1) ? GameMode.SURVIVAL : GameMode.CREATIVE);
                   
                    return true;
                }
                p.sendMessage("&cBrak gracza");
                return false;
            }

        }
        Player player = (Player)p;
        if (args.length == 0) {
            switch ($SWITCH_TABLE$org$bukkit$GameMode()[player.getGameMode().ordinal()]) {
                case 1: {
                    final Player player3 = player;
                    player3.setGameMode(GameMode.SURVIVAL);
                    ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                    break;
                }
                case 4: {
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
                }
                default: {
                    final Player player4 = player;
                    player4.setGameMode(GameMode.CREATIVE);
                    ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                    break;
                }
            }
            return true;
        }
        if (args.length == 1) {

            @SuppressWarnings("unused")
			final int i;
            switch (i = Integer.parseInt(args[0])) {
                case 1: {
                    final Player player5 = player;
                    player5.setGameMode(GameMode.CREATIVE);
                    ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                    break;
                }
                case 2: {
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
                }
                default: {
                    final Player player6 = player;
                    player6.setGameMode(GameMode.SURVIVAL);
                    ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                    break;
                }
            }
            return true;
        }
        else {
            if (args.length < 2) {
                return false;
            }

            final int i = Integer.parseInt(args[0]);
            final Player player2;
            if ((player2 = Bukkit.getPlayer(args[1])) != null) {
                switch (i) {
                    case 1: {
                        final Player player7 = player2;
                        player7.setGameMode(GameMode.CREATIVE);
                        ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                        break;
                    }
                    default: {
                        player2.setGameMode(GameMode.SURVIVAL);
                        ChatUtil.sendTitleMessage(p, "", ChatUtil.fixColor("&bZmieniono tryb"), 20, 20, 20);
                        break;
                    }
                }
                return true;
            }
            p.sendMessage("&cBrak gracza");
            return false;
        }
    }
    
    static /* synthetic */ int[] $SWITCH_TABLE$org$bukkit$GameMode() {
        final int[] $switch_TABLE$org$bukkit$GameMode = GamemodeCommand.$SWITCH_TABLE$org$bukkit$GameMode;
        if ($switch_TABLE$org$bukkit$GameMode != null) {
            return $switch_TABLE$org$bukkit$GameMode;
        }
        final int[] array = new int[GameMode.values().length];
        int[] $switch_TABLE$org$bukkit$GameMode2 = null;
        int[] array5 = null;
        Label_0073: {
            Label_0056: {
                Label_0039: {
                    try {
                        array[GameMode.ADVENTURE.ordinal()] = 3;
                        @SuppressWarnings("unused")
						final int[] array2 = array;
                        break Label_0039;
                    }
                    catch (NoSuchFieldError noSuchFieldError) {
                        @SuppressWarnings("unused")
						final int[] array2 = array;
                    }
                    try {
                        final int[] array2 = array;
                        array2[GameMode.CREATIVE.ordinal()] = 1;
                        @SuppressWarnings("unused")
						final int[] array3 = array;
                        break Label_0056;
                    }
                    catch (NoSuchFieldError noSuchFieldError2) {
                        @SuppressWarnings("unused")
						final int[] array3 = array;
                    }
                }
                try {
                    final int[] array3 = array;
                    array3[GameMode.SPECTATOR.ordinal()] = 4;
                    @SuppressWarnings("unused")
					final int[] array4 = array;
                    break Label_0073;
                }
                catch (NoSuchFieldError noSuchFieldError3) {
                    @SuppressWarnings("unused")
					final int[] array4 = array;
                }
            }
            try {
                final int[] array4 = array;
                array4[GameMode.SURVIVAL.ordinal()] = 2;
                array5 = ($switch_TABLE$org$bukkit$GameMode2 = array);
            }
            catch (NoSuchFieldError noSuchFieldError4) {
                array5 = ($switch_TABLE$org$bukkit$GameMode2 = array);
            }
        }
        GamemodeCommand.$SWITCH_TABLE$org$bukkit$GameMode = $switch_TABLE$org$bukkit$GameMode2;
        return array5;
            
    }

}