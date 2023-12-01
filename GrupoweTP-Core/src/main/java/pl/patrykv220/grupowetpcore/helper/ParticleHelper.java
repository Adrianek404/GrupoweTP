package pl.patrykv220.grupowetpcore.helper;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import net.minecraft.server.v1_8_R3.*;

public class ParticleHelper
{
    public static void spawnParticle(final Player player, final EnumParticle particle, final Location location) {
        final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float)location.getX(), (float)(location.getY() + 0.1), (float)location.getZ(), 0.0f, 0.0f, 0.0f, 0.0f, 20, new int[] { 10 });
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}

