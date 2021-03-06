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
package me.refracdevelopment.simpleannounce.spigot.utilities.files;

import me.refracdevelopment.simpleannounce.spigot.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.spigot.utilities.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Files {
    private static File configFile;
    private static FileConfiguration config;
    private static File discordFile;
    private static FileConfiguration discord;

    public static void loadFiles(SimpleAnnounce announce) {
        if (!announce.getDataFolder().exists()) {
            announce.getDataFolder().mkdirs();
        }

        configFile = new File(announce.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            announce.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        discordFile = new File(announce.getDataFolder(), "discord.yml");
        if (!discordFile.exists()) {
            announce.saveResource("discord.yml", false);
        }
        discord = YamlConfiguration.loadConfiguration(discordFile);

        Config.loadConfig();
        Discord.loadDiscord();

        Logger.NONE.out("&c==========================================");
        Logger.NONE.out("&aAll files have been loaded correctly!");
        Logger.NONE.out("&c==========================================");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getDiscord() {
        return discord;
    }

    public static void reloadFiles(SimpleAnnounce announce) {
        configFile = new File(announce.getDataFolder(), "config.yml");
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch (Exception e) {
            Logger.ERROR.out("Failed to reload the config file!");
            e.printStackTrace();
        }

        discordFile = new File(announce.getDataFolder(), "discord.yml");
        try {
            discord = YamlConfiguration.loadConfiguration(discordFile);
        } catch (Exception e) {
            Logger.ERROR.out("Failed to reload the discord file!");
            e.printStackTrace();
        }

        Config.loadConfig();
        Discord.loadDiscord();

        Logger.NONE.out("&c==========================================");
        Logger.NONE.out("&aAll files have been loaded correctly!");
        Logger.NONE.out("&c==========================================");
    }
}