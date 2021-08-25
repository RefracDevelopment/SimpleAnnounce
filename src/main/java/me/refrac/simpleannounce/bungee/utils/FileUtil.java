/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.utils;

import com.google.common.io.ByteStreams;
import me.refrac.simpleannounce.bungee.BungeeAnnounce;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class FileUtil {
    private final BungeeAnnounce instance;
    private Configuration config;
    private Configuration discord;

    public FileUtil(BungeeAnnounce instance) {
        this.instance = instance;
    }

    public void loadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(
                    loadResource(instance, "bungee-config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDiscord() {
        try {
            discord = ConfigurationProvider.getProvider(YamlConfiguration.class).load(
                    loadResource(instance, "discord.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File loadResource(Plugin plugin, String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdir();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResourceAsStream(resource);
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }

    public Configuration getConfig() {
        return config;
    }

    public Configuration getDiscord() {
        return discord;
    }
}