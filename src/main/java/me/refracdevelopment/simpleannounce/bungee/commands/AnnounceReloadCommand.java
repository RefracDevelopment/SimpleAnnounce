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
package me.refracdevelopment.simpleannounce.bungee.commands;

import me.refracdevelopment.simpleannounce.bungee.BungeeAnnounce;
import me.refracdevelopment.simpleannounce.bungee.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Config;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Discord;
import me.refracdevelopment.simpleannounce.bungee.utilities.files.Files;
import me.refracdevelopment.simpleannounce.shared.Permissions;
import me.refracdevelopment.simpleannounce.bungee.tasks.AnnounceTask;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class AnnounceReloadCommand extends Command {

    private final BungeeAnnounce plugin;

    public AnnounceReloadCommand(BungeeAnnounce plugin) {
        super(Config.RELOAD_COMAND, "", Config.RELOAD_ALIAS);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!Config.RELOAD_ENABLED) return;

        if (!sender.hasPermission(Permissions.ANNOUNCE_ADMIN)) {
            Color.sendMessage(sender, Config.NO_PERMISSION, true, true);
            return;
        }

        Files.loadFiles(plugin);
        Config.loadConfig();
        Discord.loadDiscord();
        plugin.getProxy().getScheduler().cancel(plugin);
        plugin.getProxy().getScheduler().schedule(plugin, new AnnounceTask(plugin), Config.INTERVAL, Config.INTERVAL, TimeUnit.SECONDS);
        Color.sendMessage(sender, Config.RELOAD, true, true);
    }
}