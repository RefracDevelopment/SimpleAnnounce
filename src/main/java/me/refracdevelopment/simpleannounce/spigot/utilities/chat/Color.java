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
package me.refracdevelopment.simpleannounce.spigot.utilities.chat;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refracdevelopment.simpleannounce.spigot.utilities.VersionCheck;
import me.refracdevelopment.simpleannounce.spigot.utilities.files.Config;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Color {

    private static final Pattern HEX_PATTERN = Pattern.compile("(&#[0-9a-fA-F]{6})");

    public static String translate(Player player, String source) {
        source = Placeholders.setPlaceholders(player, source);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, translate(source));
        } else return translate(source);
    }

    public static String translate(String source) {
        String hexColored = source;

        if (VersionCheck.isOnePointSixteenPlus()) {
            Matcher matcher = HEX_PATTERN.matcher(source);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String hex = matcher.group(1).substring(1);
                matcher.appendReplacement(sb, net.md_5.bungee.api.ChatColor.of(hex) + "");
            }
            matcher.appendTail(sb);

            hexColored = sb.toString();
        }

        return org.bukkit.ChatColor.translateAlternateColorCodes('&', hexColored);
    }

    public static List<String> translate(List<String> source) {
        return source.stream().map(Color::translate).collect(Collectors.toList());
    }

    public static void sendMessage(CommandSender sender, String source, boolean color, boolean placeholders) {
        if (source.equalsIgnoreCase("%empty%") || source.contains("%empty%")) return;
        if (placeholders) source = Placeholders.setPlaceholders(sender, source);

        if (sender instanceof Player) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                source = PlaceholderAPI.setPlaceholders((Player) sender, source);
            }
        }

        if (color) source = translate(source);

        sender.sendMessage(source);
    }

    public static void log(String message) {
        sendMessage(Bukkit.getConsoleSender(), Config.PREFIX + " " + message, true, true);
    }
}