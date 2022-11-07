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

import lombok.Getter;
import me.refracdevelopment.simpleannounce.bungee.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Config;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Discord;
import me.refracdevelopment.simpleannounce.shared.Settings;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Files;
import me.refracdevelopment.simpleannounce.bungee.commands.AnnounceCommand;
import me.refracdevelopment.simpleannounce.bungee.commands.AnnounceReloadCommand;
import me.refracdevelopment.simpleannounce.bungee.tasks.AnnounceTask;
import me.refracdevelopment.simpleannounce.bungee.utilities.DevJoin;
import me.refracdevelopment.simpleannounce.bungee.utilities.DiscordImpl;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import java.util.concurrent.TimeUnit;

@Getter
public final class BungeeAnnounce extends Plugin {
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Files.loadFiles(this);
        Config.loadConfig();
        Discord.loadDiscord();

        this.discordImpl = new DiscordImpl();

        getProxy().getPluginManager().registerCommand(this, new AnnounceCommand(this));
        getProxy().getPluginManager().registerCommand(this, new AnnounceReloadCommand(this));
        Color.log("&aLoaded commands.");
        loadListeners();
        Color.log("&aLoaded listeners.");

        new Metrics(this, 15596);

        Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
        Color.log("&e" + Settings.getName + " has been enabled.");
        Color.log(" &f[*] &6Version&f: &b" + Settings.getVersion);
        Color.log(" &f[*] &6Name&f: &b" + Settings.getName);
        Color.log(" &f[*] &6Author&f: &b" + Settings.getDeveloper);
        Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
    }

    private void loadListeners() {
        getProxy().getPluginManager().registerListener(this, new DevJoin());

        getProxy().getScheduler().schedule(this, new AnnounceTask(this), Config.INTERVAL, Config.INTERVAL, TimeUnit.SECONDS);
    }
}