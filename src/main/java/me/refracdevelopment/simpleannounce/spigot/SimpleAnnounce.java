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

import lombok.Getter;
import me.refracdevelopment.simpleannounce.shared.Settings;
import me.refracdevelopment.simpleannounce.spigot.tasks.AnnounceTask;
import me.refracdevelopment.simpleannounce.spigot.utilities.CommandManager;
import me.refracdevelopment.simpleannounce.spigot.utilities.DevJoin;
import me.refracdevelopment.simpleannounce.spigot.utilities.DiscordImpl;
import me.refracdevelopment.simpleannounce.spigot.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Files;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class SimpleAnnounce extends JavaPlugin {
    private DiscordImpl discordImpl;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Files.loadFiles(this);

        this.discordImpl = new DiscordImpl();

        CommandManager.registerAll();
        Color.log("&aLoaded commands.");
        loadListeners();
        Color.log("&aLoaded listeners.");

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Color.log("&aHooked into PlaceholderAPI.");
        }

        new Metrics(this, 15595);

        Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
        Color.log("&e" + Settings.getName + " has been enabled.");
        Color.log(" &f[*] &6Version&f: &b" + Settings.getVersion);
        Color.log(" &f[*] &6Name&f: &b" + Settings.getName);
        Color.log(" &f[*] &6Author&f: &b" + Settings.getDeveloper);
        Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new DevJoin(), this);

        getServer().getScheduler().runTaskTimer(this, new AnnounceTask(this), Config.INTERVAL*20L, Config.INTERVAL*20L);
    }
}