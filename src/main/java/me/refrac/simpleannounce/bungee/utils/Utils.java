/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.utils;

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

    public static final String getName = "SimpleAnnounce";
    public static final String getVersion = "1.1";
    public static final String getDeveloper = "Refrac";

}
