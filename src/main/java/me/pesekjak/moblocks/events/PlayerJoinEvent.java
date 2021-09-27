package me.pesekjak.moblocks.events;

import me.pesekjak.moblocks.packets.PacketReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onJoin(final org.bukkit.event.player.PlayerJoinEvent e) {
        PacketReader packetReader = new PacketReader(e.getPlayer());
        packetReader.inject();
    }

}
