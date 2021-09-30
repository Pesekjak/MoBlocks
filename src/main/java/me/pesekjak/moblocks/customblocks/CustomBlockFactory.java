package me.pesekjak.moblocks.customblocks;

public class CustomBlockFactory {

    private String id;
    private Long hardness;
    private String tool;
    private String level;
    private String sound;

    public void setId(String id) {
        this.id = id;
    }

    public void setHardness(Long hardness) {
        this.hardness = hardness;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public CustomBlock build() {
        if (id == null || hardness == null || tool == null || level == null || sound == null) {
            return null;
        }
        return new CustomBlock(id, hardness, tool, level, sound);
    }

}
