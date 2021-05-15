/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.utils;

import org.bukkit.ChatColor;

/**
 * @author Zachary Baldwin / Refrac
 */
public class Utils {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static final String getName = "SimpleAnnounce";
    public static final String getVersion = "1.0";
    public static final String getDeveloper = "Refrac";

}