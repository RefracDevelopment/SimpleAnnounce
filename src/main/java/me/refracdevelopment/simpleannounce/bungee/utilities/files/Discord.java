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
package me.refracdevelopment.simpleannounce.bungee.utilities.files;

public class Discord {
    public static boolean DISCORD_ENABLED;
    public static String DISCORD_WEBHOOK;
    public static boolean DISCORD_EMBED;
    public static String DISCORD_TITLE;
    public static String DISCORD_FOOTER;

    public static void loadDiscord() {
        DISCORD_ENABLED = Files.getDiscord().getBoolean("Discord.ENABLED");
        DISCORD_WEBHOOK = Files.getDiscord().getString("Discord.WEBHOOK");
        DISCORD_EMBED = Files.getDiscord().getBoolean("Discord.EMBED-MESSAGE");
        DISCORD_TITLE = Files.getDiscord().getString("Discord.TITLE");
        DISCORD_FOOTER = Files.getDiscord().getString("Discord.FOOTER");
    }
}