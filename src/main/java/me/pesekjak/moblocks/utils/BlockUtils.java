package me.pesekjak.moblocks.utils;

import org.bukkit.block.Block;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockUtils {

    private static final Pattern dataPattern = Pattern.compile("\\{[^}]*\\}");
    public static String getSimpleData(Block block) {
        String stringBlockdata = block.getBlockData().toString();
        Matcher matcher = dataPattern.matcher(stringBlockdata);
        if (matcher.find()) {
            stringBlockdata = matcher.group().substring(1, matcher.group().length() - 1);
            return stringBlockdata;
        }
        return null;
    }

}
