package pl.patrykv220.grupowetpcore.command.guild;

import java.io.DataOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import pl.yspar.core.CorePlugin;
import pl.yspar.core.basic.Guild;
import pl.yspar.core.basic.User;
import pl.yspar.core.command.PlayerCommand;
import pl.yspar.core.manager.GuildManager;
import pl.yspar.core.manager.RankingManager;
import pl.yspar.core.store.MySQL;
import pl.yspar.core.task.ActionType;

import pl.yspar.core.utils.ChatUtil;

public class DeleteCommand extends PlayerCommand
{
    public DeleteCommand() {
        super("zamknij", "usuwa gildie", "/zamknij", "core.cmd.user", new String[] { "usun" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = User.get(p).getGuild();

        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cNie posiadasz gildii!");
        }
        if (!g.isOwner(User.get(p))) {
            return ChatUtil.sendMessage((CommandSender)p, "&4Blad: &cNie jestes zalozycielem gildii!");
        }
        Bukkit.broadcastMessage(ChatUtil.fixColor("&aGildie &8★ &7Gracz &f" + p.getName() + " &7usunal gildie &8[&2" + g.getTag() + "&8] &a" + g.getName()));
        RankingManager.removeRanking(g);
        RankingManager.sortGuildRankings();
        GuildManager.deleteGuild(g);
        MySQL.getInst().load();
        MySQL.getInst().save();
           
           
        

        return false;
    }
}
