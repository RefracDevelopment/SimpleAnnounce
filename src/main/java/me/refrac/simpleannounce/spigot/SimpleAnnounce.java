/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot;

import me.refrac.simpleannounce.spigot.command.commands.*;
import me.refrac.simpleannounce.spigot.tasks.*;
import me.refrac.simpleannounce.spigot.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleAnnounce extends JavaPlugin {
    private FileUtil fileUtil;
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        fileUtil = new FileUtil(this);
        discordImpl = new DiscordImpl(this);

        saveDefaultConfig();
        fileUtil.loadDiscord();

        registerCommands();
        registerListeners();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Logger.INFO.out("[" + Utils.getName + "] Hooked into PlaceholderAPI.");
        }

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
        getServer().getScheduler().cancelTasks(this);
    }

    private void registerCommands() {
        if (getConfig().getBoolean("Commands.ANNOUNCE.ENABLED")) {
            getCommand("announce").setExecutor(new AnnounceCommand(this));
        }
        getCommand("announcedev").setExecutor(new AnnounceDevCommand(this));
        if (getConfig().getBoolean("Commands.RELOAD.ENABLED")) {
            getCommand("announcereload").setExecutor(new AnnounceReloadCommand(this));
        }
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new AnnounceDevCommand(this), this);

        getServer().getScheduler().runTaskTimer(this, new AnnounceTask(this), 20L, getConfig().getLong("Interval")*20);
    }

    public FileUtil getFileUtil() {
        return fileUtil;
    }

    public DiscordImpl getDiscordImpl() {
        return discordImpl;
    }

}