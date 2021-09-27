package me.pesekjak.moblocks.packets;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import me.pesekjak.moblocks.MoBlocksPlugin;
import me.pesekjak.moblocks.customevents.BlockDigCustomEvent;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.List;

public class PacketReader {

    private final Player player;

    public PacketReader(Player player) {
        this.player = player;
    }

    public boolean inject() {
        CraftPlayer nmsPlayer = (CraftPlayer) player;
        Channel channel = nmsPlayer.getHandle().b.a.k;

        if (channel.pipeline().get("MBPacketInjector") != null) {
            return false;
        }

        channel.pipeline().addAfter("decoder", "MBPacketInjector", new MessageToMessageDecoder<PacketPlayInBlockDig>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, PacketPlayInBlockDig packetPlayInBlockDig, List<Object> list) throws Exception {

                list.add(packetPlayInBlockDig);
                readBlockDig(packetPlayInBlockDig);

            }
        });
        return true;
    }

    private void readBlockDig(PacketPlayInBlockDig packetPlayInBlockDig) {

        BlockPosition location = (BlockPosition) getValue(packetPlayInBlockDig, "a");
        PacketPlayInBlockDig.EnumPlayerDigType digType = (PacketPlayInBlockDig.EnumPlayerDigType) getValue(packetPlayInBlockDig, "c");

        Location bukkitLocation = new Location(player.getWorld(), location.getX(), location.getY(), location.getZ());

        if(digType == PacketPlayInBlockDig.EnumPlayerDigType.a || digType == PacketPlayInBlockDig.EnumPlayerDigType.b) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(new BlockDigCustomEvent(player, bukkitLocation, digType));
                }
            }.runTaskLater(MoBlocksPlugin.getInstance(), 0);
        }

    }

    private Object getValue(Object instance, String name) {
        Object result = null;
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            result = field.get(instance);
            field.setAccessible(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
