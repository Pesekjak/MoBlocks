package me.pesekjak.moblocks.utils;

public class ParticleUtils {

    public static void blockCrackParticle(org.bukkit.block.Block block) {
        block.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK, block.getLocation().add(0.5, 0, 0.5), 10, block.getBlockData());
    }

}
