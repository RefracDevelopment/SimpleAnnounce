/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.bungeeannounce.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author Zachary Baldwin / Refrac
 */
public class Utils {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static TextComponent formatComponent(String message) {
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String getName = "SimpleAnnounce";
    public static String getVersion = "1.0";
    public static String getDeveloper = "Refrac";

}