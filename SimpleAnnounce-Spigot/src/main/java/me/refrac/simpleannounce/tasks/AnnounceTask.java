/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.tasks;

import me.refrac.simpleannounce.SimpleAnnounce;
import me.refrac.simpleannounce.utils.Logger;
import me.refrac.simpleannounce.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceTask implements Runnable {

    @Override
    public void run() {
        Set<String> broadcastList = SimpleAnnounce.getInstance().getConfig().getConfigurationSection("Announcements").getKeys(false);
        String broadcastId = getRandom(broadcastList);
        ConfigurationSection broadcast = SimpleAnnounce.getInstance().getConfig().getConfigurationSection("Announcements." + broadcastId);
        for (String message : broadcast.getStringList("LINES")) {
            Bukkit.getOnlinePlayers().forEach((player -> player.sendMessage(Utils.format(message.replace("{arrow}", "»")))));
        }

        if (broadcast.getBoolean("SOUND.ENABLED") &&
                broadcast.getString("SOUND.NAME") != null) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                try {
                    p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("SOUND.NAME")), 5.0F, 5.0F);
                } catch (Exception e) {
                    if (p.hasPermission("simpleannounce.admin")) {
                        p.sendMessage(ChatColor.RED + "Something went wrong with the Sound Name. Check console for more information.");
                    }
                    Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                    Logger.ERROR.out("SimpleAnnounce - Sound Error");
                    Logger.NONE.out("");
                    Logger.ERROR.out("You have not setup your SOUND NAME for '" + broadcast + "' correctly!");
                    Logger.ERROR.out("Please make sure your SOUND NAME is correct.");
                    Logger.NONE.out("");
                    Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                }
            }
        }
    }

    private static String getRandom(Set<String> set) {
        int index = (new Random()).nextInt(set.size());
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}