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

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.command.*;
import me.refrac.simpleannounce.spigot.utilities.*;
import me.refrac.simpleannounce.spigot.utilities.files.Config;
import me.refrac.simpleannounce.spigot.utilities.files.Discord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.awt.*;

@CommandInfo(name = "announce", description = "Allows you to announce your messages manually", requiresPlayer = true)
public class AnnounceCommand extends CommandFramework {

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            if (!player.hasPermission(Config.ANNOUNCE_PERMISSION)) {
                player.sendMessage(me.refrac.simpleannounce.spigot.utilities.chat.Color.translate("&cYou don't have permission to execute this command."));
                return;
            }

            me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(player, "&b&lSimpleAnnounce &7by &bRefrac", true);
            me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(player, "", false);
            me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(player, "&b/announce <message> &7- Allows you to announce your messages manually.", true);
            me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(player, "&b/announcereload &7- Reloads the Simple Announce plugin", true);
        }

        if (args.length >= 1) {
            if (!player.hasPermission(Config.ANNOUNCE_PERMISSION)) {
                player.sendMessage(me.refrac.simpleannounce.spigot.utilities.chat.Color.translate("&cYou don't have permission to execute this command."));
                return;
            }

            if (Config.FORMAT_ENABLED) {
                for (String format : Config.FORMAT_LINES) {
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        Bukkit.getOnlinePlayers().forEach(p -> me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(p,
                                PlaceholderAPI.setPlaceholders(p, format.replace("{message}", stringArrayToString(args))), true, true));
                    } else {
                        Bukkit.getOnlinePlayers().forEach(p -> me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(p,
                                format.replace("{message}", stringArrayToString(args)), true, true));
                    }
                }

                if (Config.FORMAT_SOUND_ENABLED && Config.FORMAT_SOUND_NAME != null) {
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        try {
                            p.playSound(p.getLocation(), Sound.valueOf(Config.FORMAT_SOUND_NAME),
                                    (float) Config.FORMAT_SOUND_VOLUME, (float) Config.FORMAT_SOUND_PITCH);
                        } catch (Exception e) {
                            if (p.hasPermission("simpleannounce.admin")) {
                                me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(p, "&cSomething went wrong with the sound name. Check console for more information.", true);
                            }
                            Logger.NONE.out(me.refrac.simpleannounce.spigot.utilities.chat.Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                            Logger.ERROR.out("SimpleAnnounce - Sound Error");
                            Logger.NONE.out("");
                            Logger.ERROR.out("The sound name '" + Config.FORMAT_SOUND_NAME + "' is invalid!");
                            Logger.ERROR.out("Please make sure your sound name is correct.");
                            Logger.NONE.out("");
                            Logger.NONE.out(me.refrac.simpleannounce.spigot.utilities.chat.Color.translate("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                        }
                    });
                }
            } else {
                String format = Config.PREFIX + stringArrayToString(args);

                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    Bukkit.getOnlinePlayers().forEach(p -> me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(p, PlaceholderAPI.setPlaceholders(player, format), true, true));
                } else {
                    Bukkit.getOnlinePlayers().forEach(p -> me.refrac.simpleannounce.spigot.utilities.chat.Color.sendMessage(p, format, true, true));
                }
            }

            if (Discord.DISCORD_EMBED) {
                SimpleAnnounce.getInstance().getDiscordImpl().sendEmbed(stringArrayToString(args).replace("{arrow}", "»"), Color.CYAN);
            } else
                SimpleAnnounce.getInstance().getDiscordImpl().sendMessage(stringArrayToString(args).replace("{arrow}", "»"));
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