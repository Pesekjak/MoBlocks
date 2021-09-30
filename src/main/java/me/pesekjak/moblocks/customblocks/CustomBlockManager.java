package me.pesekjak.moblocks.customblocks;

import me.pesekjak.moblocks.utils.BlockUtils;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class CustomBlockManager {

    private static final Map<String, CustomBlock> registry = new HashMap<>();
    private static CustomBlockManager singleton;

    public static CustomBlockManager get() {
        singleton = singleton == null ? new CustomBlockManager() : singleton;
        return singleton;
    }

    public CustomBlock getCustomBlock(String name) {
        if(registry.containsKey(name)) {
            return registry.get(name);
        }
        return null;
    }

    public CustomBlock getCustomBlock(Block block) {
        String stringBlockdata = BlockUtils.getSimpleData(block);
        if(registry.containsKey(stringBlockdata)) {
            return registry.get(stringBlockdata);
        }
        return null;
    }

    public Boolean isCustomBlock(Block block) {
        String stringBlockdata = BlockUtils.getSimpleData(block);
        return registry.containsKey(stringBlockdata);
    }

    public Boolean isCustomBlock(String data) {
        return registry.containsKey(data);
    }

    public void register(CustomBlock customBlock) {
        registry.put(customBlock.getId(), customBlock);
    }

    public void unregister(String id) {
        registry.remove(id);
    }

    public Map<String, CustomBlock> getRegistry() {
        return registry;
    }

    public void clear() {
        registry.clear();
    }

}
