/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.commands;

import com.google.common.base.Joiner;
import me.refrac.simpleannounce.SimpleAnnounce;
import me.refrac.simpleannounce.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(Utils.format("&b&lSimpleAnnounce &7by Refrac"));
                player.sendMessage("");
                player.sendMessage(Utils.format("&b/announce <message> &7- Announce your messages"));
                player.sendMessage(Utils.format("&b/announce reload &7- Reload your config files"));
            } else if (args.length >= 1) {
                if (SimpleAnnounce.getInstance().getConfig().getBoolean("Format.ENABLED")) {
                    if (!player.hasPermission("simpleannounce.use")) {
                        player.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
                        return false;
                    }
                    String message = Joiner.on(" ").join(args);

                    for (String format : SimpleAnnounce.getInstance().getConfig().getStringList("Format.LINES")) {
                        Bukkit.broadcastMessage(Utils.format(format.replace("{arrow}", "»").replace("{message}", message)));
                    }
                } else {
                    if (!player.hasPermission("simpleannounce.use")) {
                        player.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
                        return false;
                    }
                    String message = Joiner.on(" ").join(args);

                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.sendMessage(Utils.format(SimpleAnnounce.getInstance().getConfig().getString("Prefix") + message));
                    }
                }
            }
        }
        return false;
    }
}