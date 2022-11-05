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
package me.refracdevelopment.simpleannounce.spigot.utilities.files;

import org.bukkit.configuration.ConfigurationSection;

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
    public static boolean FORMAT_SOUND_ENABLED;
    public static String FORMAT_SOUND_NAME;
    public static int FORMAT_SOUND_VOLUME;
    public static int FORMAT_SOUND_PITCH;
    public static List<String> FORMAT_LINES;

    // Announcements
    public static ConfigurationSection ANNOUNCEMENTS;

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
        FORMAT_SOUND_ENABLED = Files.getConfig().getBoolean("format.sound.enabled");
        FORMAT_SOUND_NAME = Files.getConfig().getString("format.sound.name");
        FORMAT_SOUND_VOLUME = Files.getConfig().getInt("format.sound.volume");
        FORMAT_SOUND_PITCH = Files.getConfig().getInt("format.sound.pitch");
        FORMAT_LINES = Files.getConfig().getStringList("format.lines");

        // Announcements
        ANNOUNCEMENTS = Files.getConfig().getConfigurationSection("announcements");
    }
}