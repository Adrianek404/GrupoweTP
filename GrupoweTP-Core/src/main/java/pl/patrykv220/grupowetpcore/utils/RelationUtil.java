package pl.patrykv220.grupowetpcore.utils;

import pl.patrykv220.grupowetpcore.basic.Guild;
import pl.patrykv220.grupowetpcore.basic.User;

public class RelationUtil {
	public static RelationType getRelation(final Guild g1, final Guild g2) {
		return g1.getRelationGuild(g2);
	}

	public static RelationType getRelation(final User user, final Guild guild) {
		if (user.hasGuild()) {
			return user.getGuild().getRelationGuild(guild);
		}
		return RelationType.ENEMY;
	}

	public static RelationType getRelation(final User g1, final User g2) {
		if (g1.hasGuild() && g2.hasGuild()) {
			return g1.getGuild().getRelationGuild(g2.getGuild());
		}
		return RelationType.ENEMY;
	}
}
