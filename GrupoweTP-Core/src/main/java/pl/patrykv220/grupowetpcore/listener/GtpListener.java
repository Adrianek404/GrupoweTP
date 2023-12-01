package pl.patrykv220.grupowetpcore.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;

import pl.yspar.core.Locations;
import pl.yspar.core.manager.UserManager;
import pl.yspar.core.utils.ChatUtil;
import pl.yspar.core.utils.RandomUtil;



public class GtpListener implements Listener {

    @EventHandler
    public void onGTP1vs1(final PlayerInteractEvent e) {
        if (!e.hasBlock()) {
            return;
        }
        final Block c = e.getClickedBlock();
        if (c.getType() != Material.STONE_BUTTON) {
            return;
        }
        final Button b = (Button)c.getState().getData();
        final Block block = c.getRelative(b.getAttachedFace());
        if (block.getType() != Material.JUKEBOX) {
            return;
        }
        final Block ba = e.getPlayer().getLocation().getBlock();

        final List<Player> x = getPlayersInRadius(e.getClickedBlock().getLocation(), 4, Material.BEDROCK);
        final List<Player> y = getPlayersInRadius(e.getClickedBlock().getLocation(), 4, Material.BEDROCK);
        if (x.size() == 1) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&4[!] &cNie mozesz teleportowac sie samemu.");
            return;
        }
        if (y.size() == 1) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&4[!] &cNie mozesz teleportowac sie samemu.");
            return;
        }
        teleport(y, 2);
    }
    
    @EventHandler
    public void onGTPXvsX(final PlayerInteractEvent e) {
        if (!e.hasBlock()) {
            return;
        }
        final Block c = e.getClickedBlock();
        if (c.getType() != Material.STONE_BUTTON) {
            return;
        }
        final Button b = (Button)c.getState().getData();
        final Block block = c.getRelative(b.getAttachedFace());
        if (block.getType() != Material.JUKEBOX) {
            return;
        }
        final Block ba = e.getPlayer().getLocation().getBlock();

        final List<Player> ps = getPlayersInRadius(e.getClickedBlock().getLocation(), 4, Material.BEDROCK);
        final List<Player> ps2 = getPlayersInRadius(e.getClickedBlock().getLocation(), 4, Material.BEDROCK);
        if (ps.size() == 1) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&4[!] &cNie mozesz teleportowac sie samemu.");
            return;
        }
        if (ps2.size() == 1) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&4[!] &cNie mozesz teleportowac sie samemu.");
            return;
        }
        for (final Player players : this.getPlayersRadius(e.getClickedBlock().getLocation(), 2)) {
            final int x = RandomUtil.getRandInt(Locations.RANDOMTP_OD, Locations.RANDOMTP_DO);
            final int z = RandomUtil.getRandInt(Locations.RANDOMTP_OD, Locations.RANDOMTP_DO);
            final Location loc = new Location(e.getPlayer().getWorld(), (double)x, (double)(e.getPlayer().getWorld().getHighestBlockYAt(x, z) + 1.0f), (double)z);
            e.getPlayer().teleport(loc);
            players.teleport(e.getPlayer().getLocation());
        }
    }
    
    public static void teleport(final List<Player> players, final int i) {
        final int x = RandomUtil.getRandInt(Locations.RANDOMTP_OD, Locations.RANDOMTP_DO);
        final int z = RandomUtil.getRandInt(Locations.RANDOMTP_OD, Locations.RANDOMTP_DO);
        final Location loc = new Location(Bukkit.getWorld("gtp"), (double)x, (double)(Bukkit.getWorld("gtp").getHighestBlockYAt(x, z) + 1.0f), (double)z);
        int x2 = 0;
        for (final Player target : players) {
            if (x2 == i) {
                return;
            }
            target.getActivePotionEffects().clear();
            target.teleport(loc);
            UserManager.loadItems(target);
            ++x2;
        }
    }
    
    public static List<Player> getPlayersInRadius(final Location location, final int size, final Material mat) {
        final List<Player> players = new ArrayList<Player>();
        for (final Player p : location.getWorld().getPlayers()) {
            if (location.distance(p.getLocation()) <= size) {
                final Block b = p.getLocation().getBlock();
                final Block b2 = b.getRelative(BlockFace.DOWN);
                if (!b.getType().equals((Object)mat) && !b2.getType().equals((Object)mat)) {
                    continue;
                }
                players.add(p);
                if (players.size() == 2) {
                    return players;
                }
                continue;
            }
        }
        return players;
    }
    
    public List<Player> getPlayersRadius(final Location location, final int size) {
        final List<Player> players = new ArrayList<Player>();
        for (final Player p : location.getWorld().getPlayers()) {
            if (location.distance(p.getLocation()) <= size) {
                players.add(p);
            }
        }
        return players;
    }
}