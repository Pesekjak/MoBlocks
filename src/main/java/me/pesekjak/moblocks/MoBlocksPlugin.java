package me.pesekjak.moblocks;

import io.netty.channel.Channel;
import me.pesekjak.moblocks.commands.MoBlocksCommand;
import me.pesekjak.moblocks.events.BlockDigEvent;
import me.pesekjak.moblocks.events.PlayerJoinEvent;
import me.pesekjak.moblocks.files.DataManager;
import me.pesekjak.moblocks.packets.PacketReader;
import me.pesekjak.moblocks.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class MoBlocksPlugin extends JavaPlugin {

    public static MoBlocksPlugin instance;
    public static DataManager blocks;
    public static DataManager messages;
    private final PluginManager PLUGIN_MANAGER = Bukkit.getServer().getPluginManager();

    @Override
    public void onEnable() {
        instance = this;
        blocks = new DataManager(instance, "blocks.yml");
        messages = new DataManager(instance, "messages.yml");
        registerEvents();
        registerCommands();
        for(Player player : Bukkit.getOnlinePlayers()) {
            PacketReader packetReader = new PacketReader(player);
            packetReader.inject();
        }
        Bukkit.getConsoleSender().sendMessage(ColorUtils.applyColor("&7[&3MoBlocks&7] &fThe plugin has loaded successfully!"));
        Bukkit.getConsoleSender().sendMessage(ColorUtils.applyColor("&7[&3MoBlocks&7] &f(c) Quartz Studios (https://quartzstudios.net/)"));
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer nmsPlayer = (CraftPlayer) player;
            Channel channel = nmsPlayer.getHandle().b.a.k;
            channel.pipeline().remove("MBPacketInjector");
        }
        blocks.saveConfig();
        messages.saveConfig();
        Bukkit.getConsoleSender().sendMessage(ColorUtils.applyColor("&7[&3MoBlocks&7] &fThe plugin was disabled!"));
    }

    public static MoBlocksPlugin getInstance() {
        return instance;
    }

    private void registerEvents() {
        PLUGIN_MANAGER.registerEvents(new PlayerJoinEvent(), this);
        PLUGIN_MANAGER.registerEvents(new BlockDigEvent(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(instance.getCommand("moblocks")).setExecutor(new MoBlocksCommand());
    }

}