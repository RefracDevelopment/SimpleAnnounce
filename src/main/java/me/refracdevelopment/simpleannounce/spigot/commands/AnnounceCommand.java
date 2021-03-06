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
package me.refracdevelopment.simpleannounce.spigot.commands;

import me.refracdevelopment.simpleannounce.shared.Permissions;
import me.refracdevelopment.simpleannounce.spigot.utilities.Logger;
import me.refracdevelopment.simpleannounce.spigot.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Discord;
import me.refracdevelopment.simpleannounce.spigot.SimpleAnnounce;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AnnounceCommand implements CommandExecutor {

    private final SimpleAnnounce plugin;

    public AnnounceCommand(SimpleAnnounce plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!Config.ANNOUNCE_ENABLED) return true;

        if (!sender.hasPermission(Permissions.ANNOUNCE_USE)) {
            Color.sendMessage(sender, Config.NO_PERMISSION, true, true);
            return true;
        }

        if (args.length >= 1) {
            if (Config.FORMAT_ENABLED) {
                for (String format : Config.FORMAT_LINES)
                    for (Player p : plugin.getServer().getOnlinePlayers()) {
                        Color.sendMessage(p, format.replace("%message%", stringArrayToString(args)), true, true);
                    }

                if (Config.FORMAT_SOUND_ENABLED && Config.FORMAT_SOUND_NAME != null) {
                    for (Player p : plugin.getServer().getOnlinePlayers()) {
                        try {
                            p.playSound(p.getLocation(), Sound.valueOf(Config.FORMAT_SOUND_NAME),
                                    (float) Config.FORMAT_SOUND_VOLUME, (float) Config.FORMAT_SOUND_PITCH);
                        } catch (Exception e) {
                            if (p.hasPermission(Permissions.ANNOUNCE_ADMIN))
                                Color.sendMessage(p, "&cSomething went wrong with the sound name. Check console for more information.", true, true);
                            Logger.NONE.out("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
                            Logger.ERROR.out("SimpleAnnounce - Sound Error");
                            Logger.NONE.out("");
                            Logger.ERROR.out("The sound name '" + Config.FORMAT_SOUND_NAME + "' is invalid!");
                            Logger.ERROR.out("Please make sure your sound name is correct.");
                            Logger.NONE.out("");
                            Logger.NONE.out("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
                        }
                    }
                }
            } else {
                String format = Config.PREFIX + stringArrayToString(args);

                for (Player p : plugin.getServer().getOnlinePlayers()) {
                    Color.sendMessage(p, format, true, true);
                }
            }

            if (Discord.DISCORD_EMBED) {
                plugin.getDiscordImpl().sendEmbed(stringArrayToString(args).replace("%arrow%", "??"), java.awt.Color.CYAN);
            } else plugin.getDiscordImpl().sendMessage(stringArrayToString(args).replace("%arrow%", "??"));
        } else {
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
        }
        return true;
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