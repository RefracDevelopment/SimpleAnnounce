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
package me.refracdevelopment.simpleannounce.spigot.tasks;

import me.refracdevelopment.simpleannounce.shared.Permissions;
import me.refracdevelopment.simpleannounce.spigot.utilities.Logger;
import me.refracdevelopment.simpleannounce.spigot.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import me.refracdevelopment.simpleannounce.spigot.SimpleAnnounce;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnnounceTask implements Runnable {

    private final SimpleAnnounce plugin;

    public AnnounceTask(SimpleAnnounce plugin) {
        this.plugin = plugin;
    }

    public void run() {
        Set<String> broadcastList = Config.ANNOUNCEMENTS.getKeys(false);

        if (broadcastList.isEmpty()) {
            Logger.WARNING.out("[SimpleAnnounce] There are no announcements :(");
            Bukkit.getServer().getScheduler().cancelTasks(plugin);
            return;
        }

        String broadcastId = getRandom(broadcastList);
        ConfigurationSection broadcast = Config.ANNOUNCEMENTS.getConfigurationSection(broadcastId);

        for (String message : broadcast.getStringList("lines")) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                if (p.hasPermission(Objects.requireNonNull(broadcast.getString("permission"))) && !Objects.requireNonNull(broadcast.getString("permission")).equalsIgnoreCase("none")) {
                    Color.sendMessage(p, message, true, true);
                }
            }
        }

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            try {
                if (p.hasPermission(Objects.requireNonNull(broadcast.getString("permission"))) && !Objects.requireNonNull(broadcast.getString("permission")).equalsIgnoreCase("none")) {
                    p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("sound.name")),
                            (float) broadcast.getInt("sound.volume"), (float) broadcast.getInt("sound.pitch"));
                }
            } catch (Exception e) {
                if (p.hasPermission(Permissions.ANNOUNCE_ADMIN)) {
                    Color.sendMessage(p, "&cSomething went wrong with the sound name. Check console for more information.", true, true);
                }
                Logger.NONE.out(Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                Logger.ERROR.out("SimpleAnnounce - Sound Error");
                Logger.NONE.out("");
                Logger.ERROR.out("The sound name '" + broadcast.getString("sound.name") + "' is invalid!");
                Logger.ERROR.out("Please make sure your sound name is correct.");
                Logger.NONE.out("");
                Logger.NONE.out(Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
            }
        }
    }

    private String getRandom(Set<String> set) {
        int index = ThreadLocalRandom.current().nextInt(set.size());
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}