/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.tasks;

import me.refrac.simpleannounce.bungee.BungeeAnnounce;
import me.refrac.simpleannounce.bungee.utils.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceTask implements Runnable {

    private final Configuration config;

    public AnnounceTask(Configuration config) {
        this.config = config;
    }

    @Override
    public void run() {
        Set<String> broadcastList = (Set<String>) config.getSection("Announcements").getKeys();
        String broadcastId = getRandom(broadcastList);
        Configuration broadcast = config.getSection("Announcements." + broadcastId);
        for (String message : broadcast.getStringList("LINES")) {
            ProxyServer.getInstance().getPlayers().forEach((player -> player.sendMessage(Utils.formatComponent(message.replace("{arrow}", "»")))));
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