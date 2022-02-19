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
package me.refrac.simpleannounce.spigot.command.commands;

import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.command.*;
import me.refrac.simpleannounce.spigot.tasks.*;
import me.refrac.simpleannounce.spigot.utilities.chat.Color;
import me.refrac.simpleannounce.spigot.utilities.files.Config;
import me.refrac.simpleannounce.spigot.utilities.files.Discord;
import me.refrac.simpleannounce.spigot.utilities.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "announcereload", description = "Reloads the Simple Announce plugin", requiresPlayer = false)
public class AnnounceReloadCommand extends CommandFramework {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission(Config.RELOAD_PERMISSION)) {
            Color.sendMessage(sender, "&cYou don't have permission to execute this command.", true);
            return;
        }

        Files.reloadFiles(SimpleAnnounce.getInstance());
        Config.loadConfig();
        Discord.loadDiscord();
        Bukkit.getServer().getScheduler().cancelTasks(SimpleAnnounce.getInstance());
        Bukkit.getServer().getScheduler().runTaskTimer(SimpleAnnounce.getInstance(), new AnnounceTask(), Config.INTERVAL, Config.INTERVAL*20L);
        Color.sendMessage(sender, "&7Config files reloaded. Changes should be live in-game!", true);
    }
}