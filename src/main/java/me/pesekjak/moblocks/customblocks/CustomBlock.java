package me.pesekjak.moblocks.customblocks;

import me.pesekjak.moblocks.utils.BlockUtils;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CustomBlock {

    private static final List<CustomBlock> registry = new ArrayList<>();

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

    public static CustomBlock getCustomBlock(String name) {
        for (CustomBlock customBlock : registry)
            if (customBlock.getId().equalsIgnoreCase(name))
                return customBlock;
        return null;
    }

    public static CustomBlock getCustomBlock(Block block) {
        String stringBlockdata = BlockUtils.getSimpleData(block);
        for (CustomBlock customBlock : registry)
            if (customBlock.getId().equalsIgnoreCase(stringBlockdata))
                return customBlock;
        return null;
    }

    public static Boolean isCustomBlock(Block block) {
        String stringBlockdata = BlockUtils.getSimpleData(block);
        for (CustomBlock customBlock : registry)
            if (customBlock.getId().equalsIgnoreCase(stringBlockdata))
                return true;
        return false;
    }

    public static Boolean isCustomBlock(String data) {
        for (CustomBlock customBlock : registry)
            if (customBlock.getId().equalsIgnoreCase(data))
                return true;
        return false;
    }

    public static void register(CustomBlock block) {
        for (CustomBlock customBlock : registry)
            if (customBlock.getId().equals(block.getId()))
                unregister(customBlock.getId());
        registry.add(block);
    }

    public static void unregister(String id) {
        registry.remove(getCustomBlock(id));
    }

    public static void clear() {
        registry.clear();
    }

    public static List<CustomBlock> getRegistry() {
        return registry;
    }

    public String getId() {
        return id;
    }

    public Long getHardness() {
        return hardness;
    }

    public String getTool() {
        return tool;
    }

    public String getLevel() {
        return level;
    }

    public String getSound() {
        return sound;
    }

}