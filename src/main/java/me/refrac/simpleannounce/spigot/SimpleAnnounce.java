/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot;

import me.refrac.simpleannounce.spigot.commands.AnnounceCommand;
import me.refrac.simpleannounce.spigot.commands.AnnounceReloadCommand;
import me.refrac.simpleannounce.spigot.tasks.AnnounceTask;
import me.refrac.simpleannounce.spigot.utils.Logger;
import me.refrac.simpleannounce.spigot.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Zachary Baldwin / Refrac
 */
public final class SimpleAnnounce extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getCommand("announce").setExecutor(new AnnounceCommand(this));
        getCommand("announcereload").setExecutor(new AnnounceReloadCommand(this));

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AnnounceTask(this), 0, getConfig().getInt("Interval"));

        Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
        Logger.NONE.out(Utils.format("&e" + Utils.getName + " has been enabled."));
        Logger.NONE.out(Utils.format(" &f[*] &6Version&f: &b" + Utils.getVersion));
        Logger.NONE.out(Utils.format(" &f[*] &6Name&f: &b" + Utils.getName));
        Logger.NONE.out(Utils.format(" &f[*] &6Author&f: &b" + Utils.getDeveloper));
        Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getScheduler().cancelTasks(this);
    }
}