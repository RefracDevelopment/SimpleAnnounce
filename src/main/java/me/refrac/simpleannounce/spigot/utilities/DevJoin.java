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
package me.refrac.simpleannounce.spigot.utilities;

import me.refrac.simpleannounce.shared.Settings;
import me.refrac.simpleannounce.spigot.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DevJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.getUniqueId().toString().equalsIgnoreCase(Settings.getDevUUID)) return;

        player.sendMessage("");
        Color.sendMessage(player, "&aWelcome " + Settings.getName + " Developer!", true, true);
        Color.sendMessage(player, "&aThis server is currently running " + Settings.getName + " &bv" + Settings.getVersion + "&a.", true, true);
        Color.sendMessage(player, "&aPlugin name&7: &f" + Settings.getName, true, true);
        player.sendMessage("");
        Color.sendMessage(player, "&aServer version&7: &f" + Bukkit.getVersion(), true, true);
        player.sendMessage("");
    }
}