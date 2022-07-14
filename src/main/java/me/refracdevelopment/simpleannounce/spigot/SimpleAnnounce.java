/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 RefracDevelopment
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
package me.refracdevelopment.simpleannounce.spigot;

import me.refracdevelopment.simpleannounce.shared.Settings;
import me.refracdevelopment.simpleannounce.spigot.command.commands.AnnounceCommand;
import me.refracdevelopment.simpleannounce.spigot.command.commands.AnnounceReloadCommand;
import me.refracdevelopment.simpleannounce.spigot.tasks.AnnounceTask;
import me.refracdevelopment.simpleannounce.spigot.utilities.DevJoin;
import me.refracdevelopment.simpleannounce.spigot.utilities.DiscordImpl;
import me.refracdevelopment.simpleannounce.spigot.utilities.Logger;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Files;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SimpleAnnounce extends JavaPlugin {
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Files.loadFiles(this);

        this.discordImpl = new DiscordImpl();

        loadCommands();
        loadListeners();

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Logger.INFO.out("Hooked into PlaceholderAPI.");
        }

        new Metrics(this, 15595);

        Logger.NONE.out("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
        Logger.NONE.out("&e" + Settings.getName + " has been enabled.");
        Logger.NONE.out(" &f[*] &6Version&f: &b" + Settings.getVersion);
        Logger.NONE.out(" &f[*] &6Name&f: &b" + Settings.getName);
        Logger.NONE.out(" &f[*] &6Author&f: &b" + Settings.getDeveloper);
        Logger.NONE.out("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getScheduler().cancelTasks(this);
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("announce")).setExecutor(new AnnounceCommand(this));
        Objects.requireNonNull(getCommand("announcereload")).setExecutor(new AnnounceReloadCommand(this));
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new DevJoin(), this);

        getServer().getScheduler().runTaskTimer(this, new AnnounceTask(this), Config.INTERVAL*20L, Config.INTERVAL*20L);
    }

    public DiscordImpl getDiscordImpl() {
        return discordImpl;
    }
}