/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.utils;

import me.refrac.simpleannounce.spigot.SimpleAnnounce;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private final SimpleAnnounce instance;
    private File discordFile;
    private FileConfiguration discord;

    public FileUtil(SimpleAnnounce instance) {
        this.instance = instance;
    }

    public void loadDiscord() {
        discordFile = new File(instance.getDataFolder(), "discord.yml");
        if (!discordFile.exists()) {
            discordFile.getParentFile().mkdirs();
            instance.saveResource("discord.yml", false);
        }

        discord = new YamlConfiguration();
        try {
            discord.load(discordFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getDiscord() {
        return discord;
    }

}