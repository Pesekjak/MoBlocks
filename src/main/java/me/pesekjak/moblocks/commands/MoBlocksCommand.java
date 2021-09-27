package me.pesekjak.moblocks.commands;

import me.pesekjak.moblocks.MoBlocksPlugin;
import me.pesekjak.moblocks.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoBlocksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ColorUtils.applyColor("&7[&3MoBlocks&7] &fOnly players can use this command!"));
        }
        else {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length == 0) {
                    sendHelp(player);
                    return true;
                }

                switch (args[0]) {
                    case "reload" -> {
                        MoBlocksPlugin.blocks.reloadConfig();
                        MoBlocksPlugin.messages.reloadConfig();
                        player.sendMessage(ColorUtils.applyColor("<#20df79>&lMoBlocks &r<#51615b>━ <#91aca1>Config of plugin reloaded successfully."));
                    }
                    case "about" -> player.sendMessage(ColorUtils.applyColor("<#20df79>&lMoBlocks <#91aca1>is a plugin that adds behavior for custom blocks created by QuartzStudios."));
                    default -> sendHelp(player);
                }
            }
            else {
                player.sendMessage(ColorUtils.applyColor("<#ef442a>Only players with op can execute this command!"));
                player.playSound(player.getLocation(), "entity.illusioner.hurt", 1, 1);
            }
        }
        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage(ColorUtils.applyColor("<#51615b>&m------------&r <#20df79>&lMoBlocks <#51615b>&m------------"));
        player.sendMessage(ColorUtils.applyColor("<#13a067>■ reload <#51615b>- <#91aca1>Reloads the config."));
        player.sendMessage(ColorUtils.applyColor("<#13a067>■ about <#51615b>- <#91aca1>Shows about page."));
    }
}
