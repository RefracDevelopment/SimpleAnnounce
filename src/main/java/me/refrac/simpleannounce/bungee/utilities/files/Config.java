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
package me.refrac.simpleannounce.bungee.utilities.files;

import me.refrac.simpleannounce.spigot.utilities.files.Files;

import java.util.List;

public class Config {
    // General
    public static String PREFIX;
    public static int INTERVAL;

    // Format
    public static boolean FORMAT_ENABLED;
    public static List<String> FORMAT_LINES;

    // Commands
    public static boolean ANNOUNCE_ENABLED;
    public static String ANNOUNCE_COMAND;
    public static String ANNOUNCE_PERMISSION;
    public static String ANNOUNCE_ALIAS;
    public static boolean RELOAD_ENABLED;
    public static String RELOAD_COMAND;
    public static String RELOAD_PERMISSION;
    public static String RELOAD_ALIAS;

    public static void loadConfig() {
        // General
        PREFIX = Files.getConfig().getString("Prefix");
        INTERVAL = Files.getConfig().getInt("Interval");

        // Format
        FORMAT_ENABLED = Files.getConfig().getBoolean("Format.ENABLED");
        FORMAT_LINES = Files.getConfig().getStringList("Format.LINES");

        // Commands
        ANNOUNCE_ENABLED = Files.getConfig().getBoolean("Commands.ANNOUNCE.ENABLED");
        ANNOUNCE_COMAND = Files.getConfig().getString("Commands.ANNOUNCE.COMMAND");
        ANNOUNCE_PERMISSION = Files.getConfig().getString("Commands.ANNOUNCE.PERMISSION");
        ANNOUNCE_ALIAS = Files.getConfig().getString("Commands.ANNOUNCE.ALIAS");
        RELOAD_ENABLED = Files.getConfig().getBoolean("Commands.RELOAD.ENABLED");
        RELOAD_COMAND = Files.getConfig().getString("Commands.RELOAD.COMMAND");
        RELOAD_PERMISSION = Files.getConfig().getString("Commands.RELOAD.PERMISSION");
        RELOAD_ALIAS = Files.getConfig().getString("Commands.RELOAD.ALIAS");
    }
}
