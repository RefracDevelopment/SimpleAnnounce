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
import me.refracdevelopment.simpleannounce.spigot.command.Command;
import me.refracdevelopment.simpleannounce.spigot.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Files;
import me.refracdevelopment.simpleannounce.spigot.SimpleAnnounce;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AnnounceReloadCommand extends Command {

    private final SimpleAnnounce plugin;

    public AnnounceReloadCommand(SimpleAnnounce plugin) {
        super("announcereload");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!sender.hasPermission(Permissions.ANNOUNCE_ADMIN)) {
            Color.sendMessage(sender, Config.RELOAD, true, true);
            return true;
        }

        Files.reloadFiles();
        Color.sendMessage(sender, Config.RELOAD, true, true);
        return true;
    }

    @Override
    public int compareTo(@NotNull Command o) {
        return 0;
    }
}