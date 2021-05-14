/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.commands;

import me.refrac.simpleannounce.SimpleAnnounce;
import me.refrac.simpleannounce.tasks.AnnounceTask;
import me.refrac.simpleannounce.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("simpleannounce.admin")) {
            sender.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
            return false;
        }
        SimpleAnnounce.getInstance().reloadConfig();
        Bukkit.getScheduler().cancelTasks(SimpleAnnounce.getInstance());
        Bukkit.getScheduler().runTaskTimerAsynchronously(SimpleAnnounce.getInstance(), new AnnounceTask(), 0, SimpleAnnounce.getInstance().getConfig().getInt("Interval"));
        sender.sendMessage(Utils.format("&7Config files reloaded. Changes should be live in-game!"));
        return true;
    }
}