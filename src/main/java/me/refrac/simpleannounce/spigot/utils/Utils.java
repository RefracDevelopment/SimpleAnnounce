/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static final String getName = "SimpleAnnounce";
    public static final String getVersion = "1.3-beta.1";
    public static final String getDeveloper = "Refrac";

}