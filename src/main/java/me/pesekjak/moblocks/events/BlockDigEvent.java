package me.pesekjak.moblocks.events;

import me.pesekjak.moblocks.MoBlocksPlugin;
import me.pesekjak.moblocks.customevents.BlockDigCustomEvent;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockDigEvent implements Listener {

    private final Pattern dataPattern = Pattern.compile("\\{[^}]*\\}");

    @EventHandler
    public void onBlockDig(final BlockDigCustomEvent e) {

        Block block = e.getLocation().getBlock();
        BlockData blockdata = block.getBlockData();
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, -1, false, false, false);
        PotionEffect[] potionArray = {potionEffect};
        Collection<PotionEffect> potionCollection = Arrays.asList(potionArray);

        String stringBlockdata = blockdata.toString();

        Matcher matcher = dataPattern.matcher(stringBlockdata);
        if (matcher.find()) {
            stringBlockdata = matcher.group().substring(1, matcher.group().length() - 1);
        }

        if(!(MoBlocksPlugin.blocks.getConfig().get("custom blocks." + stringBlockdata + ".hardness") == null)) {
            if(e.getAction() == PacketPlayInBlockDig.EnumPlayerDigType.a) {
                e.getPlayer().addPotionEffects(potionCollection);
            }
            else if (e.getAction() == PacketPlayInBlockDig.EnumPlayerDigType.b) {
                e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
            }
        }

    }

}
