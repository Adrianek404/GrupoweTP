package pl.patrykv220.grupowetpcore.utils;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class PacketEquipment
{
    public static void sendEquipment(final Player player, final int id, final int slot, final ItemStack item) {
        try {
            final Class<?> packetClass = ReflectUtils.getCraftClass("PacketPlayOutEntityEquipment");
            final Object packet = packetClass.newInstance();
            ReflectUtils.setValue(ReflectUtils.getField(packet.getClass(), "a"), packet, id);
            ReflectUtils.setValue(ReflectUtils.getField(packet.getClass(), "b"), packet, slot);
            ReflectUtils.setValue(ReflectUtils.getField(packet.getClass(), "c"), packet, ReflectUtils.getMethod(ReflectUtils.getBukkitClass("inventory.CraftItemStack"), "asNMSCopy", ItemStack.class).invoke(null, item));
            Sender.sendPacket(player, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

