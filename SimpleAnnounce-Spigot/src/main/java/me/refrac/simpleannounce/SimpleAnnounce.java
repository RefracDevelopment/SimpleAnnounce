/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce;

import me.refrac.simpleannounce.commands.AnnounceCommand;
import me.refrac.simpleannounce.commands.AnnounceReloadCommand;
import me.refrac.simpleannounce.tasks.AnnounceTask;
import me.refrac.simpleannounce.utils.Logger;
import me.refrac.simpleannounce.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Zachary Baldwin / Refrac
 */
public final class SimpleAnnounce extends JavaPlugin {

    private static SimpleAnnounce instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveDefaultConfig();

        getCommand("announce").setExecutor(new AnnounceCommand());
        getCommand("announcereload").setExecutor(new AnnounceReloadCommand());

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AnnounceTask(), 0, getConfig().getInt("Interval"));

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
        instance = null;

        Bukkit.getScheduler().cancelTasks(this);
    }

    public static SimpleAnnounce getInstance() {
        return instance;
    }
}