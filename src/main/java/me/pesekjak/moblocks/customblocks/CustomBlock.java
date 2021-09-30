package me.pesekjak.moblocks.customblocks;

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