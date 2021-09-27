package me.pesekjak.moblocks.customevents;

import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BlockDigCustomEvent extends Event {

    private final Player player;
    private final Location location;
    private final PacketPlayInBlockDig.EnumPlayerDigType action;

    private static final HandlerList HANDLERS = new HandlerList();

    public BlockDigCustomEvent(Player player, Location location, PacketPlayInBlockDig.EnumPlayerDigType action) {
        this.player = player;
        this.location = location;
        this.action = action;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location;
    }

    public PacketPlayInBlockDig.EnumPlayerDigType getAction() {
        return action;
    }

}
