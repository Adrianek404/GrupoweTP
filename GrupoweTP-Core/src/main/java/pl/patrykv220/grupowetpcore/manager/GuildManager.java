package pl.patrykv220.grupowetpcore.manager;

import java.util.concurrent.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;


import pl.patrykv220.grupowetpcore.Main;
import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;
import pl.patrykv220.grupowetpcore.store.MySQL;
import pl.patrykv220.grupowetpcore.utils.Logger;

import java.util.*;

import java.sql.*;
import org.bukkit.*;


public class GuildManager {
	private static List<Guild> guilds = new ArrayList<Guild>();

	public static List<Guild> getGuilds(List<String> tags) {
		Iterator<String> iterator;
		ArrayList<Guild> guildList = new ArrayList<Guild>();
		Iterator<String> iterator2 = iterator = tags.iterator();
		while (iterator2.hasNext()) {
			Guild guild = Guild.get(iterator.next());
			if (guild == null) {
				iterator2 = iterator;
				continue;
			}
			guildList.add(guild);
			iterator2 = iterator;
		}
		return guildList;
	}
	
	public static List<Guild> getGuilds() {
		return new ArrayList<Guild>(guilds);
	}

	


	public static List<String> getTags(List<Guild> guildList) {
		Iterator<Guild> iterator;
		ArrayList<String> tags = new ArrayList<String>();
		Iterator<Guild> iterator2 = iterator = guildList.iterator();
		while (iterator2.hasNext()) {
			Guild guild = iterator.next();
			iterator2 = iterator;
			tags.add(guild.getTag());
		}
		return tags;
	}


	

	public static void remove(Guild guild) {
		if (guilds.contains(guild)) {
			guilds.remove(guild);
		}
	}

	public static void add(Guild guild) {
		if (!guilds.contains(guild)) {
			guilds.add(guild);
		}
	}


	public static void deleteGuild(Guild guild) {
		Guild guild2 = guild;
		guild2.setChanges(false);


		for (User member : guild.getMembers()) {
			member.setGuild(null);
		}

		MySQL.getInst().removeGuild(guild.getTag());

		GuildManager.remove(guild);
		MySQL.getInst().save();
	}
}