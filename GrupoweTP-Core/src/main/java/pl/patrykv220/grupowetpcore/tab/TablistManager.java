package pl.patrykv220.grupowetpcore.tab;

import java.util.List;
import com.comphenix.protocol.events.PacketContainer;
import java.lang.reflect.InvocationTargetException;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import pl.yspar.core.Config;
import pl.yspar.core.utils.ChatUtil;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import java.util.ArrayList;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TablistManager {
    private static ConcurrentHashMap<UUID, Tab> cache = new ConcurrentHashMap();

    public static boolean isTab(UUID uuid) {
        return cache.containsKey(uuid);
    }

    public static Tab getTab(UUID uuid) {
        return cache.get(uuid);
    }

    public static void executeCreate(Player player) {
        if (TablistManager.isTab(player.getUniqueId())) {
            return;
        }
        Tab t2 = new Tab(player);
        PacketContainer container = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        PacketContainer pc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        ArrayList<PlayerInfoData> list = new ArrayList<PlayerInfoData>();
        for (int i2 = 0; i2 < 4; ++i2) {
            for (int j2 = 0; j2 < 20; ++j2) {
                PlayerInfoData playerInfoData = new PlayerInfoData(t2.getEntries()[i2][j2], 0, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText((String)ChatUtil.fixColor(t2.getSlot()[i2][j2])));
                list.add(playerInfoData);
            }
        
    }
        EnumWrappers.PlayerInfoAction action = EnumWrappers.PlayerInfoAction.ADD_PLAYER;
        container.getPlayerInfoAction().write(0, (PlayerInfoAction)action);
        container.getPlayerInfoDataLists().write(0, list);
        pc.getChatComponents().write(0, (WrappedChatComponent)WrappedChatComponent.fromText(ChatUtil.fixColor(Config.SERWERNAME + "\n&7      Na serwerze znajduje sie &a" + Bukkit.getOnlinePlayers().size() + "&7 graczy\n"))).write(1, (WrappedChatComponent)WrappedChatComponent.fromText(ChatUtil.fixColor("")));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, pc);
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        cache.put(player.getUniqueId(), t2);
    }

    public static void executeRemove(Player player) {
        if (!TablistManager.isTab(player.getUniqueId())) {
            return;
        }
        PacketContainer container = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        EnumWrappers.PlayerInfoAction action = EnumWrappers.PlayerInfoAction.REMOVE_PLAYER;
        container.getPlayerInfoAction().write(0, (PlayerInfoAction)action);
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        cache.remove(player.getUniqueId());
    }

    public static void update(Player player) {
    	executeCreate(player);
        int i2;
        Tab t2 = TablistManager.getTab(player.getUniqueId());
        if (t2 == null) {
            return;
        }
        t2.update();
        PacketContainer container = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        PacketContainer pc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        pc.getChatComponents().write(0, (WrappedChatComponent)WrappedChatComponent.fromText(ChatUtil.fixColor(Config.SERWERNAME + "\n&7      Na serwerze znajduje sie &a" + Bukkit.getOnlinePlayers().size() + "&7 graczy\n"))).write(1, (WrappedChatComponent)WrappedChatComponent.fromText(ChatUtil.fixColor("")));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, pc);
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
        cache.put(player.getUniqueId(), t2);
        EnumWrappers.PlayerInfoAction action = EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME;
        container.getPlayerInfoAction().write(0, (PlayerInfoAction)action);
        ArrayList<PlayerInfoData> list = new ArrayList<PlayerInfoData>();
        for (i2 = 0; i2 < 16; ++i2) {
            list.add(new PlayerInfoData(t2.getEntries()[0][i2 + 3], 0, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText((String)ChatUtil.fixColor(t2.getSlot()[0][i2 + 3]))));
        }
        for (i2 = 0; i2 < 16; ++i2) {
            list.add(new PlayerInfoData(t2.getEntries()[1][i2 + 3], 0, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText((String)ChatUtil.fixColor(t2.getSlot()[1][i2 + 3]))));
        }
        for (i2 = 0; i2 < 16; ++i2) {
            list.add(new PlayerInfoData(t2.getEntries()[2][i2 + 3], 0, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText((String)ChatUtil.fixColor(t2.getSlot()[2][i2 + 3]))));
        }
        for (i2 = 0; i2 < 16; ++i2) {
            list.add(new PlayerInfoData(t2.getEntries()[3][i2 + 3], 0, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText((String)ChatUtil.fixColor(t2.getSlot()[3][i2 + 3]))));
        }
        container.getPlayerInfoDataLists().write(0, list);
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, container);
        }
        catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }
}

