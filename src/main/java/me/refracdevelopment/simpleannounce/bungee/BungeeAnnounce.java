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
package me.refracdevelopment.simpleannounce.bungee;

import me.refracdevelopment.simpleannounce.bungee.utilities.files.Config;
import me.refracdevelopment.simpleannounce.shared.Settings;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Files;
import me.refracdevelopment.simpleannounce.bungee.commands.AnnounceCommand;
import me.refracdevelopment.simpleannounce.bungee.commands.AnnounceReloadCommand;
import me.refracdevelopment.simpleannounce.bungee.tasks.AnnounceTask;
import me.refracdevelopment.simpleannounce.bungee.utilities.DevJoin;
import me.refracdevelopment.simpleannounce.bungee.utilities.DiscordImpl;
import me.refracdevelopment.simpleannounce.bungee.utilities.Logger;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import java.util.concurrent.TimeUnit;

public final class BungeeAnnounce extends Plugin {
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Files.loadFiles(this);

        this.discordImpl = new DiscordImpl();

        loadCommands();
        loadListeners();

        new Metrics(this, 15596);

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
        getProxy().getScheduler().cancel(this);
        getProxy().getPluginManager().unregisterCommands(this);
        getProxy().getPluginManager().unregisterListeners(this);
    }

    private void loadCommands() {
        getProxy().getPluginManager().registerCommand(this, new AnnounceCommand(this));
        getProxy().getPluginManager().registerCommand(this, new AnnounceReloadCommand(this));
    }

    private void loadListeners() {
        getProxy().getPluginManager().registerListener(this, new DevJoin());

        getProxy().getScheduler().schedule(this, new AnnounceTask(this), Config.INTERVAL, Config.INTERVAL, TimeUnit.SECONDS);
    }

    public DiscordImpl getDiscordImpl() {
        return discordImpl;
    }
}