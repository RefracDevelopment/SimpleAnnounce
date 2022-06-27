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
package me.refrac.simpleannounce.bungee;

import me.refrac.simpleannounce.bungee.commands.*;
import me.refrac.simpleannounce.bungee.tasks.*;
import me.refrac.simpleannounce.bungee.utilities.*;
import me.refrac.simpleannounce.bungee.utilities.files.Config;
import me.refrac.simpleannounce.bungee.utilities.files.Discord;
import me.refrac.simpleannounce.shared.Settings;
import me.refrac.simpleannounce.bungee.utilities.files.Files;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class BungeeAnnounce extends Plugin {
    private static BungeeAnnounce instance;
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Files.loadFiles(this);
        Config.loadConfig();
        Discord.loadDiscord();

        this.discordImpl = new DiscordImpl();

        loadCommands();
        loadListeners();

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
        instance = null;
        getProxy().getScheduler().cancel(this);
        getProxy().getPluginManager().unregisterCommands(this);
        getProxy().getPluginManager().unregisterListeners(this);
    }

    private void loadCommands() {
        getProxy().getPluginManager().registerCommand(this, new AnnounceCommand());
        getProxy().getPluginManager().registerCommand(this, new AnnounceReloadCommand());
    }

    private void loadListeners() {
        getProxy().getPluginManager().registerListener(this, new DevJoin());

        getProxy().getScheduler().schedule(this, new AnnounceTask(), Config.INTERVAL, Config.INTERVAL, TimeUnit.SECONDS);
    }

    public static BungeeAnnounce getInstance() {
        return instance;
    }

    public DiscordImpl getDiscordImpl() {
        return discordImpl;
    }
}