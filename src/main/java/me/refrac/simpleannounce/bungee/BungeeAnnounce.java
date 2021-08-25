/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee;

import me.refrac.simpleannounce.bungee.commands.*;
import me.refrac.simpleannounce.bungee.tasks.*;
import me.refrac.simpleannounce.bungee.utils.*;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class BungeeAnnounce extends Plugin {
    private FileUtil fileUtil;
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        fileUtil = new FileUtil(this);
        discordImpl = new DiscordImpl(this);

        fileUtil.loadConfig();
        fileUtil.loadDiscord();

        registerCommands();
        registerListeners();

        if (getProxy().getPluginManager().getPlugin("PlaceholderAPI") != null) {
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
        getProxy().getScheduler().cancel(this);
        getProxy().getPluginManager().unregisterCommands(this);
        getProxy().getPluginManager().unregisterListeners(this);
    }

    private void registerCommands() {
        if (fileUtil.getConfig().getBoolean("Commands.ANNOUNCE.ENABLED")) {
            getProxy().getPluginManager().registerCommand(this, new AnnounceCommand(this));
        }
        getProxy().getPluginManager().registerCommand(this, new AnnounceDevCommand(this));
        if (fileUtil.getConfig().getBoolean("Commands.RELOAD.ENABLED")) {
            getProxy().getPluginManager().registerCommand(this, new AnnounceReloadCommand(this));
        }
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new AnnounceDevCommand(this));

        getProxy().getScheduler().schedule(this, new AnnounceTask(this), 20, fileUtil.getConfig().getInt("Interval"), TimeUnit.SECONDS);
    }

    public FileUtil getFileUtil() {
        return fileUtil;
    }

    public DiscordImpl getDiscordImpl() {
        return discordImpl;
    }

}