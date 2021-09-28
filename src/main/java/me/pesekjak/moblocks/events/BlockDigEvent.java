package me.pesekjak.moblocks.events;

import me.pesekjak.moblocks.MoBlocksPlugin;
import me.pesekjak.moblocks.customevents.BlockDigCustomEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockDigEvent implements Listener {

    private final Pattern dataPattern = Pattern.compile("\\{[^}]*\\}");

    @EventHandler
    public void onBlockDig(final BlockDigCustomEvent e) {

        Block block = e.getLocation().getBlock();
        BlockData blockdata = block.getBlockData();

        String stringBlockdata = blockdata.toString();

        Matcher matcher = dataPattern.matcher(stringBlockdata);
        if (matcher.find()) {
            stringBlockdata = matcher.group().substring(1, matcher.group().length() - 1);
        }

        if(!(MoBlocksPlugin.blocks.getConfig().get("custom blocks." + stringBlockdata + ".hardness") == null)) {
            e.getPlayer().sendMessage(stringBlockdata + " is a custom block");
        }

    }

}
