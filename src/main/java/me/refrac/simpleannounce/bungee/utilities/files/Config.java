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
package me.refrac.simpleannounce.bungee.utilities.files;

import net.md_5.bungee.config.Configuration;

import java.util.List;

public class Config {
    // General
    public static int INTERVAL;

    // Messages
    public static String PREFIX;
    public static String NO_PERMISSION;
    public static String RELOAD;
    public static String ANNOUNCE_OUTPUT;
    public static List<String> ANNOUNCE_MESSAGE;

    // Format
    public static boolean FORMAT_ENABLED;
    public static List<String> FORMAT_LINES;

    // Announcements
    public static Configuration ANNOUNCEMENTS;

    // Commands
    public static boolean ANNOUNCE_ENABLED;
    public static String ANNOUNCE_COMMAND;
    public static String ANNOUNCE_ALIAS;
    public static boolean RELOAD_ENABLED;
    public static String RELOAD_COMAND;
    public static String RELOAD_ALIAS;

    public static void loadConfig() {
        // General
        INTERVAL = Files.getConfig().getInt("interval");

        // Messages
        PREFIX = Files.getConfig().getString("messages.prefix");
        NO_PERMISSION = Files.getConfig().getString("messages.no-permission");
        RELOAD = Files.getConfig().getString("messages.reload");
        ANNOUNCE_OUTPUT = Files.getConfig().getString("messages.announce-output");
        ANNOUNCE_MESSAGE = Files.getConfig().getStringList("messages.announce-message");

        // Format
        FORMAT_ENABLED = Files.getConfig().getBoolean("format.enabled");
        FORMAT_LINES = Files.getConfig().getStringList("format.lines");

        // Announcements
        ANNOUNCEMENTS = Files.getConfig().getSection("announcements");

        // Commands
        ANNOUNCE_ENABLED = Files.getConfig().getBoolean("commands.announce.enabled");
        ANNOUNCE_COMMAND = Files.getConfig().getString("commands.announce.command");
        ANNOUNCE_ALIAS = Files.getConfig().getString("commands.announce.alias");
        RELOAD_ENABLED = Files.getConfig().getBoolean("commands.reload.enabled");
        RELOAD_COMAND = Files.getConfig().getString("commands.reload.command");
        RELOAD_ALIAS = Files.getConfig().getString("commands.reload.alias");
    }
}
