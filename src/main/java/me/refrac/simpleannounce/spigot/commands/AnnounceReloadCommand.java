/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.commands;

import me.refrac.simpleannounce.spigot.SimpleAnnounce;
import me.refrac.simpleannounce.spigot.tasks.AnnounceTask;
import me.refrac.simpleannounce.spigot.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceReloadCommand implements CommandExecutor {

    private final SimpleAnnounce instance;

    public AnnounceReloadCommand(SimpleAnnounce instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("simpleannounce.admin")) {
            sender.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
            return false;
        }

        instance.reloadConfig();
        Bukkit.getScheduler().cancelTasks(instance);
        Bukkit.getScheduler().runTaskTimerAsynchronously(instance, new AnnounceTask(instance), 0, instance.getConfig().getInt("Interval"));
        sender.sendMessage(Utils.format("&7Config files reloaded. Changes should be live in-game!"));
        return true;
    }
}