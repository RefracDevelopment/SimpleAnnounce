/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 RefracDevelopment
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
package me.refrac.simpleannounce.spigot.tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.utilities.*;
import me.refrac.simpleannounce.spigot.utilities.chat.Color;
import me.refrac.simpleannounce.spigot.utilities.files.Config;
import me.refrac.simpleannounce.spigot.utilities.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class AnnounceTask implements Runnable {

    @Override
    public void run() {
        Set<String> broadcastList = Files.getConfig().getConfigurationSection("Announcements").getKeys(false);

        if (broadcastList.isEmpty()) {
            Logger.WARNING.out("[SimpleAnnounce] There are no announcements :(");
            Bukkit.getServer().getScheduler().cancelTasks(SimpleAnnounce.getInstance());
            return;
        }

        String broadcastId = getRandom(broadcastList);
        ConfigurationSection broadcast = Files.getConfig().getConfigurationSection("Announcements." + broadcastId);

        for (String message : broadcast.getStringList("LINES")) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> {
                if (!p.hasPermission(broadcast.getString("PERMISSION"))) return;

                if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    Color.sendMessage(p, PlaceholderAPI.setPlaceholders(p, message), true, true);
                } else {
                    Color.sendMessage(p, message, true, true);
                }
            });
        }

        Bukkit.getServer().getOnlinePlayers().forEach(p -> {
            try {
                if (!p.hasPermission(broadcast.getString("PERMISSION"))) return;

                p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("SOUND.NAME")),
                        (float) broadcast.getInt("SOUND.VOLUME"), (float) broadcast.getInt("SOUND.PITCH"));
            } catch (Exception e) {
                if (p.hasPermission("simpleannounce.admin")) {
                    Color.sendMessage(p, "&cSomething went wrong with the sound name. Check console for more information.", true);
                }
                Logger.NONE.out(Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                Logger.ERROR.out("SimpleAnnounce - Sound Error");
                Logger.NONE.out("");
                Logger.ERROR.out("The sound name '" + broadcast.getString("SOUND.NAME") + "' is invalid!");
                Logger.ERROR.out("Please make sure your sound name is correct.");
                Logger.NONE.out("");
                Logger.NONE.out(Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
            }
        });
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