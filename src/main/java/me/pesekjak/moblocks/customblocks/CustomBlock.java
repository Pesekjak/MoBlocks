package me.pesekjak.moblocks.customblocks;

import org.bukkit.Location;

public class CustomBlock {

    private final String id;
    private final Long hardness;
    private final String tool;
    private final String level;
    private final String sound;

    public CustomBlock(String id, Long hardness, String tool, String level, String sound) {
        this.id = id;
        this.hardness = hardness;
        this.tool = tool;
        this.level = level;
        this.sound = sound;
    }

}
