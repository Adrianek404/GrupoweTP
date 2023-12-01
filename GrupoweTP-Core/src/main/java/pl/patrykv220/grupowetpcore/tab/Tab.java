package pl.patrykv220.grupowetpcore.tab;


import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.mysql.jdbc.TimeUtil;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.Packet;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.manager.RankingManager;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;


public class Tab {
    private String[][] slot;
    private WrappedGameProfile[][] entries;
    private User playerData;

    public Tab(Player player) {
        Guild g2;
        int j2;
        int i2;
        this.playerData = User.get(player);
        this.entries = new WrappedGameProfile[4][20];
        this.slot = new String[4][20];
        int base = 97;
        for (i2 = 0; i2 < 4; ++i2) {
            for (j2 = 0; j2 < 20; ++j2) {
                char first = (char)(97 + i2);
                char second = (char)(97 + j2);
                String name = "!!UPDATEMC" + first + second;
                WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(UUID.randomUUID(), name);
                this.entries[i2][j2] = wrappedGameProfile;
            }
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.slot[i][j] = "";
            }
        }
        this.setSLOT(0, 1, "  &7(&fTopowi gracze&7)");
        this.setSLOT(1, 2, "");
        this.setSLOT(0, 3, getReplacementU(1));
        this.setSLOT(0, 4, getReplacementU(2));
        this.setSLOT(0, 5, getReplacementU(3));
        this.setSLOT(0, 6, getReplacementU(4));
        this.setSLOT(0, 7, getReplacementU(5));
        this.setSLOT(0, 8, getReplacementU(6));
        this.setSLOT(0, 9, getReplacementU(7));
        this.setSLOT(0, 10, getReplacementU(8));
        this.setSLOT(0, 11, getReplacementU(9));
        this.setSLOT(0, 12, getReplacementU(10));
        this.setSLOT(0, 13, getReplacementU(11));
        this.setSLOT(0, 14, getReplacementU(12));
        this.setSLOT(0, 15, getReplacementU(13));
        this.setSLOT(0, 16, getReplacementU(14));
        this.setSLOT(0, 17, getReplacementU(15));
        this.setSLOT(0, 18, getReplacementU(16));
        this.setSLOT(1, 1, "  &7(&fTwoje statystyki&7)");
        this.setSLOT(1, 2, "");
        final User u = User.get(player);
        if (u != null) {
        	this.setSLOT(1, 3, "&7Nick: &f" + u.getName());
            this.setSLOT(1, 4, "&7Ranga: &fLadowanie...");
            this.setSLOT(1, 5, "&7Punkty: &fLadowanie...");
            this.setSLOT(1, 6, "&7Pozycja: &fLadowanie...");
            this.setSLOT(1, 7, "&7Zabojstwa: &fLadowanie...");
            this.setSLOT(1, 8, "&7Zgony: &fLadowanie...");
            this.setSLOT(1, 9, "&7K/D Ratio: &fLadowanie...");
   

        }
        final Guild g = u.getGuild();
        if (g != null) {
            this.setSLOT(1, 11, "  &7(&fTwoja gildia&7)");
            this.setSLOT(1, 12, "");
            this.setSLOT(1, 13, "&7Tag: &fLadowanie...");
            this.setSLOT(1, 14, "&7Lider &fLadowanie...");
            this.setSLOT(1, 15, "&7Punkty: &fLadowanie...");
            this.setSLOT(1, 16, "&7Teren: &fLadowanie...");
            this.setSLOT(1, 17, "&7Czlonkow online: &fLadowanie...");

        }
        else {
            this.setSLOT(1, 11, "  &7(&fTwoja gildia&7)");
            this.setSLOT(1, 12, "");
            this.setSLOT(1, 13, "&7Tag: &cBrak");
            this.setSLOT(1, 14, "&7Lider &cBrak");
            this.setSLOT(1, 15, "&7Punkty: &cBrak");
            this.setSLOT(1, 16, "&7Teren: &cBrak");
            this.setSLOT(1, 17, "&7Czlonkow online: &cBrak");

        }
        this.setSLOT(2, 1, "  &7(&fSerwer&7)");
        this.setSLOT(2, 2, "");
        this.setSLOT(2, 3, "&7Proxy: &fproxy-1");
        this.setSLOT(2, 4, "&7Godzina: &f" + getTime());
        this.setSLOT(2, 4, "");

        this.setSLOT(2, 7, "");
        this.setSLOT(2, 8, "  &7(&fPrzydatne komendy&7)");
        this.setSLOT(2, 9, "");
        this.setSLOT(2, 10, "     &2Nasz TeamSpeak");
        this.setSLOT(2, 11, "         &f/teamspeak");
        this.setSLOT(2, 12, "");
        this.setSLOT(2, 13, "     &2Nasz discord");
        this.setSLOT(2, 14, "         &f/discord");
        this.setSLOT(2, 15, "");
        this.setSLOT(2, 16, "     &2Strona www");
        this.setSLOT(2, 17, "         &f/wwww");
        this.setSLOT(3, 1, "  &7(&fTopowe gildie&7)");
        this.setSLOT(3, 2, "");
        this.setSLOT(3, 3, getReplacementG(1));
        this.setSLOT(3, 4, getReplacementG(2));
        this.setSLOT(3, 5, getReplacementG(3));
        this.setSLOT(3, 6, getReplacementG(4));
        this.setSLOT(3, 7, getReplacementG(5));
        this.setSLOT(3, 8, getReplacementG(6));
        this.setSLOT(3, 9, getReplacementG(7));
        this.setSLOT(3, 10, getReplacementG(8));
        this.setSLOT(3, 11, getReplacementG(9));
        this.setSLOT(3, 12, getReplacementG(10));
        this.setSLOT(3, 13, getReplacementG(11));
        this.setSLOT(3, 14, getReplacementG(12));
        this.setSLOT(3, 15, getReplacementG(13));
        this.setSLOT(3, 16, getReplacementG(14));
        this.setSLOT(3, 17, getReplacementG(15));
        this.setSLOT(3, 18, getReplacementG(16));

    }
    
    public String getTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }
    
    public void update() {
        this.setSLOT(0, 3, getReplacementU(1));
        this.setSLOT(0, 4, getReplacementU(2));
        this.setSLOT(0, 5, getReplacementU(3));
        this.setSLOT(0, 6, getReplacementU(4));
        this.setSLOT(0, 7, getReplacementU(5));
        this.setSLOT(0, 8, getReplacementU(6));
        this.setSLOT(0, 9, getReplacementU(7));
        this.setSLOT(0, 10, getReplacementU(8));
        this.setSLOT(0, 11, getReplacementU(9));
        this.setSLOT(0, 12, getReplacementU(10));
        this.setSLOT(0, 13, getReplacementU(11));
        this.setSLOT(0, 14, getReplacementU(12));
        this.setSLOT(0, 15, getReplacementU(13));
        this.setSLOT(0, 16, getReplacementU(14));
        this.setSLOT(0, 17, getReplacementU(15));
        this.setSLOT(0, 18, getReplacementU(16));
        this.setSLOT(3, 3, getReplacementG(1));
        this.setSLOT(3, 4, getReplacementG(2));
        this.setSLOT(3, 5, getReplacementG(3));
        this.setSLOT(3, 6, getReplacementG(4));
        this.setSLOT(3, 7, getReplacementG(5));
        this.setSLOT(3, 8, getReplacementG(6));
        this.setSLOT(3, 9, getReplacementG(7));
        this.setSLOT(3, 10, getReplacementG(8));
        this.setSLOT(3, 11, getReplacementG(9));
        this.setSLOT(3, 12, getReplacementG(10));
        this.setSLOT(3, 13, getReplacementG(11));
        this.setSLOT(3, 14, getReplacementG(12));
        this.setSLOT(3, 15, getReplacementG(13));
        this.setSLOT(3, 16, getReplacementG(14));
        this.setSLOT(3, 17, getReplacementG(15));
        this.setSLOT(3, 18, getReplacementG(16));

        this.setSLOT(2, 1, "  &7(&fSerwer&7)");
        this.setSLOT(2, 2, "");
        this.setSLOT(2, 3, "&7Proxy: &fproxy-1");
        this.setSLOT(2, 4, "&7Godzina: &f" + getTime());
        this.setSLOT(2, 5, "");

        final User u = User.get(this.getPlayerData().getPlayer());

        if (u != null) {
        	this.setSLOT(1, 4, "");
            this.setSLOT(1, 5, "&7Punkty: &f" + u.getPoints());
            this.setSLOT(1, 6, "&7Pozycja: &f" + RankingManager.getPlaceUser(u));
            this.setSLOT(1, 7, "&7Zabojstwa: &f" + u.getKills());
            this.setSLOT(1, 8, "&7Zgony: &f" + u.getDeaths());
            this.setSLOT(1, 9, "&7K/D Ratio: &f" + u.getKd());

        }
        final Guild g = u.getGuild();
        if (g != null) {
            this.setSLOT(1, 13, "&7Tag: &f" + g.getTag());
            this.setSLOT(1, 14, "&7Lider: &f" + g.getOwner().getName());
            this.setSLOT(1, 15, "&7Punkty: &f" + g.getPoints() + " &8(#" + RankingManager.getPlaceGuild(g) + ")");

            this.setSLOT(1, 16, "");
            this.setSLOT(1, 17, "");

        }

        else {
            this.setSLOT(1, 13, "&7Niestety ale nie posiadasz gildii");
            this.setSLOT(1, 14, "&7Aby ja zalozyc potrzebujesz");
            this.setSLOT(1, 15, "&7Coins &6x1000 &7wiecej pod /g");
            this.setSLOT(1, 16, "");
            this.setSLOT(1, 17, "");
            this.setSLOT(1, 18, "");
        }

    }

    
    public static String getReplacementU(final Integer i) {
        if (RankingManager.getRankings().size() >= i) {
            String s;
            if (i == 1) {
                s = "&a&l" + i + ". &f";
            }
            else if (i == 2) {
                s = "&a&l" + i + ". &f";
            }
            else if (i == 3) {
                s = "&a&l" + i + ". &f";
            }
            else {
                s = "&7" + i + ". &7";
            }
            return ChatUtil.fixColor(String.valueOf(s) + (RankingManager.getRankings().get(i - 1).isOnline() ? "&a" : "&c") + RankingManager.getRankings().get(i - 1).getName() + " &8[&2" + RankingManager.getRankings().get(i - 1).getPoints() + "&8]");
        }
        return "&7" + i + ".";
    }
    
    public static String getReplacementG(final Integer i) {
        if (RankingManager.getGuildRankings().size() >= i) {
            String s;
            if (i == 1) {
                s = "&a&l" + i + ". &f";
            }
            else if (i == 2) {
                s = "&a&l" + i + ". &f";
            }
            else if (i == 3) {
                s = "&a&l" + i + ". &f";
            }
            else {
                s = "&7" + i + ". &7";
            }
            return ChatUtil.fixColor(String.valueOf(s) + RankingManager.getGuildRankings().get(i - 1).getTag() + " &8[&2" + RankingManager.getGuildRankings().get(i - 1).getPoints() + "&8]");
        }
        return "&7" + i + ".";
    }
    
    public WrappedGameProfile[][] getEntries() {
        return this.entries;
    }
    
    public String[][] getSlot() {
        return this.slot;
    }
    
    public void setSLOT(final int a, final int b, final String tab) {
        this.slot[a][b] = tab;
    }
    
    public User getPlayerData() {
        return this.playerData;
    }
}