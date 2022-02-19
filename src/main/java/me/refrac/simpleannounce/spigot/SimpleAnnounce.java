/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 RefracDevelopment
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package me.refrac.simpleannounce.spigot;

import me.refrac.simpleannounce.spigot.utilities.files.*;
import me.refrac.simpleannounce.shared.*;
import me.refrac.simpleannounce.spigot.command.commands.*;
import me.refrac.simpleannounce.spigot.tasks.*;
import me.refrac.simpleannounce.spigot.utilities.*;
import me.refrac.simpleannounce.spigot.utilities.chat.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleAnnounce extends JavaPlugin {
    private static SimpleAnnounce instance;
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        discordImpl = new DiscordImpl();

        Files.loadFiles(this);
        Config.loadConfig();
        Discord.loadDiscord();

        registerCommands();
        registerListeners();

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Logger.INFO.out("[" + Settings.getName + "] Hooked into PlaceholderAPI.");
        }

        Logger.NONE.out(Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
        Logger.NONE.out(Color.translate("&e" + Settings.getName + " has been enabled."));
        Logger.NONE.out(Color.translate(" &f[*] &6Version&f: &b" + Settings.getVersion));
        Logger.NONE.out(Color.translate(" &f[*] &6Name&f: &b" + Settings.getName));
        Logger.NONE.out(Color.translate(" &f[*] &6Author&f: &b" + Settings.getDeveloper));
        Logger.NONE.out(Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        getServer().getScheduler().cancelTasks(this);
    }

    private void registerCommands() {
        if (getConfig().getBoolean("Commands.ANNOUNCE.ENABLED")) {
            getCommand("announce").setExecutor(new AnnounceCommand());
        }
        if (getConfig().getBoolean("Commands.RELOAD.ENABLED")) {
            getCommand("announcereload").setExecutor(new AnnounceReloadCommand());
        }
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new DevJoin(), this);

        getServer().getScheduler().runTaskTimer(this, new AnnounceTask(), Config.INTERVAL*20L, Config.INTERVAL*20L);
    }

    public static SimpleAnnounce getInstance() {
        return instance;
    }

    public DiscordImpl getDiscordImpl() {
        return discordImpl;
    }

}