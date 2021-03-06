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
package me.refracdevelopment.simpleannounce.bungee.utilities;

import me.refracdevelopment.simpleannounce.bungee.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.shared.Settings;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DevJoin implements Listener {

    @EventHandler
    public void onJoin(ServerConnectedEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if (player.getName().equalsIgnoreCase("Refracxx")) {
            sendDevMessage(player);
        } else if (player.getName().equalsIgnoreCase("RyanMood")) {
            sendDevMessage(player);
        }
    }

    private void sendDevMessage(ProxiedPlayer player) {
        player.sendMessage(new TextComponent(""));
        Color.sendMessage(player, "&aWelcome " + Settings.getName + " Developer!", true, true);
        Color.sendMessage(player, "&aThis server is currently running " + Settings.getName + " &bv" + Settings.getVersion + "&a.", true, true);
        Color.sendMessage(player, "&aPlugin name&7: &f" + Settings.getName, true, true);
        player.sendMessage(new TextComponent(""));
        Color.sendMessage(player, "&aServer version&7: &f" + ProxyServer.getInstance().getVersion(), true, true);
        player.sendMessage(new TextComponent(""));
    }
}