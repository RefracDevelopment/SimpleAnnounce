/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.command.commands;

import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.command.*;
import me.refrac.simpleannounce.spigot.tasks.*;
import me.refrac.simpleannounce.spigot.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "announcereload", description = "Reloads the Simple Announce plugin", requiresPlayer = false)
public class AnnounceReloadCommand extends CommandFramework {
    private final SimpleAnnounce instance;

    public AnnounceReloadCommand(SimpleAnnounce instance) {
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission(instance.getConfig().getString("Commands.RELOAD.PERMISSION"))) {
            sender.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
            return;
        }

        instance.reloadConfig();
        instance.getFileUtil().loadDiscord();
        Bukkit.getScheduler().cancelTasks(instance);
        Bukkit.getScheduler().runTaskTimer(instance, new AnnounceTask(instance), 20L, instance.getConfig().getLong("Interval")*20);
        sender.sendMessage(Utils.format("&7Config files reloaded. Changes should be live in-game!"));
    }
}