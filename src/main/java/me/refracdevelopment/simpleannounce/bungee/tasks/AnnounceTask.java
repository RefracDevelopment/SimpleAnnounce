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
package me.refracdevelopment.simpleannounce.bungee.tasks;

import me.refracdevelopment.simpleannounce.bungee.BungeeAnnounce;
import me.refracdevelopment.simpleannounce.bungee.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Config;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AnnounceTask implements Runnable {

    private final BungeeAnnounce plugin;

    public AnnounceTask(BungeeAnnounce plugin) {
        this.plugin = plugin;
    }

    public void run() {
        Set<String> broadcastList = (Set<String>) Config.ANNOUNCEMENTS.getKeys();

        if (broadcastList.isEmpty()) {
            Color.log("&eThere are no announcements :(");
            plugin.getProxy().getScheduler().cancel(plugin);
            return;
        }

        String broadcastId = getRandom(broadcastList);
        Configuration broadcast = Config.ANNOUNCEMENTS.getSection(broadcastId);

        for (String message : broadcast.getStringList("lines")) {
            for (ProxiedPlayer p : plugin.getProxy().getPlayers()) {
                if (p.hasPermission(broadcast.getString("permission")) && !broadcast.getString("permission").equalsIgnoreCase("none")) {
                    Color.sendMessage(p, message, true, true);
                } else if (broadcast.getString("permission").equalsIgnoreCase("none")) {
                    Color.sendMessage(p, message, true, true);
                }
            }
        }
    }

    private static String getRandom(Set<String> set) {
        int index = ThreadLocalRandom.current().nextInt(set.size());
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}