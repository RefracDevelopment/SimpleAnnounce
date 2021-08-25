/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

public class AnnounceTask implements Runnable {
    private final SimpleAnnounce instance;

    public AnnounceTask(SimpleAnnounce instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        Set<String> broadcastList = instance.getConfig().getConfigurationSection("Announcements").getKeys(false);

        if (broadcastList.isEmpty()) {
            Logger.WARNING.out("[SimpleAnnounce] There are no announcements :(");
            Bukkit.getServer().getScheduler().cancelTasks(instance);
            return;
        }

        String broadcastId = getRandom(broadcastList);
        ConfigurationSection broadcast = instance.getConfig().getConfigurationSection("Announcements." + broadcastId);

        for (String message : broadcast.getStringList("LINES")) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(PlaceholderAPI.setPlaceholders(p, Utils.format(
                        message.replace("{arrow}", "»")))));
            } else {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Utils.format(
                        message.replace("{arrow}", "»"))));
            }
        }

        if (broadcast.getBoolean("SOUND.ENABLED") && broadcast.getString("SOUND.NAME") != null) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                try {
                    p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("SOUND.NAME")),
                            (float) broadcast.getInt("SOUND.VOLUME"), (float) broadcast.getInt("SOUND.PITCH"));
                } catch (Exception e) {
                    if (p.hasPermission("simpleannounce.admin")) {
                        p.sendMessage(ChatColor.RED + "Something went wrong with the Sound Name. Check console for more information.");
                    }
                    Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                    Logger.ERROR.out("SimpleAnnounce - Sound Error");
                    Logger.NONE.out("");
                    Logger.ERROR.out("The Sound '" + broadcast.getString("SOUND.NAME") + "' is invalid!");
                    Logger.ERROR.out("Please make sure your SOUND NAME is correct.");
                    Logger.NONE.out("");
                    Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                }
            }
        }
    }

    private String getRandom(Set<String> set) {
        int index = (new Random()).nextInt(set.size());
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}