/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee;

import com.google.common.io.ByteStreams;
import me.refrac.simpleannounce.bungee.commands.AnnounceCommand;
import me.refrac.simpleannounce.bungee.commands.AnnounceReloadCommand;
import me.refrac.simpleannounce.bungee.tasks.AnnounceTask;
import me.refrac.simpleannounce.bungee.utils.Logger;
import me.refrac.simpleannounce.bungee.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Zachary Baldwin / Refrac
 */
public final class BungeeAnnounce extends Plugin {

    private static Configuration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AnnounceCommand(this));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AnnounceReloadCommand(this));

        ProxyServer.getInstance().getScheduler().schedule(this, new AnnounceTask(this), 0, getConfig().getInt("Interval"), TimeUnit.SECONDS);

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
        ProxyServer.getInstance().getScheduler().cancel(this);
        ProxyServer.getInstance().getPluginManager().unregisterCommands(this);
    }

    public Configuration getConfig() {
        return config;
    }

    public void loadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(
                    loadResource(this, "bungee-config.yml"));
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
}