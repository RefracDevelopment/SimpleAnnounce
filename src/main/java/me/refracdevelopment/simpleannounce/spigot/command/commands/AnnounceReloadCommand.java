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
package me.refracdevelopment.simpleannounce.spigot.command.commands;

import me.refracdevelopment.simpleannounce.shared.Permissions;
import me.refracdevelopment.simpleannounce.spigot.tasks.AnnounceTask;
import me.refracdevelopment.simpleannounce.spigot.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Files;
import me.refracdevelopment.simpleannounce.spigot.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.spigot.command.CommandFramework;
import me.refracdevelopment.simpleannounce.spigot.command.CommandInfo;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "announcereload", description = "Reloads the Simple Announce plugin", requiresPlayer = false)
public class AnnounceReloadCommand extends CommandFramework {

    private SimpleAnnounce plugin;

    public AnnounceReloadCommand(SimpleAnnounce plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!Config.RELOAD_ENABLED) return;

        if (!sender.hasPermission(Permissions.ANNOUNCE_ADMIN)) {
            Color.sendMessage(sender, Config.RELOAD, true, true);
            return;
        }

        Files.reloadFiles(plugin);
        plugin.getServer().getScheduler().cancelTasks(plugin);
        plugin.getServer().getScheduler().runTaskTimer(plugin, new AnnounceTask(plugin), Config.INTERVAL*20L, Config.INTERVAL*20L);
        Color.sendMessage(sender, Config.RELOAD, true, true);
    }
}