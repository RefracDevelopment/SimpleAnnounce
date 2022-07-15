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
import me.refracdevelopment.simpleannounce.shared.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AnnounceCommand extends Command {

    private final BungeeAnnounce plugin;

    public AnnounceCommand(BungeeAnnounce plugin) {
        super(Config.ANNOUNCE_COMMAND, "", Config.ANNOUNCE_ALIAS);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!Config.ANNOUNCE_ENABLED) return;

        if (args.length == 0) {
            if (!sender.hasPermission(Permissions.ANNOUNCE_USE)) {
                Color.sendMessage(sender, Config.NO_PERMISSION, true, true);
                return;
            }

            if (Config.ANNOUNCE_OUTPUT.equalsIgnoreCase("custom") && Config.ANNOUNCE_MESSAGE != null) {
                for (String s : Config.ANNOUNCE_MESSAGE)
                    Color.sendMessage(sender, s, true, true);
            } else {
                Color.sendMessage(sender, "", false, false);
                Color.sendMessage(sender, "&b&lSimpleAnnounce %arrow_2% Help", true, true);
                Color.sendMessage(sender, "", false, false);
                Color.sendMessage(sender, "&b/announce <message> - Allows you to send announcements.", true, true);
                Color.sendMessage(sender, "&b/announcereload - Reloads the config files.", true, true);
                Color.sendMessage(sender, "", false, false);
            }
        } else if (args.length >= 1) {
            if (!sender.hasPermission(Permissions.ANNOUNCE_USE)) {
                Color.sendMessage(sender, Config.NO_PERMISSION, true, true);
                return;
            }

            if (Config.FORMAT_ENABLED) {
                for (String format : Config.FORMAT_LINES) {
                    for (ProxiedPlayer p : plugin.getProxy().getPlayers())
                        Color.sendMessage(p, format.replace("{message}", stringArrayToString(args)), true, true);
                }
            } else {
                plugin.getProxy().getPlayers().forEach(p -> Color.sendMessage(p, Config.PREFIX + stringArrayToString(args), true, true));
            }

            if (Discord.DISCORD_EMBED) {
                plugin.getDiscordImpl().sendEmbed(stringArrayToString(args).replace("{arrow}", "\u00BB"), java.awt.Color.CYAN);
            } else plugin.getDiscordImpl().sendMessage(stringArrayToString(args).replace("{arrow}", "\u00BB"));
        }
    }

    protected String stringArrayToString(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}