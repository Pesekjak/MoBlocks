package me.pesekjak.moblocks.packets;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayOutBlockBreakAnimation {

    private final Integer id;
    private final Location location;
    private final Integer stage;

    public PlayOutBlockBreakAnimation(Integer id, Location location, Integer stage) {
        this.id = id;
        this.location = location;
        this.stage = stage;
    }

    public void send(Player player) {
        BlockPosition blockPosition = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(id, blockPosition, stage);
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().b;
        playerConnection.sendPacket(packet);
    }

}
