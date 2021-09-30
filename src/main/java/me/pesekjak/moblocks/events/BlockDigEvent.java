package me.pesekjak.moblocks.events;

import me.pesekjak.moblocks.MoBlocksPlugin;
import me.pesekjak.moblocks.customevents.BlockDigCustomEvent;
import me.pesekjak.moblocks.utils.BlockUtils;
import net.minecraft.network.protocol.game.PacketPlayInBlockDig;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collection;

public class BlockDigEvent implements Listener {


    @EventHandler
    public void onBlockDig(final BlockDigCustomEvent e) {

        Block block = e.getLocation().getBlock();
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, -1, false, false, false);
        PotionEffect[] potionArray = {potionEffect};
        Collection<PotionEffect> potionCollection = Arrays.asList(potionArray);

        String stringBlockdata = BlockUtils.getSimpleData(block);

        if(!(MoBlocksPlugin.blocks.getConfig().get("custom blocks." + stringBlockdata + ".hardness") == null)) {
            if(e.getAction() == PacketPlayInBlockDig.EnumPlayerDigType.a) {
                e.getPlayer().addPotionEffects(potionCollection);
            }
            else if (e.getAction() == PacketPlayInBlockDig.EnumPlayerDigType.b) {
                e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
            }
        }

    }

    public boolean isRightTool(ItemStack tool, String category) {
        if(category.equals("hand")) {
            return true;
        }
        return tool.getType().toString().equalsIgnoreCase(category);
    }

}
