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
package me.refrac.simpleannounce.bungee.tasks;

import me.refrac.simpleannounce.bungee.*;
import me.refrac.simpleannounce.bungee.utilities.*;
import me.refrac.simpleannounce.bungee.utilities.chat.Color;
import me.refrac.simpleannounce.bungee.utilities.files.Config;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AnnounceTask implements Runnable {

    public void run() {
        Set<String> broadcastList = (Set<String>) Config.ANNOUNCEMENTS.getKeys();

        if (broadcastList.isEmpty()) {
            Logger.WARNING.out("[SimpleAnnounce] There are no announcements :(");
            ProxyServer.getInstance().getScheduler().cancel(BungeeAnnounce.getInstance());
            return;
        }

        String broadcastId = getRandom(broadcastList);
        Configuration broadcast = Config.ANNOUNCEMENTS.getSection(broadcastId);

        for (String message : broadcast.getStringList("lines")) {
            ProxyServer.getInstance().getPlayers().forEach(p -> {
                if (!p.hasPermission(broadcast.getString("permission")) && !broadcast.getString("permission").equalsIgnoreCase("none")) return;

                Color.sendMessage(p, message, true, true);
            });
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